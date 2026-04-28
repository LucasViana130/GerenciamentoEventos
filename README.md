# Eventos API

Sistema de Gerenciamento de Eventos desenvolvido com Spring Boot como projeto acadêmico da disciplina Java Advanced — FIAP.

## Objetivo

API REST para gerenciar eventos, organizadores, participantes e categorias. Permite cadastrar eventos, inscrever participantes, filtrar por cidade, data, categoria e verificar disponibilidade de vagas.

## Entidades

- **Event**: Evento principal, com nome, data, cidade, capacidade e relacionamentos com organizador, categoria e participantes.
- **Organizer**: Responsável pela organização do evento.
- **Participant**: Pessoa inscrita em um ou mais eventos.
- **Category**: Classificação do evento (ex: Tecnologia, Música, Esportes).

## Relacionamentos

- `Event` → `Organizer`: ManyToOne
- `Event` → `Category`: ManyToOne
- `Event` ↔ `Participant`: ManyToMany

## Tecnologias

- Java 17
- Spring Boot 4
- Spring Data JPA
- H2 Database (em memória)
- Lombok
- Bean Validation

## Como executar

```bash
./mvnw spring-boot:run
```

A API ficará disponível em `http://localhost:8080`.

O console H2 está disponível em `http://localhost:8080/h2-console`.

## Endpoints principais

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | /events | Lista todos os eventos (paginado) |
| GET | /events/{id} | Busca evento por ID |
| POST | /events | Cria evento |
| PUT | /events/{id} | Atualiza evento |
| DELETE | /events/{id} | Remove evento |
| GET | /events/search?name= | Busca por nome (paginado) |
| GET | /events/by-city?city= | Filtra por cidade |
| GET | /events/by-organizer/{id} | Filtra por organizador |
| GET | /events/by-category/{id} | Filtra por categoria |
| GET | /events/by-date-range?from=&to= | Filtra por período |
| GET | /events/available | Eventos com vagas |
| GET | /events/upcoming | Próximos eventos |
| POST | /events/{id}/participants/{id} | Inscreve participante |
| DELETE | /events/{id}/participants/{id} | Remove participante |
| GET | /organizers | Lista organizadores |
| GET | /organizers/by-city?city= | Filtra organizadores por cidade |
| GET | /participants | Lista participantes |
| GET | /participants/search?name= | Busca participante por nome |
| GET | /categories | Lista categorias |
