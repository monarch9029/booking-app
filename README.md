🚛 Load & Booking Management System
A backend system for efficiently managing load postings and transporter bookings. Built with Spring Boot and PostgreSQL, optimized for performance, security, and scalability.

📦 Features
✅ Load Management

Create, update, view, and delete loads

Filter loads by shipperId, truckType, etc.

Automatically timestamps and sets load status

✅ Booking Management

Create, update, view, and delete bookings

Status transitions: PENDING → ACCEPTED/REJECTED

Automatically sets Load to BOOKED when a booking is made

Cancels Load if booking is deleted

✅ Security

Basic Auth authentication

CSRF disabled for API usage

✅ Robustness

Centralized exception handling

Logging with SLF4J

📁 Tech Stack
Java 17

Spring Boot 3

Spring Data JPA

PostgreSQL

Spring Security

SLF4J (Logging)

IntelliJ IDEA

Postman (for testing)

🧩 Database Schema (Simplified)
Load
json
Copy
Edit
{
  "id": "UUID",
  "shipperId": "String",
  "facility": {
    "loadingPoint": "String",
    "unloadingPoint": "String",
    "loadingDate": "Timestamp",
    "unloadingDate": "Timestamp"
  },
  "productType": "String",
  "truckType": "String",
  "noOfTrucks": "int",
  "weight": "double",
  "comment": "String",
  "datePosted": "Timestamp",
  "status": "POSTED | BOOKED | CANCELLED"
}
Booking
json
Copy
Edit
{
  "id": "UUID",
  "load": {
    "id": "UUID"
  },
  "transporterId": "String",
  "proposedRate": "double",
  "comment": "String",
  "status": "PENDING | ACCEPTED | REJECTED",
  "requestedAt": "Timestamp"
}
🔐 Authentication
This app uses Basic Auth. Add credentials in Postman under the Authorization tab.

Username: admin

Password: admin123

🛠️ API Endpoints
Load Management
POST /load – Create a new load

GET /load – Fetch all loads (with optional filters)

GET /load/{loadId} – Get load by ID

PUT /load/{loadId} – Update a load

DELETE /load/{loadId} – Delete a load

Booking Management
POST /booking – Create a new booking

GET /booking – Fetch all bookings (filter by shipperId/transporterId)

GET /booking/{bookingId} – Get booking by ID

PUT /booking/{bookingId} – Update booking status

DELETE /booking/{bookingId} – Cancel booking and mark load as CANCELLED

🧪 How to Test in Postman
Sample Booking Payload:
json
Copy
Edit
{
  "load": {
    "id": "YOUR-LOAD-ID"
  },
  "transporterId": "TRANS123",
  "proposedRate": 18000.0,
  "comment": "Available tomorrow"
}
Don’t forget to set:

Content-Type: application/json

Authorization: Basic Auth with your credentials

