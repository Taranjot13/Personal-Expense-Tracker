# 📁 **Cloud-Native Expense Tracker - Project Structure**

```
Personal-Expense-Tracker/
├── 📁 .github/
│   └── 📁 workflows/
│       └── ci-cd.yml                  # GitHub Actions CI/CD pipeline
├── 📁 docs/
│   ├── API.md                         # API documentation
│   └── DEPLOYMENT.md                  # Deployment guide
├── 📁 infrastructure/
│   └── main.tf                        # Terraform infrastructure as code
├── 📁 scripts/
│   ├── deploy-aws.sh                  # AWS deployment script
│   ├── run-local.bat                  # Windows local run script
│   └── run-local.sh                   # Linux/Mac local run script
├── 📁 src/
│   ├── 📁 main/
│   │   ├── 📁 java/com/expensetracker/
│   │   │   ├── 📁 config/
│   │   │   │   └── AwsConfig.java     # AWS configuration
│   │   │   ├── 📁 controller/
│   │   │   │   └── ExpenseController.java  # REST API endpoints
│   │   │   ├── 📁 dto/
│   │   │   │   ├── ExpenseRequest.java     # Request DTOs
│   │   │   │   └── ExpenseResponse.java    # Response DTOs
│   │   │   ├── 📁 exception/
│   │   │   │   ├── ExpenseNotFoundException.java
│   │   │   │   └── GlobalExceptionHandler.java
│   │   │   ├── 📁 model/
│   │   │   │   └── Expense.java       # Domain model
│   │   │   ├── 📁 repository/
│   │   │   │   ├── ExpenseRepository.java     # Repository interface
│   │   │   │   └── DynamoDbExpenseRepository.java  # DynamoDB implementation
│   │   │   ├── 📁 service/
│   │   │   │   └── ExpenseService.java       # Business logic
│   │   │   └── ExpenseTrackerApplication.java  # Main Spring Boot class
│   │   └── 📁 resources/
│   │       └── application.yml        # Application configuration
│   └── 📁 test/
│       ├── 📁 java/com/expensetracker/
│       │   ├── 📁 controller/
│       │   │   └── ExpenseControllerTest.java
│       │   └── ExpenseTrackerApplicationTests.java
│       └── 📁 resources/
│           └── application-test.yml   # Test configuration
├── .env.example                       # Environment variables template
├── .gitignore                         # Git ignore rules
├── docker-compose.yml                 # Docker Compose configuration
├── Dockerfile                         # Container definition
├── LICENSE                           # MIT License
├── pom.xml                           # Maven build configuration
└── README.md                         # Comprehensive documentation
```

## 🎯 **Key Architecture Components:**

### **📱 Application Layer**
- **ExpenseTrackerApplication.java** - Spring Boot main class
- **ExpenseController.java** - REST API endpoints with validation
- **GlobalExceptionHandler.java** - Centralized error handling

### **🏗️ Business Layer**
- **ExpenseService.java** - Business logic and caching
- **Expense.java** - Domain model with DynamoDB annotations
- **DTOs** - Request/Response data transfer objects

### **💾 Data Layer**
- **ExpenseRepository.java** - Repository abstraction
- **DynamoDbExpenseRepository.java** - AWS DynamoDB implementation
- **AwsConfig.java** - AWS SDK configuration

### **🐳 DevOps & Deployment**
- **Dockerfile** - Multi-stage container build
- **docker-compose.yml** - Local development environment
- **ci-cd.yml** - GitHub Actions pipeline
- **main.tf** - Terraform infrastructure

### **📋 Documentation & Scripts**
- **README.md** - Comprehensive project documentation
- **API.md** - API endpoint documentation
- **DEPLOYMENT.md** - Deployment instructions
- **Scripts** - Automation for local development and AWS deployment

---

## 🔥 **Interview Highlights:**

### **Cloud-Native Features:**
✅ **Microservices Architecture** with Spring Boot  
✅ **AWS DynamoDB** for scalable NoSQL storage  
✅ **Docker Containerization** for consistent deployments  
✅ **Infrastructure as Code** with Terraform  
✅ **CI/CD Pipeline** with GitHub Actions  
✅ **Monitoring & Observability** with CloudWatch  

### **Software Engineering Practices:**
✅ **Clean Architecture** with separation of concerns  
✅ **Comprehensive Testing** with JUnit  
✅ **API Documentation** with clear examples  
✅ **Error Handling** with global exception handling  
✅ **Input Validation** with Bean Validation  
✅ **Caching Strategy** for performance optimization  

This structure demonstrates **enterprise-level organization** and **cloud engineering best practices** perfect for your interviews! 🚀
