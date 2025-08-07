# Quick Start Guide

## Prerequisites
- Java 17+ (you have Java 24 ✅)
- VS Code with Java Extension Pack
- Git

## Local Development

### 1. VS Code Method (Easiest)
```bash
# Open in VS Code
code .

# Press F5 or Run → Start Debugging
# Select "Run Expense Tracker"
# Application starts at http://localhost:8080
```

### 2. Command Line Method
```bash
# Compile and run
java -cp "src/main/java;src/main/resources" -Dspring.profiles.active=local com.expensetracker.ExpenseTrackerApplication
```

### 3. Script Method
```bash
# Windows
scripts\run-local.bat
```

## Testing the Application

### Health Check
```
GET http://localhost:8080/api/v1/expenses/health
```

### Create Expense
```bash
# Using curl or Postman
POST http://localhost:8080/api/v1/expenses
Headers: User-Id: student123, Content-Type: application/json
Body: {
  "amount": 25.50,
  "category": "Food", 
  "description": "Lunch"
}
```

### Get Expenses
```bash
GET http://localhost:8080/api/v1/expenses
Headers: User-Id: student123
```

## Configuration Profiles

### Local Profile (Default)
- Uses in-memory storage
- No AWS dependencies
- Perfect for development and demos

### Production Profile
- Uses AWS DynamoDB
- Requires AWS credentials
- For cloud deployment

## Cloud Deployment (Advanced)

### Using Docker
```bash
docker-compose up -d
```

### Using Terraform
```bash
cd infrastructure
terraform init
terraform apply
```
