package Library;


public class WantToRead {
     private User user;
    private Book book;

    public WantToRead(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public String toString() {
        return user.getName() + "<N/>" + book.getName();
    }

    public String toString2() {
        return user.getName() + "<N/>" + book.getName();
    }
}

