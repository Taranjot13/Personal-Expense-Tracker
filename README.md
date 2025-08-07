# Cloud-Native Personal Expense Tracker

[![CI/CD Pipeline](https://github.com/Taranjot13/Personal-Expense-Tracker/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/Taranjot13/Personal-Expense-Tracker/actions/workflows/ci-cd.yml)
[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![AWS](https://img.shields.io/badge/AWS-Cloud%20Native-orange.svg)](https://aws.amazon.com/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-blue.svg)](https://www.docker.com/)

A cloud-native expense tracking microservice built with Spring Boot, demonstrating modern software engineering practices and cloud deployment capabilities.

## Features

- **RESTful API** with comprehensive CRUD operations
- **AWS DynamoDB** integration for scalable cloud storage
- **Docker containerization** for consistent deployment
- **Infrastructure as Code** with Terraform
- **CI/CD pipeline** with GitHub Actions
- **Profile-based configuration** for multiple environments
- **Comprehensive testing** with JUnit

## Architecture

The application follows clean architecture principles with clear separation of concerns:

- **Controller Layer**: REST API endpoints with input validation
- **Service Layer**: Business logic and caching
- **Repository Layer**: Data access abstraction
- **Model Layer**: Domain entities and DTOs

## Technology Stack

| Component | Technology | Purpose |
|-----------|------------|---------|
| Backend | Java 17+, Spring Boot 3.2 | Core application framework |
| Database | AWS DynamoDB / In-Memory | NoSQL storage (cloud/local) |
| Containerization | Docker, Docker Compose | Application packaging |
| Infrastructure | Terraform | Infrastructure as Code |
| CI/CD | GitHub Actions | Automated testing & deployment |
| Testing | JUnit 5, Spring Boot Test | Unit and integration testing |

---

## 🛠️ **Technology Stack**

| **Category** | **Technology** | **Purpose** |
|--------------|----------------|-------------|
| **Backend** | Java 17+, Spring Boot 3.2 | Core application framework |
| **Database** | AWS DynamoDB / In-Memory | NoSQL storage (cloud/local) |
| **Containerization** | Docker, Docker Compose | Application packaging |
| **CI/CD** | GitHub Actions | Automated testing & deployment |
| **Infrastructure** | Terraform | Infrastructure as Code |
| **Testing** | JUnit 5, Spring Boot Test | Unit and integration testing |
| **IDE** | VS Code with Java Extensions | Development environment |

---

## 📋 **Essential Features**

### **REST API Endpoints**
- `POST /api/v1/expenses` - Create expense
- `GET /api/v1/expenses` - List expenses (paginated)
- `GET /api/v1/expenses/{id}` - Get specific expense
- `PUT /api/v1/expenses/{id}` - Update expense
- `DELETE /api/v1/expenses/{id}` - Delete expense
- `GET /api/v1/expenses/analytics` - Get analytics
- `GET /api/v1/expenses/health` - Health check

### **Cloud-Ready Features**
- **Profile-based Configuration** (local vs production)
- **Repository Pattern** for data abstraction
- **Global Exception Handling**
- **Input Validation** with Bean Validation
- **Comprehensive Testing**
- **Docker Containerization**
- **CI/CD Pipeline**

---

## 📁 **Project Structure**
```
src/main/java/com/expensetracker/
├── controller/     # REST API endpoints
├── service/        # Business logic
├── repository/     # Data access layer
├── model/          # Domain entities
├── dto/            # Data transfer objects
├── exception/      # Error handling
└── config/         # Configuration classes
```

---

## Getting Started

### Prerequisites
- Java 17 or higher
- VS Code with Java Extension Pack (recommended)
- Docker (for containerized deployment)

### Running Locally

**Method 1: VS Code (Recommended)**
1. Open project in VS Code
2. Press `F5` or go to Run → Start Debugging
3. Select "Run Expense Tracker"
4. Application starts at `http://localhost:8080`

**Method 2: Command Line**
```bash
java -cp "src/main/java;src/main/resources" -Dspring.profiles.active=local com.expensetracker.ExpenseTrackerApplication
```

**Method 3: Docker**
```bash
docker-compose up -d
```

### Testing the API

```bash
# Health Check
GET http://localhost:8080/api/v1/expenses/health

# Create Expense
POST http://localhost:8080/api/v1/expenses
Headers: User-Id: demo-user, Content-Type: application/json
Body: {"amount": 25.50, "category": "Food", "description": "Lunch"}

# Get Expenses
GET http://localhost:8080/api/v1/expenses
Headers: User-Id: demo-user
```

## API Documentation

### Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/v1/expenses/health` | Health check |
| `POST` | `/api/v1/expenses` | Create new expense |
| `GET` | `/api/v1/expenses` | List user expenses (paginated) |
| `GET` | `/api/v1/expenses/{id}` | Get specific expense |
| `PUT` | `/api/v1/expenses/{id}` | Update expense |
| `DELETE` | `/api/v1/expenses/{id}` | Delete expense |
| `GET` | `/api/v1/expenses/analytics` | Get expense analytics |

### Configuration Profiles

- **Local Profile**: Uses in-memory storage, no AWS dependencies
- **Production Profile**: Uses AWS DynamoDB for cloud deployment

## Deployment

### Local Development
```bash
# Run with local profile (default)
java -Dspring.profiles.active=local -jar target/expense-tracker.jar
```

### Cloud Deployment
```bash
# Deploy infrastructure
cd infrastructure
terraform init && terraform apply

# Build and deploy application
docker build -t expense-tracker .
# Push to container registry and deploy
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Submit a pull request

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 📋 **API Endpoints**

| **Method** | **Endpoint** | **Description** |
|------------|--------------|-----------------|
| `POST` | `/api/v1/expenses` | Create new expense |
| `GET` | `/api/v1/expenses` | Get user expenses (paginated) |
| `GET` | `/api/v1/expenses/{id}` | Get specific expense |
| `PUT` | `/api/v1/expenses/{id}` | Update expense |
| `DELETE` | `/api/v1/expenses/{id}` | Delete expense (soft delete) |
| `GET` | `/api/v1/expenses/analytics` | Get expense analytics |
| `GET` | `/api/v1/expenses/health` | Health check |

## About

This project demonstrates modern software engineering practices including:

- **Clean Architecture** with separation of concerns
- **Repository Pattern** for data abstraction
- **Profile-based Configuration** for environment management
- **Comprehensive Testing** with unit and integration tests
- **Global Exception Handling** for robust error management
- **Input Validation** with Bean Validation API
- **Containerization** with Docker for consistent deployment
- **Infrastructure as Code** with Terraform
- **CI/CD Automation** with GitHub Actions

## Development

The application uses Spring Boot profiles to support different environments:

- **Local Profile**: In-memory storage for development and testing
- **Production Profile**: AWS DynamoDB for cloud deployment

This design allows for rapid local development without requiring cloud resources while maintaining production-ready cloud integration.

## Architecture

```
src/main/java/com/expensetracker/
├── controller/     # REST API endpoints
├── service/        # Business logic layer
├── repository/     # Data access abstraction
├── model/          # Domain entities
├── dto/            # Data transfer objects
├── exception/      # Error handling
└── config/         # Configuration classes
```
