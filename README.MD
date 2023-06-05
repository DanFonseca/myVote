# My Vote



[![LinkedIn][linkedin-shield]][linkedin-url]

<!-- PROJECT LOGO -->
<br />
<p align="center">
  <a href="https://github.com/othneildrew/Best-README-Template">
 </a>

<h3 align="center">My Vote</h3>




<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-project">Sobre o Projeto</a>
      <ul>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Começando</a>
      <ul>
        <li><a href="#prerequisites">Pré-Requisito</a></li>
        <li><a href="#installation">Instalação</a></li>
      </ul>
    </li>
    <li><a href="#usage">Como usar</a></li>
    <li><a href="#contact">Contato</a></li>
  </ol>
</details>



<!-- ABOUT THE PROJECT -->
## Sobre o Projeto
API Rest com a função principal de votar em uma determinada pauta no BackEnd.

Os principais recursos desta API são:

<ol>
<li><a href="#1-criar-associado">Criação de um associado</a></li>
<li><a href="#2-criacaoo-da-pauta">Criação da Pauta</a></li> 
<li><a href="#3-criacao-da-sessao-de-votacao">Criação da Sessão de Votação</a></li>
<li><a href="#4-make-transfer">Votar</a></li>
</ol>

### Decisões Técnicas

* Arquitetura:
    * A Arquitetura do projeto foram dividida em três camadas:
        * business: A camada de business representa toda regra de negócio do desafio, onde ficará os casos de uso (services),
          e utils na qual será utilizado para apoiar a regra de negócio.
        * Data: A camada de data, representa os dados transacionais que serão armazenados e manipulado em tempo de execução.
        * Web: A camada web representa o entrypoint do projeto, onde chegará as requisições e também as comunicações externas da API.
        * Job: Camada responsável pela configuração e implementação do Job. Foi utilizado neste desafio para verificar se a Sessão de Votação foi encerrada e postar o resultado em uma fila utilizando mensageria.

  * Motivo:
    * Seguindo este padrão de arquitetura, há uma facilidade de identificar os contextos e responsabilidade de cada camada na aplicação.
            Facilitando assim sua manutibilidade e entendimento.



### Built With

As tecnologias usadas neste projeto são:
* [SpringBoot](https://spring.io/projects/spring-boot)
* [Kafka](https://www.confluent.io/lp/apache-kafka/?utm_medium=sem&utm_source=google&utm_campaign=ch.sem_br.nonbrand_tp.prs_tgt.kafka-broad_mt.xct_rgn.latam_lng.eng_dv.all_con.kafka-general&utm_term=kafka&creative=&device=c&placement=&gad=1&gclid=Cj0KCQjw7PCjBhDwARIsANo7Cgnln-Gs5EvW6VOWMpBaTeNf-s3bBuQVFDhJKRUYS2WPihnKXY9rA1UaAvCLEALw_wcB)
* [Postgres](https://www.postgresql.org/)
* [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Lombock](https://projectlombok.org/)
* [Docker](https://www.docker.com/)



<!-- GETTING STARTED -->
## Começando

### Pré-Requisitos

* Verifique seu Maven (a versão do maven construída é 3.8.6)

  ```sh
  mvn -version
  ```

* Verifique a versão do Java (As versões utilizadas são: ````openjdk version "18.0.2.1" 2022-08-18
  OpenJDK Runtime Environment (build 18.0.2.1+1-1)
  OpenJDK 64-Bit Server VM (build 18.0.2.1+1-1, mixed mode, sharing)````

    ```sh
    java -version
    ```

### Installation

1. Clone o repositorio
   ```sh
   git clone https://github.com/DanFonseca/myVote.git
   ```
2. Up Docker Daemon e insira o comando abaixo no projeto raiz
   ```sh
   docker-compose up -d
   ```
   
3. Você pode importar as collections do PostMan na raiz desta  projeto (myVote.postman_collection.json)
4. Importe o projeto em sua IDE favorita e pronto =)

<!-- USAGE EXAMPLES -->
## Usage

* ### 1 Criar um Associado

```sh
   curl --location 'localhost:8080/associate' \
--header 'Content-Type: application/json' \
--data-raw '{
    "cpf": "11235",
    "name" : "Daniel Freitas",
    "email" : "daniel.freitas.ms1@gmail.com"
}'
   ```

* ### 2 Criar Uma Pauta
```json
   {
    "title": "Nescau x Toddy",
    "description": "Você Prefere Nescau ao inves do Toddy?"
  }
   ```

* ### 3 Retorna as pautas
```sh
   curl --location 'localhost:8080/vote-session' \
--data ''
   ```
* #### Resposta

```json
[
    {
        "id": 2,
        "title": "Nescau x Toddy",
        "description": "Você Prefere Nescau ao inves do Toddy?"
    }
]
   ```
    
* ### 4 Criar uma Sessão de Votação
 - Utilize o ID da pauta (Agenda) retornado anteriormente para cadastrar uma Sessão de Votação:
 - "minute_to_expire": representa por quantos minutos a sessão de votação estará disponivel.
```sh
   curl --location 'localhost:8080/vote-session' \
--header 'Content-Type: application/json' \
--data '{
     "agenda": {
        "id": 2
    },
    "minute_to_expire": 10
}'
   ```

* ##### 5   Retornar as sessões de votação

```sh
   curl --location 'localhost:8080/vote-session' \
--data ''
   ```
* #### Resposta

```json
[
  {
    "id": 2,
    "agenda": {
      "id": 2,
      "title": "Nescau x Toddy",
      "description": "Você Prefere Nescau ao inves do Toddy?"
    },
    "created_at": "2024-06-04T23:59:50.752+00:00",
    "minutes_to_expire": 1
  }
]
   ```

* ##### 6 Votar

```sh
curl --location 'localhost:8080/vote' \
--header 'Content-Type: application/json' \
--data '{
    "vote": "sim",
      "associate": {
          "cpf": "112135"
      },    

     "vote_session": {
        "id": 2
    }
}
   ```

* ##### 7 Resultado da votação

```sh
curl --location 'localhost:8080/vote/result?session-id=2' \
--data ''
   ```
* #### Resposta

```json
{
  "Totais de sim: ": 1,
  "Totais de não: ": 0,
  "Id da sessão de votação: ": 2
}
   ```

<!-- LICENSE -->
## License

Distributed under the MIT License. See `LICENSE` for more information.



<!-- CONTACT -->
## Contato

Daniel Fonseca  - daniel.freitas.ms1@gmail.com


<!-- MARKDOWN LINKS & IMAGES -->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[stars-shield]: https://img.shields.io/github/stars/othneildrew/Best-README-Template.svg?style=for-the-badge
[stars-url]: https://github.com/othneildrew/Best-README-Template/stargazers
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555
[linkedin-url]:https://www.linkedin.com/in/%F0%9F%91%A8%F0%9F%8F%BD%E2%80%8D%F0%9F%92%BB-daniel-freitas-28666a134/