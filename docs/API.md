# API Documentation

## Overview
The Expense Tracker API provides RESTful endpoints for managing personal expenses with cloud-native features.

## Base URL
```
http://localhost:8080/api/v1
```

## Authentication
Currently uses simple User-Id header for user identification.
```
User-Id: your-user-id
```

## Endpoints

### Health Check
```http
GET /expenses/health
```

### Create Expense
```http
POST /expenses
Content-Type: application/json
User-Id: user123

{
  "amount": 25.50,
  "category": "Food",
  "description": "Lunch at restaurant"
}
```

### Get User Expenses
```http
GET /expenses?page=0&size=20
User-Id: user123
```

### Get Specific Expense
```http
GET /expenses/{expenseId}
User-Id: user123
```

### Update Expense
```http
PUT /expenses/{expenseId}
Content-Type: application/json
User-Id: user123

{
  "amount": 30.00,
  "category": "Food",
  "description": "Updated lunch cost"
}
```

### Delete Expense
```http
DELETE /expenses/{expenseId}
User-Id: user123
```

### Get Analytics
```http
GET /expenses/analytics?period=monthly
User-Id: user123
```

## Response Format

### Success Response
```json
{
  "expenseId": "uuid",
  "amount": 25.50,
  "category": "Food",
  "description": "Lunch",
  "status": "ACTIVE",
  "createdDate": "2025-08-07T12:00:00",
  "updatedDate": "2025-08-07T12:00:00"
}
```

### Error Response
```json
{
  "timestamp": "2025-08-07T12:00:00",
  "status": 400,
  "error": "Validation Failed",
  "fieldErrors": {
    "amount": "Amount is required"
  }
}
```

## Status Codes
- `200` - Success
- `201` - Created
- `204` - No Content (for deletes)
- `400` - Bad Request (validation errors)
- `404` - Not Found
- `500` - Internal Server Error
