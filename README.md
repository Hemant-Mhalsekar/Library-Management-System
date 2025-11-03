# 📚 Library Management System (Java + MySQL)

A full-featured **Library Management System** built using **Java** and **MySQL**, following the **MVC (Model–DAO–Service–UI)** architecture pattern.  
This system allows librarians to manage **books**, **members**, and **transactions** (book issue and return) with persistent data storage and fine calculation.

---

## 🧩 Table of Contents

1. [Overview](#overview)
2. [Features](#features)
3. [System Architecture](#system-architecture)
4. [Technologies Used](#technologies-used)
5. [Project Structure](#project-structure)
6. [Database Setup](#database-setup)
7. [Maven Dependencies](#maven-dependencies)
8. [How to Run the Project](#how-to-run-the-project)
9. [Example Console Output](#example-console-output)
10. [Future Enhancements](#future-enhancements)
11. [Author](#author)
12. [License](#license)

---

## 🧠 Overview

The **Library Management System** simplifies and automates day-to-day operations of a library.  
It allows librarians to:

- Add and manage books
- Register members
- Issue and return books
- Track transaction history
- Automatically calculate fines for late returns

All operations are connected to a **MySQL database**, ensuring that data is stored and retrieved reliably.

---

## 🚀 Features

### 👤 Librarian Functionalities
- Add new books to the library  
- Add new members (Students/Readers)  
- Issue and return books  
- Calculate fines automatically  
- View all transactions and members  
- View available and issued books  

### 🧩 System Highlights
- Clean architecture (Model–DAO–Service–UI)  
- JDBC connection with MySQL  
- Auto-incrementing primary keys for all tables  
- Error handling and input validation  
- Console-based user interface  
- Well-structured, modular code  

---

## 🏗️ System Architecture

```
┌───────────────────────────────────────────────────┐
│                 UI Layer                          │
│          (LibraryApp.java)                        │
│    ⇅ user interacts through console               │
├───────────────────────────────────────────────────┤
│               Service Layer                       │
│  (BookService, MemberService, TransactionService) │
│   ⇅ contains business logic and validation        │
├───────────────────────────────────────────────────┤
│                 DAO Layer                         │
│    (BookDAO, MemberDAO, TransactionDAO)           │
│   ⇅ handles SQL queries and DB interaction        │
├───────────────────────────────────────────────────┤
│                 Model Layer                       │
│  (Book, Member, Transaction, Librarian)           │
│   ⇅ represents entities and data structure        │
└───────────────────────────────────────────────────┘
```

---

## 💻 Technologies Used

| Category | Technology |
|-----------|-------------|
| Language | Java 17+ |
| Database | MySQL 8 |
| Build Tool | Maven |
| Architecture | MVC |
| IDE | IntelliJ IDEA / Eclipse |
| Libraries | JDBC (MySQL Connector) |

---

## 📁 Project Structure

```
Library Management System/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/library/
│       │       ├── model/          # Entity classes (Book, Member, Librarian, Transaction)
│       │       ├── dao/            # Data Access Layer (Database queries)
│       │       ├── service/        # Business Logic Layer
│       │       └── ui/             # User Interface (LibraryApp.java)
│       └── resources/
│           └── application.properties (optional)
├── pom.xml                          # Maven dependencies
└── README.md
```

---

## 🧩 Database Setup

### Step 1 — Create Database
```sql
CREATE DATABASE library_db;
USE library_db;
```

### Step 2 — Create Tables
```sql
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_name VARCHAR(100),
    book_author VARCHAR(100),
    book_availability BOOLEAN
);

CREATE TABLE members (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    member_name VARCHAR(100),
    member_type VARCHAR(50),
    membership_date DATE
);

CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    member_id INT,
    issue_date DATE,
    return_date DATE,
    fine_amount DOUBLE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (member_id) REFERENCES members(member_id)
);
```

✅ Each table uses `AUTO_INCREMENT` for unique IDs.

---

## ⚙️ Maven Dependencies

Make sure your `pom.xml` includes the **MySQL Connector** dependency:

```xml
<dependencies>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <version>8.0.33</version>
    </dependency>
</dependencies>
```

---

## 🖥️ How to Run the Project

### Step 1 — Clone or Download
```bash
git clone https://github.com/Hemant-Mhalsekar/Library-Management-System.git
```

### Step 2 — Configure Database Connection
Edit `DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/library_db";
private static final String USER = "root";
private static final String PASSWORD = "your_password";
```

### Step 3 — Build and Run
1. Open the project in IntelliJ IDEA or Eclipse  
2. Rebuild the project (Maven → Reload Project)  
3. Run the `LibraryApp.java` file  

### Step 4 — Use the Console Menu
```
============== LIBRARY MANAGEMENT SYSTEM =============
1. Add Book
2. View All Books
3. Add Member
4. View All Members
5. Issue Book
6. Return Book
7. View All Transactions
8. Exit
```

---

## 📄 Example Console Output

```
============== LIBRARY MANAGEMENT SYSTEM =============
1. Add Book
2. View All Books
3. Add Member
4. View All Members
5. Issue Book
6. Return Book
7. View All Transactions
8. Exit
Enter your choice: 1
Enter Book Name: Atomic Habits
Enter Book Author: James Clear
Book added successfully! Generated Book ID: 1
```

```
Enter your choice: 5
Enter Book ID: 1
Enter Member ID: 2
Book issued successfully! Transaction added successfully! ID: 4
```

---

## 🌟 Future Enhancements

- ✅ Add librarian login and authentication  
- ✅ GUI version using **JavaFX** or **Swing**  
- ✅ Search and filter functionality  
- ✅ Export transaction reports to PDF/Excel  
- ✅ Email or SMS notifications for due dates  
- ✅ Cloud-based deployment (AWS / Firebase)  

---

## 👨‍💻 Author

**Hemant Mhalsekar**  
🎓 MCA (Artificial Intelligence & Machine Learning) — Jain (Deemed-to-be University)  
💼 Interests: Software Development, Web Development, AI/ML  
🌍 Location: Goa / Bangalore  
📧 Email: hemantmhalsekar1@gmail.com  
🔗 [LinkedIn Profile](https://www.linkedin.com/in/hemant-mhalsekar-464a50244/)

---

## 🪪 License

This project is open-source and available under the [MIT License](LICENSE).

---

### ⭐ Show Your Support

If you find this project helpful, please ⭐ star the repository — it motivates further development!
