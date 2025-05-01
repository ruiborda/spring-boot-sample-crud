# Spring Boot Sample CRUD Application - Software Architecture CODIGO By TECSUP

A simple Product Management System built with Spring Boot that demonstrates CRUD operations through REST API and web interface.

## Technologies

- Java 21
- Spring Boot 3.4.5
- Spring Data JPA
- Thymeleaf
- PostgreSQL
- Docker
- TailwindCSS

## Project Structure

- `controller`: REST API controllers and MVC controllers
- `service`: Business logic implementation
- `repository`: Data access interfaces
- `model`: Domain entities
- `dto`: Data Transfer Objects

## Configuration

### Database Configuration

PostgreSQL database configuration in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/database
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

### Docker Configuration

The application is containerized using Docker with the following setup:

1. **PostgreSQL Database**: 
   - Image: postgres:16
   - Credentials: user/password
   - Database Name: database
   - Port: 5432

2. **Spring Boot Application**:
   - Built from source using Maven
   - Exposed on port 8080

## Running the Application

### Using Docker Compose

```bash
docker-compose up -d
```

This will start both the PostgreSQL database and the Spring Boot application.

### Manual Setup

1. Start PostgreSQL:
```bash
docker-compose up -d db
```

2. Run Spring Boot app:
```bash
./mvnw spring-boot:run
```

## API Documentation

The REST API documentation is available via Swagger/OpenAPI:
- API Docs: http://localhost:8080/api-docs
- Swagger UI: http://localhost:8080/swagger-ui.html

## Web Interface

- Admin Dashboard: http://localhost:8080/admin
- Product Management: http://localhost:8080/admin/products
