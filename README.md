# Documentação math-api

- Está API futuramente será chamada em outro projeto, que rodará localmente.
- Este outro projeto será um aplicativo mobile Flutter, o qual o mesmo irá se comunidar com está API, ela por sua vez se comunicará com um banco de dados local ou em memória e vai responder a essas comunicações depende do perfil que estiver utilizando

## Lista de tasks

- [ ] Desenvolver a API
  - [x] Configurar projeto Spring Boot
  - [x] Configurar JPA
  - [x] Configurar H2
  - [x] Desenvolver exception para ResourceNotFound
  - [x] Desenvolver handler para ResourceNotFound
  - [ ] Implementar sistemática do Spring Security com JWT
  - [ ] Configurar perfis de desenvolvimento
- [ ] Desenvolver o App
- [ ] Estrutura banco de dados em memória (H2)
- [ ] Estruturar banco de dados local (PostgreSQL)

## Endpoint desenvolvidos

- Url base: http://localhost:8080

| Endpoint         | Descrição                                            |
| ---------------- | ---------------------------------------------------- |
| `/usuarios`      | Retorna todos os usuário que tem no banco            |
| `/usuarios/{id}` | Retorna um usuário ou uma exception ResourceNotFound |
