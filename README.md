# CareerTrack

CareerTrack is a complete job application tracking REST API built using Java, Spring Boot, Spring Data JPA, Maven and H2 Database.

The project helps users manage job applications, track recruitment stages, search applications and view career application analytics.

---

## Project Overview

Searching for jobs can quickly become difficult to manage when applications are submitted through multiple platforms.

CareerTrack provides a structured backend system for storing and tracking job applications.

Each application can contain information such as:

- Company
- Job role
- Location
- Application source
- Application stage
- Applied date
- Notes
- Created date
- Updated date

The project also provides analytics such as interview rate and offer rate.

---

## Main Features

- Java backend application
- Spring Boot REST API
- Spring Data JPA
- H2 persistent database
- Complete CRUD operations
- Job application tracking
- Recruitment stage tracking
- Search applications
- Filter applications by status
- Career analytics
- Interview rate calculation
- Offer rate calculation
- Status breakdown
- Automatic sample data
- Data validation
- H2 database console
- Maven project
- RESTful architecture

---

## Technology Stack

```text
Java 17
Spring Boot
Spring Web
Spring Data JPA
Hibernate
H2 Database
Jakarta Validation
Maven
REST API
```

---

## Folder Structure

```text
careertrack-spring/
│
├── pom.xml
├── .gitignore
├── README.md
│
└── src/
    ├── main/
    │   ├── java/
    │   │   └── com/
    │   │       └── danish/
    │   │           └── careertrack/
    │   │               ├── CareerTrackApplication.java
    │   │               ├── controller/
    │   │               │   └── JobApplicationController.java
    │   │               ├── model/
    │   │               │   ├── ApplicationStatus.java
    │   │               │   └── JobApplication.java
    │   │               ├── repository/
    │   │               │   └── JobApplicationRepository.java
    │   │               └── service/
    │   │                   └── JobApplicationService.java
    │   │
    │   └── resources/
    │       └── application.properties
    │
    └── test/
        └── java/
            └── com/
                └── danish/
                    └── careertrack/
                        └── CareerTrackApplicationTests.java
```

---

# Application Statuses

CareerTrack supports the following recruitment stages:

```text
SAVED
APPLIED
SCREENING
INTERVIEW
TECHNICAL_TEST
FINAL_INTERVIEW
OFFER
REJECTED
WITHDRAWN
```

---

# REST API Endpoints

## API Information

```http
GET /api
```

---

## Get All Applications

```http
GET /api/applications
```

Example:

```text
http://localhost:8080/api/applications
```

---

## Get Application by ID

```http
GET /api/applications/{id}
```

Example:

```text
http://localhost:8080/api/applications/1
```

---

## Search Applications

```http
GET /api/applications?search=data
```

Example:

```text
http://localhost:8080/api/applications?search=engineer
```

The search checks:

- Company
- Role
- Location
- Source

---

## Filter by Status

```http
GET /api/applications?status=INTERVIEW
```

Example:

```text
http://localhost:8080/api/applications?status=APPLIED
```

---

## Filter and Search Together

```http
GET /api/applications?status=APPLIED&search=engineer
```

---

## Create Application

```http
POST /api/applications
```

Example JSON:

```json
{
  "company": "DataCloud Singapore",
  "role": "Data Engineer",
  "location": "Singapore",
  "source": "LinkedIn",
  "status": "APPLIED",
  "appliedDate": "2026-09-22",
  "notes": "Applied through LinkedIn."
}
```

---

## Update Application

```http
PUT /api/applications/{id}
```

Example:

```http
PUT /api/applications/1
```

Example JSON:

```json
{
  "company": "DataCloud Singapore",
  "role": "Data Engineer",
  "location": "Singapore",
  "source": "LinkedIn",
  "status": "INTERVIEW",
  "appliedDate": "2026-09-22",
  "notes": "First interview scheduled."
}
```

---

## Update Only Status

```http
PATCH /api/applications/{id}/status?status=INTERVIEW
```

Example:

```text
http://localhost:8080/api/applications/1/status?status=INTERVIEW
```

---

## Delete Application

```http
DELETE /api/applications/{id}
```

Example:

```text
DELETE /api/applications/1
```

---

# Analytics

## Application Summary

```http
GET /api/analytics/summary
```

Example output:

```json
{
  "totalApplications": 8,
  "saved": 1,
  "applied": 3,
  "screening": 1,
  "interviews": 2,
  "offers": 1,
  "rejected": 0,
  "withdrawn": 0,
  "interviewRate": 25.0,
  "offerRate": 12.5
}
```

---

## Status Breakdown

```http
GET /api/analytics/status-breakdown
```

Example:

```json
{
  "SAVED": 1,
  "APPLIED": 3,
  "SCREENING": 1,
  "INTERVIEW": 1,
  "TECHNICAL_TEST": 1,
  "FINAL_INTERVIEW": 0,
  "OFFER": 1,
  "REJECTED": 0,
  "WITHDRAWN": 0
}
```

---

# How to Run

## Requirements

Install:

```text
Java 17 or above
Maven 3.8 or above
```

Check Java:

```bash
java -version
```

Check Maven:

```bash
mvn -version
```

---

## Clone Repository

```bash
git clone https://github.com/your-username/careertrack-spring.git
```

Move into the folder:

```bash
cd careertrack-spring
```

---

## Run Using Maven

```bash
mvn spring-boot:run
```

The API will run at:

```text
http://localhost:8080
```

---

# H2 Database Console

CareerTrack includes the H2 web console.

Open:

```text
http://localhost:8080/h2-console
```

Use:

```text
JDBC URL:
jdbc:h2:file:./data/careertrack

Username:
sa

Password:
leave blank
```

Click:

```text
Connect
```

---

# Database Table

CareerTrack automatically creates:

```text
job_applications
```

Main fields:

```text
id
company
role
location
source
status
applied_date
notes
created_at
updated_at
```

---

# Sample Data

The application automatically creates sample job applications when the database is empty.

Examples include:

```text
Data Engineer
Cloud Engineer
Junior Data Analyst
```

This allows the API to be tested immediately.

---

# Testing with cURL

## Create Application

```bash
curl -X POST http://localhost:8080/api/applications \
-H "Content-Type: application/json" \
-d '{
  "company":"Tech Solutions",
  "role":"Data Engineer",
  "location":"Singapore",
  "source":"LinkedIn",
  "status":"APPLIED",
  "appliedDate":"2026-09-22",
  "notes":"Submitted application."
}'
```

---

## Get Applications

```bash
curl http://localhost:8080/api/applications
```

---

## Get Analytics

```bash
curl http://localhost:8080/api/analytics/summary
```

---

# Project Architecture

CareerTrack follows a standard Spring Boot layered architecture.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

### Controller

Handles HTTP requests and API endpoints.

### Service

Contains application business logic.

### Repository

Communicates with the H2 database through Spring Data JPA.

### Model

Defines database entities and application statuses.

---

# Project Category

```text
Java Backend Application
Spring Boot REST API
Career Management System
CRUD Application
Database Project
```

---

# Suggested Repository Description

```text
A Java Spring Boot job application tracker with REST APIs, H2 database, search, filtering and application analytics.
```

---

# Suggested GitHub Topics

```text
java
spring-boot
spring-data-jpa
rest-api
h2-database
backend
job-tracker
career-management
crud-api
maven
java-project
portfolio
```

---

# Suggested First Commit

```text
feat(api): add complete Spring Boot job application tracker
```

---

# Suggested Future Commits

```text
docs(readme): add complete API documentation

feat(search): add application search and filtering

feat(analytics): add career application statistics

feat(status): add recruitment stage tracking

feat(database): add persistent H2 database

test(service): add job application service tests

feat(auth): add user authentication

feat(export): add CSV application export
```

---

# Future Improvements

The project can later be expanded with:

- PostgreSQL database
- MySQL database
- JWT authentication
- Multiple users
- Company profiles
- Interview dates
- Interview notes
- Salary expectations
- Job descriptions
- Resume version tracking
- Cover letter tracking
- Recruiter contact details
- Follow-up reminders
- Email notifications
- CSV export
- Excel export
- PDF reports
- Docker deployment
- Swagger/OpenAPI documentation
- Frontend React dashboard
- Unit tests
- Integration tests
- GitHub Actions CI/CD

---

# Learning Outcomes

By completing CareerTrack, you can practice:

- Java
- Object-oriented programming
- Spring Boot
- Spring MVC
- REST API development
- Spring Data JPA
- Hibernate ORM
- Database design
- H2 database
- CRUD operations
- Dependency injection
- Validation
- Search and filtering
- Backend architecture
- Maven
- API testing

---

# Author

Muhammad Danish

Web Developer  
Computer Science Background

---

# License

This project is open-source and available for educational, portfolio, and personal development purposes.
