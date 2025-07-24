User Registration System – Technical Exam
 A secure and modular user registration system built with Java Spring Boot, integrating PostgreSQL, Redis, RabbitMQ, JWT Authentication.
Project Overview
This application enables secure user registration through:
   1- Encrypted password handling
   2- One-Time Password (OTP) generation and verification
   3- Integration with Redis for OTP storage
   4- RabbitMQ for asynchronous messaging
   5- Secure login and JWT-protected endpoints
Tech Stack	
   1- Java 17+
   2- Spring Boot (Web, Security, Data JPA, AMQP, Redis)
   3- PostgreSQL
   4- Redis
   5- RabbitMQ
   6- JWT (JSON Web Token)
   7 JUnit (Unit testing)
 
Functional Flow
1. User registers ➝ Saved as inactive in DB ➝ OTP generated, stored in Redis
2. OTP message published to RabbitMQ ➝ Simulated as console log
3. User verifies OTP ➝ Token checked from Redis ➝ Account activated
4. User logs in ➝ JWT token returned
5. Authenticated endpoints require JWT


 API Endpoints

| Method | Endpoint              | Description               | Authentication |
|--------|-----------------------|---------------------------|----------------|
| POST   | `/api/auth/register`  | Register a new user       | ❌ No          |
| POST   | `/api/auth/verify`    | Verify OTP                | ❌ No          |
| POST   | `/api/auth/login`     | Login and receive JWT     | ❌ No          |
| GET    | `/api/user`           | Get user information      | ✅ Yes         |

>  Use the **Authorization tab** in Postman with **Bearer Token** for secured endpoints.

 Access Points

| Service     | URL                               | Notes                      |
|-------------|------------------------------------|----------------------------|
| App (API)   | `http://localhost:8080`            | Main application server    |
| Swagger UI  | `http://localhost:8080/swagger-ui/index.html` | API documentation |
| RabbitMQ UI | `http://localhost:15672`           | Username: guest / guest    |
| PostgreSQL  | `localhost:5432`                   | Default port               |
| Redis       | `localhost:6379`                   | Default port               |


 Docker Compose Files:

 RabbitMQ
```yaml
version: "3.9"
services:
  rabbitmq:
    image: rabbitmq:4.1-management
    ports:
      - "5672:5672"
      - "15672:15672"
```
redis 
```
version: "3.8"
services:
  redis:
    image: redis:7.2-alpine
    ports:
      - "6379:6379"
```
postgres 
```
services:
  postgres:
    image: postgres:14
    restart: always
    environment:
      POSTGRES_USER: user
      POSTGRES_PASSWORD: password
      POSTGRES_DB: mydatabase
    ports:
      - "5432:5432"
    volumes:
      - db_data:/var/lib/postgresql/data

volumes:
  db_data:
```
