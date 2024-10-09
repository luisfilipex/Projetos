# ARQUITETURA DE MICROSSERVIÇOS

## O que são microsserviços?

Microsserviços são uma abordagem de arquitetura para a criação de aplicações. O que diferencia a arquitetura de microsserviços das abordagens monolíticas tradicionais é como ela decompõe a aplicação por funções básicas. Cada função é denominada um serviço e pode ser criada e implantada de maneira independente. Isso significa que cada serviço individual pode funcionar ou falhar sem comprometer os demais.

![img](https://www.redhat.com/cms/managed-files/monolithic-vs-microservices.png)

Pense na última vez em que você acessou o site de uma loja. Provavelmente, você usou a barra de pesquisa do site para procurar produtos. Essa pesquisa representa um serviço. Talvez você também tenha visto recomendações de produtos relacionados, extraídas de um banco de dados das preferências dos compradores. Isso também é um serviço. Você adicionou algum item ao carrinho de compras? Isso mesmo, esse é mais um serviço.

Portanto, um microsserviço é uma função essencial de uma aplicação e é executado independentemente dos outros serviços. No entanto, a arquitetura de microsserviços é mais complexa do que o mero acoplamento flexível das funções essenciais de uma aplicação. Trata-se da [restruturação das equipes de desenvolvimento](https://www.redhat.com/pt-br/topics/devops) e da comunicação entre serviços de modo a preparar a aplicação para falhas inevitáveis, escalabilidade futura e [integração de funcionalidades novas](https://www.redhat.com/pt-br/topics/integration).

Como isso é possível? Com a adaptação dos fundamentos da arquitetura orientada a serviço (SOA) para a implantação de microsserviços.

------

## Isso soa familiar?

Se você conhece o método de decompor aplicações em funções essenciais para evitar os problemas provocados pelas arquiteturas monolíticas é porque o estilo de arquitetura de microsserviços é semelhante ao da arquitetura orientada a serviço (SOA), já consagrado no desenvolvimento de programas de software.

Nos primórdios do desenvolvimento de aplicações, até mesmo as alterações mais insignificantes em uma aplicação pronta exigiam uma atualização da versão de atacado, com um ciclo próprio de garantia da qualidade (QA). Isso, provavelmente, atrasava o trabalho de muitas subequipes. Muitas vezes, essa abordagem é chamada de "monolítica" porque o código-fonte da aplicação toda era incorporado em uma única unidade de implantação, como .war ou .ear. Se a atualização de alguma das partes causasse erros, era necessário desativar a aplicação inteira, reverter a escala e corrigir o problema. Embora essa abordagem ainda seja viável para aplicações menores, as empresas em ampla expansão não podem se dar ao luxo de sofrer com tempo de inatividade.

A arquitetura orientada a serviço serve pra resolver essa questão, pois estrutura as aplicações em serviços distintos e reutilizáveis que se comunicam por meio de um Enterprise Service Bus (ESB). Nessa arquitetura, os serviços individuais, cada um deles organizado em torno de um processo de negócios específico, aderem a um protocolo de comunicação, como [SOAP](https://middlewareblog.redhat.com/2017/08/30/integrating-soap-based-web-services-into-red-hat-3scale-api-management/), [ActiveMQ](https://www.redhat.com/pt-br/technologies/jboss-middleware/amq-2) ou [Apache Thrift](https://thrift.apache.org/), para que sejam compartilhados por meio do ESB. Quando reunidos em um pacote integrado por meio de um ESB, esses serviços formam uma aplicação.

Por um lado, isso permite criar, testar e ajustar os serviços de maneira simultânea, eliminando os ciclos de desenvolvimento monolíticos. No entanto, por outro lado, o ESB representa um ponto único de falha no sistema inteiro. Portanto, todo o esforço empregado para eliminar uma estrutura monolítica, de certo modo, serviu apenas para criar outra: o ESB, que potencialmente pode congestionar toda a organização.

------

## Da SOA aos microsserviços

Qual a diferença? Os microsserviços podem se comunicar *entre si*, normalmente de maneira stateless. Portanto, as aplicações criadas dessa maneira podem ser mais tolerantes a falhas e depender menos de um único ESB. Além disso, as equipes de desenvolvimento podem escolher as ferramentas que desejarem, pois os microsserviços podem se comunicar por meio de interfaces de programação de aplicações ([APIs](https://www.redhat.com/pt-br/topics/api/what-are-application-programming-interfaces)) independentes de linguagem.

Levando em consideração a história da SOA, os microsserviços não são uma ideia completamente nova. Porém, eles se tornaram mais viáveis graças aos avanços nas tecnologias de containerização. Com os [containers Linux](https://www.redhat.com/pt-br/topics/containers/whats-a-linux-container), agora é possível executar várias partes de uma aplicação de maneira independente no mesmo hardware e com um controle muito maior sobre os componentes individuais e ciclos de vida. Juntamente com as APIs e as equipes de [DevOps](https://www.redhat.com/pt-br/topics/devops), os microsserviços em containers são os pilares das aplicações nativas em cloud.

[Como a Red Hat ajuda a criar aplicações nativas em cloud com microsserviços?](https://www.redhat.com/pt-br/topics/cloud-native-apps/why-choose-red-hat-cloud-native)

------

## Quais são os benefícios da arquitetura de microsserviços?

Com os microsserviços, suas equipes e tarefas rotineiras podem se tornar mais eficientes por meio do desenvolvimento distribuído. Além disso, é possível desenvolver vários microsserviços ao mesmo tempo. Isso significa que você pode ter mais desenvolvedores trabalhando simultaneamente na mesma aplicação, o que resulta em menos tempo gasto com desenvolvimento.

### Lançamento no mercado com mais rapidez

Como os ciclos de desenvolvimento são reduzidos, a arquitetura de microsserviços é compatível com implantações e atualizações mais ágeis.

### Altamente escalável

À medida que a demanda por determinados serviços aumenta, você pode fazer implantações em vários servidores e infraestruturas para atender às suas necessidades.

### Resiliente

Os serviços independentes, se construídos corretamente, não afetam uns aos outros. Isso significa que, se um elemento falhar, o restante da aplicação permanece em funcionamento, diferentemente do modelo monolítico.

### Fácil de implementar

Como as aplicações baseadas em microsserviços são mais modulares e menores do que as aplicações monolíticas tradicionais, as preocupações resultantes dessas implantações são invalidadas. Isso requer uma coordenação maior, mas as recompensas podem ser extraordinárias.

### Acessível

Como a aplicação maior é decomposta em partes menores, os desenvolvedores têm mais facilidade para entender, atualizar e aprimorar essas partes. Isso resulta em ciclos de desenvolvimento mais rápidos, principalmente quando também são empregadas as tecnologias de desenvolvimento ágil.

### Mais open source

Devido ao uso de APIs poliglotas, os desenvolvedores têm liberdade para escolher a melhor linguagem e tecnologia para a função necessária.

------

## E os desafios?

Se a sua organização pretende migrar para a arquitetura de microsserviços, tenha em mente que será necessário implementar mudanças não somente nas aplicações, mas também no modo como as pessoas trabalham. As mudanças organizacionais e culturais podem ser, em parte, [consideradas como desafios](https://www.redhat.com/pt-br/blog/state-microservices), porque cada equipe terá um ritmo próprio de implantação e será responsável por um serviço exclusivo, com um conjunto próprio de clientes. Talvez essas não sejam preocupações típicas para os desenvolvedores, mas elas serão essenciais para o sucesso da arquitetura de microsserviços.

Além das mudanças na cultura e nos processos, a complexidade e a eficiência são outros dois grandes desafios da arquitetura baseada em microsserviços. John Frizelle, arquiteto de plataformas do Red Hat Mobile, definiu as oito categorias de desafios a seguir em sua [palestra no Red Hat Summit de 2017](https://rh2017.smarteventscloud.com/connect/sessionDetail.ww?SESSION_ID=104609&tclass=popup):

1. **Compilação:** é necessário dedicar um tempo à identificação das dependências entre os serviços. É preciso estar ciente de que concluir uma compilação pode gerar muitas outras devido a essas dependências. Também é necessário levar em consideração como os microsserviços [afetam os dados](https://developers.redhat.com/blog/2016/08/02/the-hardest-part-about-microservices-your-data/).
2. **Testes:** os testes de integração, assim como os testes end-to-end, podem ser mais difíceis e importantes como jamais foram. Saiba que uma falha em uma parte da arquitetura pode provocar outra mais adiante, dependendo de como os serviços foram projetados para suportar uns aos outros.
3. **Controle de versão:** ao atualizar para versões novas, lembre-se de que a compatibilidade com versões anteriores pode ser rompida. É possível resolver esse problema usando a lógica condicional, mas isso pode se tornar outra complicação rapidamente. Como alternativa, você pode colocar no ar várias versões ativas para clientes diferentes, mas essa solução é mais complexa em termos de manutenção e gerenciamento.
4. **Implantação:** sim, isso também é um desafio, pelo menos na configuração inicial. Para facilitar a implantação, primeiro é necessário investir bastante na [automação](https://www.redhat.com/pt-br/topics/automation), pois a complexidade dos microsserviços é demais para a implantação manual. Pense sobre como e em que ordem os serviços serão implementados.
5. **Geração de logs:** com os sistemas distribuídos, é necessário ter logs centralizados para unificar tudo. Caso contrário, é impossível gerenciar a escala.
6. **Monitoramento:** é crítico ter uma visualização centralizada do sistema para identificar as fontes de problemas.
7. **Depuração:** a depuração remota não é uma opção e não funciona com centenas de serviços. Infelizmente, no momento não há uma única resposta para como realizar depurações.
8. **Conectividade:** considere a detecção de serviços, seja de maneira centralizada ou integrada.

[Leia mais no blog do Red Hat Developer Program](https://developers.redhat.com/blog/2017/05/04/the-truth-about-microservices/)