# SkyBook

A Spring Boot backend for an airline booking management system. SkyBook is organized as a layered monolithic application using Java, Spring Boot, Spring Data JPA, Hibernate, Jakarta Validation, and a relational database.

## Table of Contents

- [Overview](#overview)
- [Project Goals](#project-goals)
- [Technology Stack](#technology-stack)
- [Architecture](#architecture)
- [Project Structure](#project-structure)
- [Modules](#modules)
  - [Aircraft](#aircraft)
  - [Airport](#airport)
  - [Flight](#flight)
  - [Passenger](#passenger)
  - [Booking](#booking)
- [Application Flow](#application-flow)
- [API Overview](#api-overview)
- [Response Format](#response-format)
- [Validation and Error Handling](#validation-and-error-handling)
- [Database and Persistence](#database-and-persistence)
- [Configuration](#configuration)
- [Running the Project](#running-the-project)
- [Building the Project](#building-the-project)
- [Testing](#testing)
- [Source Navigation](#source-navigation)
- [Development Principles](#development-principles)
- [Current Project Status](#current-project-status)
- [Future Improvements](#future-improvements)

---

## Overview

SkyBook is a backend application for managing core airline operations and passenger bookings.

The application currently contains domain models and REST APIs for:

- Aircraft management
- Airport management
- Flight management
- Passenger management
- Booking management

The project follows a layered architecture so that HTTP handling, business logic, persistence, object mapping, validation, and error handling remain separated.

---

## Project Goals

SkyBook is designed to provide a structured backend for an airline booking workflow.

The core workflow is:

```text
Aircraft
   |
   v
Flight <---- Airport
   |
   v
Passenger
   |
   v
Booking
```

A booking connects an existing passenger with an existing flight.

For example:

```text
Passenger #1
      |
      | creates booking
      v
Flight #10
      |
      v
Booking #25
```

---

## Technology Stack

| Technology | Purpose |
|---|---|
| Java | Application programming language |
| Spring Boot | Application framework |
| Spring Web | REST API development |
| Spring Data JPA | Data access |
| Hibernate | ORM / JPA implementation |
| Jakarta Validation | Request validation |
| Maven | Build and dependency management |
| Relational Database | Persistent application data |

The exact dependency versions are defined in [`pom.xml`](pom.xml).

---

## Architecture

SkyBook is a **monolithic layered application**.

All modules run inside the same Spring Boot application and are deployed as one application.

### Layered Architecture

```text
                    HTTP CLIENT
                         |
                         v
                 +---------------+
                 |  Controllers  |
                 +-------+-------+
                         |
                         v
                 +---------------+
                 |   Services    |
                 +-------+-------+
                         |
                         v
                 +---------------+
                 |  Repositories |
                 +-------+-------+
                         |
                         v
                    DATABASE
```

Supporting the main flow:

```text
DTOs
  |
  v
Controllers
  |
  v
Services
  |
  +----> Mappers
  |
  +----> Repositories
  |
  v
Entities
  |
  v
Database

Exceptions
  |
  v
GlobalExceptionHandler
```

### Main responsibilities

**Controller**

Receives HTTP requests and returns HTTP responses.

**Service**

Contains application and business logic.

**Repository**

Provides database access through Spring Data JPA.

**Entity**

Represents persistent database data.

**DTO**

Defines the data entering and leaving the API.

**Mapper**

Converts between DTOs and entities.

**Exception**

Represents application-specific errors.

**Global Exception Handler**

Converts exceptions into consistent HTTP error responses.

---

## Project Structure

```text
skybook/
├── .gitattributes
├── .gitignore
├── HELP.md
├── mvnw
├── mvnw.cmd
├── pom.xml
│
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── dev/
│   │   │       └── alpha/
│   │   │           └── skybook/
│   │   │               ├── SkybookApplication.java
│   │   │               │
│   │   │               ├── common/
│   │   │               │   ├── ApiResponse.java
│   │   │               │   └── ErrorResponse.java
│   │   │               │
│   │   │               ├── controller/
│   │   │               ├── dto/
│   │   │               │   ├── request/
│   │   │               │   └── response/
│   │   │               ├── entity/
│   │   │               ├── enums/
│   │   │               ├── exception/
│   │   │               ├── mapper/
│   │   │               ├── repository/
│   │   │               ├── service/
│   │   │               │   └── impl/
│   │   │               ├── util/
│   │   │               └── validation/
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application.yml
│   │       ├── db/
│   │       │   └── migration/
│   │       ├── static/
│   │       └── templates/
│   │
│   └── test/
│       └── java/
│           └── dev/
│               └── alpha/
│                   └── skybook/
│                       └── SkybookApplicationTests.java
│
└── target/
    └── generated build output
```

`target/` is generated by Maven and should not be treated as application source code.

---

# Modules

## Aircraft

Aircraft represents the aircraft available to the airline.

### Source files

- [Aircraft Entity](src/main/java/dev/alpha/skybook/entity/Aircraft.java)
- [Aircraft Request DTO](src/main/java/dev/alpha/skybook/dto/request/AircraftRequest.java)
- [Aircraft Response DTO](src/main/java/dev/alpha/skybook/dto/response/AircraftResponse.java)
- [Aircraft Status](src/main/java/dev/alpha/skybook/enums/AircraftStatus.java)
- [Aircraft Mapper](src/main/java/dev/alpha/skybook/mapper/AircraftMapper.java)
- [Aircraft Repository](src/main/java/dev/alpha/skybook/repository/AircraftRepository.java)
- [Aircraft Service](src/main/java/dev/alpha/skybook/service/AircraftService.java)
- [Aircraft Service Implementation](src/main/java/dev/alpha/skybook/service/impl/AircraftServiceImpl.java)
- [Aircraft Controller](src/main/java/dev/alpha/skybook/controller/AircraftController.java)
- [Aircraft Already Exists Exception](src/main/java/dev/alpha/skybook/exception/AircraftAlreadyExistsException.java)
- [Aircraft Not Found Exception](src/main/java/dev/alpha/skybook/exception/AircraftNotFoundException.java)

---

## Airport

Airport represents airports used as flight departure and arrival locations.

### Source files

- [Airport Entity](src/main/java/dev/alpha/skybook/entity/Airport.java)
- [Airport Request DTO](src/main/java/dev/alpha/skybook/dto/request/AirportRequest.java)
- [Airport Response DTO](src/main/java/dev/alpha/skybook/dto/response/AirportResponse.java)
- [Airport Mapper](src/main/java/dev/alpha/skybook/mapper/AirportMapper.java)
- [Airport Repository](src/main/java/dev/alpha/skybook/repository/AirportRepository.java)
- [Airport Service](src/main/java/dev/alpha/skybook/service/AirportService.java)
- [Airport Service Implementation](src/main/java/dev/alpha/skybook/service/impl/AirportServiceImpl.java)
- [Airport Controller](src/main/java/dev/alpha/skybook/controller/AirportController.java)
- [Airport Already Exists Exception](src/main/java/dev/alpha/skybook/exception/AirportAlreadyExistsException.java)
- [Airport Not Found Exception](src/main/java/dev/alpha/skybook/exception/AirportNotFoundException.java)
- [Same Airport Exception](src/main/java/dev/alpha/skybook/exception/SameAirportException.java)

---

## Flight

Flight connects an aircraft with a departure airport, an arrival airport, schedule information, price, and a flight status.

The `Flight` entity has relationships with `Aircraft` and `Airport`.

### Source files

- [Flight Entity](src/main/java/dev/alpha/skybook/entity/Flight.java)
- [Flight Request DTO](src/main/java/dev/alpha/skybook/dto/request/FlightRequest.java)
- [Flight Response DTO](src/main/java/dev/alpha/skybook/dto/response/FlightResponse.java)
- [Flight Status](src/main/java/dev/alpha/skybook/enums/FlightStatus.java)
- [Flight Mapper](src/main/java/dev/alpha/skybook/mapper/FlightMapper.java)
- [Flight Repository](src/main/java/dev/alpha/skybook/repository/FlightRepository.java)
- [Flight Service](src/main/java/dev/alpha/skybook/service/FlightService.java)
- [Flight Service Implementation](src/main/java/dev/alpha/skybook/service/impl/FlightServiceImpl.java)
- [Flight Controller](src/main/java/dev/alpha/skybook/controller/FlightController.java)
- [Flight Already Exists Exception](src/main/java/dev/alpha/skybook/exception/FlightAlreadyExistsException.java)
- [Flight Not Found Exception](src/main/java/dev/alpha/skybook/exception/FlightNotFoundException.java)
- [Invalid Flight Time Exception](src/main/java/dev/alpha/skybook/exception/InvalidFlightTimeException.java)

### Flight data model

```text
Flight
 |
 +-- Aircraft
 |
 +-- Departure Airport
 |
 +-- Arrival Airport
 |
 +-- Departure Time
 |
 +-- Arrival Time
 |
 +-- Price
 |
 +-- Status
```

---

## Passenger

Passenger represents a person who can make bookings.

The passenger module contains request validation, persistence, mapping, service logic, REST endpoints, and custom exceptions.

### Source files

- [Passenger Entity](src/main/java/dev/alpha/skybook/entity/Passenger.java)
- [Passenger Request DTO](src/main/java/dev/alpha/skybook/dto/request/PassengerRequest.java)
- [Passenger Response DTO](src/main/java/dev/alpha/skybook/dto/response/PassengerResponse.java)
- [Passenger Status](src/main/java/dev/alpha/skybook/enums/PassengerStatus.java)
- [Passenger Mapper](src/main/java/dev/alpha/skybook/mapper/PassengerMapper.java)
- [Passenger Repository](src/main/java/dev/alpha/skybook/repository/PassengerRepository.java)
- [Passenger Service](src/main/java/dev/alpha/skybook/service/PassengerService.java)
- [Passenger Service Implementation](src/main/java/dev/alpha/skybook/service/impl/PassengerServiceImpl.java)
- [Passenger Controller](src/main/java/dev/alpha/skybook/controller/PassengerController.java)
- [Passenger Already Exists Exception](src/main/java/dev/alpha/skybook/exception/PassengerAlreadyExistsException.java)
- [Passenger Not Found Exception](src/main/java/dev/alpha/skybook/exception/PassengerNotFoundException.java)

### Passenger data

The passenger entity includes:

- First name
- Last name
- Email
- Phone
- Passport number
- Date of birth
- Passenger status

The email and passport number are configured as unique database fields.

---

## Booking

Booking connects a passenger to a flight.

A Booking contains:

- Booking ID
- Passenger
- Flight
- Booking status

The entity uses JPA `@ManyToOne` relationships because multiple bookings can reference the same passenger or flight.

### Source files

- [Booking Entity](src/main/java/dev/alpha/skybook/entity/Booking.java)
- [Booking Request DTO](src/main/java/dev/alpha/skybook/dto/request/BookingRequest.java)
- [Booking Response DTO](src/main/java/dev/alpha/skybook/dto/response/BookingResponse.java)
- [Booking Status](src/main/java/dev/alpha/skybook/enums/BookingStatus.java)
- [Booking Mapper](src/main/java/dev/alpha/skybook/mapper/BookingMapper.java)
- [Booking Repository](src/main/java/dev/alpha/skybook/repository/BookingRepository.java)
- [Booking Service](src/main/java/dev/alpha/skybook/service/BookingService.java)
- [Booking Service Implementation](src/main/java/dev/alpha/skybook/service/impl/BookingServiceImpl.java)
- [Booking Controller](src/main/java/dev/alpha/skybook/controller/BookingController.java)
- [Booking Not Found Exception](src/main/java/dev/alpha/skybook/exception/BookingNotFoundException.java)

### Booking creation flow

```text
BookingRequest
     |
     | passengerId + flightId
     v
BookingServiceImpl
     |
     +------> PassengerRepository
     |              |
     |              v
     |          Passenger
     |
     +------> FlightRepository
                    |
                    v
                 Flight
                    |
                    v
              BookingMapper
                    |
                    v
               Booking Entity
                    |
                    v
             BookingRepository
                    |
                    v
                 Database
```

The service verifies that the referenced passenger and flight exist before creating the booking.

---

# Application Flow

A typical REST request follows this path:

```text
HTTP Request
     |
     v
Controller
     |
     v
Request DTO
     |
     v
Validation
     |
     v
Service
     |
     +----> Repository
     |
     +----> Mapper
     |
     v
Entity
     |
     v
Database
     |
     v
Entity
     |
     v
Response Mapper
     |
     v
Response DTO
     |
     v
ApiResponse
     |
     v
HTTP Response
```

For an invalid operation:

```text
Request
  |
  v
Service
  |
  v
Custom Exception
  |
  v
GlobalExceptionHandler
  |
  v
ErrorResponse
  |
  v
HTTP Error Response
```

---

# API Overview

The application exposes REST controllers for the main domains.

| Domain | Controller |
|---|---|
| Aircraft | [`AircraftController`](src/main/java/dev/alpha/skybook/controller/AircraftController.java) |
| Airport | [`AirportController`](src/main/java/dev/alpha/skybook/controller/AirportController.java) |
| Flight | [`FlightController`](src/main/java/dev/alpha/skybook/controller/FlightController.java) |
| Passenger | [`PassengerController`](src/main/java/dev/alpha/skybook/controller/PassengerController.java) |
| Booking | [`BookingController`](src/main/java/dev/alpha/skybook/controller/BookingController.java) |

### Booking endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/bookings` | Create a booking |
| `GET` | `/api/bookings` | Retrieve all bookings |
| `GET` | `/api/bookings/{id}` | Retrieve one booking |
| `PUT` | `/api/bookings/{id}` | Update a booking |
| `DELETE` | `/api/bookings/{id}` | Delete a booking |

The exact endpoints for the other modules are defined directly in their controller classes.

---

# Response Format

SkyBook uses a shared [`ApiResponse`](src/main/java/dev/alpha/skybook/common/ApiResponse.java) wrapper.

A successful response follows this structure:

```json
{
  "success": true,
  "message": "Operation completed successfully",
  "data": {},
  "timestamp": "2026-08-18T..."
}
```

The generic response structure is:

```java
ApiResponse<T>
```

with:

- `success`
- `message`
- `data`
- `timestamp`

Errors use [`ErrorResponse`](src/main/java/dev/alpha/skybook/common/ErrorResponse.java).

---

# Validation and Error Handling

Request DTOs use Jakarta Validation annotations such as:

```java
@NotNull
@NotBlank
@Email
@DecimalMin
```

This prevents invalid request data from reaching business logic unnecessarily.

Examples include:

- Required passenger information
- Required flight references
- Required booking status
- Valid email format
- Non-negative flight prices

Application-specific exceptions are kept in:

[`exception/`](src/main/java/dev/alpha/skybook/exception/)

The central handler is:

[`GlobalExceptionHandler`](src/main/java/dev/alpha/skybook/exception/GlobalExceptionHandler.java)

This keeps error responses consistent across the application.

---

# Database and Persistence

SkyBook uses Jakarta Persistence / JPA entities.

Examples:

```java
@Entity
@Table(name = "bookings")
```

Relationships are represented using JPA annotations such as:

```java
@ManyToOne
@JoinColumn(...)
```

Repositories extend Spring Data JPA interfaces such as:

```java
JpaRepository<Entity, Long>
```

Database-related configuration is located in:

- [`application.properties`](src/main/resources/application.properties)
- [`application.yml`](src/main/resources/application.yml)

Database migration resources are located under:

[`src/main/resources/db/migration/`](src/main/resources/db/migration/)

---

# Configuration

Application configuration files:

- [`application.properties`](src/main/resources/application.properties)
- [`application.yml`](src/main/resources/application.yml)

Keep environment-specific secrets outside source control where appropriate.

If both configuration files are present, verify which properties are active for the current Spring Boot configuration before changing deployment settings.

---

# Running the Project

## Prerequisites

Make sure you have:

- Java installed
- A compatible JDK for the project
- Git
- A relational database configured for the application

The project includes the Maven Wrapper, so Maven does not need to be installed globally.

## Windows

From the project root:

```powershell
.\mvnw.cmd clean compile
```

Run the application:

```powershell
.\mvnw.cmd spring-boot:run
```

## Linux / macOS

```bash
./mvnw clean compile
```

Run:

```bash
./mvnw spring-boot:run
```

---

# Building the Project

Compile:

```bash
./mvnw clean compile
```

Package:

```bash
./mvnw clean package
```

Run tests:

```bash
./mvnw test
```

On Windows, use `.\mvnw.cmd` instead of `./mvnw`.

---

# Testing

The project contains the Spring Boot application test:

- [SkybookApplicationTests.java](src/test/java/dev/alpha/skybook/SkybookApplicationTests.java)

The Booking module has also been exercised through its REST CRUD workflow and validation/error scenarios during development.

A complete project-wide automated test suite should be expanded as the project evolves.

---

# Source Navigation

## Application

- [SkybookApplication.java](src/main/java/dev/alpha/skybook/SkybookApplication.java)

## Common

- [ApiResponse.java](src/main/java/dev/alpha/skybook/common/ApiResponse.java)
- [ErrorResponse.java](src/main/java/dev/alpha/skybook/common/ErrorResponse.java)

## Controllers

- [AircraftController.java](src/main/java/dev/alpha/skybook/controller/AircraftController.java)
- [AirportController.java](src/main/java/dev/alpha/skybook/controller/AirportController.java)
- [FlightController.java](src/main/java/dev/alpha/skybook/controller/FlightController.java)
- [PassengerController.java](src/main/java/dev/alpha/skybook/controller/PassengerController.java)
- [BookingController.java](src/main/java/dev/alpha/skybook/controller/BookingController.java)
- [TestController.java](src/main/java/dev/alpha/skybook/controller/TestController.java)

## Request DTOs

- [AircraftRequest.java](src/main/java/dev/alpha/skybook/dto/request/AircraftRequest.java)
- [AirportRequest.java](src/main/java/dev/alpha/skybook/dto/request/AirportRequest.java)
- [FlightRequest.java](src/main/java/dev/alpha/skybook/dto/request/FlightRequest.java)
- [PassengerRequest.java](src/main/java/dev/alpha/skybook/dto/request/PassengerRequest.java)
- [BookingRequest.java](src/main/java/dev/alpha/skybook/dto/request/BookingRequest.java)

## Response DTOs

- [AircraftResponse.java](src/main/java/dev/alpha/skybook/dto/response/AircraftResponse.java)
- [AirportResponse.java](src/main/java/dev/alpha/skybook/dto/response/AirportResponse.java)
- [FlightResponse.java](src/main/java/dev/alpha/skybook/dto/response/FlightResponse.java)
- [PassengerResponse.java](src/main/java/dev/alpha/skybook/dto/response/PassengerResponse.java)
- [BookingResponse.java](src/main/java/dev/alpha/skybook/dto/response/BookingResponse.java)

## Entities

- [Aircraft.java](src/main/java/dev/alpha/skybook/entity/Aircraft.java)
- [Airport.java](src/main/java/dev/alpha/skybook/entity/Airport.java)
- [Flight.java](src/main/java/dev/alpha/skybook/entity/Flight.java)
- [Passenger.java](src/main/java/dev/alpha/skybook/entity/Passenger.java)
- [Booking.java](src/main/java/dev/alpha/skybook/entity/Booking.java)

## Enums

- [AircraftStatus.java](src/main/java/dev/alpha/skybook/enums/AircraftStatus.java)
- [FlightStatus.java](src/main/java/dev/alpha/skybook/enums/FlightStatus.java)
- [PassengerStatus.java](src/main/java/dev/alpha/skybook/enums/PassengerStatus.java)
- [BookingStatus.java](src/main/java/dev/alpha/skybook/enums/BookingStatus.java)

## Exceptions

- [AircraftAlreadyExistsException.java](src/main/java/dev/alpha/skybook/exception/AircraftAlreadyExistsException.java)
- [AircraftNotFoundException.java](src/main/java/dev/alpha/skybook/exception/AircraftNotFoundException.java)
- [AirportAlreadyExistsException.java](src/main/java/dev/alpha/skybook/exception/AirportAlreadyExistsException.java)
- [AirportNotFoundException.java](src/main/java/dev/alpha/skybook/exception/AirportNotFoundException.java)
- [FlightAlreadyExistsException.java](src/main/java/dev/alpha/skybook/exception/FlightAlreadyExistsException.java)
- [FlightNotFoundException.java](src/main/java/dev/alpha/skybook/exception/FlightNotFoundException.java)
- [InvalidFlightTimeException.java](src/main/java/dev/alpha/skybook/exception/InvalidFlightTimeException.java)
- [PassengerAlreadyExistsException.java](src/main/java/dev/alpha/skybook/exception/PassengerAlreadyExistsException.java)
- [PassengerNotFoundException.java](src/main/java/dev/alpha/skybook/exception/PassengerNotFoundException.java)
- [BookingNotFoundException.java](src/main/java/dev/alpha/skybook/exception/BookingNotFoundException.java)
- [SameAirportException.java](src/main/java/dev/alpha/skybook/exception/SameAirportException.java)
- [GlobalExceptionHandler.java](src/main/java/dev/alpha/skybook/exception/GlobalExceptionHandler.java)

## Mappers

- [AircraftMapper.java](src/main/java/dev/alpha/skybook/mapper/AircraftMapper.java)
- [AirportMapper.java](src/main/java/dev/alpha/skybook/mapper/AirportMapper.java)
- [FlightMapper.java](src/main/java/dev/alpha/skybook/mapper/FlightMapper.java)
- [PassengerMapper.java](src/main/java/dev/alpha/skybook/mapper/PassengerMapper.java)
- [BookingMapper.java](src/main/java/dev/alpha/skybook/mapper/BookingMapper.java)

## Repositories

- [AircraftRepository.java](src/main/java/dev/alpha/skybook/repository/AircraftRepository.java)
- [AirportRepository.java](src/main/java/dev/alpha/skybook/repository/AirportRepository.java)
- [FlightRepository.java](src/main/java/dev/alpha/skybook/repository/FlightRepository.java)
- [PassengerRepository.java](src/main/java/dev/alpha/skybook/repository/PassengerRepository.java)
- [BookingRepository.java](src/main/java/dev/alpha/skybook/repository/BookingRepository.java)

## Services

### Interfaces

- [AircraftService.java](src/main/java/dev/alpha/skybook/service/AircraftService.java)
- [AirportService.java](src/main/java/dev/alpha/skybook/service/AirportService.java)
- [FlightService.java](src/main/java/dev/alpha/skybook/service/FlightService.java)
- [PassengerService.java](src/main/java/dev/alpha/skybook/service/PassengerService.java)
- [BookingService.java](src/main/java/dev/alpha/skybook/service/BookingService.java)

### Implementations

- [AircraftServiceImpl.java](src/main/java/dev/alpha/skybook/service/impl/AircraftServiceImpl.java)
- [AirportServiceImpl.java](src/main/java/dev/alpha/skybook/service/impl/AirportServiceImpl.java)
- [FlightServiceImpl.java](src/main/java/dev/alpha/skybook/service/impl/FlightServiceImpl.java)
- [PassengerServiceImpl.java](src/main/java/dev/alpha/skybook/service/impl/PassengerServiceImpl.java)
- [BookingServiceImpl.java](src/main/java/dev/alpha/skybook/service/impl/BookingServiceImpl.java)

## Configuration and Resources

- [application.properties](src/main/resources/application.properties)
- [application.yml](src/main/resources/application.yml)
- [Database migrations](src/main/resources/db/migration/)

## Tests

- [SkybookApplicationTests.java](src/test/java/dev/alpha/skybook/SkybookApplicationTests.java)

## Build and Project Files

- [pom.xml](pom.xml)
- [Maven Wrapper](mvnw)
- [Windows Maven Wrapper](mvnw.cmd)
- [Maven Wrapper Properties](.mvn/wrapper/maven-wrapper.properties)
- [HELP.md](HELP.md)
- [.gitignore](.gitignore)
- [.gitattributes](.gitattributes)

---

# Development Principles

SkyBook follows several architectural principles:

### Separation of concerns

Each layer has a focused responsibility.

```text
Controller  → HTTP
Service     → Business logic
Repository  → Persistence
Mapper      → Object conversion
Entity      → Database model
DTO         → API contract
Exception   → Error representation
```

### Constructor injection

Services and controllers use constructor injection rather than field injection.

### DTO-based API boundaries

Entities are not used as the primary request/response contract. Request and response DTOs define the API boundary.

### Centralized error handling

Application exceptions are handled through a global exception handler instead of duplicating error-response logic across controllers.

### Explicit relationships

JPA relationships are defined directly in the entity model.

---

# Current Project Status

| Area | Status |
|---|---|
| Aircraft module | Complete |
| Airport module | Complete |
| Flight module | Complete |
| Passenger module | Complete |
| Booking CRUD | Complete |
| Booking API testing | Completed |
| Validation | Implemented |
| Global exception handling | Implemented |
| Layered architecture | Implemented |
| Automated test coverage | Can be expanded |
| Advanced airline business rules | Not currently defined |

The project should not be considered production-ready solely because the CRUD modules work. Production hardening, broader automated testing, security, deployment configuration, observability, and domain-specific airline rules can be added as requirements become defined.

---

# Future Improvements

Potential future work includes:

- More comprehensive unit tests
- Service-layer integration tests
- Controller/API integration tests
- OpenAPI/Swagger documentation
- Authentication and authorization
- Role-based access control
- Seat inventory and seat assignment
- Booking capacity rules
- Booking cancellation rules
- Payment integration
- Ticket generation
- Email notifications
- Database migration strategy
- Production logging and monitoring
- Containerization
- CI/CD
- Deployment configuration

These are future possibilities, not assumptions about the current requirements.

---

# License

Add the project's license here when one has been selected.

---

# Author

**MUNEZERO Alpha**

SkyBook is developed as a Java/Spring Boot backend project focused on learning and implementing professional backend architecture, persistence, REST APIs, validation, exception handling, and domain relationships.
