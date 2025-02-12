package Library;

/**
 *
 * @author HaninChaito
 */
public class FeaturedBookDecorator extends BookDecorator{
    private double discount;

    public FeaturedBookDecorator(Book decoratedBook, double discount) {
        super(decoratedBook);
        this.discount = discount;
    }

    @Override
    public double getPrice() {
        double originalPrice = decoratedBook.getPrice();
        return originalPrice - (originalPrice * discount);
    }

    @Override
    public String toString() {
        return decoratedBook.toString() + "\nDiscount: " + (discount * 100) + "%";
    }
    
}
