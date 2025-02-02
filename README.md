# Advanced Student Management System

## 📌 Overview
The **Advanced Student Management System** is a Java-based application for managing student records, built using **Swing** for the UI and **SQLite** for database storage. The project follows a **MVC (Model-View-Controller) architecture**, with clear separations between:
- **GUI (Swing-based)**
- **Business Logic (StudentManager)**
- **Database Layer (DatabaseHelper)**

## 🚀 Features
- **Add, update, delete, and view students** with ID, Name, Age, and Grade.
- **SQLite database support** (stored locally).
- **Data validation** to prevent incorrect input.
- **Exception handling** for database errors and invalid operations.
- **Maven-based build system** for easy dependency management.

## 🛠️ Technologies Used
- **Java (JDK 17)** – Main programming language.
- **Swing (JFrame, JTable, JButton, etc.)** – GUI development.
- **SQLite (via JDBC)** – Embedded database for local data storage.
- **Maven** – Dependency management and project build automation.

## 📂 Database Setup

- **No manual setup is required!** The SQLite database (`students.db`) is automatically created in the `database/` folder when the application runs for the first time.
- If needed, you can manually create the `students` table using:
```sql
CREATE TABLE students (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER NOT NULL,
    grade REAL NOT NULL
);
```
## 👨‍💻 Author
- **Cansın Güler 53224**

## 📜 License
- **This project is open-source under the MIT License.**
