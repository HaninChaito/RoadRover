
package Library;

/**
 *
 * @author User
 */
public class BookProxy {
     private Book realBook;
    private User user;

    public BookProxy(Book realBook, User user) {
        this.realBook = realBook;
        this.user = user;
    }

   
    public void deleteBook(Database database) {
        if (user instanceof Admin) {
            int index = database.getBook(realBook.getName());
            if (index > -1) {
                database.deleteBook(index);
                System.out.println("Book deleted successfully by admin: " + user.getName());
            } else {
                System.out.println("Book not found!");
            }
        } else {
            System.out.println("Permission denied! Only admins can delete books.");
        }
    }


    public String getName() {
        return realBook.getName();
    }

    public String getAuthor() {
        return realBook.getAuthor();
    }

    public String getPublisher() {
        return realBook.getPublisher();
    }
}
/*
Permission Check:

The BookProxy class checks if the user is an instance of Admin before allowing the deletion of a book.

If the user is not an admin, the operation is denied, and a message is displayed.

Delegation:

If the user has permission, the BookProxy delegates the actual deletion operation to the Database class.

Separation of Concerns:

The BookProxy class handles the permission logic, while the Book class remains focused on representing book data.

<<<proxy 3am y5alle mpre secure ino ma hada y2dar y3mel api la yma7e book w ma ykun admin wbnfs wa2et ma 3m yda5el book class bil delete directly,
3m tsir bi wasetet bookproxy.



*/