package Library;

import java.util.ArrayList;
import java.util.List;


/*
Subject (Library class):

Maintains a list of observers (List<LibraryObserver>).

Provides methods to register, remove, and notify observers.

Observer (User class):

Implements the LibraryObserver interface.

Defines the update method to handle notifications.

Notification Mechanism:

When the Admin adds a book, the Library class notifies all registered observers (users).
*/

/*
for:
Avoid Re-Registering Users:

Currently, every time a book is added, all users are re-registered as observers. This is redundant if the users are already registered.

To fix this, you can register users as observers only once (e.g., when they are added to the database or when they log in).
*/

/*
Yes, the Observer Pattern is working correctly in your case. All users in the database are notified when a new book is added, 
and the notifications are displayed as expected. However, you can optimize the code to avoid re-registering users and improve efficiency.
*/
public class Libraryy implements LibrarySubject{
     private List<LibraryObserver> observers = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    @Override
    public void registerObserver(LibraryObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(LibraryObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (LibraryObserver observer : observers) {
            observer.update(message);
        }
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added: " + book.getName());
        notifyObservers("New book available: " + book.getName());
    }

    public List<Book> getBooks() {
        return books;
    }

    
}
