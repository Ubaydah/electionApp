# Election App

An election management REST API built with Java 25, Spring Boot 4.1.0-M3, MongoDB, Maven, and test-driven development.

## Features

- Register voters
- Register candidates
- Cast one vote per voter
- View election results
- Prevent duplicate voter registration
- Prevent double voting

## Technologies

- Java 25
- Spring Boot 4.1.0-M3
- Spring Web
- Spring Data MongoDB
- Maven
- Lombok
- JUnit 5
- Mockito
- MockMvc

## Project Structure

```text
src/main/java/com/example/electionapp/
├── ElectionAppApplication.java
├── controllers/
├── services/
├── data/
│   ├── models/
│   └── repositories/
├── dtos/
│   ├── requests/
│   └── responses/
├── exceptions/
└── utils/
```

## TDD Flow Used

1. Write service tests first.
2. Implement service logic until tests pass.
3. Write controller tests.
4. Implement controller endpoints.
5. Run all tests.

## Run Instructions

### 1. Start MongoDB
Make sure MongoDB is running locally on:

```text
mongodb://localhost:27017/election_app_db
```

### 2. Run tests

```bash
mvn test
```

### 3. Start the app

```bash
mvn spring-boot:run
```

## API Endpoints

### Register voter
`POST /api/voters`

```json
{
  "fullName": "John Doe",
  "email": "john@gmail.com",
  "voterCardNumber": "VC123"
}
```

### Register candidate
`POST /api/candidates`

```json
{
  "name": "Alice Johnson",
  "party": "ABC Party"
}
```

### Cast vote
`POST /api/votes`

```json
{
  "voterId": "v1",
  "candidateId": "c1"
}
```

### View results
`GET /api/candidates/results`


