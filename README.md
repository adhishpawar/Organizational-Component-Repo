
# 📦 Organizational Component Repository

A Spring Boot 3.5.3-based web application for managing reusable software components across projects in an organization. Built with role-based access (Admin/Employee), JWT authentication, Bucket4J rate limiting, and dynamic search capabilities.

---

## 🚀 Features

- ✅ **User Roles:** Admin and Employee
- 🔐 **JWT Authentication & Authorization**
- ⚙️ **Component Repository:**
  - Create, Update, Enable/Disable components (Admin only)
  - Global search across all fields (Employee + Admin)
  - Tags, Description, Technology stack stored per component
- 🔄 **Rate Limiting** (Bucket4J per IP or JWT):
  - 20 requests/minute on `/components/*` APIs
- 💾 **Database Layer**: Optimized queries, Indexing supported
- 🔍 **Search Endpoints**:
  - By name, description, technology, tag, or global
- 📄 **Exception Handling:** Centralized using `@RestControllerAdvice`

---

## 🛠️ Tech Stack

| Layer          | Technology            |
|----------------|------------------------|
| Backend        | Spring Boot 3.5.5      |
| Security       | Spring Security + JWT  |
| Rate Limiting  | Bucket4J               |
| Build Tool     | Maven                  |
| Database       | MySQL / PostgreSQL     |

---

## 🧑‍💻 Roles

### 🛡️ Admin:
- Register/login
- Add/update/disable/enable components
- Search components
- View disabled components

### 👨‍💼 Employee:
- Register/login
- Search components
- View component details

---

## 📬 API Endpoints

### 🔐 Auth

| Endpoint              | Method | Description         |
|-----------------------|--------|---------------------|
| `/auth/register`      | POST   | Register user       |
| `/auth/login`         | POST   | Login and get token |

### 🧱 Components

| Endpoint                             | Method | Access       | Description                     |
|--------------------------------------|--------|--------------|---------------------------------|
| `/components/create`                 | POST   | Admin Only   | Add a new component             |
| `/components/update/{id}`           | PUT    | Admin Only   | Update component details        |
| `/components/disable/{id}`          | PUT    | Admin Only   | Disable a component             |
| `/components/enable/{id}`           | PUT    | Admin Only   | Enable a component              |
| `/components/{id}`                  | GET    | All users    | View component by ID            |
| `/components/search/name`           | GET    | All users    | Search by name                  |
| `/components/search/description`    | GET    | All users    | Search by description           |
| `/components/search/technology`     | GET    | All users    | Search by technology            |
| `/components/search/tag`            | GET    | All users    | Search by tag                   |
| `/components/search/global`         | GET    | All users    | Global search across all fields |
| `/components/admin/disabled`        | GET    | Admin Only   | Get disabled components         |

---

## 🔒 JWT Usage

All protected endpoints require the following header:
```
Authorization: Bearer <your-token>
```

Use `/auth/login` to retrieve a valid JWT.

---

## 🧪 Testing Rate Limiting (Bucket4J)

To test:
```bash
for i in {1..25}; do
  echo "Request $i"
  curl -H "Authorization: Bearer <your-token>" http://localhost:8080/components/search/global?query=aws
done
```
After 20 requests/min, you'll receive:
```
HTTP 429 Too Many Requests – Rate limit exceeded.
```

---

## 📂 Project Structure

```
├── controller/
├── dto/
├── entity/
├── repository/
├── service/
├── config/
│   └── SecurityConfig.java
├── jwt/
│   ├── JwtFilter.java
│   ├── JwtService.java
│   └── RateLimitingFilter.java
├── exception/
│   └── GlobalExceptionHandler.java
```

---

## 🧠 Advanced Features

- RateLimiter uses either JWT Subject or fallback IP
- Search on multiple fields with optimized DB queries
- Centralized error messages using `ApiResponse<T>`
- `@Valid` and Bean Validation for inputs

---

## ⚙️ Requirements

- Java 17+
- Spring Boot 3.5.3
- Maven
- MySQL
- Postman for testing

---

## 👨‍💼 Developer

**Adhish Pawar**

- Email: adhish@example.com

---

---

## 📌 Future Improvements

- Redis-based distributed rate limiting
- Swagger/OpenAPI integration
- Caching frequent search results
- CI/CD pipeline (GitHub Actions or Jenkins)

---


---

> 💡 **Tip:** Always use the `/auth/login` token in Postman for accessing protected routes!
