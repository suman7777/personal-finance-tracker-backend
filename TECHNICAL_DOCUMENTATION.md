# Personal Finance Tracker – Backend Technical Documentation

## Overview
This backend is a Spring Boot REST API for the Personal Finance Tracker. It manages users, accounts, transactions, budgets, recurring payments, and reports. It is designed for easy integration with a modern React frontend.

---

## Tech Stack
- Spring Boot 3.x
- Java 17+
- Spring Web, Spring Data JPA
- H2 (in-memory, dev)
- Spring Security (disabled for now)
- Maven

---

## Project Structure
- `PersonalFinanceBackendApplication.java`: Main entry, CORS config
- `controller/`: REST controllers for all entities (`UserController`, `AccountController`, etc.), `AuthController` for login
- `service/`: Business logic for each entity
- `repository/`: JPA repositories
- `model/`: Entity classes (`User`, `Account`, `Transaction`, etc.)
- `config/SecurityConfig.java`: Disables security for all endpoints (for dev)

---

## Main Features & Endpoints
- `/api/users`: User CRUD
- `/api/accounts`: Account CRUD
- `/api/transactions`: Transaction CRUD
- `/api/budgets`, `/api/recurring-payments`, `/api/reports`: Other features
- `/api/auth/login`: Login endpoint (plain username/password, demo only)

---

## Security
- All endpoints are currently open (no authentication required)
- Security can be enabled by updating `SecurityConfig.java`

---

## Database (H2)
- In-memory H2 database for development
- H2 console enabled at [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- Default JDBC URL: `jdbc:h2:mem:testdb`, user: `sa`, password: (blank)
- Tables: `app_user`, `account`, `transaction`, etc.

---

## How to Run
```sh
cd personal-finance
./mvnw spring-boot:run
```
App runs at [http://localhost:8080](http://localhost:8080)

---

## Version Control
- Backend repo: [personal-finance-tracker-backend](https://github.com/suman7777/personal-finance-tracker-backend)
- Uses git for version control

---

## Additional Notes
- For production, enable security and use hashed passwords/JWT
- For persistent data, switch from H2 to PostgreSQL or MySQL
- The codebase is modular and easy to extend for new features

---
