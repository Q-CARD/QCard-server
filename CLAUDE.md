# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

QCard-server is a Spring Boot application providing an interview question management system with user authentication, question/answer management, and heart (like) functionality. **Note: This project was successfully migrated from a multi-module to a single root-level project for simplicity.**

## Build System & Commands

### Build and Run
- **Build project**: `./gradlew clean build`
- **Run locally**: `chmod +x scripts/run-local.sh && ./scripts/run-local.sh` (builds and starts with Docker Compose)
- **Run tests**: `./gradlew test`
- **Start with Docker**: `docker compose up` (requires Redis)

### Development
- **Local development**: Use `scripts/run-local.sh` which handles build and Docker Compose setup
- **Stop local**: `scripts/stop-local.sh`

## Single Root Project Architecture

The project uses a layered architecture in a single root project:

### Project Structure
```
QCard-server/
├── src/main/java/com/qcard/
│   ├── QCardApplication.java    # Main application entry point
│   ├── api/                     # REST controllers and application services
│   │   ├── account/            # Account management
│   │   ├── question/           # Question management  
│   │   ├── answer/             # Answer management
│   │   └── heart/              # Heart/like functionality
│   ├── domain/                 # Domain entities, repositories, and services
│   │   ├── account/
│   │   ├── question/
│   │   ├── answer/
│   │   ├── heart/
│   │   └── interview/
│   ├── security/               # Authentication and authorization
│   │   ├── config/            # Security configurations
│   │   ├── jwt/               # JWT handling
│   │   ├── filter/            # Security filters
│   │   ├── redis/             # Redis session management
│   │   ├── resolver/          # Argument resolvers
│   │   └── service/           # Security services
│   ├── infrastructure/        # Infrastructure concerns
│   │   ├── kafka/             # Message queue integration
│   │   └── QueryDslConfig.java
│   └── common/                # Shared utilities
│       ├── entity/            # BaseTimeEntity
│       ├── enums/             # Category, QuestionType, etc.
│       ├── exception/         # Error handling
│       ├── config/            # Swagger configuration
│       └── dto/               # Shared DTOs
├── src/main/generated/         # QueryDSL Q-classes
├── src/main/resources/
│   └── application.yml        # Application configuration
├── build.gradle              # Single unified build configuration
└── settings.gradle           # Simple project settings
```

## Key Technologies

- **Spring Boot 3.1.2** with Java 17
- **JPA/Hibernate** with QueryDSL for database operations
- **JWT** for authentication
- **Redis** for caching/session management
- **Kafka** for message queuing
- **Swagger/OpenAPI** for API documentation
- **Docker** and Docker Compose for containerization
- **Lombok** for reducing boilerplate code

## Application Profiles

The application uses Spring profiles:
- **devs**: Development (dev + common)
- **prods**: Production (prod + common) - default active
- Additional profiles: admin, kafka

## Database & Entities

All entities extend `BaseTimeEntity` for automatic timestamp management. Key domain models:
- **Account**: User management
- **Question**: Interview questions with categories and types
- **Answer**: User responses to questions
- **Heart**: Like/favorite functionality
- **Interview**: Interview session management

## Development Notes

- QueryDSL Q-classes are generated in the `src/main/generated` directory
- Use the existing service layer patterns when adding new features
- Follow the DTO pattern for API requests/responses
- All entities should extend BaseTimeEntity for audit trails
- Use builder pattern for entity construction (Lombok @Builder)
- Controllers should only depend on application services, not domain services directly
- Domain services handle business logic, application services handle orchestration