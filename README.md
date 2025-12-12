# Spring Boot Calculator Application

A web-based calculator application built with Spring Boot that performs arithmetic operations and maintains a history of calculations in a MySQL database.

## Features

- Perform basic arithmetic operations (+, -, *, /)
- View calculation history with pagination
- REST API endpoints for mobile/integration
- Modern UI with Bootstrap
- Input validation for security
- Docker support for containerization
- CI/CD pipeline with Jenkins

## Prerequisites

- Java 17
- Maven 3.6+
- MySQL 8.0
- Docker (optional)

## Quick Start

### Option 1: Using Docker Compose (Recommended)

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd calculator-app
   ```

2. Run with Docker Compose:
   ```bash
   docker-compose up -d
   ```

3. Access the application at http://localhost:8080

### Option 2: Manual Setup

1. Create MySQL database:
   ```sql
   CREATE DATABASE calculatordb;
   ```

2. Update database credentials in `src/main/resources/application.properties`

3. Build and run:
   ```bash
   mvn clean package
   java -jar target/calculator-app-0.0.1-SNAPSHOT.jar
   ```

## API Endpoints

- `POST /api/calculate` - Calculate an expression
  ```json
  {
    "expression": "5+10/2"
  }
  ```

- `GET /api/history` - Get calculation history with pagination
  ```
  /api/history?page=0&size=10
  ```

## Project Structure

```
calculator-app/
├── src/main/java/com/example/calculator/
│   ├── controller/     # REST and Web controllers
│   ├── model/         # JPA entities
│   ├── repository/    # Data access layer
│   └── service/       # Business logic
├── src/main/resources/
│   ├── templates/     # Thymeleaf templates
│   └── application.properties
└── Dockerfile         # Container configuration
```

## Running Tests

```bash
mvn test
```

## Building for Production

```bash
mvn clean package -Pprod
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License.
```

## How to Run This Application

1. **Prerequisites**:
   - Install Java 17
   - Install Maven 3.6+
   - Install MySQL 8.0

2. **Database Setup**:
   ```sql
   CREATE DATABASE calculatordb;
   ```

3. **Update Configuration**:
   Edit `src/main/resources/application.properties` with your MySQL credentials

4. **Build and Run**:
   ```bash
   mvn clean package
   java -jar target/calculator-app-0.0.1-SNAPSHOT.jar
   ```

5. **Access the Application**:
   Open your browser and go to http://localhost:8080

6. **For Docker Deployment**:
   ```bash
   docker-compose up -d
   ```