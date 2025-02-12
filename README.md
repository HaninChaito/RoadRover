📚 BookWaves Library Management System
🔹 Repository
GitHub Link

⚙️ Technologies Used
Programming Language: Java, JavaFX (for the interface)
Database: Using text files to store data
Tools: NetBeans, PlantText
📌 Introduction
The BookWaves Library system aims to address the inefficiencies of manual library operations by providing a digital platform for managing books, users, and transactions.

👥 User Roles
Admins: Can manage books and users.
Normal Users: Can borrow books, place orders, and maintain a "Want to Read" list.
🏗️ Design Patterns Used
Singleton Pattern - Ensures a single instance of the database.
Factory Pattern - Creates user objects dynamically (Admin & NormalUser).
Decorator Pattern - Adds book features like "Bestseller" and "New Arrival."
Observer Pattern - Notifies users about book availability changes.
Proxy Pattern - Controls access to book deletion for Admins.
Strategy Pattern - Implements various operations (Search, Borrow, Return).
🔨 System Features
🔹 Admin Features
📚 Manage Books - Add, delete, search, and view books.
👥 View Users - See all registered users.
🛒 Manage Orders - View all placed orders.
🌟 Manage Book Features - Add/Remove "Bestseller" or "New Arrival" tags.
🔹 User Features
📖 View Books - Browse available books and featured books.
🛍 Buy Books - Place orders and specify quantities.
🔄 Borrow & Return Books - Includes a penalty system for late returns.
📌 "Want to Read" List - Add/remove books.
🔔 Notifications - Get updates when a book is added.
📂 Data Structures Used
List<User> - Stores user data.
Set<String> - Ensures unique usernames.
List<Book> - Maintains book data.
LinkedList<Order> - Optimized for frequent additions/removals.
Map<String, User> - Enables quick user lookup.
🔥 Key Code Concepts
🎯 Generics
loadEntities<T> - Reduces code duplication when retrieving users, books, and orders.
addEntity<T> - Streamlines adding books and users.
⚡ Lambda Expressions
Used to simplify event handling in Library.java.
Replaces loops with stream operations in Database.java (e.g., userExists(), getUserByName()).
🎯 Future Improvements
Improve UI with an enhanced JavaFX interface.
Add more automation for book recommendations.
Implement a database instead of text file storage.
📌 How to Run the Project
Clone the repository:
sh
Copy
Edit
git clone https://github.com/HaninChaito/BookWaves.git
Open NetBeans and load the project.
Run the Library.java file to start the system.
📌 Class Diagram Overview
Book: Represents book details.
User: Base class for all users.
Admin: Manages books & users.
NormalUser: Can borrow books and place orders.
Database: Singleton class handling data storage.
Order & Borrowing: Tracks book orders and borrow records.
📜 License
This project is open-source. Feel free to modify and improve!
