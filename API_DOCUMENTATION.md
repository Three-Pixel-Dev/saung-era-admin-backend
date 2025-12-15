# Saung Era Admin Backend API Documentation

## Table of Contents

1. [Overview](#overview)
2. [Base URL](#base-url)
3. [Authentication](#authentication)
4. [Common Response Codes](#common-response-codes)
5. [Category Management](#category-management)
6. [Product Management](#product-management)
7. [User Management](#user-management)
8. [Order Management](#order-management)
9. [Caching Strategy](#caching-strategy)
10. [Error Handling](#error-handling)

---

## Overview

The Saung Era Admin Backend API provides comprehensive endpoints for managing the e-commerce platform. The API supports CRUD operations for categories, products, users, and order management. All endpoints use Redis caching for improved performance.

**Version:** 1.0.0  
**Base URL:** `http://localhost:9002`  
**API Documentation:** `http://localhost:9002/swagger-ui.html`

---

## Base URL

```
http://localhost:9002/api/admin
```

---

## Authentication

Currently, user identification is done via the `X-User-Id` header (optional). In production, this should be replaced with proper JWT authentication.

**Header:**
```
X-User-Id: 1
```

---

## Common Response Codes

| Code | Description |
|------|-------------|
| 200  | OK - Request successful |
| 201  | Created - Resource created successfully |
| 204  | No Content - Request successful, no response body |
| 400  | Bad Request - Invalid request data |
| 404  | Not Found - Resource not found |
| 500  | Internal Server Error - Server error |

---

## Category Management

### Base Endpoint
```
/api/admin/categories
```

### 1. Get All Categories

Retrieves a list of all active (non-deleted) categories. Results are cached in Redis for 30 minutes.

**Endpoint:** `GET /api/admin/categories`

**Headers:**
```
Content-Type: application/json
```

**Response:** `200 OK`
```json
[
  {
    "id": 1,
    "name": "Men's Clothing",
    "description": "This category contains all men's clothing items",
    "createdAt": "2025-12-14T15:30:00",
    "updatedAt": "2025-12-14T15:30:00",
    "createdBy": {
      "id": 1,
      "name": "Admin User",
      "username": "admin",
      "email": "admin@saungera.com",
      "phoneNumber": "+1234567890"
    },
    "updatedBy": {
      "id": 1,
      "name": "Admin User",
      "username": "admin",
      "email": "admin@saungera.com",
      "phoneNumber": "+1234567890"
    }
  },
  {
    "id": 2,
    "name": "Women's Clothing",
    "description": "This category contains all women's clothing items",
    "createdAt": "2025-12-14T15:31:00",
    "updatedAt": "2025-12-14T15:31:00",
    "createdBy": {
      "id": 1,
      "name": "Admin User",
      "username": "admin",
      "email": "admin@saungera.com",
      "phoneNumber": "+1234567890"
    },
    "updatedBy": {
      "id": 1,
      "name": "Admin User",
      "username": "admin",
      "email": "admin@saungera.com",
      "phoneNumber": "+1234567890"
    }
  }
]
```

---

### 2. Get Category by ID

Retrieves a specific category by its unique identifier. Only active categories can be retrieved.

**Endpoint:** `GET /api/admin/categories/{id}`

**Path Parameters:**
- `id` (Long, required) - Category ID

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Men's Clothing",
  "description": "This category contains all men's clothing items",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T15:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

**Error Response:** `404 Not Found`
```json
{
  "message": "Category not found with id: 1"
}
```

---

### 3. Create Category

Creates a new product category. The category name must be unique among active categories.

**Endpoint:** `POST /api/admin/categories`

**Headers:**
```
Content-Type: application/json
X-User-Id: 1 (optional)
```

**Request Body:**
```json
{
  "name": "Men's Clothing",
  "description": "This category contains all men's clothing items"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Men's Clothing",
  "description": "This category contains all men's clothing items",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T15:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

**Error Response:** `400 Bad Request`
```json
{
  "message": "Category with name 'Men's Clothing' already exists"
}
```

---

### 4. Update Category

Updates an existing active category. The category name must be unique among active categories.

**Endpoint:** `PUT /api/admin/categories/{id}`

**Path Parameters:**
- `id` (Long, required) - Category ID

**Headers:**
```
Content-Type: application/json
X-User-Id: 1 (optional)
```

**Request Body:**
```json
{
  "name": "Men's Fashion",
  "description": "Updated description for men's fashion"
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Men's Fashion",
  "description": "Updated description for men's fashion",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T16:00:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

### 5. Soft Delete Category

Performs a soft delete on a category by setting the deletedAt timestamp. The category can be restored later.

**Endpoint:** `DELETE /api/admin/categories/{id}`

**Path Parameters:**
- `id` (Long, required) - Category ID

**Headers:**
```
X-User-Id: 1 (optional)
```

**Response:** `204 No Content`

---

### 6. Hard Delete Category

Permanently deletes a category from the database. This operation cannot be undone.

**Endpoint:** `DELETE /api/admin/categories/{id}/hard`

**Path Parameters:**
- `id` (Long, required) - Category ID

**Response:** `204 No Content`

---

### 7. Restore Category

Restores a soft-deleted category. The category name must be unique among active categories.

**Endpoint:** `POST /api/admin/categories/{id}/restore`

**Path Parameters:**
- `id` (Long, required) - Category ID

**Headers:**
```
X-User-Id: 1 (optional)
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Men's Clothing",
  "description": "This category contains all men's clothing items",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T16:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

**Error Response:** `400 Bad Request`
```json
{
  "message": "Cannot restore category: A category with name 'Men's Clothing' already exists"
}
```

---

## Product Management

### Base Endpoint
```
/api/admin/products
```

### 1. Get All Products

Retrieves a list of all active products with pagination support.

**Endpoint:** `GET /api/admin/products`

**Query Parameters:**
- `page` (Integer, optional) - Page number (default: 0)
- `size` (Integer, optional) - Page size (default: 20)
- `sort` (String, optional) - Sort field and direction (e.g., "name,asc")

**Response:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "name": "Men's T-Shirt",
      "description": "Comfortable cotton t-shirt",
      "quantity": 100,
      "price": 29.99,
      "discountType": "PERCENTAGE",
      "discountAmount": 10.00,
      "shortDescription": "Premium cotton t-shirt",
      "longDescription": "Made from 100% organic cotton...",
      "weight": 0.2,
      "countryId": 1,
      "categories": [
        {
          "id": 1,
          "name": "Men's Clothing",
          "description": "This category contains all men's clothing items",
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        },
        {
          "id": 2,
          "name": "T-Shirts",
          "description": "All types of t-shirts",
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        }
      ],
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    }
  ],
  "totalElements": 50,
  "totalPages": 3,
  "size": 20,
  "number": 0
}
```

---

### 2. Search Products

Searches products by name, description, or other criteria.

**Endpoint:** `GET /api/admin/products/search`

**Query Parameters:**
- `query` (String, required) - Search query
- `page` (Integer, optional) - Page number (default: 0)
- `size` (Integer, optional) - Page size (default: 20)

**Example:** `GET /api/admin/products/search?query=t-shirt&page=0&size=20`

**Response:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "name": "Men's T-Shirt",
      "description": "Comfortable cotton t-shirt",
      "quantity": 100,
      "price": 29.99,
      "categories": [
        {
          "id": 1,
          "name": "Men's Clothing",
          "description": "This category contains all men's clothing items",
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        }
      ]
    }
  ],
  "totalElements": 5,
  "totalPages": 1
}
```

---

### 3. Get Product by ID

Retrieves a specific product by its unique identifier.

**Endpoint:** `GET /api/admin/products/{id}`

**Path Parameters:**
- `id` (Long, required) - Product ID

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Men's T-Shirt",
  "description": "Comfortable cotton t-shirt",
  "quantity": 100,
  "price": 29.99,
  "discountType": "PERCENTAGE",
  "discountAmount": 10.00,
  "shortDescription": "Premium cotton t-shirt",
  "longDescription": "Made from 100% organic cotton...",
  "weight": 0.2,
  "countryId": 1,
  "categoryIds": [1, 2]
}
```

**Note:** 
- **Request:** Use `categoryIds` array (e.g., `[1, 2]`) to specify which categories to associate with the product
- **Response:** Returns full `categories` array with complete `CategoryResponse` objects containing all category details

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Men's T-Shirt",
  "description": "Comfortable cotton t-shirt",
  "quantity": 100,
  "price": 29.99,
  "discountType": "PERCENTAGE",
  "discountAmount": 10.00,
  "shortDescription": "Premium cotton t-shirt",
  "longDescription": "Made from 100% organic cotton, this t-shirt offers comfort and style.",
  "weight": 0.2,
  "countryId": 1,
  "categories": [
    {
      "id": 1,
      "name": "Men's Clothing",
      "description": "This category contains all men's clothing items",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    },
    {
      "id": 2,
      "name": "T-Shirts",
      "description": "All types of t-shirts",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    }
  ],
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T15:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

### 4. Create Product

Creates a new product.

**Endpoint:** `POST /api/admin/products`

**Headers:**
```
Content-Type: application/json
X-User-Id: 1 (optional)
```

**Request Body:**
```json
{
  "name": "Men's T-Shirt",
  "description": "Comfortable cotton t-shirt",
  "quantity": 100,
  "price": 29.99,
  "discountType": "PERCENTAGE",
  "discountAmount": 10.00,
  "shortDescription": "Premium cotton t-shirt",
  "longDescription": "Made from 100% organic cotton, this t-shirt offers comfort and style.",
  "weight": 0.2,
  "countryId": 1,
  "categoryIds": [1, 2]
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "Men's T-Shirt",
  "description": "Comfortable cotton t-shirt",
  "quantity": 100,
  "price": 29.99,
  "discountType": "PERCENTAGE",
  "discountAmount": 10.00,
  "shortDescription": "Premium cotton t-shirt",
  "longDescription": "Made from 100% organic cotton, this t-shirt offers comfort and style.",
  "weight": 0.2,
  "countryId": 1,
  "categories": [
    {
      "id": 1,
      "name": "Men's Clothing",
      "description": "This category contains all men's clothing items",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    },
    {
      "id": 2,
      "name": "T-Shirts",
      "description": "All types of t-shirts",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    }
  ],
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T15:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

### 5. Update Product

Updates an existing active product.

**Endpoint:** `PUT /api/admin/products/{id}`

**Path Parameters:**
- `id` (Long, required) - Product ID

**Headers:**
```
Content-Type: application/json
X-User-Id: 1 (optional)
```

**Request Body:**
```json
{
  "name": "Men's Premium T-Shirt",
  "description": "Updated description",
  "quantity": 150,
  "price": 34.99,
  "discountType": "FIXED",
  "discountAmount": 5.00,
  "categoryIds": [1, 3]
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Men's Premium T-Shirt",
  "description": "Updated description",
  "quantity": 150,
  "price": 34.99,
  "discountType": "FIXED",
  "discountAmount": 5.00,
  "categories": [
    {
      "id": 1,
      "name": "Men's Clothing",
      "description": "This category contains all men's clothing items",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    },
    {
      "id": 3,
      "name": "Premium",
      "description": "Premium products",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    }
  ],
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T16:00:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

### 6. Soft Delete Product

Performs a soft delete on a product. The product can be restored later.

**Endpoint:** `DELETE /api/admin/products/{id}`

**Path Parameters:**
- `id` (Long, required) - Product ID

**Headers:**
```
X-User-Id: 1 (optional)
```

**Response:** `204 No Content`

---

### 7. Hard Delete Product

Permanently deletes a product from the database. This operation cannot be undone.

**Endpoint:** `DELETE /api/admin/products/{id}/hard`

**Path Parameters:**
- `id` (Long, required) - Product ID

**Response:** `204 No Content`

---

### 8. Restore Product

Restores a soft-deleted product.

**Endpoint:** `POST /api/admin/products/{id}/restore`

**Path Parameters:**
- `id` (Long, required) - Product ID

**Headers:**
```
X-User-Id: 1 (optional)
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "Men's T-Shirt",
  "description": "Comfortable cotton t-shirt",
  "quantity": 100,
  "price": 29.99,
  "discountType": "PERCENTAGE",
  "discountAmount": 10.00,
  "shortDescription": "Premium cotton t-shirt",
  "longDescription": "Made from 100% organic cotton, this t-shirt offers comfort and style.",
  "weight": 0.2,
  "countryId": 1,
  "categories": [
    {
      "id": 1,
      "name": "Men's Clothing",
      "description": "This category contains all men's clothing items",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    }
  ],
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T16:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

## User Management

### Base Endpoint
```
/api/admin/users
```

### 1. Get All Users

Retrieves a list of all active (non-blocked) users with pagination support.

**Endpoint:** `GET /api/admin/users`

**Query Parameters:**
- `page` (Integer, optional) - Page number (default: 0)
- `size` (Integer, optional) - Page size (default: 20)
- `sort` (String, optional) - Sort field and direction (e.g., "name,asc")

**Response:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "name": "John Doe",
      "username": "johndoe",
      "email": "john.doe@example.com",
      "phoneNumber": "+1234567890",
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    }
  ],
  "totalElements": 100,
  "totalPages": 5
}
```

---

### 2. Get User by ID

Retrieves a specific user by their unique identifier.

**Endpoint:** `GET /api/admin/users/{id}`

**Path Parameters:**
- `id` (Long, required) - User ID

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "John Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T15:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

### 3. Create User

Creates a new user account.

**Endpoint:** `POST /api/admin/users`

**Headers:**
```
Content-Type: application/json
X-User-Id: 1 (optional)
```

**Request Body:**
```json
{
  "name": "John Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "password": "SecurePassword123!"
}
```

**Response:** `201 Created`
```json
{
  "id": 1,
  "name": "John Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "phoneNumber": "+1234567890",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T15:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

**Note:** Password is not returned in the response for security reasons.

---

### 4. Update User

Updates an existing active user's information.

**Endpoint:** `PUT /api/admin/users/{id}`

**Path Parameters:**
- `id` (Long, required) - User ID

**Headers:**
```
Content-Type: application/json
X-User-Id: 1 (optional)
```

**Request Body:**
```json
{
  "name": "John Smith",
  "email": "john.smith@example.com",
  "phoneNumber": "+1234567891"
}
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "John Smith",
  "username": "johndoe",
  "email": "john.smith@example.com",
  "phoneNumber": "+1234567891",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T16:00:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

### 5. Block User (Soft Delete)

Blocks a user by performing a soft delete. The user account is not permanently removed and can be unblocked later.

**Endpoint:** `POST /api/admin/users/{id}/block`

**Path Parameters:**
- `id` (Long, required) - User ID

**Headers:**
```
X-User-Id: 1 (optional)
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "John Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "deletedAt": "2025-12-14T16:00:00",
  "deletedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

### 6. Unblock User (Restore)

Unblocks a previously blocked user by restoring their account.

**Endpoint:** `POST /api/admin/users/{id}/unblock`

**Path Parameters:**
- `id` (Long, required) - User ID

**Headers:**
```
X-User-Id: 1 (optional)
```

**Response:** `200 OK`
```json
{
  "id": 1,
  "name": "John Doe",
  "username": "johndoe",
  "email": "john.doe@example.com",
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T16:30:00",
  "createdBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  },
  "updatedBy": {
    "id": 1,
    "name": "Admin User",
    "username": "admin",
    "email": "admin@saungera.com",
    "phoneNumber": "+1234567890"
  }
}
```

---

## Order Management

### Base Endpoint
```
/api/admin/orders
```

### 1. Get All Orders

Retrieves a list of all orders with pagination support. Orders are sorted by ordered date in descending order by default.

**Endpoint:** `GET /api/admin/orders`

**Query Parameters:**
- `page` (Integer, optional) - Page number (default: 0)
- `size` (Integer, optional) - Page size (default: 20)
- `sort` (String, optional) - Sort field and direction (e.g., "orderedDate,desc")
- `status` (String, optional) - Filter by order status
- `userId` (Long, optional) - Filter by user ID
- `startDate` (String, optional) - Filter orders from date (ISO format)
- `endDate` (String, optional) - Filter orders to date (ISO format)

**Example:** `GET /api/admin/orders?page=0&size=20&userId=1&startDate=2025-12-01&endDate=2025-12-31`

**Response:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "userId": 1,
      "userName": "John Doe",
      "userEmail": "john.doe@example.com",
      "totalPrice": 149.99,
      "orderedDate": "2025-12-14T15:30:00",
      "promotionCode": "SAVE10",
      "promotionId": 1,
      "orderItems": [
        {
          "id": 1,
          "name": "Men's T-Shirt",
          "description": "Comfortable cotton t-shirt",
          "quantity": 100,
          "price": 29.99,
          "discountType": "PERCENTAGE",
          "discountAmount": 10.00,
          "shortDescription": "Premium cotton t-shirt",
          "longDescription": "Made from 100% organic cotton...",
          "weight": 0.2,
          "countryId": 1,
          "categories": [
            {
              "id": 1,
              "name": "Men's Clothing",
              "description": "This category contains all men's clothing items",
              "createdAt": "2025-12-14T15:30:00",
              "updatedAt": "2025-12-14T15:30:00",
              "createdBy": {
                "id": 1,
                "name": "Admin User",
                "username": "admin",
                "email": "admin@saungera.com",
                "phoneNumber": "+1234567890"
              },
              "updatedBy": {
                "id": 1,
                "name": "Admin User",
                "username": "admin",
                "email": "admin@saungera.com",
                "phoneNumber": "+1234567890"
              }
            }
          ],
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        },
        {
          "id": 2,
          "name": "Men's Jeans",
          "description": "Classic fit jeans",
          "quantity": 50,
          "price": 89.99,
          "discountType": null,
          "discountAmount": null,
          "shortDescription": "Classic fit denim jeans",
          "longDescription": "Premium denim jeans with classic fit...",
          "weight": 0.5,
          "countryId": 1,
          "categories": [
            {
              "id": 1,
              "name": "Men's Clothing",
              "description": "This category contains all men's clothing items",
              "createdAt": "2025-12-14T15:30:00",
              "updatedAt": "2025-12-14T15:30:00",
              "createdBy": {
                "id": 1,
                "name": "Admin User",
                "username": "admin",
                "email": "admin@saungera.com",
                "phoneNumber": "+1234567890"
              },
              "updatedBy": {
                "id": 1,
                "name": "Admin User",
                "username": "admin",
                "email": "admin@saungera.com",
                "phoneNumber": "+1234567890"
              }
            }
          ],
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        }
      ],
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00"
    }
  ],
  "totalElements": 50,
  "totalPages": 3,
  "size": 20,
  "number": 0
}
```

---

### 2. Get Order by ID

Retrieves a specific order by its unique identifier with full order item details.

**Endpoint:** `GET /api/admin/orders/{id}`

**Path Parameters:**
- `id` (Long, required) - Order ID

**Response:** `200 OK`
```json
{
  "id": 1,
  "userId": 1,
  "userName": "John Doe",
  "userEmail": "john.doe@example.com",
  "totalPrice": 149.99,
  "orderedDate": "2025-12-14T15:30:00",
  "promotionCode": "SAVE10",
  "promotionId": 1,
  "orderItems": [
    {
      "id": 1,
      "name": "Men's T-Shirt",
      "description": "Comfortable cotton t-shirt",
      "quantity": 100,
      "price": 29.99,
      "discountType": "PERCENTAGE",
      "discountAmount": 10.00,
      "shortDescription": "Premium cotton t-shirt",
      "longDescription": "Made from 100% organic cotton, this t-shirt offers comfort and style.",
      "weight": 0.2,
      "countryId": 1,
      "categories": [
        {
          "id": 1,
          "name": "Men's Clothing",
          "description": "This category contains all men's clothing items",
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        }
      ],
      "orderItemQuantity": 2,
      "orderItemPrice": 29.99,
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    },
    {
      "id": 2,
      "name": "Men's Jeans",
      "description": "Classic fit jeans",
      "quantity": 50,
      "price": 89.99,
      "discountType": null,
      "discountAmount": null,
      "shortDescription": "Classic fit denim jeans",
      "longDescription": "Premium denim jeans with classic fit and comfort.",
      "weight": 0.5,
      "countryId": 1,
      "categories": [
        {
          "id": 1,
          "name": "Men's Clothing",
          "description": "This category contains all men's clothing items",
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        }
      ],
      "orderItemQuantity": 1,
      "orderItemPrice": 89.99,
      "createdAt": "2025-12-14T15:30:00",
      "updatedAt": "2025-12-14T15:30:00",
      "createdBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      },
      "updatedBy": {
        "id": 1,
        "name": "Admin User",
        "username": "admin",
        "email": "admin@saungera.com",
        "phoneNumber": "+1234567890"
      }
    }
  ],
  "createdAt": "2025-12-14T15:30:00",
  "updatedAt": "2025-12-14T15:30:00"
}
```

---

### 3. Get Orders by User

Retrieves all orders for a specific user.

**Endpoint:** `GET /api/admin/orders/user/{userId}`

**Path Parameters:**
- `userId` (Long, required) - User ID

**Query Parameters:**
- `page` (Integer, optional) - Page number (default: 0)
- `size` (Integer, optional) - Page size (default: 20)

**Response:** `200 OK`
```json
{
  "content": [
    {
      "id": 1,
      "userId": 1,
      "totalPrice": 149.99,
      "orderedDate": "2025-12-14T15:30:00",
      "orderItems": [
        {
          "id": 1,
          "name": "Men's T-Shirt",
          "description": "Comfortable cotton t-shirt",
          "quantity": 100,
          "price": 29.99,
          "categories": [
            {
              "id": 1,
              "name": "Men's Clothing",
              "description": "This category contains all men's clothing items",
              "createdAt": "2025-12-14T15:30:00",
              "updatedAt": "2025-12-14T15:30:00",
              "createdBy": {
                "id": 1,
                "name": "Admin User",
                "username": "admin",
                "email": "admin@saungera.com",
                "phoneNumber": "+1234567890"
              },
              "updatedBy": {
                "id": 1,
                "name": "Admin User",
                "username": "admin",
                "email": "admin@saungera.com",
                "phoneNumber": "+1234567890"
              }
            }
          ],
          "createdAt": "2025-12-14T15:30:00",
          "updatedAt": "2025-12-14T15:30:00",
          "createdBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          },
          "updatedBy": {
            "id": 1,
            "name": "Admin User",
            "username": "admin",
            "email": "admin@saungera.com",
            "phoneNumber": "+1234567890"
          }
        }
      ]
    }
  ],
  "totalElements": 5,
  "totalPages": 1
}
```

---

## Caching Strategy

The Admin Backend API uses Redis caching to improve performance:

### Category Cache
- **TTL:** 30 minutes
- **Cache Keys:**
  - `categories::all` - All categories list
  - `categories::category:{id}` - Individual category by ID
- **Cache Eviction:** Automatically cleared on create, update, delete, or restore operations

### Product Cache
- **TTL:** 1 hour (default)
- **Cache Keys:**
  - `products::all` - All products list (with pagination)
  - `products::product:{id}` - Individual product by ID
  - `products::search:{query}` - Search results
- **Cache Eviction:** Automatically cleared on create, update, delete, or restore operations

### User Cache
- **TTL:** 1 hour (default)
- **Cache Keys:**
  - `users::all` - All users list (with pagination)
  - `users::user:{id}` - Individual user by ID
- **Cache Eviction:** Automatically cleared on create, update, or block/unblock operations

### Order Cache
- **TTL:** 30 minutes
- **Cache Keys:**
  - `orders::all` - All orders list (with pagination and filters)
  - `orders::order:{id}` - Individual order by ID
  - `orders::user:{userId}` - Orders by user
- **Cache Eviction:** Automatically cleared when new orders are created

---

## Error Handling

### Standard Error Response Format

```json
{
  "timestamp": "2025-12-14T15:30:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Category with name 'Men's Clothing' already exists",
  "path": "/api/admin/categories"
}
```

### Common Error Scenarios

#### 400 Bad Request
- Invalid request data
- Validation errors
- Business rule violations (e.g., duplicate names)

#### 404 Not Found
- Resource not found
- Resource has been deleted/blocked

#### 500 Internal Server Error
- Server-side errors
- Database connection issues
- Cache errors

---

## Request/Response Examples

### Complete Category Workflow

1. **Create Category**
```bash
POST /api/admin/categories
Content-Type: application/json
X-User-Id: 1

{
  "name": "Electronics",
  "description": "Electronic products and gadgets"
}
```

2. **Get All Categories**
```bash
GET /api/admin/categories
```

3. **Update Category**
```bash
PUT /api/admin/categories/1
Content-Type: application/json
X-User-Id: 1

{
  "name": "Electronics & Gadgets",
  "description": "Updated description"
}
```

4. **Soft Delete Category**
```bash
DELETE /api/admin/categories/1
X-User-Id: 1
```

5. **Restore Category**
```bash
POST /api/admin/categories/1/restore
X-User-Id: 1
```

---

## Notes

1. **Soft Delete vs Hard Delete:**
   - Soft delete sets `deletedAt` timestamp and allows restoration
   - Hard delete permanently removes the record from the database

2. **User Blocking:**
   - Blocking a user is equivalent to soft delete
   - Unblocking a user is equivalent to restore
   - Blocked users cannot log in or place orders

3. **Cache Invalidation:**
   - All cache operations are automatic
   - Cache is cleared immediately after write operations
   - No manual cache management required

4. **Pagination:**
   - Default page size: 20
   - Maximum page size: 100
   - Page numbers start from 0

5. **Date Formats:**
   - All dates use ISO 8601 format: `YYYY-MM-DDTHH:mm:ss`
   - Example: `2025-12-14T15:30:00`

---

## Support

For API support and questions:
- **Email:** admin@saungera.com
- **Documentation:** http://localhost:9002/swagger-ui.html
- **API Docs:** http://localhost:9002/api-docs

---

**Last Updated:** December 16, 2025  
**API Version:** 1.0.0
