# MelodyGuard API

## Project Overview
A secure REST API for managing a music catalog with JWT authentication and role-based access control, developed using Spring Boot.

## Technologies
- Spring Boot
- Spring Security
- JWT Authentication
- MongoDB (NoSQL database)
- Jenkins (CI/CD)
- Docker

## Key Features
- Album Management
- Song Management
- User Authentication
- Role-Based Access Control
  - USER role: Read operations
  - ADMIN role: Full CRUD operations

## Authentication
- Stateless JWT authentication
- Secure password encryption
- Role-based access to endpoints

## Setup and Installation
1. Clone the repository
2. Configure application properties
3. Build with Maven
4. Run Docker container

## Endpoints
- `/api/auth/login`: Authentication
- `/api/auth/register`: User registration
- `/api/user/albums`: User album operations
- `/api/admin/albums`: Admin album operations

## Development Environment
- Java 8
- Maven
- MongoDB
- Docker
- Jenkins

## Testing
- Unit Tests: JUnit
- Integration Tests: Mockito
- API Testing: Postman

## Contributing
Please read CONTRIBUTING.md for details on our code of conduct and the process for submitting pull requests.

---
