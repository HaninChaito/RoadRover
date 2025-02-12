package Library;

//HaninChaito-105049

public class Book {
	
        
	private String name;		
	private String author;		
	private String publisher;	
        private int year;
	private int qty;		
	private double price;		
	private int brwcopies;	
	
	public Book() {};
	
	public Book(String name, String author, String publisher,
			int year, int qty, double price, int brwcopies) {
            
		this.name = name;
		this.author = author;
		this.publisher = publisher;
		
                this.year=year;
		this.qty = qty;
		this.price = price;
		this.brwcopies = brwcopies;
	}
	
	public String toString() {
		return 
           "Book Name: " + name + "\n" +
           "Author: " + author + "\n" +
           "Publisher: " + publisher + "\n" +
           "Year: " + year + "\n" +
           "Qty: " + qty + "\n" +
           "Price: " + price + "\n" +
           "Borrowing Copies: " + brwcopies;
	}

       
	
   
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	

	

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getBrwcopies() {
		return brwcopies;
	}

	public void setBrwcopies(int brwcopies) {
		this.brwcopies = brwcopies;
	}
	 public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
	public String toString2() {
		String text =name+"<N/>"+author+"<N/>"+publisher+ "<N/>" +
               year + "<N/>"+String.valueOf(qty)+"<N/>"+String.valueOf(price)+"<N/>"+String.valueOf(brwcopies);
		return text;
	}
        
        

}

