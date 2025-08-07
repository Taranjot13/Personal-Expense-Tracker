# Deployment Guide

## Prerequisites
- Java 17+
- Maven 3.6+
- Docker
- AWS CLI (for cloud deployment)
- Terraform (for infrastructure)

## Local Development

### 1. Using Maven
```bash
# Compile and run
mvn clean compile
mvn spring-boot:run

# Run tests
mvn test

# Package
mvn package
```

### 2. Using Docker
```bash
# Build and run with Docker Compose
docker-compose up -d

# View logs
docker-compose logs -f expense-tracker-api

# Stop services
docker-compose down
```

### 3. Using Scripts
```bash
# Linux/Mac
./scripts/run-local.sh

# Windows
scripts\run-local.bat
```

## AWS Cloud Deployment

### 1. Infrastructure Setup
```bash
cd infrastructure
terraform init
terraform plan
terraform apply
```

### 2. Application Deployment
```bash
# Set environment variables
export AWS_REGION=us-east-1
export ECR_REPO_NAME=expense-tracker

# Deploy
./scripts/deploy-aws.sh
```

### 3. Environment Variables
```bash
# Required for AWS
AWS_REGION=us-east-1
DYNAMODB_TABLE_NAME=expense-tracker-expenses-dev
SPRING_PROFILES_ACTIVE=production
```

## Monitoring

### Health Checks
- Application: `http://localhost:8080/api/v1/expenses/health`
- Actuator: `http://localhost:8080/actuator/health`

### Metrics
- Metrics: `http://localhost:8080/actuator/metrics`
- Prometheus: `http://localhost:8080/actuator/prometheus`

## Troubleshooting

### Common Issues
1. **Port already in use**: Change port in application.yml
2. **DynamoDB connection**: Check AWS credentials
3. **Build failures**: Ensure Java 17+ is installed

### Logs
```bash
# Docker logs
docker-compose logs expense-tracker-api

# Application logs (if running locally)
tail -f logs/expense-tracker.log
```
