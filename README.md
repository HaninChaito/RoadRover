# 📚 BookWaves Library Management System

![GitHub](https://img.shields.io/badge/Java-17-blue)
![GitHub](https://img.shields.io/badge/JavaFX-17-orange)
![GitHub](https://img.shields.io/badge/NetBeans-IDE-green)
![GitHub](https://img.shields.io/badge/PlantText-UML-lightgrey)

## 🔗 Repository
[GitHub Link](https://github.com/HaninChaito/BookWaves)

---

## ⚙️ Technologies Used
- **Programming Language**: Java, JavaFX (for the interface)
- **Database**: Text files for data storage
- **Tools**: NetBeans, PlantText

---

## 📌 Overview
The **BookWaves Library Management System** is designed to streamline and digitize library operations, making it easier to manage books, users, and transactions. This system replaces manual processes with a digital platform, improving efficiency and user experience.

---

## 👥 User Roles
- **Admins**: Can manage books and users.
- **Normal Users**: Can borrow books, place orders, and maintain a "Want to Read" list.

---

## 🏗️ Design Patterns Used
- **Singleton Pattern**: Ensures a single instance of the database.
- **Factory Pattern**: Creates user objects dynamically (Admin & NormalUser).
- **Decorator Pattern**: Adds book features like "Bestseller" and "New Arrival."
- **Observer Pattern**: Notifies users about book availability changes.
- **Proxy Pattern**: Controls access to book deletion for Admins.
- **Strategy Pattern**: Implements various operations (Search, Borrow, Return).

---

## 🔨 System Features

### 🔹 Admin Features
- **📚 Manage Books**: Add, delete, search, and view books.
- **👥 View Users**: See all registered users.
- **🛒 Manage Orders**: View all placed orders.
- **🌟 Manage Book Features**: Add/Remove "Bestseller" or "New Arrival" tags.

### 🔹 User Features
- **📖 View Books**: Browse available books and featured books.
- **🛍 Buy Books**: Place orders and specify quantities.
- **🔄 Borrow & Return Books**: Includes a penalty system for late returns.
- **📌 "Want to Read" List**: Add/remove books.
- **🔔 Notifications**: Get updates when a book is added.

---

## 📂 Data Structures Used
- **`List<User>`**: Stores user data.
- **`Set<String>`**: Ensures unique usernames.
- **`List<Book>`**: Maintains book data.
- **`LinkedList<Order>`**: Optimized for frequent additions/removals.
- **`Map<String, User>`**: Enables quick user lookup.

---

## 🔥 Key Code Concepts

### 🎯 Generics
- **`loadEntities<T>`**: Reduces code duplication when retrieving users, books, and orders.
- **`addEntity<T>`**: Streamlines adding books and users.

### ⚡ Lambda Expressions
- Used to simplify event handling in `Library.java`.
- Replaces loops with stream operations in `Database.java` (e.g., `userExists()`, `getUserByName()`).

---

## 🎯 Future Improvements
- Improve UI with an enhanced JavaFX interface.
- Add more automation for book recommendations.
- Implement a database instead of text file storage.

---

## 📌 How to Run the Project
1. Clone the repository:
   ```sh
   git clone https://github.com/HaninChaito/BookWaves.git
2. Open the Project in NetBeans
  -Open NetBeans IDE.
  -Go to File > Open Project and navigate to the cloned BookWaves directory.
  -Select the project and click Open.
3. Run the Project
   -In the Projects pane, locate the Library.java file (usually under src/main/java or a similar directory).
    -Right-click on Library.java and select Run File.

