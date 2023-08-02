# Jobs Unimed Ceará

## Descrição Geral

O sistema Core é uma API REST que foi criada para centralizar os endpoints que ofertam serviços para outros sistemas internos da empresa. Está disponível em produção [AQUI](https://apps.unimedcerara.com.br/core) e em teste [AQUI](https://appsteste.unimedcerara.com.br/core).

O sistema foi construído com Spring Boot em Java 11. Utiliza JPA para acessar um banco de dados Oracle.

## Serviços disponíveis

Serviços disponíveis na versão 1.0:

- Listar boletos em aberto
- Emitir boletos
- Emitir declaração de imposto de renda
- Buscar prestadores por carteirinha (Guia Médico)
- Emitir declaração de coparticipação
- Emitir declaração de tempo de contrato
- Listar períodos de carência de uma carteira
- Solicitar guia de autorização
- Listar guias de autorização abertas

## Dependências externas

Na presente versão, o Core consome alguns serviços ofertados por outros pacotes. A falha em algum desses pacotes podem acarretar em erros no Core. Segue a lista:

- [https://apps.unimedceara.com.br/oauth-file-server/](https://apps.unimedceara.com.br/oauth-file-server/), para guardar arquivos.
- [sux-web05p.unimedceara.com.br](https://sux-web05p.unimedceara.com.br/unimed/2via_ir/gera_pdf_ana.php) para gerar relatório de imposto de renda.
- [http://cmed.unimedceara.com.br/sabius-servicos-web/](http://cmed.unimedceara.com.br/sabius-servicos-web/rest-servicos/2ViaFaturaFacil/v4/gerar2ViaFaturaFacil) para gerar boletos.
- [https://vmcard.unimedceara.com.br/apisite](https://vmcard.unimedceara.com.br/apisite/services/coparticipacao/relatorio) para gerar relatório de coparticipação.
- [https://maps.googleapis.com](https://maps.googleapis.com/maps/api/geocode/json) para gerar localização dos prestadores no guia médico.



# Tutorial de Instalação

Atualmente o projeto está configurado para rodar em wildfly 23+ e Java 11.

## Clonando o projeto

Clone o projeto no gitlab.


## Instalando e configurando o Wildfly (jboss)

lore ipsum

# Arquitetura do projeto

O projeto é feito em três camadas: a camada de ***Controller***, ***Service*** e ***Repository*** (ou ***DAO***).

A camada de ***Controller*** é responsável por por definir informações relacionadas a camada REST dos endpoints. Ou seja, é nela devem ser configurados dados como assinatura do método, *query params*, *path params*, *accepted content* e formatos de entrada e resposta. A validação de dados em termos de API devem ser feitos aqui.

A camada de ***Service*** é responsável pela regra de negócio e lógica dos servicos disponibilidados. Por tanto, manipulação, validação e criação de **dados de negócio** devem ser feitos nesta camada.

A camada de ***Repository*** é responsável pela comunicação com o banco de dados. todo e qualquer acesso ao DB deve ser feito por ela.

![Arquitetura geral do sistema](images\estrutua_basica.PNG)

Além dessa divisão, o escopo do código é dividido por também *modelos*. Modelo é uma classe que representa uma entidade de negócio, que porvavelmente também possui uma tabela no banco de dados. Por exemplo, *Beneficiário* é um modelo. Assim, cada modelo tem sua camada de código também. Quando você ver, por exemplo, um arquivo chamado *BeneficiarioService.java*, você deduzir que aquele arquivo trata sobre a camada de *service* do modelo *Beneficiário*.

## Estrutura de pastas e arquivos

O Código fonte do projeto segue a seguinte organização de pastas:

- **Config:** pasta que contém arquivos de configuração, especialmente relacionados a configuração de autenticação do keycloak e manipulação de erros.
- **Model:** Pasta que contém os modelos da aplicação. Representam uma entidade do banco de dados e, por tanto, possuem anotações *Hibernate*.
- **Dtos:** Pasta que contém DTO's (Data Transfer Objects). Eles representam os modelos descritos na pasta Model, mas o a responsabilidade dele é ser uma representação para as camadas de transporte (representação em JSON para os controllers, por exemplo), e não de banco de dados. Aqui devem ser descritas personalizações relacionadas, como por exemplo anotações do *javax*.
- **Exceptions**: Pasta que contém exceções personalizadas para o projeto. É necessária
- **Controllers:** Contém os controllers da aplicação, divididos pelos modelos.
- **Services:** Contém os services da aplicação, divididos pelos modelos.
- **Repository** Contém os repositories da aplicação, divididos pelos modelos e entidades no banco de dados. Lembrando que o repository é a camada DAO, mas seguindo o padrão do JPA.

# Outros pontos importantes

## Status do deploy

Para verificar a saúde do projeto, o *hash* do último commit e o timestamp do último deploy, você pode usar o *endpoint* **/core/health**.

## Atributos de configuração

Este projeto não utiliza arquivo de configuração. Os atributos de configuração são guardados na tabela UNMVMCARD.CONFIG_CORE e são carregados quando o projeto é inicializado. Eles ficam em cachê para evitar acessos constantes ao banco de dados e são sincronizados de hora em hora. As classes responsáveis são ***ConfigurationCore***, ***ConfiguracaoCoreController***, ***ConfigurationCoreRepository*** e ***ConfigurationCoreScheduler***.

## Keycloak e autenticação

- O projeto é autenticado usando keycloak. 

- A grande maioria das configurações relacionadas a autenticação são feitas pela classe ***KeycloakSpringSecurityConfig***.

- A classe ***PahBasedKeycloakConfigResolver*** é responsável por gerenciar os *realms* do projeto.

## Lançamento de exceções

O padrão do projeto é lançar exceções quando algo dá errado. O tipo da exceção indica qual o status HTTP e mensagem que será devolvido para o cliente. Isso é gerenciado pela classe ***ExceptionHandler***, que captura as exceções lançadas no projeto gera uma resposta correspondente para o cliente.

Por exemplo, se um dado que o cliente enviou não está correto e essa verificação ocorreu na camada de serviço, o método que fez a verificação deve lançar um *BadRequestException* com um *throw* na sua assinatura. O método da camada de controller que utilizar esse serviço deve fazer a mesma coisa para que a exceção seja capturada pelo Spring. Assim, quando o Spring capturar, ele vai direcionar para o *ExceptionHandler* e classe vai construir a resposta adequada para o cliente.

Atualmente, o padrão é que uma exceção causada por uma dependência interna (por exemplo, uma conexão com um endpoint de projeto) cause um erro 500. Independentemente do erro 500 ter sido lançado intensionalmente ou não, o *ExceptionHandler* vai imprimir o *stack trace* no *log*.

## Melhoria de código

Para melhorias no projeto, siga as sugestões dos comentários TODO no código.

Além disso, o projeto ainda não tem o Swagger completamente configurado. Isso é uma melhoria importante.