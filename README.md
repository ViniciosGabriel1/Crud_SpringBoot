# Crud_SpringBoot

# Spring Boot Product Management

Este é um projeto simples desenvolvido com Spring Boot para gerenciamento de produtos. Ele fornece uma API REST para criar, listar, obter, atualizar e excluir produtos.

## Tecnologias Utilizadas

- Spring Boot
- Spring Data JPA
- PostgreSQL (como banco de dados)
- Maven
- java 17

## Configuração do Banco de Dados

Este projeto está configurado para usar o PostgreSQL como banco de dados. Certifique-se de ter um servidor PostgreSQL em execução e atualize as configurações no arquivo `application.properties`.

```properties
# application.properties

spring.datasource.url=jdbc:postgresql://localhost:5432/seu_nome_de_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
