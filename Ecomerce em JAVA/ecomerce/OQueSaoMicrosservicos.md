# O que são microsserviços?

Microsserviços são uma abordagem arquitetônica e organizacional do desenvolvimento de software na qual o software consiste em pequenos serviços independentes que se comunicam usando APIs bem definidas. Esses serviços pertencem a pequenas equipes autossuficientes.

As arquiteturas de microsserviços facilitam a escalabilidade e agilizam o desenvolvimento de aplicativos, habilitando a inovação e acelerando o tempo de introdução de novos recursos no mercado.

### Diferenças entre as arquiteturas monolítica e de microsserviços

Com as arquiteturas monolíticas, todos os processos são altamente acoplados e executam como um único serviço. Isso significa que se um processo do aplicativo apresentar um pico de demanda, toda a arquitetura deverá ser escalada. A complexidade da adição ou do aprimoramento de recursos de aplicativos monolíticos aumenta com o crescimento da base de código. Essa complexidade limita a experimentação e dificulta a implementação de novas ideias. As arquiteturas monolíticas aumentam o risco de disponibilidade de aplicativos, pois muitos processos dependentes e altamente acoplados aumentam o impacto da falha de um único processo.

Com uma arquitetura de microsserviços, um aplicativo é criado como componentes independentes que executam cada processo do aplicativo como um serviço. Esses serviços se comunicam por meio de uma interface bem definida usando APIs leves. Os serviços são criados para recursos empresariais e cada serviço realiza uma única função. Como são executados de forma independente, cada serviço pode ser atualizado, implantado e escalado para atender a demanda de funções específicas de um aplicativo.

![monol&iacute;tica vs. microsservi&ccedil;os](https://d1.awsstatic.com/Developer%20Marketing/containers/monolith_1-monolith-microservices.70b547e30e30b013051d58a93a6e35e77408a2a8.png)

*Divisão de um aplicativo monolítico em microsserviços*

## Características dos microsserviços

![100x100_benefit_deployment2](https://d1.awsstatic.com/icons/benefit-icons/100x100_benefit_deployment2.c68823bf6f80f3f85c4fff89410069de9a1bd60c.png)

### Autônomos

Cada serviço do componente de uma arquitetura de microsserviços pode ser desenvolvido, implantado, operado e escalado sem afetar o funcionamento de outros serviços. Os serviços não precisam compartilhar nenhum código ou implementação com os outros serviços. Todas as comunicações entre componentes individuais ocorrem por meio de APIs bem definidas.

![benefit_85x85_gear-2](https://d1.awsstatic.com/product-marketing/Serverless/benefit_85x85_gear-2.f27f2d94fe6897e2785603c4a7715d18173ff71a.png)

### Especializados

Cada serviço é projetado para ter um conjunto de recursos e é dedicado à solução de um problema específico. Se os desenvolvedores acrescentarem mais código a um serviço ao longo do tempo, aumentando sua complexidade, ele poderá ser dividido em serviços menores.

# Benefícios dos microsserviços

![100x100_benefit_Low-Latency](https://d1.awsstatic.com/icons/benefit-icons/100x100_benefit_Low-Latency.2e96f1de3dacb3bda2a679b372d04c104718be02.png)

### Agilidade

Os microsserviços promovem uma organização de equipes pequenas e independentes que são proprietárias de seus serviços. As equipes atuam dentro de um contexto pequeno e claramente compreendido e têm autonomia para trabalhar de forma mais independente e rápida. O resultado é a aceleração dos ciclos de desenvolvimento. Você obtém benefícios significativos do throughput agregado da organização.

![100x100_benefit_scalable](https://d1.awsstatic.com/icons/benefit-icons/100x100_benefit_scalable.f24d5f6848364dc06c177951f15f29c63fd22f41.png)

### Escalabilidade flexível

Os microsserviços permitem que cada serviço seja escalado de forma independente para atender à demanda do recurso de aplicativo oferecido por esse serviço. Isso permite que as equipes dimensionem corretamente as necessidades de infraestrutura, meçam com precisão o custo de um recurso e mantenham a disponibilidade quando um serviço experimenta um pico de demanda.

![100x100_benefit_delivery-pipeline](https://d1.awsstatic.com/icons/benefit-icons/100x100_benefit_delivery-pipeline.1e300fd9b26f94b1865ffe571f81eef55c833d38.png)

### Fácil implantação

Os microsserviços permitem a integração e a entrega contínuas, o que facilita o teste de novas ideias e sua reversão caso algo não funcione corretamente. O baixo custo de falha permite a experimentação, facilita a atualização do código e acelera o tempo de introdução de novos recursos no mercado.

![100x100_benefit_code-configuration](https://d1.awsstatic.com/icons/benefit-icons/100x100_benefit_code-configuration.29104e7e882b2a715b6284a4352abfc97f9a60fc.png)

### Liberdade tecnológica

As arquiteturas de microsserviços não seguem uma abordagem generalista. As equipes são livres para escolher a melhor ferramenta para resolver problemas específicos. O resultado é que as equipes que criam microsserviços podem optar pela melhor ferramenta para cada tarefa.

![100x100_benefit_transparency](https://d1.awsstatic.com/icons/benefit-icons/100x100_benefit_transparency.d2636d5bf5c2cfe25a70cc7f7bac5b22fb8cc547.png)

### Código reutilizável

A divisão do software em módulos pequenos e bem definidos permite que as equipes usem funções para várias finalidades. Um serviço criado para uma determinada função pode ser usado como componente básico para outro recurso. Isso permite que os aplicativos sejam reutilizados, pois os desenvolvedores podem criar recursos sem precisar escrever código.

![100x100_benefit_durable](https://d1.awsstatic.com/icons/benefit-icons/100x100_benefit_durable.54299a12d07fd6a95f634ea029ec94a6609f8eb6.png)

### Resiliência

A independência do serviço aumenta a resistência a falhas do aplicativo. Em uma arquitetura monolítica, a falha de um único componente poderá causar a falha de todo o aplicativo. Com os microsserviços, os aplicativos lidam com a falha total do serviço degradando a funcionalidade, sem interromper todo o aplicativo.

# A plataforma mais completa para microsserviços

A AWS integrou componentes básicos que oferecem suporte a qualquer arquitetura de aplicativos, independentemente de escala, carga ou complexidade.

## Computação

Capacidade de processamento para microsserviços.

![Amazon EMR](https://d1.awsstatic.com/product-marketing/DevOps/benefit_extensible-standalone.a836262f6432452aef21ba7054ff0c94bd2a83fb.png)

### Contêineres

**Amazon Elastic Container Service**

Um serviço de gerenciamento de contêineres com altos níveis de escalabilidade e performance que oferece suporte a contêineres do Docker, além de permitir que você execute facilmente aplicativos em um cluster gerenciado de instâncias do Amazon EC2.
[Saiba mais »](https://aws.amazon.com/pt/ecs/)

![DevOps-Solution_logobreak_coursera](https://d1.awsstatic.com/Developer%20Marketing/DevOps/DevOps-Solution_logobreak_coursera.47af5ce54793b4535e9e50b05961a3e531af17b5.png)

 

Agora, usando o Amazon ECS, o Coursera pode implantar alterações de software em minutos, em vez de horas, em um ambiente isolado de recursos.
[Saiba mais »](https://aws.amazon.com/solutions/case-studies/coursera-ecs/)

![Amazon EMR](https://d1.awsstatic.com/Solutions/Big%20Data/Big%20Data%20Redesign/BigData_Serverless-Compute.ed096cf44c6e0eb36799d4b46bc74e7f2cffe7e6.png)

### Arquiteturas Sem servidor

**AWS Lambda**

O AWS Lambda permite que você execute código sem provisionar ou gerenciar servidores. Basta fazer upload do código e o Lambda gerencia tudo o que é necessário para executar e alterar a escala do código com alta disponibilidade.
[Saiba mais »](https://aws.amazon.com/pt/lambda/)

![DevOps-Solution_logobreak_localytics](https://d1.awsstatic.com/Developer%20Marketing/DevOps/DevOps-Solution_logobreak_localytics.767de419858d440b9800fd8c7ef139d0cfc4cd04.png)

 

A Localytics usou o AWS Lambda para criar microsserviços que habilitaram as equipes de desenvolvimento a criar análises personalizadas sem o suporte central. [Saiba mais »](https://aws.amazon.com/solutions/case-studies/localytics/)

## Armazenamento e bancos de dados

Armazenamento físico de dados escalável, resiliente e seguro.  

![Aurora-benefit_high-performance](https://d1.awsstatic.com/products/Aurora/Aurora%20Redesign/Aurora-benefit_high-performance.873ed37b33b6624b2f2eadd2857298710e2a2b08.png)

### Armazenamento em cache

**Amazon ElastiCache**

O Amazon ElastiCache aprimora a performance de serviços, permitindo que você recupere informações de caches de memória rápidos e gerenciáveis, em vez de depender inteiramente de bancos de dados baseados em disco, que são mais lentos. [Saiba mais »](https://aws.amazon.com/pt/elasticache/)

### Armazenamento de objetos

**Amazon S3**

O Amazon S3 disponibiliza a desenvolvedores e equipes de TI um armazenamento de objetos altamente confiável, seguro e escalável para todos os dados, qualquer que seja o volume. [Saiba mais »](https://aws.amazon.com/pt/s3/)

### Bancos de dados NoSQL

**Amazon DynamoDB**

Um serviço de banco de dados NoSQL rápido e flexível para todos os aplicativos que precisam de latência consistente abaixo de 10 milissegundos, em qualquer escala. [Saiba mais »](https://aws.amazon.com/pt/dynamodb/)

### Bancos de dados relacionais

**Amazon RDS**

Configure, opere e escale facilmente um [banco de dados relacional](https://aws.amazon.com/pt/relational-database/) na nuvem. Escolha entre seis mecanismos populares de banco de dados, incluindo Oracle, Microsoft SQL Server, PostgreSQL, MySQL e MariaDB. [Saiba mais »](https://aws.amazon.com/pt/rds/)

**Amazon Aurora**

Um mecanismo de [banco de dados relacional](https://aws.amazon.com/pt/relational-database/) que combina a velocidade e a confiabilidade de bancos de dados comerciais avançados com a simplicidade e a economia de bancos de dados de código aberto. Entregue até cinco vezes o throughput do MySQL padrão executando no mesmo hardware.[ Saiba mais »](https://aws.amazon.com/pt/rds/aurora/)

![DevOps-Solution_logobreak_remind](https://d1.awsstatic.com/Developer%20Marketing/DevOps/DevOps-Solution_logobreak_remind.ed3b246d97c91538f9aec11f71c0232a57e077ac.png)

 

A Remind reduziu os tempos de resposta de aplicativos em 200% criando uma PaaS para microsserviços na Amazon ECS. 
[Saiba mais »](https://aws.amazon.com/solutions/case-studies/remind/)

## Redes

Serviços de redes com alto throughput e latência inferior a um milissegundo.

![service-discovery-and-mesh](https://d1.awsstatic.com/acs/characters/icons/service-discovery-and-mesh.8f20f8c2625523956b2cee43d863b231d7e14f87.png)

### Descoberta de serviços

**AWS Cloud Map**

O AWS Cloud Map é uma descoberta de serviços para todos os recursos de nuvem. Com o Cloud Map, você pode definir nomes personalizados para os recursos do aplicativo, e ele manterá a localização atualizada desses recursos em constante mudança.

[Saiba mais »](https://aws.amazon.com/pt/cloud-map/)

### Malha de serviços

**AWS App Mesh
**

O AWS App Mesh facilita o monitoramento e o controle de microsserviços executados na AWS. O App Mesh padroniza a forma de comunicação dos microsserviços, oferecendo visibilidade completa e ajudando a garantir alta disponibilidade para os aplicativos.

[Saiba mais »](https://aws.amazon.com/pt/app-mesh/)

![ApplicationDiscoveryService_MCV3_Benefit_StreamlinePlanning](https://d1.awsstatic.com/Inspector/ApplicationDiscoveryService_MCV3_Benefit_StreamlinePlanning.88f368946cb4a52297529ca8f42d29e475869254.png)

### Elastic Load Balancing

**Application Load Balancer**

O Application Load Balancer executa balanceamento de carga de tráfego HTTP e HTTPS na camada de aplicativos (camada 7) e oferece roteamento avançado de solicitações para a entrega de arquiteturas modernas de aplicativos, incluindo microsserviços e contêineres.

[Saiba mais »](https://aws.amazon.com/pt/elasticloadbalancing/details/)

**Network Load Balancer**

O Network Load Balancer oferece balanceamento de carga de alta performance que opera na camada de conexão de rede (camada 4) e permite rotear conexões para microsserviços com base em dados do protocolo IP. O Network Load Balancer pode tratar milhões se solicitações por segundo mantendo latências ultrabaixas.

[Saiba mais »](https://aws.amazon.com/pt/elasticloadbalancing/details/)

![BigData_Direct-Connectivity](https://d1.awsstatic.com/Solutions/Big%20Data/Big%20Data%20Redesign/BigData_Direct-Connectivity.1817cb43dc4aefe2e67afa043d3d93fa4859ce4c.png)

### Proxy de API

**Amazon API Gateway**

O Amazon API Gateway oferece uma plataforma abrangente para [gerenciamento de APIs](https://aws.amazon.com/pt/api-gateway/api-management/). O Amazon API Gateway permite processar centenas de milhares de chamadas de API simultâneas e se encarrega das atividades de gerenciamento de tráfego, autorização e controle de acesso, monitoramento e gerenciamento de versões de APIs.

[Saiba mais »](https://aws.amazon.com/pt/api-gateway/)

### DNS

**Amazon Route 53**

O Amazon Route 53 é um web service altamente disponível e escalável de Domain Name System (DNS) na nuvem que conecta eficazmente as solicitações à infraestrutura executada na AWS. O serviço pode ser usado para verificações de integridade de IP e descoberta de serviços para microsserviços.

[Saiba mais »](https://aws.amazon.com/pt/route53/)

![R-Divider_Airtime_logo](https://d1.awsstatic.com/logos/customers/R-Divider_Airtime_logo.5919c8c84514a99f4d47e86e64a56d59eec3057d.png)

 

Após reprojetar seu aplicativo como microsserviços executados na AWS, a Airtime disponibiliza sua experiência social para os clientes de modo mais rápido, confiável e sem atrasos. [Saiba mais »](https://aws.amazon.com/solutions/case-studies/airtime/)

## Sistema de mensagens

Publique e coordene comunicações entre processos.

![DMS_benefit-fast-setup](https://d1.awsstatic.com/products/DMS/DMS_benefit-fast-setup.0909e653975bade7a908ed9423f768e08133a3e4.png)

### Publicação e assinatura de mensagens

**Amazon Simple Notification Service (Amazon SNS)**

O Amazon SNS é um serviço gerenciado de sistemas de mensagens do tipo publicação/assinatura (pub/sub) que facilita o desacoplamento e a escalabilidade de microsserviços, sistemas distribuídos e aplicativos sem servidor.
[Saiba mais »](https://aws.amazon.com/pt/sns/)

### Enfileiramento de mensagens

**Amazon Simple Queue Service (Amazon SQS)**

O Amazon SQS é um serviço gerenciado de enfileiramento de mensagens que facilita o desacoplamento e a escalabilidade de microsserviços, sistemas distribuídos e aplicativos sem servidor.
[Saiba mais »](https://aws.amazon.com/pt/sqs/)

![R-Divider_Lyft_Logo](https://d1.awsstatic.com/logos/right-divider_logos/R-Divider_Lyft_Logo.b5bd837a89d212a261544f616be8ea6034565d68.png)

 

A Lyft usa a AWS para ter maior agilidade empresarial e gerenciar seu crescimento exponencial. Com os produtos da AWS, a empresa oferece suporte a mais de 100 microsserviços que aprimoram cada elemento da experiência dos clientes. [Saiba mais »](https://aws.amazon.com/solutions/case-studies/lyft/)

## Registro em log e monitoramento

Monitore a performance e a utilização de recursos dos serviços. Rastreie em arquiteturas complexas para resolução de problemas e otimização.

![ApplicationDiscoveryService_MCV3_Benefit_DiscoverInventory](https://d1.awsstatic.com/Inspector/ApplicationDiscoveryService_MCV3_Benefit_DiscoverInventory.955ba06ea197f9dddb543d2448405b3fbe519021.png)

### Monitoramento de APIs

**AWS CloudTrail**

Com o CloudTrail, é possível registrar em log, monitorar continuamente e reter a atividade da conta relacionada às ações executadas na infraestrutura. O histórico de eventos do CloudTrail simplifica a análise de segurança, o rastreamento de alterações de recursos e a solução de problemas. [Saiba mais »](https://aws.amazon.com/pt/cloudtrail/)

### Monitoramento de aplicativos e recursos

**Amazon CloudWatch**

Você pode usar o Amazon CloudWatch para coletar e rastrear métricas, coletar e monitorar arquivos de log, definir alarmes e reagir automaticamente a alterações nos serviços e recursos da AWS executados.
[Saiba mais »](https://aws.amazon.com/pt/cloudwatch/)

![benefit_Fully-Managed](https://d1.awsstatic.com/logos/ElastiCache/benefit_Fully-Managed.7f495e6d85d4372b7ce7cc84751cdee76e050ddc.png)

### Rastreamento distribuído

**AWS X-Ray**

Obtenha uma visualização completa sobre as solicitações, conforme elas percorrem o aplicativo, além de um mapa dos componentes subjacentes do aplicativo. À medida que um conjunto de microsserviços trabalha para processar uma solicitação, o AWS X-Ray pode fornecer uma visualização centralizada dos logs, o que permite monitorar e resolver problemas de interações complexas. [Saiba mais »](https://aws.amazon.com/pt/xray/)

![R-Divider_Shippable_Logo](https://d1.awsstatic.com/logos/right-divider_logos/R-Divider_Shippable_Logo.02248a2ae942e27514343e7ea18edcd66fc76933.png)

 

Usando microsserviços hospedados no Amazon ECS, a Shippable conseguiu se dedicar à entrega de recursos aos clientes e acelerou o a implantação desses recursos de uma vez por semana para vários por dia. [Saiba mais »](https://aws.amazon.com/solutions/case-studies/shippable/)

## DevOps

Gerencie o ciclo de vida do código, da confirmação à execução.

![Aurora-benefit_highly-scalable](https://d1.awsstatic.com/products/Aurora/Aurora%20Redesign/Aurora-benefit_highly-scalable.5a7a888815e098a4a3f8ed4846b5d843662213ea.png)

### Repositórios de imagens de contêiner

**Amazon Elastic Container Registry (Amazon ECR)**

Um registro gerenciado de contêineres do Docker que facilita o armazenamento, o gerenciamento e a implantação de imagens de contêineres do Docker. O Amazon ECR é integrado ao Amazon Elastic Container Service (Amazon ECS), o que simplifica o fluxo de trabalho de contêineres, do desenvolvimento até a produção. [Saiba mais »](https://aws.amazon.com/pt/ecr/)

![benefit_practice-devops](https://d1.awsstatic.com/product-marketing/DevOps/benefit_practice-devops.ba0a0ea8df2356756e149daa19bdc11b56945e0d.png)

### Entrega contínua

**Ferramentas do desenvolvedor da AWS**

As ferramentas do desenvolvedor da AWS são um conjunto de serviços que permite que desenvolvedores e profissionais de operações de TI que trabalham com DevOps possam entregar software com rapidez e segurança. Juntos, esses serviços ajudam a armazenar e controlar com segurança versões de código-fonte de aplicativos, além de criar, testar e implantar automaticamente aplicativos no ambiente local ou na AWS. [Saiba mais »](https://aws.amazon.com/pt/products/developer-tools/)

![R-Divider_Gilt-Groupe_Logo](https://d1.awsstatic.com/logos/right-divider_logos/R-Divider_Gilt-Groupe_Logo.d827f30c246f4c9a6af806934926e855354c2700.png)

 

A Gilt mudou de um datacenter local para a AWS para aproveitar a velocidade e a eficiência de uma infraestrutura de microsserviços baseados na nuvem.
[Saiba mais »](https://aws.amazon.com/solutions/case-studies/gilt/)

# Comece a usar

Comece a criar microsserviços ainda hoje com estes recursos.

### Whitepapers

[Microsserviços na AWS
](https://d1.awsstatic.com/whitepapers/microservices-on-aws.pdf)[Microsserviços conteinerizados na AWS
](https://d1.awsstatic.com/whitepapers/DevOps/running-containerized-microservices-on-aws.pdf)[Arquiteturas multicamada sem servidor da AWS](https://d0.awsstatic.com/whitepapers/AWS_Serverless_Multi-Tier_Architectures.pdf)

### Workshops

[Zombie Serverless Microservices](https://github.com/awslabs/aws-lambda-zombie-workshop)
[Microsserviços conteinerizados: quebre o monólito](https://aws.amazon.com/getting-started/container-microservices-tutorial)
[Treinamento: Running Container-Enabled Microservices on AWS (Como executar microsserviços habilitados para contêineres na AWS)](https://aws.amazon.com/training/course-descriptions/container-microservices/)

### Projetos de código aberto

[Serverless Application Model (SAM)](https://github.com/awslabs/serverless-application-model)
[Microsserviços em Go](https://github.com/awslabs/ecs-refarch-cloudformation)
[Microsserviços em Node.js](https://github.com/awslabs/amazon-ecs-nodejs-microservices)
[Microsserviços em Java](https://github.com/awslabs/amazon-ecs-java-microservices)

### Blogs

[Microservices without the Servers](https://aws.amazon.com/blogs/compute/microservices-without-the-servers/)
[Deploying Java Microservices on Amazon Elastic Container Service](https://aws.amazon.com/blogs/compute/deploying-java-microservices-on-amazon-ec2-container-service/)
[Building Scalable Applications and Microservices](https://aws.amazon.com/blogs/compute/building-scalable-applications-and-microservices-adding-messaging-to-your-toolbox/)
[Run Containerized Microservices with Amazon ECS and ALB](https://aws.amazon.com/blogs/compute/microservice-delivery-with-amazon-ecs-and-application-load-balancers/)
[Using Amazon API Gateway with microservices deployed on Amazon ECS](https://aws.amazon.com/blogs/compute/using-amazon-api-gateway-with-microservices-deployed-on-amazon-ecs/)
[Service Discovery: An Amazon ECS Reference Architecture](https://aws.amazon.com/blogs/compute/service-discovery-an-amazon-ecs-reference-architecture/)

### Documentação

[Amazon ECS](https://aws.amazon.com/documentation/ecs/)
[AWS Lambda](https://aws.amazon.com/documentation/lambda/)
[Amazon Elasticache](https://aws.amazon.com/documentation/elasticache/)
[Amazon S3](https://aws.amazon.com/documentation/s3/)
[Amazon RDS
](https://aws.amazon.com/documentation/rds/)[Amazon Route 53](https://aws.amazon.com/documentation/route53/)
[Elastic Load Balancing](https://aws.amazon.com/documentation/elastic-load-balancing/)
[Amazon SNS](https://aws.amazon.com/documentation/sns/)
[Amazon SQS](https://aws.amazon.com/documentation/sqs/)
[AWS CloudTrail](https://aws.amazon.com/documentation/cloudtrail/)
[Amazon CloudWatch](https://aws.amazon.com/documentation/cloudwatch/)
[AWS X-Ray](https://aws.amazon.com/documentation/xray/)
[Amazon ECR](https://aws.amazon.com/documentation/ecr/)
[Ferramentas do desenvolvedor](https://aws.amazon.com/documentation/codestar/)