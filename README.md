# User-api
A production-style User Management REST API built with Spring Boot, featuring DTO-based request/response handling, validation, global exception handling, soft and hard deletion, and clean layered architecture.

# User Management REST API

A User Management REST API built with **Java and Spring Boot**.

This project focuses on building a clean and maintainable backend API with DTOs, input validation, centralized exception handling, and proper HTTP status codes.

## Features

* Create users
* Get a user by ID
* Get all users
* Update user information
* Soft delete users
* Permanently delete users
* Request and response DTOs
* Input validation
* Custom exceptions
* Global exception handling
* Standard HTTP status codes
* Layered architecture
* MySQL database integration

## Tech Stack

* **Java**
* **Spring Boot**
* **Spring Web**
* **Spring Validation**
* **MySQL**
* **Maven**

## Project Structure

The project follows a layered structure where each layer has a specific responsibility.

```text
src
└── main
    ├── java
    │   └── com.vansh.User
    │       │
    │       ├── UserApplication.java
    │       │
    │       ├── controller
    │       │   └── UserController.java
    │       │
    │       ├── dto
    │       │   ├── CreateUserRequestDTO.java
    │       │   ├── CreateUserResponseDTO.java
    │       │   ├── GetUserResponseDTO.java
    │       │   ├── UpdateUserRequestDTO.java
    │       │   └── UpdateUserResponseDTO.java
    │       │
    │       ├── exception
    │       │   ├── DuplicateEmailException.java
    │       │   ├── EmptyDataSetException.java
    │       │   ├── GlobalExceptionHandler.java
    │       │   ├── UserNotFoundException.java
    │       │   │
    │       │   └── exceptionDTO
    │       │       ├── ExceptionResponseDTO.java
    │       │       └── ValidExceptionDTO.java
    │       │
    │       ├── model
    │       │   └── User.java
    │       │
    │       ├── repository
    │       │   └── UserRepository.java
    │       │
    │       └── service
    │           └── UserService.java
    │
    └── resources
        └── application.properties
```

## Architecture

The application follows a layered architecture:

```text
Client
  │
  ▼
Controller
  │
  ▼
DTO + Validation
  │
  ▼
Service
  │
  ▼
Repository
  │
  ▼
Database
```

### Controller

The controller handles HTTP requests and responses. It receives request DTOs, validates input, calls the service layer, and returns appropriate HTTP responses.

### DTO

DTOs are used to control the data transferred between the client and the application.

The project uses separate DTOs for different operations:

* `CreateUserRequestDTO`
* `CreateUserResponseDTO`
* `GetUserResponseDTO`
* `UpdateUserRequestDTO`
* `UpdateUserResponseDTO`

This prevents the database model from being directly exposed through the API.

### Service

The service layer contains the application's user-related business logic.

It handles operations such as creating, retrieving, updating, and deleting users.

### Repository

The repository layer is responsible for communicating with the database.

### Exception Handling

The application uses custom exceptions and a global exception handler to provide consistent error responses.

Custom exceptions include:

* `UserNotFoundException`
* `DuplicateEmailException`
* `EmptyDataSetException`

The `GlobalExceptionHandler` handles these exceptions and converts them into structured API responses.

## API Endpoints

### Create User

```http
POST /api/user
```

Creates a new user.

**Response:** `201 Created`

---

### Get User

```http
GET /api/user?id={id}
```

Returns a user using their ID.

**Response:** `200 OK`

---

### Get All Users

```http
GET /api/users
```

Returns all users.

**Response:** `200 OK`

---

### Update User

```http
PUT /api/user?id={id}
```

Updates an existing user's information.

**Response:** `200 OK`

---

### Soft Delete User

```http
PATCH /api/user/soft-delete?id={id}
```

Marks a user as deleted without permanently removing the record from the database.

**Response:** `204 No Content`

---

### Hard Delete User

```http
DELETE /api/user?id={id}
```

Permanently removes a user from the database.

**Response:** `204 No Content`

## HTTP Status Codes

The API uses standard HTTP status codes to communicate the result of each request.

| Status Code                 | Description                                              |
| --------------------------- | -------------------------------------------------------- |
| `200 OK`                    | Request completed successfully                           |
| `201 Created`               | User was successfully created                            |
| `204 No Content`            | Operation completed successfully without a response body |
| `400 Bad Request`           | Request data is invalid                                  |
| `404 Not Found`             | Requested user does not exist                            |
| `409 Conflict`              | Request conflicts with existing data                     |
| `500 Internal Server Error` | Unexpected server-side error                             |

## Validation

User input is validated before it reaches the service layer.

For example, request DTOs are used with Spring Validation:

```java
@Valid @RequestBody CreateUserRequestDTO request
```

If the request contains invalid data, the validation error is handled by the global exception handler and returned as a structured response.

## Error Response

The API provides a consistent error response format.

Example:

```json
{
    "timestamp": "2026-10-06T22:00:00",
    "status": 404,
    "message": "User not found",
    "path": "/api/user"
}
```

Validation errors are handled separately through `ValidExceptionDTO`.

## Soft Delete and Hard Delete

The API supports two types of deletion.

### Soft Delete

Soft deletion changes the user's state while keeping the record in the database.

```text
User
 ↓
Deleted
```

The record can still exist in the database for future reference or recovery.

### Hard Delete

Hard deletion permanently removes the user's record from the database.

```text
User
 ↓
Removed from database
```

## Running the Project

### 1. Clone the repository

```bash
git clone https://github.com/vanshr21/User-api.git
```

### 2. Open the project

Open the project in an IDE such as IntelliJ IDEA.

### 3. Configure the database

Create the required MySQL database and configure the database connection in:

```text
src/main/resources/application.properties
```

Example:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Do not commit real database credentials to GitHub.

### 4. Run the application

Using Maven:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

The API will start on the configured Spring Boot port.

## Project Goals

The main goal of this project was to understand how to build a structured backend REST API using Spring Boot.

The project helped in understanding:

* REST API design
* HTTP methods and status codes
* DTO-based request and response handling
* Input validation
* Exception handling
* Layered architecture
* Database interaction
* Soft deletion
* Hard deletion
* Separation of responsibilities

## Future Improvements

Possible future improvements include:

* API documentation with OpenAPI/Swagger
* Unit and integration testing
* Pagination and sorting
* Authentication and authorization
* Logging
* Docker support
* Database migrations using Flyway or Liquibase
* API monitoring and observability

## Author

**Vansh**

Built as a Spring Boot backend project to practice REST API development and backend engineering.
