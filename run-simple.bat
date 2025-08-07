@echo off
REM Simple Java compilation and run script

echo 🚀 Building Expense Tracker with Java...

REM Create output directory
if not exist "target\classes" mkdir target\classes

echo 📦 Compiling Java files...

REM Compile all Java files (basic approach)
javac -cp "." -d target\classes ^
  src\main\java\com\expensetracker\*.java ^
  src\main\java\com\expensetracker\model\*.java ^
  src\main\java\com\expensetracker\controller\*.java ^
  src\main\java\com\expensetracker\service\*.java ^
  src\main\java\com\expensetracker\repository\*.java ^
  src\main\java\com\expensetracker\dto\*.java ^
  src\main\java\com\expensetracker\config\*.java ^
  src\main\java\com\expensetracker\exception\*.java

if %ERRORLEVEL% neq 0 (
    echo ❌ Compilation failed. You need Maven and Spring Boot dependencies.
    echo.
    echo 🛠️ Alternative: Install Maven from https://maven.apache.org/download.cgi
    echo    Then run: mvn spring-boot:run
    echo.
    echo 🐳 Or use Docker: docker-compose up
    pause
    exit /b 1
)

echo ✅ Compilation successful!
echo 🌟 To run the app, install Maven and use: mvn spring-boot:run
pause
