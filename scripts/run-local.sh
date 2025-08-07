#!/bin/bash
# Build and run the application locally

echo "🚀 Building Expense Tracker Application..."

# Clean and compile
echo "📦 Cleaning and compiling..."
mvn clean compile

# Run tests
echo "🧪 Running tests..."
mvn test

# Package application
echo "📦 Packaging application..."
mvn package -DskipTests

# Run with Spring Boot
echo "🌟 Starting application..."
echo "Application will be available at: http://localhost:8080"
echo "API Documentation: http://localhost:8080/swagger-ui.html"
echo "Health Check: http://localhost:8080/api/v1/expenses/health"

mvn spring-boot:run
