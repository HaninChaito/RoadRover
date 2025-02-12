package Library;

public class NewArrivalDecorator extends BookDecorator{
    public NewArrivalDecorator(Book decoratedBook) {
        super(decoratedBook);
    }

    @Override
    public String toString() {
        return decoratedBook.toString() + "\nStatus: New Arrival!";
    }
    
}
