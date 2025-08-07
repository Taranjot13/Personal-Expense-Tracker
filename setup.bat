@echo off
REM Quick setup script for Windows

echo 🚀 Setting up Expense Tracker Development Environment...

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo ✅ Maven is already installed
    mvn -version
) else (
    echo ❌ Maven not found. Please install Maven:
    echo 1. Download from: https://maven.apache.org/download.cgi
    echo 2. Extract to C:\Program Files\Maven
    echo 3. Add C:\Program Files\Maven\bin to your PATH
    echo 4. Restart your command prompt
    pause
    exit /b 1
)

REM Check if Docker is installed
where docker >nul 2>nul
if %ERRORLEVEL% EQU 0 (
    echo ✅ Docker is available
    docker --version
) else (
    echo ⚠️ Docker not found. Install Docker Desktop for containerization.
)

echo.
echo 🎯 Next steps:
echo 1. Run: mvn clean compile
echo 2. Run: mvn spring-boot:run
echo 3. Access: http://localhost:8080/api/v1/expenses/health
echo.
pause
