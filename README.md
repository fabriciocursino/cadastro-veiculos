# API de Gerenciamento de Veículos

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para o gerenciamento de veículos. Ele serve como o backend para a aplicação [Veículos SPA](https://www.google.com/search?q=https://github.com/fabriciocursino/veiculos-spa) e foi criado como parte de um teste técnico.

A API oferece endpoints para operações de CRUD (Criar, Ler, Atualizar, Excluir) de veículos, além de fornecer dados estatísticos sobre a frota.

## Tecnologias Utilizadas

* [Java 21](https://www.oracle.com/java/)
* [Swagger](https://swagger.io/) - Gerador de documentação para as rotas da API
* [Spring Boot](https://spring.io/projects/spring-boot) (com Spring Web, Spring Data JPA)
* [Maven](https://maven.apache.org/) - Gerenciador de dependências e build
* [H2 Database](https://www.h2database.com/) - Banco de dados em memória para ambiente de desenvolvimento/teste

## Pré-requisitos

* [JDK (Java Development Kit)](https://www.oracle.com/java/technologies/downloads/) (versão 21 ou superior)
* [Apache Maven](https://maven.apache.org/download.cgi)

## Passo a Passo de Configuração

1. **Clone o repositório:**
   ```
   git clone https://github.com/fabriciocursino/cadastro-veiculos.git
   cd cadastro-veiculos
   ```
2. **Compile o projeto e baixe as dependências:**
   O Maven cuidará de todo o processo
   ```
   mvn clean install
   ```
3. **Execute o projeto:**
   ```
   mvn spring-boot:run
   ```

    Obs.: se o passo a passo estiver difícil, caso queira, você pode abrir o projeto na sua IDE(InteliJ, VSCode), instalar as extensões necessárias para o Maven, e executar o arquivo `CadastroveiculosApplication.java`, que está no diretório `src/main/java/com/example/cadastroveiculos/`.

   A API estará disponível em `http://localhost:8080`.

## Endpoints da API (Documentação)

A seguir, a lista dos principais endpoints disponíveis.

Todos os endpoints da API ficam disponíveis em `http://localhost:8080/swagger-ui/index.html#/` assim que o projeto é executado.

## Decisões de Projeto

* **Java com Spring:** Escolhido pela robustez, forte tipagem e ecossistema maduro, que facilitam a criação de APIs seguras e manuteníveis, além de permitir uma boa aplicação dos conceitos de Orientação a Objetos.
* **Maven vs. Gradle:** Maven foi escolhido pela simplicidade de configuração e vasto suporte, adequados para o escopo deste projeto.

* **Entidade `Marca`:** Em vez de um `Enum`, foi criada uma tabela para as marcas. Isso desacopla os dados do código, permitindo que a lista de marcas seja gerenciada via banco de dados sem a necessidade de alterar a aplicação.

* **Unificação das Rotas de Busca:** As rotas para buscar todos os veículos e para buscar veículos com filtros foram unificadas em um único endpoint (`GET /veiculos`). Esta decisão foi tomada porque a ação é a mesma — listar veículos —, e os filtros servem apenas para refinar um resultado que possui um formato idêntico. Isso simplifica a API e evita redundância.

* **Separação das Rotas `PUT` e `PATCH`:** As operações de atualização foram mantidas em duas rotas distintas (`PUT /veiculos/{id}` e `PATCH /veiculos/{id}`), respeitando os padrões do protocolo HTTP e suas intenções.

* **Banco de Dados H2:** Para facilitar a configuração e execução do projeto, foi utilizado um banco de dados em memória (H2), que não requer instalação ou configuração externa.
