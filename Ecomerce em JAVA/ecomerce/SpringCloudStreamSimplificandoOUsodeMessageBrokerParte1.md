# Spring Cloud Stream: Simplificando o uso de Message Broker — Parte 1

[![João Victor](https://miro.medium.com/fit/c/56/56/1*6YfKOA8mVD625xQ8M5bB6Q@2x.jpeg)](https://medium.com/@jvoliveiran?source=post_page-----71f1731f5f5--------------------------------)

[João Victor](https://medium.com/@jvoliveiran?source=post_page-----71f1731f5f5--------------------------------)

[Apr 9, 2019·9 min read](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5?source=post_page-----71f1731f5f5--------------------------------)







![img](https://miro.medium.com/max/1400/1*3jXUXLFSQ-Yfyl5JEMla6w.png)

Simplificando o uso de message brokers com o Spring Cloud Stream

# **Introdução**

Desacoplamento, escalabilidade, tolerância a falhas, arquiteturas orientadas a eventos, alta disponibilidade, todas essas palavras fazem parte do conjunto de requisitos não funcionais que os sistemas modernos de grande porte buscam atender. Em aplicações modernas a arquitetura é composta, de forma distribuída, por vários elementos que são responsáveis pela orquestração e comunicação entre os microsserviços, sendo os Message Brokers uma peça de extrema importância dentro dessas arquiteturas, uma vez que eles permitem garantir maior escalabilidade para a aplicação e permite a comunicação entre serviços.

Com a popularidade dos Message Brokers surgiram alguns vendors que ganharam maior popularidade pela sua robustez e simplicidade de uso, gerando uma polarização para dois vendors específicos: [*Apache Kafka*](https://kafka.apache.org/) e [*RabbitMQ*](https://www.rabbitmq.com/)*.* O uso desses message brokers a principio pode parecer complexo, apesar da documentação de ambos ser bem completa, mas compreende-los e utiliza-los possui uma curva de aprendizagem significativa.

![img](https://miro.medium.com/max/908/1*hQUWUN1ybSUEietqZpNP_w.png)

Diante da necessidade do uso de message brokers por arquiteturas modernas e a existência de diferentes vendors sendo adotados pela comunidade, a [Pivotal](https://pivotal.io/) criou um novo módulo, o [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream), fazendo o que o [Framework Spring](https://spring.io/) sabe fazer como poucos: facilitar a vida dos desenvolvedores!

# **Objetivo**

O objetivo deste artigo não é realizar comparativos entre o *Kafka* e o *RabbitMQ* para mostrar qual dos dois é mais eficiente ou recomendado em um determinado contexto.

![img](https://miro.medium.com/max/1000/1*lnTAWhl8d7maghOUgwy49A.jpeg)

O intuito desse artigo é mostrar através de exemplos práticos, leia-se: código, que o Spring Cloud Stream é capaz de abstrair, em alto nível, o uso dos Message Broker mais populares, evitando o tão indesejado *vendor lock*.

# **Pré-Requisitos**

Como o foco desse artigo é a demonstração do Spring Cloud Stream através de exemplos, é recomendável realizar o seguinte *setup:*

1. Java
2. Maven
3. Docker
4. IDE (*Recomendo o* [*Spring Tool Suite*](https://spring.io/tools) *— STS)*
5. Postman (*opcional*)

Utilizaremos o Docker para subir uma instância do *RabbitMQ* de forma simples e rápida. Ao longo dos exemplos criaremos diferentes projetos pequenos, através da interface de criação de projetos Spring presente no STS (ou pode utilizar o [Spring Initializr](https://start.spring.io/)). Utilizaremos também o Maven para gestão de dependência e build dos projetos. Em determinado ponto deste artigo realizaremos algumas requisições HTTP utilizando o Postman.

# Criando instância do RabbitMQ

Para todos os exemplos que faremos adiante, utilizaremos o RabbitMQ como nosso Message Broker. Para simplificar o uso do RabbitMQ, utilizaremos uma imagem docker do RabbitMQ, sendo necessário apenas executar o seguinte comando no terminal:

```
docker run -d --hostname local-rabbit --name rabbit-mq -p 15672:15672 -p 5672:5672 rabbitmq:3.6.9-management
```

Docker também não é o foco deste artigo, mas explicando rapidamente alguns elementos desse comando:

- *-d* : inicializa o container em background
- *— hostname :* nome do host onde a instância do RabbitMQ será executado
- *— name :* nome do container docker
- — *p :* binding de porta do container com a porta do host
- *rabbitmq:3.6.9-management :* versão da imagem docker do RabbitMQ utilizada.

Após execução desse comando, a imagem docker será baixada, caso seja a primeira vez que ela esteja sendo utilizada, e posteriormente o container será iniciado. Para verificar se o container está sendo executado, utilize o comando abaixo:

```
docker ps
```

Deverá ser exibido um log semelhante a este no console:

```
CONTAINER ID IMAGE COMMAND CREATED STATUS PORTS NAMES4040853541e8 rabbitmq:3.6.9-management “docker-entrypoint.s…” 6 days ago Up 3 days 4369/tcp, 5671/tcp, 0.0.0.0:5672->5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15672->15672/tcp rabbit-mq
```

Se tudo deu certo até agora, essa imagem docker do RabbitMQ também irá prover uma interface gráfica para utilização do message broker. Através do *browser* é possível acessar esta interface utilizando o seguinte endereço e credenciais:

```
http://localhost:15672username: guest
password: guest
```

Isso nos dará acesso ao seguinte Dashboard:

![img](https://miro.medium.com/max/60/1*XNmewEfxux6WmpmY2qHDSg.png?q=20)

![img](https://miro.medium.com/max/630/1*XNmewEfxux6WmpmY2qHDSg.png)

Dashboard RabbitMQ

E agora, sem mais delongas, vamos aos exemplos

![img](https://miro.medium.com/max/60/1*82t0RVdS0DnOBeSUZy2YrA.jpeg?q=20)

![img](https://miro.medium.com/max/630/1*82t0RVdS0DnOBeSUZy2YrA.jpeg)

# Exemplo 1 — Producer & Consumer Básico

Neste primeiro exemplo partiremos do básico, enviando uma mensagem simples através de um projeto que será o *producer,* e teremos também um *consumer,* que será outro projeto responsável por receber as mensagens enviadas pelo *producer.*

![img](https://miro.medium.com/max/1040/1*f3HZ6bUHJVT9HVguid_f0Q.png)

Fluxo simples producer-consumer

Primeiro, criaremos um projeto chamado *spring-cloud-producer-simple,* que terá como dependências apenas o Spring Cloud Stream e o RabbitMQ. Neste projeto e nos subsequentes, será utilizada a versão 2.1.4 do Spring Boot

![img](https://miro.medium.com/max/56/1*q-nQa6FJK49hwtfV9etl8w.png?q=20)

![img](https://miro.medium.com/max/421/1*q-nQa6FJK49hwtfV9etl8w.png)

O arquivo *pom.xml* gerado deverá conter as seguintes dependências:

<iframe src="https://medium.com/media/3c6c460c88394f2dd270b75f40ef6845" allowfullscreen="" frameborder="0" height="590" width="680" title="spring-cloud-producer-simple" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 590px;"></iframe>

Inicialmente é necessário realizar algumas configurações neste projeto através do arquivo *application.properties,* conforme arquivo abaixo:

<iframe src="https://medium.com/media/c4b8a083f8f36297dab49507b07801a1" allowfullscreen="" frameborder="0" height="389" width="680" title="producer-simple-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 388.993px;"></iframe>

A parte que merece destaque neste arquivo de configuração está na linha 15. Explicando em alto nível, essa configuração define o nome do local(*exchange)* dentro do message broker onde as mensagens enviadas pelo *producer* serão armazenadas para serem posteriormente consumidas.

Agora que a aplicação já consegue se conectar ao *RabbitMQ,* já sabe para onde deverá enviar as mensagens, resta então configurar o envio das mensagens. Para isso, iremos criar a classe *MyProducer.java*

<iframe src="https://medium.com/media/3d2243aad7470d6c891fcbf303ae0290" allowfullscreen="" frameborder="0" height="436" width="680" title="MySimpleProducer.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 435.99px;"></iframe>

O Spring Cloud Stream utiliza *bindings* para realizar o encaminhamento das mensagens. Podemos criar nosso próprio *binding* (faremos isso mais a frente) mas por hora utilizaremos o *binding* Source, fornecido pelo próprio Spring Cloud Stream. A anotação @EnableBinding(Source.class) indica que estamos fazendo o uso deste *binding.* A anotação @InboudChannelAdapter(Source.OUTPUT) cria um canal (*channel)* de saída (*output)* que irá enviar as mensagens. Essa anotação faz com que o método sendDefaultMessage() realize o envio da mensagem de forma repetitiva.

Feito isso, nossa aplicação já é capaz de enviar mensagens ao RabbitMQ, então vamos iniciar a aplicação e ver se o nosso producer está de fato funcionando. O STS ou Eclipse possui atalhos para facilitar o start da aplicação, mas é possível iniciar a aplicação via command line utilizando o comando abaixo:

```
mvn spring-boot:run
```

Observando os logs no console, podemos ver que o nosso método está sendo chamado de forma repetida, como esperado.

![img](https://miro.medium.com/max/60/1*KrfsZKialJiYB_0Hptqvcw.png?q=20)

![img](https://miro.medium.com/max/630/1*KrfsZKialJiYB_0Hptqvcw.png)

Logs da aplicação spring-cloud-producer-simple

Tudo ok, nossa aplicação está enviado a mensagem, mas será que o RabbitMQ está recebendo essas mensagens? Podemos verificar o nosso Dashboard do RabbitMQ através do navegador. Lá poderemos ver que foi criado um exchange com o nome *simple-message.* O nome desse exchange foi dado por nós, quando configuramos o destination do nosso output no arquivo application.properties. Nessa exchange é possível ver uma taxa de transferencia na coluna *"message rate in"* indicando que esse exchange está recebendo mensagens.

![img](https://miro.medium.com/max/60/1*7THPFQmrF88Yoqph1N6g4w.png?q=20)

![img](https://miro.medium.com/max/630/1*7THPFQmrF88Yoqph1N6g4w.png)

Aba "Exchanges" no dashboard do RabbitMQ

Clicando no exchange *simple-message* podemos ver mais detalhes das mensagens que estão chegando ao exchange.

![img](https://miro.medium.com/max/60/1*Wh66RsdwL-QMEnyu7klhAg.png?q=20)

![img](https://miro.medium.com/max/630/1*Wh66RsdwL-QMEnyu7klhAg.png)

Detalhes do exchange simple-message

Nosso primeiro producer está feito! Bastou algumas configurações no arquivo application.properties, uma classe com um método e voilà! Nossa aplicação está enviando mensagens para um Message Broker sem que fosse necessário usar trechos de código específicos do RabbitMQ. Fácil, não é?

![img](https://miro.medium.com/max/52/1*eS88jzDYslUA-I8nKwXwoQ.jpeg?q=20)

![img](https://miro.medium.com/max/188/1*eS88jzDYslUA-I8nKwXwoQ.jpeg)

Para finalizar este primeiro exemplo, vamos agora criar um projeto que irá consumir as mensagens recebidas pela nossa instância do RabbitMQ. Para isso, vamos criar um projeto chamado *spring-cloud-consumer-simple.* Ele terá as mesmas dependências do projeto anterior, apenas o RabbitMQ e o Cloud Stream.

Se criar nosso producer foi fácil, o consumer é ainda mais simples! Primeiro precisamos configurar alguns detalhes do application.properties. Nada muito diferente do anterior, mas tem um detalhe que é importante ser observado.

<iframe src="https://medium.com/media/6636a29f4ce398165cb99f5d7ffad86b" allowfullscreen="" frameborder="0" height="260" width="680" title="consumer-simple-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 260px;"></iframe>

Nesse arquivo configuramos a porta na qual a aplicação *consumer* será executada, definimos as conexões com a nossa instância do RabbitMQ e por fim, mas não menos importante, definimos de onde nossa aplicação irá consumir as mensagens. Na aplicação anterior, configuramos que o *destination* do *output* seria o *"simple-message",* e agora configuramos que o *destination* do *input* será também o *"simple-message".* Tudo isso faz bastante sentindo se pensarmos que a saída *(output)* do nosso *producer* deve ser a entrada *(input)* do nosso *consumer.*

E para terminar, precisamos escreve um pouco de código.

<iframe src="https://medium.com/media/69d83494f8ee7d9fecaafac50c9ad6a8" allowfullscreen="" frameborder="0" height="523" width="680" title="SpringCloudConsumerSimpleApplication.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 522.986px;"></iframe>

Mais uma vez usaremos *bindings* pré-definidos pelo Cloud Stream. Anotamos a classe da aplicação com @EnableBinding(Sink.class) informando que estamos utilizando o *binding* Sink. Para finalizar, precisamos apenas criar um método com a anotação @StreamListener(Sink.INPUT). Isso indica que nosso método estará ouvindo o Message Broker através do *binding* Sink. A configuração que fizemos no arquivo application.properties indica que o nosso binding INPUT deverá buscar as mensagens em *"simple-message",* que é o nosso exchange. E isso é tudo, nosso *consumer* já está pronto ler as mensagens enviadas ao Message Broker. Mais uma vez, não precisamos utilizar nada específico do *RabbitMQ,* estamos utilizando apenas classes do Cloud Stream.

Agora podemos ver o fluxo completo, como a mensagem sai do nosso *producer,* vai para o *RabbitMQ* e então é lida pelo nosso *consumer.* Para começar nossos testes, vamos iniciar a aplicação *producer* e a *consumer.* Com as duas aplicações de pé, olhando os logs no console da aplicação *consumer* podemos ver que as nossas mensagens enviadas pelo *producer* estão sendo recebidas no *consumer*.

![img](https://miro.medium.com/max/60/1*aqzFpt2xGO-QcWz1i06Pug.png?q=20)

![img](https://miro.medium.com/max/630/1*aqzFpt2xGO-QcWz1i06Pug.png)

Logs da aplicação spring-cloud-producer-simple

E para confirmar que as mensagens estão passando pelo RabbitMQ, vamos analisar o Dashboard do Message Broker, na aba de *exchange,* que é onde o *producer* envia as mensagens e onde o *consumer* irá recuperá-las.

![img](https://miro.medium.com/max/60/1*6Jy8_RJU5GNGdwCDosPDKw.png?q=20)

![img](https://miro.medium.com/max/630/1*6Jy8_RJU5GNGdwCDosPDKw.png)

Dashboard do RabbitMQ exibindo a aba "Exchanges"

Observe agora que nosso *exchange "simple-message"* agora possui valores nos campos *"message rate in"* e também em *"message rate out".* Isso obviamente indica que o *exchange* está recebendo e também enviando mensagens. Sabemos que nosso *producer* envia mensagens para o *exchange,* no entanto o nosso *consumer* não acessa diretamente o *exchange.*

No RabbitMQ as mensagens que chegam a um *exchange* são direcionadas para *Queues* através de bindings, para que então sejam consumidas*.* Uma ou mais *queues* podem ser vinculadas para receber as mensagens de um *exchange.* Note que não precisamos configurar nada disso nas nossas aplicações. O Spring Cloud já se responsabilizou por criar o *exchange* para onde o *producer* envia nossas mensagens e também criou uma *queue* que recebe as mensagens do nosso *exchange.*

![img](https://miro.medium.com/max/2000/1*GhXNU-IFb09ngUCuT6COdw.png)

Queue criada pelo Spring Cloud

Na aba *"Queues"* podemos verificar que foi criada uma queue associada ao exchange *"simple-message".* Clicando nesta queue podemos obter mais informações importantes sobre a mesma.

![img](https://miro.medium.com/max/2000/1*v5j6Vxa5GkZzsDUt9Ig5BA.png)

Informações sobre uma Queue

Esta visão do Dashboard nos traz informações importantes sobre a *queue* criada. Nela podemos ver a taxa de mensagens transferidas além dos *consumers* e *bindings* associados com essa *queue.* Note que temos um *consumer,* que é nossa aplicação *spring-cloud-consumer-simple* consumindo a *queue* através de um *channel.* Além disso, temos os *bindings* dessa *queue.* No caso da imagem acima, nossa *queue* possui apenas um *binding,* que está vinculando-a com o *exchange "simple-message".*

E assim terminamos essa versão introdutória ao Spring Cloud Stream. Vimos que é possível utilizar o Spring Cloud Stream para facilitar o uso de um Message Broker sem precisar utilizar libs/código de um vendor específico. Isso proporciona uma independência de *vendors,* facilitando uma posterior mudança ou integração de outros *vendors* com um esforço muito menor.

Por enquanto, é isso!

Dúvidas, sugestões e feedbacks nos comentários.

![img](https://miro.medium.com/max/484/1*jV6AqKbfn2ehmdU_-Rc16w.jpeg)

# Pós-créditos

Mas não para por aí!

Vimos apenas um exemplo (bastante) simples, mas em breve trarei outros exemplos mais interessantes e complexos utilizando o Spring Cloud Stream!

![img](https://miro.medium.com/max/450/1*iMu2MnTuW_qMTn5iIo25lQ.jpeg)

[**Link para a segunda parte aqui**](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-parte-2-e82d02e1371c)Spring Cloud Stream: Simplificando o uso de Message Broker — Parte 1

[![João Victor](https://miro.medium.com/fit/c/56/56/1*6YfKOA8mVD625xQ8M5bB6Q@2x.jpeg)](https://medium.com/@jvoliveiran?source=post_page-----71f1731f5f5--------------------------------)

[João Victor](https://medium.com/@jvoliveiran?source=post_page-----71f1731f5f5--------------------------------)

[Apr 9, 2019·9 min read](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5?source=post_page-----71f1731f5f5--------------------------------)







![img](https://miro.medium.com/max/1400/1*3jXUXLFSQ-Yfyl5JEMla6w.png)

Simplificando o uso de message brokers com o Spring Cloud Stream

# **Introdução**

Desacoplamento, escalabilidade, tolerância a falhas, arquiteturas orientadas a eventos, alta disponibilidade, todas essas palavras fazem parte do conjunto de requisitos não funcionais que os sistemas modernos de grande porte buscam atender. Em aplicações modernas a arquitetura é composta, de forma distribuída, por vários elementos que são responsáveis pela orquestração e comunicação entre os microsserviços, sendo os Message Brokers uma peça de extrema importância dentro dessas arquiteturas, uma vez que eles permitem garantir maior escalabilidade para a aplicação e permite a comunicação entre serviços.

Com a popularidade dos Message Brokers surgiram alguns vendors que ganharam maior popularidade pela sua robustez e simplicidade de uso, gerando uma polarização para dois vendors específicos: [*Apache Kafka*](https://kafka.apache.org/) e [*RabbitMQ*](https://www.rabbitmq.com/)*.* O uso desses message brokers a principio pode parecer complexo, apesar da documentação de ambos ser bem completa, mas compreende-los e utiliza-los possui uma curva de aprendizagem significativa.

![img](https://miro.medium.com/max/908/1*hQUWUN1ybSUEietqZpNP_w.png)

Diante da necessidade do uso de message brokers por arquiteturas modernas e a existência de diferentes vendors sendo adotados pela comunidade, a [Pivotal](https://pivotal.io/) criou um novo módulo, o [Spring Cloud Stream](https://spring.io/projects/spring-cloud-stream), fazendo o que o [Framework Spring](https://spring.io/) sabe fazer como poucos: facilitar a vida dos desenvolvedores!

# **Objetivo**

O objetivo deste artigo não é realizar comparativos entre o *Kafka* e o *RabbitMQ* para mostrar qual dos dois é mais eficiente ou recomendado em um determinado contexto.

![img](https://miro.medium.com/max/1000/1*lnTAWhl8d7maghOUgwy49A.jpeg)

O intuito desse artigo é mostrar através de exemplos práticos, leia-se: código, que o Spring Cloud Stream é capaz de abstrair, em alto nível, o uso dos Message Broker mais populares, evitando o tão indesejado *vendor lock*.

# **Pré-Requisitos**

Como o foco desse artigo é a demonstração do Spring Cloud Stream através de exemplos, é recomendável realizar o seguinte *setup:*

1. Java
2. Maven
3. Docker
4. IDE (*Recomendo o* [*Spring Tool Suite*](https://spring.io/tools) *— STS)*
5. Postman (*opcional*)

Utilizaremos o Docker para subir uma instância do *RabbitMQ* de forma simples e rápida. Ao longo dos exemplos criaremos diferentes projetos pequenos, através da interface de criação de projetos Spring presente no STS (ou pode utilizar o [Spring Initializr](https://start.spring.io/)). Utilizaremos também o Maven para gestão de dependência e build dos projetos. Em determinado ponto deste artigo realizaremos algumas requisições HTTP utilizando o Postman.

# Criando instância do RabbitMQ

Para todos os exemplos que faremos adiante, utilizaremos o RabbitMQ como nosso Message Broker. Para simplificar o uso do RabbitMQ, utilizaremos uma imagem docker do RabbitMQ, sendo necessário apenas executar o seguinte comando no terminal:

```
docker run -d --hostname local-rabbit --name rabbit-mq -p 15672:15672 -p 5672:5672 rabbitmq:3.6.9-management
```

Docker também não é o foco deste artigo, mas explicando rapidamente alguns elementos desse comando:

- *-d* : inicializa o container em background
- *— hostname :* nome do host onde a instância do RabbitMQ será executado
- *— name :* nome do container docker
- — *p :* binding de porta do container com a porta do host
- *rabbitmq:3.6.9-management :* versão da imagem docker do RabbitMQ utilizada.

Após execução desse comando, a imagem docker será baixada, caso seja a primeira vez que ela esteja sendo utilizada, e posteriormente o container será iniciado. Para verificar se o container está sendo executado, utilize o comando abaixo:

```
docker ps
```

Deverá ser exibido um log semelhante a este no console:

```
CONTAINER ID IMAGE COMMAND CREATED STATUS PORTS NAMES4040853541e8 rabbitmq:3.6.9-management “docker-entrypoint.s…” 6 days ago Up 3 days 4369/tcp, 5671/tcp, 0.0.0.0:5672->5672/tcp, 15671/tcp, 25672/tcp, 0.0.0.0:15672->15672/tcp rabbit-mq
```

Se tudo deu certo até agora, essa imagem docker do RabbitMQ também irá prover uma interface gráfica para utilização do message broker. Através do *browser* é possível acessar esta interface utilizando o seguinte endereço e credenciais:

```
http://localhost:15672username: guest
password: guest
```

Isso nos dará acesso ao seguinte Dashboard:

![img](https://miro.medium.com/max/60/1*XNmewEfxux6WmpmY2qHDSg.png?q=20)

![img](https://miro.medium.com/max/630/1*XNmewEfxux6WmpmY2qHDSg.png)

Dashboard RabbitMQ

E agora, sem mais delongas, vamos aos exemplos

![img](https://miro.medium.com/max/60/1*82t0RVdS0DnOBeSUZy2YrA.jpeg?q=20)

![img](https://miro.medium.com/max/630/1*82t0RVdS0DnOBeSUZy2YrA.jpeg)

# Exemplo 1 — Producer & Consumer Básico

Neste primeiro exemplo partiremos do básico, enviando uma mensagem simples através de um projeto que será o *producer,* e teremos também um *consumer,* que será outro projeto responsável por receber as mensagens enviadas pelo *producer.*

![img](https://miro.medium.com/max/1040/1*f3HZ6bUHJVT9HVguid_f0Q.png)

Fluxo simples producer-consumer

Primeiro, criaremos um projeto chamado *spring-cloud-producer-simple,* que terá como dependências apenas o Spring Cloud Stream e o RabbitMQ. Neste projeto e nos subsequentes, será utilizada a versão 2.1.4 do Spring Boot

![img](https://miro.medium.com/max/56/1*q-nQa6FJK49hwtfV9etl8w.png?q=20)

![img](https://miro.medium.com/max/421/1*q-nQa6FJK49hwtfV9etl8w.png)

O arquivo *pom.xml* gerado deverá conter as seguintes dependências:

<iframe src="https://medium.com/media/3c6c460c88394f2dd270b75f40ef6845" allowfullscreen="" frameborder="0" height="590" width="680" title="spring-cloud-producer-simple" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 590px;"></iframe>

Inicialmente é necessário realizar algumas configurações neste projeto através do arquivo *application.properties,* conforme arquivo abaixo:

<iframe src="https://medium.com/media/c4b8a083f8f36297dab49507b07801a1" allowfullscreen="" frameborder="0" height="389" width="680" title="producer-simple-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 388.993px;"></iframe>

A parte que merece destaque neste arquivo de configuração está na linha 15. Explicando em alto nível, essa configuração define o nome do local(*exchange)* dentro do message broker onde as mensagens enviadas pelo *producer* serão armazenadas para serem posteriormente consumidas.

Agora que a aplicação já consegue se conectar ao *RabbitMQ,* já sabe para onde deverá enviar as mensagens, resta então configurar o envio das mensagens. Para isso, iremos criar a classe *MyProducer.java*

<iframe src="https://medium.com/media/3d2243aad7470d6c891fcbf303ae0290" allowfullscreen="" frameborder="0" height="436" width="680" title="MySimpleProducer.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 435.99px;"></iframe>

O Spring Cloud Stream utiliza *bindings* para realizar o encaminhamento das mensagens. Podemos criar nosso próprio *binding* (faremos isso mais a frente) mas por hora utilizaremos o *binding* Source, fornecido pelo próprio Spring Cloud Stream. A anotação @EnableBinding(Source.class) indica que estamos fazendo o uso deste *binding.* A anotação @InboudChannelAdapter(Source.OUTPUT) cria um canal (*channel)* de saída (*output)* que irá enviar as mensagens. Essa anotação faz com que o método sendDefaultMessage() realize o envio da mensagem de forma repetitiva.

Feito isso, nossa aplicação já é capaz de enviar mensagens ao RabbitMQ, então vamos iniciar a aplicação e ver se o nosso producer está de fato funcionando. O STS ou Eclipse possui atalhos para facilitar o start da aplicação, mas é possível iniciar a aplicação via command line utilizando o comando abaixo:

```
mvn spring-boot:run
```

Observando os logs no console, podemos ver que o nosso método está sendo chamado de forma repetida, como esperado.

![img](https://miro.medium.com/max/60/1*KrfsZKialJiYB_0Hptqvcw.png?q=20)

![img](https://miro.medium.com/max/630/1*KrfsZKialJiYB_0Hptqvcw.png)

Logs da aplicação spring-cloud-producer-simple

Tudo ok, nossa aplicação está enviado a mensagem, mas será que o RabbitMQ está recebendo essas mensagens? Podemos verificar o nosso Dashboard do RabbitMQ através do navegador. Lá poderemos ver que foi criado um exchange com o nome *simple-message.* O nome desse exchange foi dado por nós, quando configuramos o destination do nosso output no arquivo application.properties. Nessa exchange é possível ver uma taxa de transferencia na coluna *"message rate in"* indicando que esse exchange está recebendo mensagens.

![img](https://miro.medium.com/max/60/1*7THPFQmrF88Yoqph1N6g4w.png?q=20)

![img](https://miro.medium.com/max/630/1*7THPFQmrF88Yoqph1N6g4w.png)

Aba "Exchanges" no dashboard do RabbitMQ

Clicando no exchange *simple-message* podemos ver mais detalhes das mensagens que estão chegando ao exchange.

![img](https://miro.medium.com/max/60/1*Wh66RsdwL-QMEnyu7klhAg.png?q=20)

![img](https://miro.medium.com/max/630/1*Wh66RsdwL-QMEnyu7klhAg.png)

Detalhes do exchange simple-message

Nosso primeiro producer está feito! Bastou algumas configurações no arquivo application.properties, uma classe com um método e voilà! Nossa aplicação está enviando mensagens para um Message Broker sem que fosse necessário usar trechos de código específicos do RabbitMQ. Fácil, não é?

![img](https://miro.medium.com/max/52/1*eS88jzDYslUA-I8nKwXwoQ.jpeg?q=20)

![img](https://miro.medium.com/max/188/1*eS88jzDYslUA-I8nKwXwoQ.jpeg)

Para finalizar este primeiro exemplo, vamos agora criar um projeto que irá consumir as mensagens recebidas pela nossa instância do RabbitMQ. Para isso, vamos criar um projeto chamado *spring-cloud-consumer-simple.* Ele terá as mesmas dependências do projeto anterior, apenas o RabbitMQ e o Cloud Stream.

Se criar nosso producer foi fácil, o consumer é ainda mais simples! Primeiro precisamos configurar alguns detalhes do application.properties. Nada muito diferente do anterior, mas tem um detalhe que é importante ser observado.

<iframe src="https://medium.com/media/6636a29f4ce398165cb99f5d7ffad86b" allowfullscreen="" frameborder="0" height="260" width="680" title="consumer-simple-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 260px;"></iframe>

Nesse arquivo configuramos a porta na qual a aplicação *consumer* será executada, definimos as conexões com a nossa instância do RabbitMQ e por fim, mas não menos importante, definimos de onde nossa aplicação irá consumir as mensagens. Na aplicação anterior, configuramos que o *destination* do *output* seria o *"simple-message",* e agora configuramos que o *destination* do *input* será também o *"simple-message".* Tudo isso faz bastante sentindo se pensarmos que a saída *(output)* do nosso *producer* deve ser a entrada *(input)* do nosso *consumer.*

E para terminar, precisamos escreve um pouco de código.

<iframe src="https://medium.com/media/69d83494f8ee7d9fecaafac50c9ad6a8" allowfullscreen="" frameborder="0" height="523" width="680" title="SpringCloudConsumerSimpleApplication.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 522.986px;"></iframe>

Mais uma vez usaremos *bindings* pré-definidos pelo Cloud Stream. Anotamos a classe da aplicação com @EnableBinding(Sink.class) informando que estamos utilizando o *binding* Sink. Para finalizar, precisamos apenas criar um método com a anotação @StreamListener(Sink.INPUT). Isso indica que nosso método estará ouvindo o Message Broker através do *binding* Sink. A configuração que fizemos no arquivo application.properties indica que o nosso binding INPUT deverá buscar as mensagens em *"simple-message",* que é o nosso exchange. E isso é tudo, nosso *consumer* já está pronto ler as mensagens enviadas ao Message Broker. Mais uma vez, não precisamos utilizar nada específico do *RabbitMQ,* estamos utilizando apenas classes do Cloud Stream.

Agora podemos ver o fluxo completo, como a mensagem sai do nosso *producer,* vai para o *RabbitMQ* e então é lida pelo nosso *consumer.* Para começar nossos testes, vamos iniciar a aplicação *producer* e a *consumer.* Com as duas aplicações de pé, olhando os logs no console da aplicação *consumer* podemos ver que as nossas mensagens enviadas pelo *producer* estão sendo recebidas no *consumer*.

![img](https://miro.medium.com/max/60/1*aqzFpt2xGO-QcWz1i06Pug.png?q=20)

![img](https://miro.medium.com/max/630/1*aqzFpt2xGO-QcWz1i06Pug.png)

Logs da aplicação spring-cloud-producer-simple

E para confirmar que as mensagens estão passando pelo RabbitMQ, vamos analisar o Dashboard do Message Broker, na aba de *exchange,* que é onde o *producer* envia as mensagens e onde o *consumer* irá recuperá-las.

![img](https://miro.medium.com/max/60/1*6Jy8_RJU5GNGdwCDosPDKw.png?q=20)

![img](https://miro.medium.com/max/630/1*6Jy8_RJU5GNGdwCDosPDKw.png)

Dashboard do RabbitMQ exibindo a aba "Exchanges"

Observe agora que nosso *exchange "simple-message"* agora possui valores nos campos *"message rate in"* e também em *"message rate out".* Isso obviamente indica que o *exchange* está recebendo e também enviando mensagens. Sabemos que nosso *producer* envia mensagens para o *exchange,* no entanto o nosso *consumer* não acessa diretamente o *exchange.*

No RabbitMQ as mensagens que chegam a um *exchange* são direcionadas para *Queues* através de bindings, para que então sejam consumidas*.* Uma ou mais *queues* podem ser vinculadas para receber as mensagens de um *exchange.* Note que não precisamos configurar nada disso nas nossas aplicações. O Spring Cloud já se responsabilizou por criar o *exchange* para onde o *producer* envia nossas mensagens e também criou uma *queue* que recebe as mensagens do nosso *exchange.*

![img](https://miro.medium.com/max/2000/1*GhXNU-IFb09ngUCuT6COdw.png)

Queue criada pelo Spring Cloud

Na aba *"Queues"* podemos verificar que foi criada uma queue associada ao exchange *"simple-message".* Clicando nesta queue podemos obter mais informações importantes sobre a mesma.

![img](https://miro.medium.com/max/2000/1*v5j6Vxa5GkZzsDUt9Ig5BA.png)

Informações sobre uma Queue

Esta visão do Dashboard nos traz informações importantes sobre a *queue* criada. Nela podemos ver a taxa de mensagens transferidas além dos *consumers* e *bindings* associados com essa *queue.* Note que temos um *consumer,* que é nossa aplicação *spring-cloud-consumer-simple* consumindo a *queue* através de um *channel.* Além disso, temos os *bindings* dessa *queue.* No caso da imagem acima, nossa *queue* possui apenas um *binding,* que está vinculando-a com o *exchange "simple-message".*

E assim terminamos essa versão introdutória ao Spring Cloud Stream. Vimos que é possível utilizar o Spring Cloud Stream para facilitar o uso de um Message Broker sem precisar utilizar libs/código de um vendor específico. Isso proporciona uma independência de *vendors,* facilitando uma posterior mudança ou integração de outros *vendors* com um esforço muito menor.

Por enquanto, é isso!

Dúvidas, sugestões e feedbacks nos comentários.

![img](https://miro.medium.com/max/484/1*jV6AqKbfn2ehmdU_-Rc16w.jpeg)

# Pós-créditos

Mas não para por aí!

Vimos apenas um exemplo (bastante) simples, mas em breve trarei outros exemplos mais interessantes e complexos utilizando o Spring Cloud Stream!

![img](https://miro.medium.com/max/450/1*iMu2MnTuW_qMTn5iIo25lQ.jpeg)

[**Link para a segunda parte aqui**](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-parte-2-e82d02e1371c)