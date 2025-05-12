# 🏫 Click Sala - Backend

**Click Sala** is a system for managing academic room bookings, allowing institutions to efficiently control spaces for classes, meetings, and other activities. This repository contains the backend of the application, developed with **Java 17** and **Spring Boot**, containerized with **Docker**.

---

## 🚀 Technologies Used

- Java 17  
- Spring Boot  
- Spring Data JPA  
- Spring Security 
- PostgreSQL  
- Docker & Docker Compose  
- Maven  
- Lombok

---

## ⚙️ Link of postman collection
[<img src="https://run.pstmn.io/button.svg" alt="Run In Postman" style="width: 128px; height: 32px;">](https://app.getpostman.com/run-collection/39006660-7efdd28b-a240-4a19-ba8b-6800520fdad2?action=collection%2Ffork&source=rip_markdown&collection-url=entityId%3D39006660-7efdd28b-a240-4a19-ba8b-6800520fdad2%26entityType%3Dcollection%26workspaceId%3D59b1b276-c40f-4e2d-b281-32093a373de0)

---

## ⚙️ How to Run the Project

### Prerequisites

- [Java 17](https://adoptium.net/en-GB/temurin/releases/) 
- [Docker](https://www.docker.com/products/docker-desktop/)  
- [Docker Compose](https://docs.docker.com/compose/)  

### Running with Docker

1. Clone the repository:

```bash
git clone https://github.com/UNIFG-PE/Back-Click-Sala.git
cd Back-Click-Sala
```
  
2. Build and run the containers with Docker Compose:

```bash
docker-compose up --build
```

3. Access the system:

-  Backend: http://localhost:8080
-  PostgreSQL database: localhost:5432



