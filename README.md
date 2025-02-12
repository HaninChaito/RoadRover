BookWaves Library Management System

Introduction

The BookWaves Library Management System is a digital solution designed to replace manual library operations. It allows Admins to manage books and users, while Normal Users can borrow books, place orders, and maintain a "Want to Read" list.

Technologies Used

Programming Language: Java, JavaFX (for UI)

Database: Text files for data storage

Tools: NetBeans, PlantText

Key Features

Admin Side:

Book Management: Add, delete, search, and view books.

User Management: View all registered users.

Order Management: View all orders placed by users.

Book Features: Add or remove labels like "New Arrival" or "Bestseller."

Client Side:

View Books: Browse available books and highlighted books (Bestseller, New Arrival).

Buy Books: Purchase books and specify quantity.

Borrow & Return: Borrow books, return them, and calculate penalties for late returns.

Want to Read List: Add/remove books from a reading list.

Notifications: Users are notified when new books are added.

Design Patterns Implemented

Singleton Pattern: Ensures only one instance of the database exists, maintaining data integrity.

Factory Pattern: Used for dynamic user creation (Admin and NormalUser roles).

Decorator Pattern: Dynamically adds features like "Bestseller" or "New Arrival" to books.

Observer Pattern: Notifies users about book availability changes.

Proxy Pattern: Restricts access to sensitive actions like deleting books (only Admins allowed).

Strategy Pattern: Implements different operations (Search, Borrow, Return, etc.) flexibly.

Collections Used

List: Maintains users with order flexibility.

Set usernames: Ensures username uniqueness.

List books: Stores books while maintaining order.

Set booknames: Ensures book name uniqueness.

LinkedList orders: Optimized for frequent additions/removals.

Map<String, User> userLookupMap: Enables quick user retrieval.

Generics Usage

loadEntities(): Generic method to load users, books, and orders, reducing code duplication.

addEntity(): Generic method to add books and users, improving flexibility and maintainability.

Lambda Expressions

Used in Library.java for event handling, replacing verbose ActionListener classes.

Used in Database.java to optimize data operations with stream processing (e.g., userExists(), getUserByName(), getBook()).

Class Overview

Book: Represents a book with attributes like name, author, publisher, and quantity.

User: Base class for users with attributes like name, email, and password.

Admin: Extends User, granting privileges to manage the library.

NormalUser: Extends User, allowing book borrowing and order placement.

Borrowing: Tracks transactions, including borrowing and return dates.

Order: Represents book purchase orders.

WantToRead: Tracks users' reading lists.

Database: Manages data storage using the Singleton pattern.

IOOperation: Interface for handling operations like searching, borrowing, and returning books.

Conclusion

The BookWaves Library Management System is a robust and scalable library solution. Its modular design and use of modern software patterns ensure flexibility and maintainability. Future improvements could include advanced features and UI enhancements.

How to Run the Project

Clone the repository from GitHub.

Open the project in NetBeans.

Compile and run the project.
