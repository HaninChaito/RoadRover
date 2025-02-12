package Library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.swing.JOptionPane;



public class Database {
	
	 // Use List<User> for users to maintain order and flexibility
    private List<User> users;

    // Use Set<String> for usernames to ensure uniqueness
    private Set<String> usernames;

    // Use List<Book> for books to maintain order and flexibility
    private List<Book> books;

    // Use Set<String> for booknames to ensure uniqueness
    private Set<String> booknames;

    // Use LinkedList<Order> for orders to optimize adding/removing
    private LinkedList<Order> orders;

    // Use List<Borrowing> for borrowings to maintain order
    private List<Borrowing> borrowings;

    // Use List<WantToRead> for wantToReads to maintain order
    private List<WantToRead> wantToReads;

    // Use Map<String, User> for faster user lookup by username
    private Map<String, User> userLookupMap;
          // Singleton instance
    private static Database instance;
    
    

   File statusFile=new File("C:\\Library Management System\\Data\\BookStatues");
        
           private Libraryy library; // Add this field for the observer
	
	private File usersfile = new File("C:\\Library Management System\\Data\\Users");
	private File booksfile = new File("C:\\Library Management System\\Data\\Books");
	private File ordersfile = new File("C:\\Library Management System\\Data\\Orders");
	private File borrowingsfile = new File("C:\\Library Management System\\Data\\Borrowings");
	private File folder = new File("C:\\Library Management System\\Data");
         private File wantToReadFile = new File("C:\\Library Management System\\Data\\WantToRead");
	
	private Database() {
		if (!folder.exists()) {
			folder.mkdirs();
		}
		if (!usersfile.exists()) {
			try {
				usersfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!booksfile.exists()) {
			try {
				booksfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!ordersfile.exists()) {
			try {
				ordersfile.createNewFile();
			} catch (Exception e) {}
		}
		if (!borrowingsfile.exists()) {
			try {
				borrowingsfile.createNewFile();
			} catch (Exception e) {}
		}
                
                if (!wantToReadFile.exists()) {
			try {
				wantToReadFile.createNewFile();
			} catch (Exception e) {}
		}
		
		  // Initialize collections with appropriate types
        users = new ArrayList<>();
        usernames = new HashSet<>();
        books = new ArrayList<>();
        booknames = new HashSet<>();
        orders = new LinkedList<>();
        borrowings = new ArrayList<>();
        wantToReads = new ArrayList<>();
        userLookupMap = new HashMap<>();
        this.library = new Libraryy();
        
                
		getUsers();
		getBooks();
		getOrders();
		getBorrowings();
                getWantToReads();
                
                
                
	}
        
        //For better performance, you can use double-checked locking:
   public static Database getInstance() {
    if (instance == null) {
        synchronized (Database.class) {
            if (instance == null) {
                instance = new Database();
            }
        }
    }
    return instance;
}
   
   public <T> void addEntity(T entity, Consumer<T> addLogic) {
    addLogic.accept(entity);
}
	
	public void addUser(User user) {
    addEntity(user, this::addUserLogic);
}

private void addUserLogic(User user) {
    users.add(user);
    usernames.add(user.getName());
    userLookupMap.put(user.getName(), user);
    library.registerObserver(user);
    saveUsers();
}
        
     
	
	public int login(String email, String password) {
	
         int n = -1;
		for (User s : users) {
			if (s.getPassword().matches(password) && s.getEmail().matches(email)) {
				n = users.indexOf(s);
                                library.registerObserver(s); // Register the user as an observer

				break;
			}
		}
		return n;
	}
	
	public User getUser(int n) {
		return users.get(n);
	}
	
public void addBook(Book book) {
    addEntity(book, this::addBookLogic);
}

private void addBookLogic(Book book) {
    books.add(book);
    booknames.add(book.getName());
    saveBooks();

    // Notify all users about the new book
    for (User user : users) {
        library.registerObserver(user);
    }
    library.addBook(book);
}

private <T> void loadEntities(File file, Function<String, T> parser, Consumer<T> addLogic, String delimiter) {
    String text = "";
    try {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null) {
            text += line;
        }
        br.close();
    } catch (Exception e) {
        System.err.println(e.toString());
    }

    if (!text.isEmpty()) {
        String[] entries = text.split(delimiter);
        for (String entry : entries) {
            if (!entry.trim().isEmpty()) {
                T entity = parser.apply(entry);
                addLogic.accept(entity);
            }
        }
    }
}
	
 private void getUsers() {
    loadEntities(usersfile, this::parseUser, user -> {
        users.add(user);
        usernames.add(user.getName());
        userLookupMap.put(user.getName(), user); // Ensure this line is present
    }, "<NewUser/>");
}

private User parseUser(String s) {
    String[] a2 = s.split("<N/>");
    UserFactory factory = a2[4].matches("Admin") ? new AdminFactory() : new NormalUserFactory();
    return factory.createUser(a2[0], a2[1], a2[2], a2[3]);
}
	private void saveUsers() {
		  String text1 = users.stream()
                        .map(user -> user.toString() + "<NewUser/>\n")
                        .collect(Collectors.joining());
		try {
			PrintWriter pw = new PrintWriter(usersfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void saveBooks() {
		String text1 = books.stream()
                        .map(book -> book.toString2() + "<NewBook/>\n")
                        .collect(Collectors.joining());
		try {
			PrintWriter pw = new PrintWriter(booksfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void getBooks() {
    loadEntities(booksfile, this::parseBook, book -> {
        books.add(book);
        booknames.add(book.getName());
    }, "<NewBook/>");
}

private Book parseBook(String s) {
  String[] a = s.split("<N/>");
		Book book = new Book();
      book.setName(a[0]);
		book.setAuthor(a[1]);
		book.setPublisher(a[2]);
		
                 book.setYear(Integer.parseInt(a[3])); // Parse the year
		book.setQty(Integer.parseInt(a[4]));
		book.setPrice(Double.parseDouble(a[5]));
		book.setBrwcopies(Integer.parseInt(a[6]));
		
		return book;
}
	public List<Book> getAllBooks() {
		return books;
	}
	
	public int getBook(String bookname) {
                 return books.stream()
                .filter(book -> book != null && book.getName() != null && book.getName().matches(bookname))
                .map(books::indexOf)
                .findFirst()
                .orElse(-1);
	}
	
	public Book getBook(int i) {
		return books.get(i);
	}
	
	public void deleteBook(int i) {
		Book book = books.get(i);
        books.remove(i);
        booknames.remove(book.getName());
		saveBooks();
	}
	
	public void deleteAllData() {
		if (usersfile.exists()) {
			try {
				usersfile.delete();
			} catch (Exception e) {}
		}
		if (booksfile.exists()) {
			try {
				booksfile.delete();
			} catch (Exception e) {}
		}
		if (ordersfile.exists()) {
			try {
				ordersfile.delete();
			} catch (Exception e) {}
		}
		if (borrowingsfile.exists()) {
			try {
				borrowingsfile.delete();
			} catch (Exception e) {}
		}
	}
	
	public void addOrder(Order order, Book book, int bookindex) {
		orders.add(order);
		books.set(bookindex, book);
		saveOrders();
		saveBooks();
	}
	
	private void saveOrders() {
		    String text1 = orders.stream()
                         .map(Order::toString2)
                         .collect(Collectors.joining("<NewOrder/>\n"));
    try {
        PrintWriter pw = new PrintWriter(ordersfile);
        pw.print(text1);
        pw.close();
    } catch (Exception e) {
        System.err.println(e.toString());
    }
	}
	
	private void getOrders() {
    loadEntities(ordersfile, this::parseOrder, order -> orders.add(order) ,"<NewOrder/>");
}


	public List<User> getAllUsers() {
    return users;
}
	public boolean userExists(String name) {
		    return users.stream().anyMatch(user -> user.getName().equalsIgnoreCase(name));

	}
	
	private User getUserByName(String name) {
		  return userLookupMap.get(name);

	}
	
	private Order parseOrder(String s) {
		 String[] a = s.split("<N/>");
    Book book = books.get(getBook(a[0]));
    User user = getUserByName(a[1]);
    if (user == null) {
        System.err.println("User not found for order: " + s);
        return null; 
    }
    double price = Double.parseDouble(a[2]);
    int qty = Integer.parseInt(a[3]);
    return new Order(book, user, price, qty);
	}
	
	public List<Order> getAllOrders() {
		return orders;
	}
	
	private void saveBorrowings() {
		String text1 = "";
		for (Borrowing borrowing : borrowings) {
			text1 = text1 + borrowing.toString2()+"<NewBorrowing/>\n";
		}
		try {
			PrintWriter pw = new PrintWriter(borrowingsfile);
			pw.print(text1);
			pw.close();
		} catch  (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void getBorrowings() {
		String text1 = "";
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(borrowingsfile));
			String s1;
			while ((s1 = br1.readLine()) !=null) {
				text1 = text1 + s1;
			}
			br1.close();
		} catch (Exception e) {
			System.err.println(e.toString());
		}
		
		if (!text1.matches("") || !text1.isEmpty()) {
			String[] a1 = text1.split("<NewBorrowing/>");
			for (String s : a1) {
				Borrowing borrowing = parseBorrowing(s);
				borrowings.add(borrowing);
			}
		}
	}
	
	private Borrowing parseBorrowing(String s) {
		String[] a = s.split("<N/>");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate start = LocalDate.parse(a[0], formatter);
		LocalDate finish = LocalDate.parse(a[1], formatter);
		Book book = getBook(getBook(a[3]));
		User user = getUserByName(a[4]);
		Borrowing brw = new Borrowing(start, finish, book, user);
		return brw;
	}
	
	public void borrowBook(Borrowing brw, Book book, int bookindex) {
		borrowings.add(brw);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
	}
	
	public List<Borrowing> getBrws() {
		return borrowings;
	}
	
	public void returnBook(Borrowing b, Book book, int bookindex) {
		borrowings.remove(b);
		books.set(bookindex, book);
		saveBorrowings();
		saveBooks();
	}
        
         // Add a book to a user's "Want to Read" list
    public void addWantToRead(User user, Book book) {
        WantToRead wantToRead = new WantToRead(user, book);
        wantToReads.add(wantToRead);
        saveWantToReads();
    }

    // Get all "Want to Read" entries for a user
    public ArrayList<WantToRead> getWantToReads(User user) {
        return wantToReads.stream()
                      .filter(wantToRead -> wantToRead.getUser().equals(user))
                      .collect(Collectors.toCollection(ArrayList::new));
    }

    // Save "Want to Read" list to file
    private void saveWantToReads() {
        String text = "";
        for (WantToRead wantToRead : wantToReads) {
            text += wantToRead.toString2() + "<NewWantToRead/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(wantToReadFile);
            pw.print(text);
            pw.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }

    // Load "Want to Read" list from file
    private void getWantToReads() {
        String text = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(wantToReadFile));
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
            }
            br.close();
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        if (!text.isEmpty()) {
            String[] entries = text.split("<NewWantToRead/>");
            for (String entry : entries) {
                String[] parts = entry.split("<N/>");
                User user = getUserByName(parts[0]);
                Book book = getBook(getBook(parts[1]));
                WantToRead wantToRead = new WantToRead(user, book);
                wantToReads.add(wantToRead);
            }
        }
    }
    public void decorateBook(int bookIndex, String decoratorType, double discount) {
    Book book = books.get(bookIndex);
    switch (decoratorType) {
        case "Featured":
            book = new FeaturedBookDecorator(book, discount);
            break;
        case "NewArrival":
            book = new NewArrivalDecorator(book);
            break;
        case "Bestseller":
            book = new BestsellerDecorator(book);
            break;
        default:
            throw new IllegalArgumentException("Invalid decorator type");
    }
    books.set(bookIndex, book);
    saveBooks();
}

/*public void updateBook(int bookIndex, Book book) {
    books.set(bookIndex, book);
}

public void displayBooks() {
    for (Book book : books) {
        System.out.println(book.toString());
    }
}*/
    public void addBookStatus(String bookName, String status) {
    // Save the book name and status to the separate table
    String text = bookName + "<N/>" + status + "<NewStatus/>\n";
    try {
        PrintWriter pw = new PrintWriter(new FileWriter(statusFile, true)); // Append mode
        pw.print(text);
        pw.close();
    } catch (Exception e) {
        System.err.println(e.toString());
    }
}
    
    public void removeBookStatus(String bookName) {
try {
        List<String> statusList = Files.lines(statusFile.toPath())
                                       .filter(line -> !line.startsWith(bookName + "<N/>"))
                                       .collect(Collectors.toList());
        Files.write(statusFile.toPath(), statusList);
    } catch (IOException e) {
        System.err.println("Failed to update status file: " + e.toString());
    }
}
    public String getBookStatus(String bookName) {
    // Retrieve the status for a book from the separate table
    try {
        BufferedReader br = new BufferedReader(new FileReader(statusFile));
        String line;
        while ((line = br.readLine()) != null) {
            if (line.startsWith(bookName + "<N/>")) {
                String[] parts = line.split("<N/>");
                return parts[1].replace("<NewStatus/>", "");
            }
        }
        br.close();
    } catch (Exception e) {
        System.err.println(e.toString());
    }
    return null; // No status found
}
 public void removeStatus(String bookName) {
        removeBookStatus(bookName);
        JOptionPane.showMessageDialog(null, "Status removed successfully!");
    }


   
  
    public File getStatusFile() {
    return statusFile;
}
 
}
/*
List<User> for users: Maintains order and provides flexibility for adding/removing users.

Set<String> for usernames: Ensures uniqueness of usernames.

List<Book> for books: Maintains order and provides flexibility for adding/removing books.

Set<String> for booknames: Ensures uniqueness of book names.

LinkedList<Order> for orders: Optimized for frequent adding/removing of orders.

Map<String, User> for userLookupMap: Provides faster user retrieval by username.
*/