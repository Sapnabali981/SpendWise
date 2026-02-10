# 💰 SpendWise – Expense Tracker Backend

Project Overview

SpendWise is a **Spring Boot REST API** application that helps users manage and track their daily expenses efficiently.
It provides features like expense categorization, pagination, sorting, monthly reports, and date range summaries.

This project demonstrates strong backend development concepts including **REST API design, database integration, exception handling, pagination, sorting, and cloud deployment.**

---

## 🚀 Live Project Links

* 🌐 **Live API:** [https://spendwise-txym.onrender.com](https://spendwise-txym.onrender.com)
* 📘 **Swagger Documentation:** [https://spendwise-txym.onrender.com/swagger-ui/index.html](https://spendwise-txym.onrender.com/swagger-ui/index.html)
* 💻 **GitHub Repository:** [https://github.com/Sapnabali981/SpendWise](https://github.com/Sapnabali981/SpendWise)

---

## 🛠️ Tech Stack

### Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Hibernate
* Maven

### Database

* PostgreSQL (Neon Cloud Database)

### Deployment

* Render Cloud Platform
* Docker

### API Documentation

* Swagger / OpenAPI

---

## ✨ Features

### ✅ Expense Management

* Create Expense
* Update Expense
* Soft Delete & Restore Expense
* Fetch Expense by ID

---

### ✅ Pagination & Sorting

* Fetch all expenses with pagination
* Sorting by:

  * Amount
  * Expense Date
  * Created Date
* Fetch expenses user-wise
* Fetch expenses category-wise

---

### ✅ Expense Analytics

* Total Expense by User
* Total Expense by Category
* Monthly Expense Summary
* Date Range Expense Summary

---

### ✅ Exception Handling

* Global Exception Handling
* Custom Exception Classes
* Input Validation

---

## 📂 API Endpoints

### Expense APIs

| Method | Endpoint                     | Description                      |
| ------ | ---------------------------- | -------------------------------- |
| POST   | `/expense/addExpense`        | Add new expense                  |
| GET    | `/expense/getAllExpense`     | Get all expenses with pagination |
| GET    | `/expense/fetchById/{id}`    | Get expense by ID                |
| GET    | `/expense/fetchByUserId`     | Get expenses by user             |
| GET    | `/expense/fetchByCategoryId` | Get expenses by category         |
| PUT    | `/expense/updateById/{id}`   | Update expense                   |
| DELETE | `/expense/deleteById/{id}`   | Soft delete expense              |
| PUT    | `/expense/restore/{id}`      | Restore deleted expense          |

---

### Reports & Summary APIs

| Method | Endpoint                                            | Description               |
| ------ | --------------------------------------------------- | ------------------------- |
| GET    | `/expense/getTotalExpenseByUserId/{userId}`         | User expense summary      |
| GET    | `/expense/getTotalExpenseByCategoryId/{categoryId}` | Category expense summary  |
| GET    | `/expense/monthly-summary/{userId}`                 | Monthly expense report    |
| GET    | `/expense/date-summary`                             | Date range expense report |

---

## 📖 Swagger Documentation

Swagger UI provides interactive API testing and documentation.

👉 Access here:
[https://spendwise-txym.onrender.com/swagger-ui/index.html](https://spendwise-txym.onrender.com/swagger-ui/index.html)

---

## 🗄️ Database Schema

The project uses PostgreSQL with entities:

* Users
* Category
* Expense

Relationships:

* One User → Multiple Expenses
* One Category → Multiple Expenses

---

## 🔐 Environment Configuration

Sensitive credentials are stored using environment variables.

```
SPRING_DATASOURCE_URL
SPRING_DATASOURCE_USERNAME
SPRING_DATASOURCE_PASSWORD
PORT
```

---

## ▶️ How to Run Locally

### Step 1 – Clone Repository

```
git clone https://github.com/Sapnabali981/SpendWise.git
```

### Step 2 – Configure Database

Update `application.properties` with PostgreSQL credentials.

---

### Step 3 – Build Project

```
mvn clean install
```

---

### Step 4 – Run Application

```
mvn spring-boot:run
```

---

## 📊 Project Highlights

* Clean layered architecture (Controller → Service → Repository)
* DTO based request handling
* Pagination & Sorting using Spring Data JPA
* Soft delete implementation
* Production deployment using Docker & Render
* Cloud PostgreSQL integration
* Swagger API documentation

---

## 👩‍💻 Author

**Sapna Bali**
Java Backend Developer
📧 [sapnabali45@gmail.com](mailto:sapnabali45@gmail.com)
🔗 GitHub: [https://github.com/Sapnabali981](https://github.com/Sapnabali981)

---
