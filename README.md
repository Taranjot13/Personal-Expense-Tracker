# 🚀 Cloud-Native Personal Expense Tracker

[![CI/CD Pipeline](https://github.com/Taranjot13/Personal-Expense-Tracker/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/Taranjot13/Personal-Expense-Tracker/actions/workflows/ci-cd.yml)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![AWS](https://img.shields.io/badge/AWS-Cloud%20Native-orange.svg)](https://aws.amazon.com/)
[![Docker](https://img.shields.io/badge/Docker-Containerized-blue.svg)](https://www.docker.com/)

A **enterprise-grade, cloud-native expense tracking system** built with modern technologies and best practices. This project demonstrates full-stack development skills with cloud engineering focus, perfect for showcasing in interviews for cloud engineer positions.

## 🎯 **For Interviewers & Recruiters**

This project showcases **real-world cloud engineering skills** including:
- ✅ **Microservices Architecture** with Spring Boot
- ✅ **AWS Cloud Integration** (DynamoDB, CloudWatch, ECS)
- ✅ **Containerization** with Docker & Docker Compose
- ✅ **CI/CD Pipelines** with GitHub Actions
- ✅ **Infrastructure as Code** (IaC) practices
- ✅ **Monitoring & Observability** 
- ✅ **RESTful API Design** with proper validation
- ✅ **Cloud Security** best practices

---

## 🏗️ **Architecture Overview**

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Frontend      │    │   REST API      │    │   AWS Cloud     │
│   (Future)      │◄──►│  Spring Boot    │◄──►│   DynamoDB      │
│                 │    │   Application   │    │   CloudWatch    │
└─────────────────┘    └─────────────────┘    └─────────────────┘
                                ▲
                                │
                    ┌─────────────────┐
                    │   Docker        │
                    │  Containers     │
                    └─────────────────┘
```

## 🚀 **Key Features**

### **Cloud-Native Capabilities**
- **RESTful API** with comprehensive endpoints
- **AWS DynamoDB** integration for scalable NoSQL storage
- **Docker containerization** for consistent deployments
- **GitHub Actions CI/CD** for automated testing and deployment
- **CloudWatch monitoring** and metrics collection
- **Caching layer** with Caffeine for performance optimization

### **Business Features**
- **CRUD Operations** for expense management
- **Real-time Analytics** and reporting
- **Category-based expense tracking**
- **Date range filtering** and search capabilities
- **Multi-user support** with user isolation
- **Soft delete** functionality for data integrity

### **Technical Excellence**
- **Input validation** and error handling
- **Comprehensive logging** and monitoring
- **Health checks** and observability
- **Test coverage** with JUnit
- **API documentation** with OpenAPI/Swagger

---

## 🛠️ **Technology Stack**

| **Category** | **Technology** | **Purpose** |
|--------------|----------------|-------------|
| **Backend** | Java 17, Spring Boot 3.2 | Core application framework |
| **Database** | AWS DynamoDB | NoSQL cloud database |
| **Caching** | Caffeine | Performance optimization |
| **Containerization** | Docker, Docker Compose | Application packaging |
| **CI/CD** | GitHub Actions | Automated testing & deployment |
| **Monitoring** | AWS CloudWatch, Micrometer | Observability and metrics |
| **Testing** | JUnit 5, Spring Boot Test | Unit and integration testing |
| **Documentation** | OpenAPI 3.0 | API documentation |

---

## 🚀 **Quick Start**

### **Prerequisites**
- Java 17+
- Docker & Docker Compose
- AWS CLI (for cloud deployment)
- Git

### **1. Clone Repository**
```bash
git clone https://github.com/Taranjot13/Personal-Expense-Tracker.git
cd Personal-Expense-Tracker
```

### **2. Local Development**
```bash
# Start with Docker Compose (includes local DynamoDB)
docker-compose up -d

# Or run locally with Maven
mvn spring-boot:run
```

### **3. Test the API**
```bash
# Health check
curl http://localhost:8080/api/v1/expenses/health

# Create expense
curl -X POST http://localhost:8080/api/v1/expenses \
  -H "Content-Type: application/json" \
  -H "User-Id: user123" \
  -d '{
    "amount": 25.50,
    "category": "Food",
    "description": "Lunch at restaurant"
  }'

# Get expenses
curl http://localhost:8080/api/v1/expenses \
  -H "User-Id: user123"
```

### **4. Access Documentation**
- **API Docs**: http://localhost:8080/swagger-ui.html
- **Health Endpoint**: http://localhost:8080/actuator/health
- **Metrics**: http://localhost:8080/actuator/metrics

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

---

## ☁️ **Cloud Deployment**

### **AWS Infrastructure**
```bash
# Deploy DynamoDB table
aws dynamodb create-table \
  --table-name expenses \
  --attribute-definitions \
    AttributeName=userId,AttributeType=S \
    AttributeName=expenseId,AttributeType=S \
  --key-schema \
    AttributeName=userId,KeyType=HASH \
    AttributeName=expenseId,KeyType=RANGE \
  --billing-mode PAY_PER_REQUEST

# Deploy to ECS (after setting up ECR repository)
docker build -t expense-tracker .
docker tag expense-tracker:latest $AWS_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/expense-tracker:latest
docker push $AWS_ACCOUNT_ID.dkr.ecr.us-east-1.amazonaws.com/expense-tracker:latest
```

### **Environment Variables**
```bash
# Required for AWS deployment
AWS_REGION=us-east-1
DYNAMODB_TABLE_NAME=expenses
SPRING_PROFILES_ACTIVE=production
```

---

## 📊 **Monitoring & Observability**

The application includes comprehensive monitoring:

- **Health Checks**: `/actuator/health`
- **Metrics**: `/actuator/metrics` 
- **CloudWatch Integration**: Automatic metrics export
- **Custom Dashboards**: Business metrics tracking
- **Alerting**: Configurable alerts for system health

---

## 🧪 **Testing**

```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report

# Integration tests
mvn verify -Pintegration-tests
```

---

## 📈 **Performance Features**

- **Caching**: Caffeine cache for frequently accessed data
- **Pagination**: Efficient data loading for large datasets  
- **Connection Pooling**: Optimized database connections
- **Async Processing**: Non-blocking operations where applicable
- **Resource Optimization**: JVM tuning for container environments

---

## 🎓 **Learning Outcomes**

This project demonstrates proficiency in:

1. **Cloud Architecture Design**
2. **Microservices Development** 
3. **AWS Services Integration**
4. **DevOps & CI/CD Practices**
5. **Containerization & Orchestration**
6. **API Design & Development**
7. **Database Design & Optimization**
8. **Monitoring & Observability**
9. **Security Best Practices**
10. **Software Engineering Principles**

---

## 🤝 **Contributing**

This is a learning project, but improvements are welcome!

1. Fork the repository
2. Create a feature branch
3. Make your changes  
4. Add tests
5. Submit a pull request

---

## 📄 **License**

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## 👨‍💻 **About the Developer**

**Taranjot Singh** - Aspiring Cloud Engineer  
📧 Email: [your-email@example.com]  
💼 LinkedIn: [your-linkedin-profile]  
🌐 Portfolio: [your-portfolio-website]

*"Built with passion for cloud technologies and modern software engineering practices."*

---

## 🔮 **Future Enhancements**

- [ ] **Frontend Dashboard** (React/Angular)
- [ ] **Machine Learning** expense categorization
- [ ] **Real-time Notifications** (SNS/SQS)
- [ ] **Multi-currency Support**
- [ ] **Budget Planning & Alerts**
- [ ] **Data Export/Import** features
- [ ] **Mobile App** (React Native/Flutter)
- [ ] **Advanced Analytics** & Reporting

---

⭐ **If this project helped you learn cloud engineering concepts, please give it a star!**
