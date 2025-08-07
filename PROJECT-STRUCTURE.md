# Cloud-Native Expense Tracker - Architecture

## Project Structure

```
Personal-Expense-Tracker/
├── .github/
│   └── workflows/
│       └── ci-cd.yml                  # GitHub Actions CI/CD pipeline
├── .vscode/
│   └── launch.json                    # VS Code run configuration
├── docs/
│   ├── API.md                         # API documentation
│   └── DEPLOYMENT.md                  # Deployment guide
├── infrastructure/
│   └── main.tf                        # Terraform infrastructure as code
├── scripts/
│   └── run-local.bat                  # Local development script
├── src/
│   ├── main/
│   │   ├── java/com/expensetracker/
│   │   │   ├── config/
│   │   │   │   └── AwsConfig.java     # AWS configuration
│   │   │   ├── controller/
│   │   │   │   └── ExpenseController.java  # REST API endpoints
│   │   │   ├── dto/
│   │   │   │   ├── ExpenseRequest.java     # Request DTOs
│   │   │   │   └── ExpenseResponse.java    # Response DTOs
│   │   │   ├── exception/
│   │   │   │   ├── ExpenseNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── model/
│   │   │   │   └── Expense.java       # Domain model
│   │   │   ├── repository/
│   │   │   │   ├── ExpenseRepository.java     # Repository interface
│   │   │   │   ├── DynamoDbExpenseRepository.java  # DynamoDB implementation
│   │   │   │   └── InMemoryExpenseRepository.java  # Local implementation
│   │   │   ├── service/
│   │   │   │   └── ExpenseService.java       # Business logic
│   │   │   └── ExpenseTrackerApplication.java  # Main Spring Boot class
│   │   └── resources/
│   │       ├── application.yml        # Application configuration
│   │       └── application-local.yml  # Local development config
│   └── test/
│       ├── java/com/expensetracker/
│       │   ├── controller/
│       │   │   └── ExpenseControllerTest.java
│       │   └── ExpenseTrackerApplicationTests.java
│       └── resources/
│           └── application-test.yml   # Test configuration
├── .gitignore                         # Git ignore rules
├── docker-compose.yml                 # Docker Compose configuration
├── Dockerfile                         # Container definition
├── LICENSE                           # MIT License
├── pom.xml                           # Maven build configuration
├── PROJECT-STRUCTURE.md              # This file
└── README.md                         # Project documentation
```

## Architecture Components

### Application Layer
| Component | Purpose | Technology |
|-----------|---------|------------|
| `ExpenseTrackerApplication.java` | Application entry point | Spring Boot |
| `ExpenseController.java` | REST API endpoints | Spring Web MVC |
| `GlobalExceptionHandler.java` | Centralized error handling | Spring AOP |

### Business Layer
| Component | Purpose | Technology |
|-----------|---------|------------|
| `ExpenseService.java` | Business logic implementation | Spring Service |
| `Expense.java` | Domain model | JPA/DynamoDB annotations |
| `ExpenseRequest/Response.java` | Data transfer objects | Bean Validation |

### Data Layer
| Component | Purpose | Technology |
|-----------|---------|------------|
| `ExpenseRepository.java` | Data access abstraction | Repository pattern |
| `DynamoDbExpenseRepository.java` | AWS cloud implementation | AWS SDK v2 |
| `InMemoryExpenseRepository.java` | Local development implementation | Concurrent collections |
| `AwsConfig.java` | AWS SDK configuration | Spring Configuration |

### Infrastructure & DevOps
| Component | Purpose | Technology |
|-----------|---------|------------|
| `Dockerfile` | Container definition | Docker multi-stage build |
| `docker-compose.yml` | Local development environment | Docker Compose |
| `ci-cd.yml` | Automated pipeline | GitHub Actions |
| `main.tf` | Infrastructure as code | Terraform |

## Design Patterns

- **Repository Pattern**: Abstracts data access logic
- **Dependency Injection**: Manages component dependencies
- **Profile-based Configuration**: Environment-specific settings
- **DTO Pattern**: Separates internal models from API contracts
- **Global Exception Handling**: Centralized error management

## Development Workflow

1. **Local Development**: Uses in-memory repository with local profile
2. **Testing**: Comprehensive unit and integration tests
3. **Containerization**: Docker for consistent deployment
4. **CI/CD**: Automated testing and deployment pipeline
5. **Infrastructure**: Terraform for cloud resource management

## Configuration Management

- **Local Profile**: In-memory storage, no external dependencies
- **Production Profile**: AWS DynamoDB, CloudWatch monitoring
- **Test Profile**: Test-specific configurations and mocks
