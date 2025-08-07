@echo off
REM Build and run the application locally on Windows

echo 🚀 Building Expense Tracker Application...

REM Clean and compile
echo 📦 Cleaning and compiling...
call mvn clean compile

REM Run tests
echo 🧪 Running tests...
call mvn test

REM Package application
echo 📦 Packaging application...
call mvn package -DskipTests

REM Run with Spring Boot
echo 🌟 Starting application...
echo Application will be available at: http://localhost:8080
echo API Documentation: http://localhost:8080/swagger-ui.html
echo Health Check: http://localhost:8080/api/v1/expenses/health

call mvn spring-boot:run
