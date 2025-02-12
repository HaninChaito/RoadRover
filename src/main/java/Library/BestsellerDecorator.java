
package Library;


public class BestsellerDecorator extends BookDecorator{
    public BestsellerDecorator(Book decoratedBook) {
        super(decoratedBook);
    }

    @Override
    public String toString() {
        return decoratedBook.toString() + "\nStatus: Bestseller!";
    }
    
}
