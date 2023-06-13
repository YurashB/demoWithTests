# Spring REST demo app 

Spring Rest demo backend application for employee managment with different technologies 

## What's inside 
This project is based on the Spring Boot and uses these technologies:
- Framework: Spring 5 (SpringBoot 2, Spring Test, Spring Data)
- DB: PostgreSQL, Flyway
- Other: MapStruct, OpenApi, Lombok, Docker

## Getting Started
To start service you need JDK 11 and PostgreSQL installed. Than configure the foloving

### PostgreSQL configuration
Configire PostgreSQL with your settings in application.yml:
```
  datasource:
    driver-class-name: org.postgresql.Driver
    url: jdbc:postgresql://localhost:5432/[db name]
    schema: [schema name of db]
    username: [username of postgresql]
    password: [password to postgresql]
```

### Flyway configuration
Configire Flyway with your settings in application.yml:
```
  flyway:
    driverClassName: org.postgresql.Driver
    user: [username of postgresql]
    password: [password to postgresql]
    url: jdbc:postgresql://localhost:5432/[db name]
    schemas: [schema name of db]
    enabled: true
    baseline-on-migrate: true
```

### Running application 
After db configuration
1. run the comand ```mvn clean install```
2. Start your project with your IDE or ``` mvn spring-boot:run ```
3. The application can be accessed via http://localhost:8087/api/

## Running with Docker
To run the application using Docker, follow these steps:
```
docker-compose up
```
Than the application can be accessed via http://localhost:8087/api/ .And if you want to stop container run
```
docker-compose down
```

## Usage
With OpenApi documentation you can see all endpoints, their usage and description. You can access documentation via http://localhost:8087/swagger-ui/index.html#/



