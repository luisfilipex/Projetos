# Message-Driven Microservices com Spring Cloud Stream

Há algum tempo desenvolvemos nossas aplicações na arquitetura de micro-serviços, mas muitas vezes não paramos para pensar na complexidade que a comunicação entre nossos serviços geram. 

Nos próximos parágrafos, quero demonstrar como o **Spring Cloud Stream** vem para abstrair todo o *boilerplate* necessário para serviços orientados a mensageria, como consumidores, produtores, grupos de consumo e serialização.

## Como fazemos hoje?

No [ChatClub](http://chatclub.me/), um dos produtos da Wavy, temos toda a nossa arquitetura basicamente orientada a mensagens (*pipes and filters*), e utilizamos diversos modelos e *bindings* para fazer a cola entre nossas aplicações: Apache Artemis, Apache Kafka, RabbitMQ, IBM MQ, entre outros. 

Todas essas ferramentas possuem ótimas APIs para o desenvolvimento de nossos serviços, e o **Spring Framework**, sem dúvida, também dá um ótimo suporte para todas elas. 

Mesmo assim, você acaba escrevendo código demais para manter esses *pipes,* e nem sempre todas essas ferramentas dispõem dos mesmos recursos de forma clara – como *consumer groups* ou *partitioning*.

![I1.png](https://i2.wp.com/movile.blog/wp-content/uploads/2018/09/I1.png?resize=827%2C141&ssl=1)

##### Diagrama do padrão de integração Pipes and Filters (https://www.enterpriseintegrationpatterns.com/patterns/messaging/PipesAndFilters.html)

## Porque Spring Cloud Stream?

O Spring Cloud Stream, é um *framework* que vem para facilitar a criação de **micro-serviços orientados a mensagens**. Ele basicamente abstrai todos os conceitos necessários para criar aplicações nessa arquitetura. 

Com o simples ato de adicionar uma nova dependência no projeto, você consegue facilmente usar o RabbitMQ ou o Apache Kafka como a cola entre suas aplicações. 

Outros *binders* já estão para serem suportados como JMS, Google Pub/Sub e Amazon Kinesis. 

Tudo isso com a **simplicidade** do *convention over configuration* do Spring Boot e agilidade no *onboarding* de novos desenvolvedores no time, pois é mais rápido de assimilar o conceito, sem precisar logo de cara entender as complexidades da ferramenta de mensageria utilizada.

![I2](https://i2.wp.com/movile.blog/wp-content/uploads/2018/09/I2.png?resize=975%2C328&ssl=1)

Com a definição do próprio site do Spring Cloud Stream, seu ecossistema é baseado nos seguintes conceitos:

- ***Destination\*** ***binders\*****:** são os componentes responsáveis por prover a integração com os sistemas de mensageria;
- ***Destination bindings\*****:** ponte entre os sistemas de mensageria e aplicações (criado pelos *destination binders*);
- ***Message\*****:** estrutura de dados usado pelos produtores e consumidores para comunicação entre os *destination binders* e outras aplicações.

## Spring Integration

Antes do Spring Cloud Stream (SCS), é necessário uma pequena introdução ao Spring Integration (SI), *framework* base para todo o conceito do SCS. Você não precisa dominar o SI para utilizar o SCS, mas saber que o mesmo existe, pode facilitar as coisas para você. 

O SI foi todo desenvolvido, tendo um livro base, de conhecimento obrigatório de todo arquiteto, engenheiro, desenvolvedor que trabalha com integrações entre sistemas, o [Enterprise Integration Patterns](https://www.amazon.com/Enterprise-Integration-Patterns-Designing-Deploying/dp/0321200683).

## Spring Cloud Stream na prática

Imagine um cenário no qual você precise desenvolver um novo clube de assinaturas, que aṕos as análises de seu time, será composto por vários micro-serviços e deve usar uma arquitetura orientada a mensagens para sua comunicação. 

Como exemplo para demonstração do *framework*, vamos precisar de uma API HTTP como *backend for frontend*, aprovar o pagamento com o banco e informar o cliente do resultado da aprovação

Todo o código da demonstração pode ser encontrado [clicando aqui](https://github.com/felipe-assoline-movile/spring-cloud-stream-demo). Vamos ver a seguir um exemplo de diagrama de um fluxo de aprovação de um Clube de Assinaturas:
![I3](https://i2.wp.com/movile.blog/wp-content/uploads/2018/09/I3.png?resize=248%2C313&ssl=1)

##### Diagrama de um exemplo do fluxo de aprovação de um Clube de Assinaturas

No diagrama acima é possível observar 3 micro-serviços, o SubscriptionApi que recebe o pedido de um novo cliente do clube de assinaturas, o PaymentService que deve aprovar o pagamento e avisar ao serviço, e o EmailService para que outras ações com base no resultado desse pagamento sejam tomadas, cada um desses serviços será um Jar de uma aplicação Spring Boot.

Conceitualmente você pode implementar por padrão 3 tipos de aplicações no Spring Cloud Stream, que são representadas por interfaces dentro do *framework*.

**Source:** São nossas aplicações que iniciam um fluxo de processamento, podem ser um HTTP, GRPC, um consumidor de uma fila que vem de um sistema legado ou de uma integração com seu cliente, até mesmo de um arquivo em um FTP; esse tipo de aplicação possui apenas 1 *outbound*. Na nossa demo será representado pela SubscriptionApi:

```
public interface Source {


  String OUTPUT = "output";


  @Output(Source.OUTPUT)
  MessageChannel output();


}
```

**Processor:** São as aplicações responsáveis por processar as mensagens colocadas no stream pelas aplicações do tipo Source, aplicações Processor sempre possuem 1 inbounde 1 outbound no stream. Na nossa demo será representado pelo PaymentService:

```
public interface Processor extends Source, Sink {


}
```

**Sink:** São as aplicações responsáveis por encerrar um processamento, ou seja, persistir em um repositório, salvar um arquivo em um FTP, enviar uma mensagem notificando o cliente, suas funções são muitas; aplicações Sink sempre possuem apenas 1 *inbound*. Será representado na nossa demo com o serviço EmailService:

```
public interface Sink {


  String INPUT = "input";


  @Input(Sink.INPUT)
  SubscribableChannel input();


}
```

Esses são os principais tipos de *binding* possíveis em uma aplicação Spring Cloud Stream, *bindings* que funcionam para a maioria dos modelos de uso, caso a nomenclatura não faça sentido ou você precise de especificidades como mais de um *consumer* ou *producer ,*você pode implementar o seu **próprio** ***binding type\***, como no exemplo abaixo:

```
public interface MySink {
  String INPUT1 = "input1";
  String INPUT2 = "input2";


  @Input(Sink.INPUT1)
  SubscribableChannel input1();


  @Input(Sink.INPUT2)
  SubscribableChannel input2();
}
```



O **Spring Cloud Stream** usa essas interfaces e a *string* que está no *value* das *annotations*@Input e o @Output para descobrir quais configurações buscar no seu properties, como no exemplo abaixo:

```
cloud.stream:
 bindings.input:
   destination: payment-approval-topic
   group: payment-service-consumer
 bindings.output:
   destination: payment-notification-topic
   contentType: application/json
```

## Source: Subscription API

Essa aplicação é a **porta de entrada do nosso fluxo de processamento**, uma API Rest que recebe um pedido e envia para o PaymentService aprovar o pagamento.

Toda a mágica acontece com a *annotation* @EnableBinding({Source.class}) e com a injeção de um objeto Source que possui os métodos que nos permitem produzir a mensagem para nosso binder.

```
@EnableBinding({Source.class})
public class SubscriptionRequestsProducer {
   private final Source source;


   public SubscriptionRequestsProducer(Source source) {
       this.source = source;
   }


   public void requestApproval(Map<String, Object> subscriptionRequest) {
       source.output().send(MessageBuilder.withPayload(subscriptionRequest).build());
   }
}
```

## Processor: Payment Service

O Processor do nosso exemplo, responsável por aprovar o pagamento solicitado pelo Source.

Aqui utilizamos outro recurso que o Spring Cloud Stream oferece, **adicionamos um** ***header\*** na mensagem que indica a prioridade da mesma. Esse *header* será utilizado pelo nosso EmailService:

```
@EnableBinding({Processor.class})
public class PaymentProcessor {
   private final Logger LOGGER = LoggerFactory.getLogger(PaymentProcessor.class);


   @SendTo(Processor.OUTPUT)
   @StreamListener(target = Processor.INPUT)
   public Message<Map<String, Object>> processPayment(Message<Map<String, Object>> paymentApprovalRequest) {
       Map<String, Object> request = paymentApprovalRequest.getPayload();


      ………..


       return MessageBuilder.withPayload(request)
               .setHeader("notificationPriority", priority)
               .build();
   }


}
```

## Sink: Email Service

Nosso terceiro projeto, o **Sink da solução**, a função desse serviço é enviar um e-mail para o usuário depois do processamento do PaymentService.

```
@EnableBinding({Sink.class})
public class EmailSink {
   private static final Logger LOGGER = LoggerFactory.getLogger(EmailSink.class);


   @StreamListener(target = Sink.INPUT, condition = "headers['notificationPriority']=='normal'")
   public void sentEmail(Message<Map<String, Object>> paymentNotification) {
       String status = String.valueOf(paymentNotification.getPayload().getOrDefault("status", "pending"));
       LOGGER.info("Normal Payment =" + status);
   }


   @StreamListener(target = Sink.INPUT, condition = "headers['notificationPriority']=='urgent'")
   public void sentUrgentEmail(Message<Map<String, Object>> paymentNotification) {
       String status = String.valueOf(paymentNotification.getPayload().getOrDefault("status", "pending"));
       LOGGER.info("Urgent Payment =" + status);
   }
}
```

É possível observar que a partir do *header* adicionado na aplicação anterior, podemos separar nossos *consumers*, separando suas regras de negócio baseado no **conteúdo das mensagens**.

## Consumer groups

Uma das necessidades quando estamos lidando com micro-serviços é **escalabilidade**. 

Para permitirmos a possibilidade de que inúmeras instâncias de sua aplicação consigam consumir do mesmo tópico – sem que haja duplicidade de mensagens –, precisamos usar o conceito de ***consumer groups\***, algo já aceito pela API padrão do Kafka, e muito bem abstraído pelo *framework* quando usamos o RabbitMQ. 

A configuração no *properties* para o Spring Cloud Stream é a mesma, não importando a ferramenta de mensageria utilizada (Kafka ou RabbitMQ).

```
cloud.stream:
 bindings.input:
   destination: payment-notification-topic
   group: email-service-consumer
```

Caso você deseje que as mensagens sejam consumidas por todas as aplicações conectadas nesse *binder*, você precisa utilizar **grupos diferentes para cada aplicação**. 

Uma boa prática é sempre **setar um grupo**, pois sem isso, como dito acima, você terá duplicidade de mensagens quando a necessidade de escala surgir. 

Abaixo estão alguns exemplos de como as filas específicas de cada sistema ficam com e sem configurações de grupo.

##### ![I4.1](https://i2.wp.com/movile.blog/wp-content/uploads/2018/09/I4.1.png?resize=975%2C484&ssl=1) Filas no RabbitMQ para aplicações consumidoras sem configuração de Grupo, ou seja, grupos anônimos.

##### ![I4](https://i1.wp.com/movile.blog/wp-content/uploads/2018/09/I4.png?resize=975%2C517&ssl=1)Filas no RabbitMQ para aplicações consumidoras com configuração de Grupo.

## Serialização – Avro

Quando lidamos com aplicações orientadas a mensagens a velocidade de serialização e **tamanho** dessas mensagens se tornam muito importantes.

Na nossa demo, trabalhamos com JSON para troca de informação, mas o JSON além de ser pesado (por levar consigo os metadados do objeto e possivelmente repeti-los inúmeras vezes), também possui um tempo serialização alto. 

O Spring já traz o Avro como sistema de serialização muito **conectado ao ecossistema do Cloud Stream**.

Como podemos ver no site do projeto, o Apache Avro é definido como um sistema de serialização de dados. Muito parecido com o Google Protocol Buffer, difere em diversos aspectos e basicamente vai te oferecer estruturas de dados ricas em um formato binário, que pode ser rapidamente serializado, com um tamanho muito reduzido.

Abaixo um arquivo avsc (Avro Schema) de nosso SubscriptionRequest:

```
{
   "type": "record",
   "name": "SubscriptionRequest",
   "namespace": "com.felipeassoline.subscription.payment.api",
   "fields": [
       {"name": "cardNumber", "type": ["string", "null"]},
       {"name": "secret", "type": ["string", "null"]},
       {"name": "status", "type": ["string", "null"]}
   ]
}
```

É muito importar acessar o [site do Avro](http://avro.apache.org/docs/1.8.2/) e entender como esse arquivo de schema funciona.

Para configurar sua aplicação para trabalhar com o Avro, será necessário as seguintes configurações:

```


cloud.stream:
 bindings.output:
   destination: payment-approval-topic
   contentType: application/*+avro
 schema:
   avro:
     schema-locations: classpath:avro/schema.avsc
 schema-registry-client:
   endpoint: http://localhost:8080
```



Outro ponto importante é a configuração do **Schema Registry**, esse serviço é responsável por armazenar seu *schema* em um repositório fora do seu fluxo de mensagens. 

Com isso, o Spring Cloud Stream apenas se preocupa em adicionar qual versão do *schema* será utilizado, não sendo necessário carregar o mesmo ao lado da mensagem.

O Spring Cloud Stream dá suporte nativo para 2 Schema Registries, o padrão do Spring, muito simples de ser criado usando uma aplicação Spring Boot como base (você simplesmente precisa de um @EnableSchemaRegistryServer na sua *application class*) ou o da [Confluent](https://docs.confluent.io/current/schema-registry/docs/index.html), que também é suportado pela API nativa do Kafka e te permite ter todos os recursos do Kafka, como KSQL e etc, com mensagens muito menores do que se estivesse utilizando JSON.

Abaixo um exemplo de como criar um Schema Registry Server com o Spring Boot:

```
@SpringBootApplication
@EnableSchemaRegistryServer
public class SchemaRegistryServerApplication {


   public static void main(String[] args) {
       SpringApplication.run(SchemaRegistryServerApplication.class, args);
   }
}
```

## Conclusão

Espero que com esse artigo você tenha um *overview* rápido do **Spring Cloud Stream**, para que consiga criar seus serviços orientados a mensagens com o mínimo de código, aproveitando ao máximo o *convention over configuration* proporcionado pelo *framework* e focando seus esforços apenas no código que traga valor para seu negócio.