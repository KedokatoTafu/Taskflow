# 📝 TaskFlow - Todo List Management

A lightweight desktop application designed to manage personal tasks efficiently. Built entirely with Java (Swing) and SQLite, this project strictly follows the MVC (Model-View-Controller) Architecture to ensure clean code and maintainability.

# 🛠 Technologies

* Java SE
* Java Swing
* SQLite
* JDBC
* JUnit 4

# ✨ Features

✅ Task Management
* Core CRUD: Full management for tasks, including adding new tasks, editing titles, and deleting.
* Status Tracking: One-click toggle to mark tasks as Completed or Uncompleted.

🔍 Search & Filter
* Smart Search: Quickly find tasks by typing keywords.
* State Filtering: Filter the task list by All, Completed, or Uncompleted statuses.

📊 Organization
* Auto-sorting: Newly created tasks are automatically pushed to the top of the list for better visibility.

# 🚀 The Process

* Database Design: Implemented a serverless approach using SQLite, automatically generating the `todos` table and ensuring zero-configuration for the end-user.
* Architectural Setup: I established the MVC Architecture:
  * Model: To map database rows into Java Objects.
  * DAO (Data Access Object): To handle raw SQL queries, PreparedStatements (preventing SQL Injection), and JDBC connections.
  * Controller: To handle data validation, listen to UI events, and coordinate between the View and DAO.
  * View: To design the Java Swing GUI with responsive Layout Managers.

# 🧠 What I Learned

* Software Architecture: I understood why separating database logic (DAO) from the view (GUI) via a Controller is crucial for long-term maintenance and scaling.
* Embedded Database: Learned how to integrate SQLite to create a standalone application that doesn't require complex database server setups (like MySQL/SQL Server).
* Automated Testing: Applied testing mindset to development by using JUnit 4 to write Unit Tests covering both happy paths and negative/edge cases for the database operations.

# 🌱 Overall Growth

This project transitioned me from writing simple algorithms to building a well-structured, production-ready application. It taught me how to handle user input validation, manage internal database states, and the immense value of writing automated tests to guarantee core logic stability before touching the UI.

# 🔮 How can it be improved?

* Migration to Web: Rewrite the backend using Spring Boot (REST API) and the frontend with a modern framework to make it accessible via browser.
* Pagination: Implement SQL `LIMIT` and `OFFSET` to handle displaying thousands of tasks efficiently without overloading the UI.
* UI/UX Enhancements: Apply modern Look and Feel libraries (like FlatLaf) to make the Swing interface look like a native Windows 11/macOS app.

# 🏃 Running the Project

To run the project in your local environment, follow these steps:
1. Clone the repository to your local machine (or extract the `.zip` file).
2. Open the project in your preferred IDE (Eclipse/IntelliJ).
3. Ensure `sqlite-jdbc` and `junit-4` libraries are added to the project's Classpath.
4. Run `Main.java` inside `src/com/taskflow/Main.java` to start the application.
* Note: You DO NOT need to install MySQL or any database server. The application will automatically generate a `taskflow.db` file in the root folder upon first run.
* Alternatively: Just double-click the provided `TaskFlow.jar` file in the Releases/Exported folder to run the app instantly.
