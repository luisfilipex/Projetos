## Apache Kafka

O Apache Kafka foi desenvolvido para processar fluxos de dados provenientes de diversas fontes, bem como para entregá-los a vários clientes. Em resumo, essa plataforma movimenta volumes imensos de dados não apenas do ponto A ao ponto B, mas também de A a Z e para qualquer outro local que você precisar simultaneamente.

O Apache Kafka é uma alternativa aos sistemas de mensageria corporativos tradicionais. Inicialmente, ele era um sistema interno desenvolvido pela LinkedIn para processar 1,4 trilhão de mensagens por dia. Mas agora é uma solução de transmissão de dados open source aplicável a variadas necessidades corporativas.

------

## Integração assíncrona com o Apache Kafka

Os [microsserviços](https://www.redhat.com/pt-br/topics/microservices) mudaram o panorama do desenvolvimento. Eles tornaram os desenvolvedores mais ágeis ao reduzir as dependências, como as camadas de banco de dados compartilhado. No entanto, as aplicações distribuídas que estão sendo criadas pelos desenvolvedores ainda precisam de algum tipo de integração para compartilhar dados. Uma opção de integração muito usada, conhecida como método síncrono, utiliza interfaces de programação de aplicações (APIs) para compartilhar dados entre usuários diferentes.

Uma segunda opção de integração, o método assíncrono, envolve a replicação de dados em um armazenamento intermediário. É aí que o Apache Kafka entra em cena, na transmissão de dados de outras equipes de desenvolvimento para preencher o armazenamento de dados. Assim, é possível compartilhar os dados entre várias equipes e respectivas aplicações.

As equipes de microsserviços têm requisitos de integração diferentes de outras equipes de desenvolvimento em cascata tradicionais. Elas requerem três recursos fundamentais:

1. [Integrações distribuídas](https://www.redhat.com/pt-br/topics/integration): integrações leves e baseadas em padrões que podem ser implantadas continuamente quando necessário e não estão limitadas por implantações do tipo ESB centralizado.
2. [APIs](https://www.redhat.com/pt-br/topics/api): serviços baseados em API para incentivar um ecossistema de parceiros, clientes e desenvolvedores que podem oferecer serviços confiáveis e lucrativos.
3. [Containers](https://www.redhat.com/pt-br/topics/containers): plataforma para desenvolver, gerenciar e escalar aplicações [nativas em nuvem](https://www.redhat.com/pt-br/topics/cloud-native-apps) e conectadas. Os containers possibilitam o desenvolvimento de artefatos lean que podem ser implantados individualmente, fazem parte de processos de [DevOps](https://www.redhat.com/pt-br/topics/devops) e são compatíveis com soluções prontas de clusterização, garantindo uma alta disponibilidade.

A Red Hat chama essa abordagem de "integração ágil", o que permite que as integrações façam parte de processos de desenvolvimento de aplicações, fornecendo soluções mais ágeis e adaptáveis. Um elemento da integração ágil é a liberdade de usar a integração síncrona ou assíncrona, dependendo das necessidades específicas da aplicação. O Apache Kafka é uma excelente opção na integração assíncrona orientada por eventos para aumentar o uso de integração síncrona e APIs, o que oferece ainda mais compatibilidade com microsserviços e possibilita a integração ágil. Dessa forma, o Apache Kafka pode ser uma parte importante da sua iniciativa para simplificar os processos de desenvolvimento, incentivar a inovação, poupar tempo e, em última análise, acelerar o tempo de disponibilização de novas funcionalidades, aplicações e serviços.

------

## Casos de uso do Apache Kafka

O Apache Kafka é incorporado a pipelines de transmissão que compartilham dados entre sistemas e/ou aplicações, bem como a sistemas e aplicações que consomem esses dados. O Apache Kafka é compatível com vários casos de uso, em que alta produtividade e escalabilidade são fatores vitais. Como o Apache Kafka minimiza a necessidade de usar integrações point-to-point (P2P) para o compartilhamento de dados em determinadas aplicações, ele é capaz de reduzir a latência a milésimos de segundos. Isso significa que os dados são disponibilizados mais rapidamente aos usuários, o que é uma vantagem para os casos de uso que exigem disponibilidade de dados em tempo real, como operações de TI e comércio eletrônico.

O Apache Kafka é capaz de lidar com milhões de pontos de dados por segundo, o que faz dessa solução a ideal para desafios que envolvem [big data](https://www.redhat.com/pt-br/topics/big-data). No entanto, o Kafka também é útil para empresas que no momento não operam em cenários com uso tão extremo de dados. Em muitos casos de uso de processamento de dados, como [Internet das Coisas](https://www.redhat.com/pt-br/topics/internet-of-things) (IoT) e mídias sociais, os dados aumentam exponencialmente e, em pouco tempo, podem sobrecarregar uma aplicação que a empresa esteja criando com base no volume de dados atual. Em termos de processamento de dados, é necessário levar em consideração a escalabilidade. Isso significa planejar para a proliferação intensificada dos dados da empresa.

### **Operações de TI**

As equipes operações de TI dependem sobretudo de dados. E elas precisam ter acesso rápido a eles. Essa é a única maneira de manter sites, aplicações e sistemas ativos e em funcionamento permanentemente. O Apache Kafka é uma excelente solução para as funções de operações de TI porque elas dependem da coleta de dados de variadas fontes, como os sistemas de monitoramento, alerta e geração de relatórios, as plataformas de gerenciamento de registros e as atividades de monitoramento de sites.

### **Internet das Coisas**

De acordo com as previsões do Gartner,[ a IoT incluirá mais de 20 bilhões de dispositivos até 2020](https://www.gartner.com/en/newsroom/press-releases/2017-02-07-gartner-says-8-billion-connected-things-will-be-in-use-in-2017-up-31-percent-from-2016). O valor da IoT está nos dados acionáveis gerados pela grande variedade de sensores. O Apache Kafka foi desenvolvido para oferecer escalabilidade capaz de processar os imensos volumes de dados provenientes da IoT.

### **E-commerce**

O comércio eletrônico é uma oportunidade em crescimento para uso do Apache Kafka, pois ele pode processar dados do tipo cliques de página, curtidas, pesquisas, pedidos, carrinhos de compra e inventários.

------

## Como executar o Apache Kafka no Kubernetes

O [Kubernetes](https://www.redhat.com/pt-br/topics/containers/what-is-kubernetes) é a plataforma ideal para o Apache Kafka. Os desenvolvedores precisam de uma plataforma escalável para hospedar as aplicações do Kafka, e o Kubernetes é a resposta.

Assim como o Apache Kafka, o Kubernetes torna o processo de desenvolvimento mais ágil. O Kubernetes, a tecnologia por trás dos serviços de cloud do Google, é um sistema open source para gerenciamento de aplicações em containers. Ele elimina muitos dos processos manuais associados aos containers. Usar o Apache Kafka no Kubernetes otimiza a implantação, a configuração, o gerenciamento e o uso do Apache Kafka.

Ao combinar o Kafka com o Kubernetes, você terá todos os benefícios e vantagens das duas soluções: escalabilidade, alta disponibilidade, portabilidade e implantação fácil.

A escalabilidade do Kubernetes é um complemento natural ao Kafka. No Kubernetes, é possível ajustar a escala de recursos com um comando simples ou automaticamente com base no uso e conforme o necessário para utilizar da melhor forma a infraestrutura de computação, sistema de rede e armazenamento. Essa capacidade permite ao Apache Kafka compartilhar um pool de recursos com outras aplicações. O Kubernetes também confere ao Apache Kafka portabilidade entre provedores de infraestrutura e sistemas operacionais. Com o Kubernetes, os clusters do Apache Kafka podem ser distribuídos em ambientes locais e em clouds públicas, privadas ou públicas, além de utilizar sistemas operacionais diferentes.

[Com o Apache Kafka no Kubernetes, você moderniza sua infraestrutura de pagamentos](https://www.redhat.com/pt-br/resources/intel-kubernetes-payments-infrastructure-overview)