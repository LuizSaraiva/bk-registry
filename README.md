<h1 align="center">
  Bank Project
</h1>

<br>

Project created to practice knowledge of JAVA, Spring Boot, etc.

These modules are part of a simulation of a banking ecosystem.

---

### Modules:
- Account Registry
- History Registry

## Tecnologias / Libraries

- Spring Boot
- Java 17
- Mysql 8
- RabbitMQ
- MapStruct
- Liquibase
- Lombok

---
## How to run:

- Clone the repository git:
```
git clone https://github.com/LuizSaraiva/bk-registry.git
```
- Run MYSQL:
<br>

In the [docs/docker/](docs/docker/docker-compose.yml) directory
```
docker-compose up
```
---

### Ecosystem Architecture

This diagram shows the project ecosystem.

![Ecosystem Architecture](docs/images/ecosystem-architecture.png)

### Module: Account Registry 

This diagram shows the account-registry flux.

![Account Flux](docs/images/account-flux.png)


### Module: History Registry

This diagram shows the history-registry flux.

![History Flux](docs/images/history-flux.png)


### Module: History Registry

This diagram shows the events flux.

![Events Flux](docs/images/flux-events.png)
