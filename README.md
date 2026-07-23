# 🛒 E-Commerce Backend Microservices

A scalable, event-driven e-commerce backend built using Spring Boot Microservices, Apache Kafka, Docker, PostgreSQL, and Spring Cloud.

---

## 🚀 Tech Stack

- Java 17
- Spring Boot
- Spring Security
- JWT Authentication
- Spring Cloud Gateway
- Eureka Server
- Apache Kafka
- Docker & Docker Compose
- PostgreSQL
- Swagger/OpenAPI
- k6 Load Testing

---

## 🏗️ Architecture


![img.png](screenshots/architectureDiagram.png)
---

## ✨ Features

- User Authentication with JWT
- API Gateway
- Service Discovery (Eureka)
- Product Service
- Order Service
- Event-driven communication using Kafka
- Dockerized microservices
- Swagger API Documentation
- Load tested using k6

---

## 📸 Screenshots

### Docker Compose

![img_1.png](screenshots/docker.png)

### Eureka Dashboard

![img_2.png](screenshots/eureka-dashboard.png)

### Swagger UI
- User Authentication Service

![img_3.png](screenshots/swagger-auth.png)

- Product Service

![img_4.png](screenshots/swagger-product.png)

- Order Service

![img_5.png](screenshots/swagger-order.png)

### Postman

- Register
![img_6.png](screenshots/postman-register.png)
- Login
![img_7.png](screenshots/postman-login.png)
- Create Product
![img_8.png](screenshots/postman-create-product.png)
- Get Product
![img_9.png](screenshots/postman-get-product.png)
- Create Order
![img_13.png](screenshots/postman-create-order.png)

### k6 Performance Test

![img_14.png](screenshots/k6-results.png)

---

## ⚡ Performance

- 50 Virtual Users
- 30 seconds Load Test
- 128K+ Requests
- ~4276 Requests/sec
- 0% Request Failure
- ~11.5 ms Average Response Time

---

## Getting Started

### Clone the repository
```bash
git clone <https://github.com/Peerul-Hadhi-Rahman/e-commerce-microservices>
```

### Start the application
```bash
docker compose up -d
```

## Access the Services

| Service | URL |
|---------|-----|
| Eureka Dashboard | http://localhost:8761 |
| API Gateway | http://localhost:8080 |
| Auth Service - Swagger UI | http://localhost:8081/swagger-ui/index.html |
| Product Service - Swagger UI | http://localhost:8082/swagger-ui/index.html |
| Order Service - Swagger UI | http://localhost:8083/swagger-ui/index.html |


## 👨‍💻 Author

**Peerul Hadhi Rahman**

- 📧 Email: peerulrahman.sde@gmail.com
- 💼 LinkedIn: www.linkedin.com/in/peerulhadhirahman
- 💻 GitHub: https://github.com/Peerul-Hadhi-Rahman
- 🧩 LeetCode: https://leetcode.com/u/Peerul_Rahman/