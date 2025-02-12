
package Library;


public class BookDecorator  extends Book{
     protected Book decoratedBook;

    public BookDecorator(Book decoratedBook) {
        this.decoratedBook = decoratedBook;
    }

    @Override
    public String toString() {
        return decoratedBook.toString();
    }

    @Override
    public String toString2() {
        return decoratedBook.toString2();
    }
}
