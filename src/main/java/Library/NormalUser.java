package Library;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NormalUser extends User {

	public NormalUser(String name) {
		super(name);
		this.operations = new IOOperation[] {
				new ViewBooks(),
				new Search(),
                          
				new PlaceOrder(),
				new BorrowBook(),
				//new CalculatePenalty(),
                                new ViewBookStatus(),
				new ReturnBook(),
                                      new AddWantToRead(), 
            new ShowWantToRead(),
				new Exit()
		};
	}
	
	public NormalUser(String name, String email, String phonenumber,String password) {
		super(name, email, phonenumber,password);
		this.operations = new IOOperation[] {
				new ViewBooks(),
                    new ViewBookStatus(),
				new Search(),
				new PlaceOrder(),
				new BorrowBook(),
                                
				new CalculatePenalty(),
				new ReturnBook(),
                                    new AddWantToRead(), 
            new ShowWantToRead(), 
				new Exit()
		};
	}
	
	@Override
	public void menu(Database database, User user) {
		
		String[] data = new String[10];
		data[0] = "View Books";
                data[1] = "View Special Books";
		data[2] = "Search";
		data[3] = "Buy Book";
		data[4] = "Borrow Book";
                
		data[5] = "Calculate Penalty";
		data[6] = "Return Book";
                 data[7] = "Add to Want to Read"; 
        data[8] = "Show Want to Read"; 
		data[9] = "Exit";
		
		JFrame frame = this.frame(data, database, user);
 
		frame.setVisible(true);
	}
	
	public String toString() {
		return name+"<N/>"+email+"<N/>"+phonenumber+"<N/>"+password+"<N/>"+"Normal";
	}
	
}
