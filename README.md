# 🛠️ Backend - Neuro-Learn

This repository contains the backend codebase for the **Neuro Learn** project, built with **Spring Boot** and enhanced with **LangChain** for AI-powered features.

---

## 📚 Tech Stack

- **Backend Framework:** Spring Boot
- **AI Integration:** LangChain
- **Database:** MySQL
- **Build Tool:** Maven
- **Containerization:** Docker

---

## ✨ Features

- 🔐 Secure Authentication and Authorization
- 📇 RESTful APIs for Contact Management (CRUD operations)
- 🤖 AI Assistance (e.g., Smart Suggestions, Autocomplete) via LangChain
- 🛢️ MySQL Database Integration
- 📜 Swagger API Documentation
- 📦 Docker support for containerized deployment

---

## ⚙️ Setup Instructions

### Prerequisites

- Java 17+
- Maven
- MySQL
- Docker (optional for containerization)

---

### 1. Clone the Repository

```bash
git clone https://github.com/TanishaMehta17/neuro_learn
cd neuro_learn
```

---

### 2. Configure Application Properties

Update the `src/main/resources/application.properties` with your database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
server.port=8081
```

---

### 3. Set Up the Database

- Create a new MySQL database:
  ```sql
  CREATE DATABASE your_database;
  ```

- (Optional) Import the pre-built schema from `/sql-scripts/schema.sql`.

---

### 4. Build and Run the Application

```bash
./mvnw spring-boot:run
```

The server will start at `http://localhost:8081`.

---

### 5. Docker (Optional)

Build and run the backend using Docker:

```bash
docker build -t neuro-learn .
docker run -p 8081:8081 neuro-learn
```

Or using Docker Compose if provided:

```bash
docker-compose up --build
```

---

## 🧠 LangChain Integration

- LangChain is used to enhance the application with AI capabilities.
- Typical Use Cases:
  - Smart grouping of contacts
  - Autocomplete suggestions
  - Predictive analytics on contact interactions
- The LangChain services are integrated as part of the Spring Service layer.

---

## 📬 Contact

For questions or suggestions:

- 📧 tanishamehta1709@gmail.com


---


