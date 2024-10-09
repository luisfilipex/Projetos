# Spring Cloud Stream: Simplificando o uso de Message Broker — Parte 2

[![João Victor](https://miro.medium.com/fit/c/25/25/1*6YfKOA8mVD625xQ8M5bB6Q@2x.jpeg)](https://medium.com/@jvoliveiran?source=post_page-----e82d02e1371c--------------------------------)

[João Victor](https://medium.com/@jvoliveiran?source=post_page-----e82d02e1371c--------------------------------)

[Apr 10, 2019·11 min read](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-parte-2-e82d02e1371c?source=post_page-----e82d02e1371c--------------------------------)







![img](https://miro.medium.com/max/630/1*3jXUXLFSQ-Yfyl5JEMla6w.png)

Simplificando o uso de message brokers com o Spring Cloud Stream

# Recapitulando

Na [parte 1](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5) deste série foi explicado sobre a situação dos Message Brokers em arquiteturas modernas e qual o problema que o Spring Cloud Stream se propõe a resolver, considerando o atual cenário de message brokers existentes no mercado. Além disso, foi criado um exemplo bem simples de *producer* e *consumer* para mostrar o quão simples pode ser o uso de um message broker utilizando o Spring Cloud Stream.

E agora, o que faremos aqui?

Agora já vamos direto para mais exemplos práticos!

Vamos nessa?!

![img](https://miro.medium.com/max/900/1*X7FeuvoMnk6wrxzK9OI5Rw.jpeg)

Here we go!

# Exemplo 1 — Custom Bindings

No primeiro exemplo da [história anterior](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5), vimos que o Spring Cloud Stream já nos fornece alguns bindings, como o Source e o Sink para utilizarmos em nossos projetos. Esses bindings funcionam de forma simples*,* no entanto eles possuem suas limitações. E se desejarmos criar nossos próprios *bindings?* Esse será o objetivo desse exemplo.

Vamos criar um projeto chamado *spring-cloud-producer-custom.* Nesse projeto adicionaremos como dependências o RabbitMQ, o Cloud Stream, Web e também o Actuator.

![img](https://miro.medium.com/max/630/1*1cWoMglswhP-aHyKHoZ3vQ.png)

Primeiro vamos criar o *binding.* Ele será apenas uma *interface* no nosso projeto. Veja no código abaixo:

<iframe src="https://medium.com/media/f17d97bec0e2b6080d013c82f7b4780b" allowfullscreen="" frameborder="0" height="348" width="680" title="MyCustomSource.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 347.986px;"></iframe>

Nosso *binding* possui duas assinaturas de métodos, onde ambas retornam um MessageChannel e estão anotadas com Output. O argumento da anotação será o nome do nosso *binding*, como temos o OUTPUT no Source e o INPUT no Sink.

Agora vamos criar uma nova classe para fazer a publicação das nossas mensagens. Dessa vez faremos um pouco diferentes, iremos publicar uma mensagem a cada 2 segundos utilizando o *binding "default-channel"* e publicaremos uma outra mensagem a cada 3 segundos utilizando o *binding "custom-channel".* O *default channel* irá publicar a mensagem no *exchange simple-message* enquanto o *custom-channel* irá publicar a mensagem no *exchange complete-message.*

![img](https://miro.medium.com/max/27/1*-oftNxalMcFlP45ASIfwjg.jpeg?q=20)

![img](https://miro.medium.com/max/235/1*-oftNxalMcFlP45ASIfwjg.jpeg)

Já criamos nosso custom *binding,* então agora precisamos utilizá-lo para publicar nossas mensagens. Para isso, criaremos umas nova classe chamada *MyCustomProducer.java*

<iframe src="https://medium.com/media/af6bbf0792535c954538acc5228c266b" allowfullscreen="" frameborder="0" height="699" width="680" title="MyCustomProducer.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 698.993px;"></iframe>

Classe responsável por envio de mensagens ao Message Broker utilizando um *custom binding.*

Inicialmente temos a anotação @EnableBinding(MyCustomSource.class) indicando que nossa classe de publicação de mensagens estará utilizando um *custom binding,* que no caso é a interface *MyCustomSource* que criamos previamente.

Nessa classe de publicação foram criados dois métodos, um para cada *exchange* que será utilizado para receber as mensagens, conforme foi explicado previamente. Nesse exemplo, a única relação dos dois métodos criados nessa classe com as assinaturas dos métodos presentes na *interface MyCustomSource* está no channel, note que eles são os mesmo definidos na anotação *Output* do *MyCustomSource.* Ambos os métodos recebem a anotação @InboundChannelAdapter fazendo com que esses métodos enviem mensagens para um *channel* específico de tempos em tempos, onde dessa vez configuramos esse tempo no atributo *poller.* Além disso, ambos os métodos também são anotados como *Beans,* uma vez que o Cloud Stream utiliza os *Beans* para realizar o envio das mensagens.

O método *sendMessageDefault* envia uma *string* simples, sendo esse o tipo de retorno do método, nada diferente do exemplo no [artigo anterior](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5). No caso do segundo método, o *sendCustomMessage* o nosso objeto de retorno é um *MessageSource* que encapsula, através de *generics,* um objeto do tipo *Message* sendo esse um objeto criado para gerar uma mensagem com mais propriedades do que uma simples *string,* simulando um DTO ou algum objeto mais complexo em um cenário real. Segue o código do nosso objeto *Message:*

<iframe src="https://medium.com/media/829be368fe8683df92718f0a402ea989" allowfullscreen="" frameborder="0" height="853" width="680" title="Message.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 852.986px;"></iframe>

Objeto message enviado pelo *producer para o Message Broker*

Por fim, falta configurarmos o arquivo application.properties com algumas informações relevantes para que nossa nova aplicação *producer* funcione corretamente.

<iframe src="https://medium.com/media/1f23245fa38afe7ccc7c80474f41613f" allowfullscreen="" frameborder="0" height="436" width="680" title="producer-custom-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 435.99px;"></iframe>

Novamente criamos *destinations* específicos para cada um dos dois *bindings* que criamos nesse projeto. Por estar utilizando o [Spring Actuator](https://docs.spring.io/spring-boot/docs/current/actuator-api/html/#overview), foi necessário realizar algumas configurações específicas nas linhas 5 e 7 desse arquivo para habilitar e também expor alguns endpoints que esse módulo do Spring oferece. O intuito de usar o *Actuator* nesse projeto é verificar que o *Cloud Stream* cria *Beans* tanto para nossa *interface* *MyCustomSource,* quanto para o *default-channel* e para o *custom-channel.* Para ter acesso aos beans basta acessar a seguinte URL:

```
http://localhost:8092/actuator/beans
```

![img](https://miro.medium.com/max/27/1*DN1XKAcox5efveMmq2TWoQ.png?q=20)

![img](https://miro.medium.com/max/630/1*DN1XKAcox5efveMmq2TWoQ.png)

Bean MyCustomSource

![img](https://miro.medium.com/max/27/1*iH4RDWj7pDdIzalVFI1pSQ.png?q=20)

![img](https://miro.medium.com/max/630/1*iH4RDWj7pDdIzalVFI1pSQ.png)

Bean default-channel

![img](https://miro.medium.com/max/27/1*F9YIkUT5OInSNtZj8NL8hw.png?q=20)

![img](https://miro.medium.com/max/630/1*F9YIkUT5OInSNtZj8NL8hw.png)

Bean custom-channel

Agora vamos iniciar a aplicação *spring-cloud-producer-custom* que acabamos de criar e verificar o dashboard do RabbitMQ

![img](https://miro.medium.com/max/27/1*nXdmDpQe-qh0y7f0LDOa0w.png?q=20)

![img](https://miro.medium.com/max/630/1*nXdmDpQe-qh0y7f0LDOa0w.png)

Dashboard RabbitMQ, aba Exchanges

É possível ver que os dois *exchanges* foram criados com os nomes que configuramos como *destination* no arquivo applications.properties. Ambos os *exchanges* estão recebendo mensagens com *rate in* diferentes, o que também é esperado pois criamos dois métodos para publicar mensagens, um a cada 3 segundos e outro a cada 2 segundos.

Agora vamos iniciar duas instancias do projeto s*pring-cloud-consumer-simple*, criado no [artigo anterior](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5). Cada instância irá criar uma *queue* que receberá mensagens de um dos dois *exchanges* que criamos. Para isso, use o terminal para navegar até a raiz do projeto *spring-cloud-consumer-simple.* Na raiz do projeto, execute o seguinte comando:

```
mvn clean package
```

Isso irá gerar um novo executável da aplicação na pasta *target*. Abra uma nova janela do terminal para executarmos duas instâncias do nosso consumer. Em cada uma das duas janelas do terminal execute um dos comandos abaixo:

```
java -jar target/spring-cloud-consumer-simple-0.0.1-SNAPSHOT.jar — server.port=80901 — spring.cloud.stream.bindings.input.destination=simple-messagejava -jar target/spring-cloud-consumer-simple-0.0.1-SNAPSHOT.jar — server.port=80902 — spring.cloud.stream.bindings.input.destination=complete-message
```

Esses comandos iniciarão uma instância da nossa aplicação *consumer* e passará alguns parâmetros de configuração via *command line* que irão sobrescrever os parâmetros que estiverem configurados no application.properties da aplicação. Dessa forma evitamos conflito de portas e também direcionamos cada instância da aplicação para receber mensagens de um dos *exchanges*.

Acessando o dashboard do RabbitMQ, na aba *Queues* podemos ver que foram criadas duas *queues* para receber as mensagens, uma para cada *exchange.*

![img](https://miro.medium.com/max/900/1*2nOYxYCG0Rmp2Tk8EIdn9Q.png)

Analisando o console de cada uma das instâncias da aplicação *spring-cloud-consumer-simple* vemos que as mensagens estão sendo recebidas pelo seu respectivo *consumer.*

![img](https://miro.medium.com/max/900/1*kLj8UhtvNVXcf-Lp_d380Q.png)

Instância da aplicação consumer recebendo mensagens do exchange simple-message

![img](https://miro.medium.com/max/900/1*pApxz5EspBk4fAXKWjYHGA.png)

Instância da aplicação consumer recebendo mensagens do exchange complete-message

E assim finalizamos esse exemplo, utilizando *custom bindings* e múltiplos *consumers,* algo mais próximo de uma aplicação real.

![img](https://miro.medium.com/max/534/1*qOqCWd2LhCdwdGM4V9Dnqw.jpeg)

# Exemplo 2 — REST Producer

Agora vamos pensar em um cenário onde um dos microsserviços é acessado através de um determinado *endpoint,* onde é realizado algum processamento e é necessário enviar uma mensagem para que outro serviço realize uma outra operação, que é parte do processamento. Esse é um cenário bem parecido com o mundo real, e esse é um cenário onde message brokers são utilizados para resolver um problema comum, que é a comunicação entre microsserviços.

Neste segundo exemplo, ou terceiro exemplo se considerarmos o [artigo anterior](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5), iremos criar um novo projeto *producer* que chamaremos de *spring-cloud-producer-rest.* Este projeto terá um *RestController* com um *endpoint* que receberá uma mensagem. Iremos encapsular essa mensagem, que virá como um JSON, e enviá-la para o Message Broker.

Como de costume, começamos com as dependências na criação do novo projeto. Usaremos as dependências do RabbitMQ, Cloud Stream e Web.

![img](https://miro.medium.com/max/630/1*bxHLAqKvGLGIsPgKz3Fwhg.png)

Dependências spring-cloud-producer-rest

Nesse projeto a mensagem enviada para o Message Broker será um Produto, representado pela seguinte classe *Product:*

<iframe src="https://medium.com/media/07834f422a9fc1f9b1f90f2655e05aa5" allowfullscreen="" frameborder="0" height="523" width="680" title="Product.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 522.986px;"></iframe>

Usaremos *custom binding* novamente, da mesma forma que utilizamos no exemplo anterior. Teremos um único *channel* chamado *product-channel* e definido no nosso *binding RestSource:*

<iframe src="https://medium.com/media/4f501bac924276ea81247135a1cc81eb" allowfullscreen="" frameborder="0" height="282" width="680" title="RestSource.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 281.997px;"></iframe>

Mais uma vez nosso *binding* é definido apenas através de uma *interface* onde informamos a assinatura dos métodos que irão representar os *channels.* Nosso *binding* terá um *channel* chamado *product-channel,* como explicado anteriormente.

Agora precisamos de um classe para habilitar o *binding* que acabamos de criar. Para isso criamos uma classe simples, *RestSourceImpl,* e iremos apenas incluir a anotação @EnableBinding(RestSource.class) e nada mais.

<iframe src="https://medium.com/media/f245150b57dffdae6d388100334b99db" allowfullscreen="" frameborder="0" height="216" width="680" title="RestSourceImpl.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 215.99px;"></iframe>

Feito isso, criaremos a classe *RestProducer* que será anotada como *@Component* para que possamos injeta-la em nosso *@RestController* mais a frente. Esta classe será responsável por fazer o envio da mensagem, dado o *payload* que se deseja enviar através de um *binding* específico.

<iframe src="https://medium.com/media/2eec2591c84dcdf08ac2ec76dba9b6e5" allowfullscreen="" frameborder="0" height="436" width="680" title="RestProducer.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 435.99px;"></iframe>

Nesse caso, nosso método de envio recebe um *Product* como *payload,* além de uma instância do nosso *custom binding RestResource.* Usamos o *MessageBuilder* para construir a nossa mensagem encapsulando o *payload* e então usamos o *channel* do *custom binding* para fazer o envio da mensagem, retornando um booleano como *status* de envio da mensagem.

Temos o nosso *custom binding* pronto, bem como o DTO *Product* e a classe que é responsável por enviar a mensagem, no caso o *RestProducer* que acabamos de criar. Agora a última classe que precisaremos criar nesse projeto será o nosso *RestController.*

<iframe src="https://medium.com/media/d2244a58e9ba5fe1355db0972c8996cb" allowfullscreen="" frameborder="0" height="762" width="680" title="ProductRestController.java" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 761.997px;"></iframe>

Nossa última classe criada é a *ProductRestController,* que é anotada como *RestController* e também como *RequestMapping("/product"),* indicando que todos os *endpoints* nessa classe sejam precedidos desse prefixo passado como argumento da anotação.

No nosso controller são injetados dois beans, o primeiro é uma *interface* que representa nosso *binding* e o segundo é o nosso componente de envio de mensagens.

Por último, foi definido um método para envio de mensagens chamado *processProduct* que espera receber um objeto *Product* no corpo da requisição feita ao *endpoint* /product/process utilizando o *method* POST. Um vantagem do módulo web no Spring é que o *parser* do JSON enviado na *request* para o objeto *Product,* que já é feito sem que seja necessário nenhum tratamento específico, desde que o nome dos campos do JSON e do objeto *Product* sejam os mesmos. Neste método utilizamos o nosso componente *RestProducer* para fazer sua função, que é enviar de fato a mensagem, e com o retorno dessa operação nós criamos uma resposta para a *request* utilizando a classe *ResponseEntity* do Spring.

O objeto *Message* passado como *generic* no retorno do método é o mesmo objeto do exemplo anterior. Pode copia-lo e coloca-lo neste projeto.

E ainda falta mais uma coisa que não fizemos até o momento

![img](https://miro.medium.com/max/27/1*_kxkz7ChjDxmJa4PXy9yvg.jpeg?q=20)

![img](https://miro.medium.com/max/561/1*_kxkz7ChjDxmJa4PXy9yvg.jpeg)

Precisamos configurar o application.properties com algumas informações.

<iframe src="https://medium.com/media/9b9113899ea68d40fc2bfe0ad7bdc477" allowfullscreen="" frameborder="0" height="282" width="680" title="producer-rest-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 281.997px;"></iframe>

Configuração do projeto spring-cloud-producer-rest

Agora já podemos testar nossa nova aplicação *producer.* Vamos inicia-la e verificar o que acontece no dashboard do *RabbitMQ.*

![img](https://miro.medium.com/max/27/1*XExR4CZBAzAcixGcfVNbdw.png?q=20)

![img](https://miro.medium.com/max/630/1*XExR4CZBAzAcixGcfVNbdw.png)

Dashboard do RabbitMQ na aba Exchange após iniciar a aplicação spring-cloud-producer-rest

Observe que nosso *exchange product-process* foi criado, conforme configuração realizada no application.properties. Outro detalhe interessante é que mesmo com nosso *producer* de pé, nenhuma taxa de *rate in* ou *rate out* está sendo exibida, uma vez que nosso *producer* não possui mais o comportamento de enviar mensagens periodicamente, já que criamos um componente de envio de mensagem e ele só é executado quando fazemos uma *request* para o *endpoint* que criamos.

No momento não temos nenhuma *queue* criada para o nosso *exchange product-process,* então vamos criar uma *queue* auxiliar através do próprio dashboard, acessando a aba *Queues.*

![img](https://miro.medium.com/max/27/1*Gr8P3sRKEl19ZNo-v5rpDg.png?q=20)

![img](https://miro.medium.com/max/630/1*Gr8P3sRKEl19ZNo-v5rpDg.png)

Criação de nova Queue no dashboard do RabbitMQ

Após criar uma nova *Queue,* ainda no dashboard podemos clicar na nova *queue* para vermos mais detalhes da mesma e acessar a opção de criar um *binding* para essa *queue,* onde essa *binding* será responsável por ler as mensagens no *exchange* e envia-las para a *queue.*

![img](https://miro.medium.com/max/27/1*_4MoszIwp0-7snVVrZByUw.png?q=20)

![img](https://miro.medium.com/max/630/1*_4MoszIwp0-7snVVrZByUw.png)

Adicionando binding para a queue

Agora que temos o *binding* criado na nossa *queue* temporária para coletar as mensagens que chegarem ao *exchange product-process,* vamos utilizar o [*Postman*](https://www.getpostman.com/) para realizar uma requisição HTTP ao *endpoint* que criamos na aplicação *spring-cloud-producer-rest.* A *request* será feita para o seguinte endereço:

```
http://localhost:8093/product/process
```

Para que a requisição funcione corretamente, devemos utilizar o *method* POST e no corpo da requisição enviar um JSON equivalente ao nosso objeto *Product.* Vamos fazer uma requisição no *Postman* considerando essas regras.

![img](https://miro.medium.com/max/27/1*HVVxWnrCYvyk2VDhjIs_bg.png?q=20)

![img](https://miro.medium.com/max/630/1*HVVxWnrCYvyk2VDhjIs_bg.png)

Requisição HTTP via Postman

Enviando essa *request* é esperado receber a seguinte *response:*

![img](https://miro.medium.com/max/900/1*UY6iFyQlGeaWOM1qhDFisw.png)

Response do spring-cloud-producer-rest para a request na imagem anterior

Note que o objeto JSON de resposta nada mais é que o objeto Message convertido para JSON. A mensagem retornada dentro do nosso objeto Message indica que o envio da mensagem para o Message Broker ocorreu com sucesso.

Para termos uma outra confirmação de que o Message Broker recebeu nossa mensagem, podemos verificar a *queue* que criamos manualmente através do dashboard.

![img](https://miro.medium.com/max/630/1*kWWaDq_yIJOY9UKwUomeqw.png)

Mensagem recebida pela queue

E isso conclui o nosso *producer,* agora conseguimos controlar programaticamente quando mensagens devem ser enviadas ao message broker, através de um exemplo mais próximo do mundo real.

Ainda tomando como base exemplos do mundo, imagine que em um período de muitos acessos da nossa aplicação, uma quantidade enorme de mensagens sejam enviadas para o Message Broker. Se tivermos apenas uma instância do consumer, provavelmente nossas mensagens demorarão muito tempo para serem processadas. O ideal seria ter diferentes instâncias do nosso *consumer* (aquele *consumer* que criamos no [artigo anterior](https://medium.com/@jvoliveiran/spring-cloud-stream-simplificando-o-uso-de-message-broker-71f1731f5f5)).

Vamos modificar o nosso application.properties na aplicação *spring-cloud-consumer-simple* para subir múltiplas instâncias do *consumer* e consumir o *exchange product-process.*

<iframe src="https://medium.com/media/8b2caa0f08bcd07d07779fb814d3aac3" allowfullscreen="" frameborder="0" height="260" width="680" title="consumer-simple-v2-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 260px;"></iframe>

Quando atribuímos o valor 0 (zero) para a porta onde a aplicação será executada, o próprio Spring se encarrega de escolher uma porta que esteja disponível. Agora vamos iniciar duas instâncias do *consumer* e verificar o impacto disso no dashboard.

![img](https://miro.medium.com/max/27/1*5iDsHbda263JHQQ-sZ84hw.png?q=20)

![img](https://miro.medium.com/max/630/1*5iDsHbda263JHQQ-sZ84hw.png)

Duas queues criadas ao subir duas instâncias do consumer

Podemos ver como resultado que foram criadas duas *queues,* uma para cada instância da aplicação *consumer* que iniciamos, logo se escalarmos o *consumer* criando mais instâncias, não estamos de fato escalando as aplicações, porque estamos criando múltiplas *queues* que continuam processando todas as mensagens que são enviadas ao *exchange.* Vamos fazer uma alteração no nosso *consumer* para evitar esse comportamento.

<iframe src="https://medium.com/media/b535303543be12fea95f572bf1456ca2" allowfullscreen="" frameborder="0" height="282" width="680" title="consumer-simple-v3-application.properties" class="ef es eo ex w" scrolling="auto" style="box-sizing: inherit; width: 680px; position: absolute; left: 0px; top: 0px; height: 281.997px;"></iframe>

Alterando o application.properties, incluímos na linha 11 uma configuração para unificar as *queues* criadas ao iniciar múltiplas instâncias do *consumer.* Vamos confirmar se isso de fato está acontecendo, iniciando novamente duas instâncias do projeto *spring-cloud-consumer-simple* e analisando a aba *Queue* no dashboard.

![img](https://miro.medium.com/max/27/1*ZO5nn2b24qO2ZEKESSfJ6A.png?q=20)

![img](https://miro.medium.com/max/630/1*ZO5nn2b24qO2ZEKESSfJ6A.png)

Queue única para duas instâncias da aplicação consumer

Agora temos uma única *Queue,* que agora possui uma nomenclatura mais amigável, formada pelo nome do *exchange* que ela consome e pelo nome do grupo que definimos no arquivo de propriedades da aplicação. Examinado essa *queue* podemos ver mais um detalhe interessante.

![img](https://miro.medium.com/max/27/1*2-yDLphxJTOOS-GlP1i9Lg.png?q=20)

![img](https://miro.medium.com/max/630/1*2-yDLphxJTOOS-GlP1i9Lg.png)

Detalhes da queue product-queue, com dois consumers.

Agora temos o resultado esperado, uma única *queue* com dois *consumers,* um para cada instância da nossa aplicação *consumer.*

Podemos utilizar o Postman para realizar múltiplas requisições e então verificar como as múltiplas instâncias do *consumer* irão se comportar para consumir as mensagens.

![img](https://miro.medium.com/max/900/1*EQvjEeg9oiz4afpWqO35Eg.png)

Logs de duas instâncias da aplicação consumer.

E como em um passe de mágica, o Cloud Stream faz o direcionamento das mensagens recebidas pela *Queue* para uma das instâncias do nosso *consumer*. Dessa forma, podemos escalar os *consumers* conforme seja necesário.

![img](https://miro.medium.com/max/544/1*0yDJFSKRBmiXt1mts0-DSg.jpeg)

E assim finalizamos mais um exemplo.

# Conclusão

Este artigo mostra (um pouco) o quão simples é trabalhar com Message Brokers utilizando o Spring Cloud Stream, um módulo não tão popular mas que definitivamente deve ser considerado por quem utiliza ou pretende utilizar Message Brokers em projetos.

A documentação do módulo ainda não é abrangente porém a Pivotal vem tentando evolui-la, além do suporte no [StackOverflow](https://stackoverflow.com/questions/tagged/spring-cloud-stream) que os engenheiros da Pivotal estão dando.

Ainda tem uma grande quantidade de *features* que não foram demonstradas nesses dois artigos, como os *Processors, Partitions, SendTo*, dentre outras que merecem ser exploradas, talvez em um próximo artigo.

Até a próxima!