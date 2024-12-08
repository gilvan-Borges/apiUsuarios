# API de Usuários

Este projeto é uma API RESTful para controle de usuários, desenvolvida como parte do treinamento Java WebDeveloper da COTI Informática.

## Tecnologias Utilizadas

- Java 11
- Spring Boot 3.3.4
- Spring Data JPA
- Hibernate
- Maven
- Lombok
- JWT (JSON Web Token)
- Swagger OpenAPI
- Docker


## Funcionalidades

- **Criação de Usuário:** Permite criar um novo usuário no sistema.
- **Autenticação de Usuário:** Permite autenticar um usuário e gerar um token JWT.
- **Listagem de Usuários:** Permite listar todos os usuários cadastrados.
- **Atualização de Usuário:** Permite atualizar as informações de um usuário existente.
- **Exclusão de Usuário:** Permite excluir um usuário do sistema.

## Endpoints

### Criar Usuário

- **URL:** `/api/usuarios/criar`
- **Método:** `POST`
- **Request Body:**
- 
 {
    "nome": "string",
    "email": "string",
    "senha": "string"
  }
  - Response:

{
  "id": "UUID",
  "nome": "string",
  "email": "string",
  "perfil": "string",
  "mensagem": "Usuário cadastrado com sucesso."
}

## Autenticar Usuário
- URL: /api/usuarios/autenticar
- Método: POST
- Request Body:
{
  "email": "string",
  "senha": "string"
}

- Response:
{
  "id": "UUID",
  "nome": "string",
  "email": "string",
  "token": "string",
  "dataHoraAcesso": "date",
  "dataHoraExpiracao": "date",
  "perfil": "string",
  "mensagem": "Usuário autenticado com sucesso."
}

## Configuração do Ambiente
### Pré-requisitos
- Java 11
- Maven
- Docker (opcional)

## Executando o Projeto
1. Clone o repositório:
git clone https://github.com/seu-usuario/apiUsuarios.git
cd apiUsuarios

2. Compile e execute o projeto:
./mvnw spring-boot:run

3. Acesse a documentação da API:
-- Swagger UI: http://localhost:8080/swagger-ui.html
-- OpenAPI: http://localhost:8080/v3/api-docs

## Contribuição
Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e pull requests.
