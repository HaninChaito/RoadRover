package Library;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Admin extends User {
	
	public Admin(String name) {
		super(name);
		this.operations = new IOOperation[] {
				new ViewBooks(),
                     new ViewNormalUsers(), 
				new AddBook(),
				new DeleteBook(),
				new Search(),
				new DecorateBook(),
                                new RemoveStatus(),
				new ViewOrders(),
				new Exit()
		};
	}
	
	public Admin(String name, String email, String phonenumber,String password) {
		super(name, email, phonenumber,password);
		this.operations = new IOOperation[] {
				new ViewBooks(),
                     new ViewNormalUsers(), // Add the new operation
				new AddBook(),
				new DeleteBook(),
				new Search(),
				  new DecorateBook(), // New operation
                                  new RemoveStatus(),
				new ViewOrders(),
				new Exit()
		};
	}
       
	@Override
	public void menu(Database database, User user) {
		String[] data = new String[9];
		data[0] = "View Books";
                data[1] = "View Users";
		data[2] = "Add Book";
		data[3] = "Delete Book";
		data[4] = "Search";
		data[5] = "Add Feature";
                  data[6] = "Remove Feature";
		data[7] = "View Orders";
		data[8] = "Exit";
		
		JFrame frame = this.frame(data, database, user);
		frame.setVisible(true);
	}
	
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+password+"<N/>"+"Admin";
	}

}
