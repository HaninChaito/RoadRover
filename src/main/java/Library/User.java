package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

//HaninChaito-105049

public abstract class User implements LibraryObserver {
	
	protected String name;
	protected String email;
	protected String phonenumber;
        protected String password;
	protected IOOperation[] operations;
	
	public User() {}
	
	public User(String name) {
		this.name = name;
	}
	
	public User(String name, String email, String phonenumber,String password) {
		this.name = name;
		this.email = email;
		this.phonenumber = phonenumber;
                this.password=password;
	}

	public String getName() {
		return name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public String getPhoneNumber() {
		return phonenumber;
	}
        
        public String getPassword() {
		return password;
	}
        @Override
    public void update(String message) {
        // Display the notification to the user
        System.out.println(name + " received notification: " + message);
       
        // You can also show this message in the GUI if needed
    }
    
     private ArrayList<WantToRead> wantToReadList = new ArrayList<>();

    // Add a book to the "Want to Read" list
    public void addWantToRead(Book book) {
        WantToRead wantToRead = new WantToRead(this, book);
        wantToReadList.add(wantToRead);
    }

    // Get the "Want to Read" list
    public ArrayList<WantToRead> getWantToReadList() {
        return wantToReadList;
    }

  
	
	abstract public String toString();
	
	abstract public void menu(Database database, User user);
	
	public JFrame frame(String[] data, Database database, User user) {
	  JFrame frame = new JFrame();
frame.setSize(1000, 600); 
frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
frame.setLocationRelativeTo(null);
frame.setTitle("Library Management System");
frame.setLayout(new BorderLayout());

// Load the background image
       // ImageIcon background = new ImageIcon("C:\\Users\\User\\Desktop\\libraryimage.jpg");
       ImageIcon background = new ImageIcon(Library.class.getResource("/libraryimage.jpg"));
        Image bgImage = background.getImage();

        // Create a panel with background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new BorderLayout());
        frame.setContentPane(backgroundPanel);

// Title Label
JLabel label1 = new JLabel("Welcome Mr. " + this.name);
label1.setFont(new Font("Segoe UI", Font.BOLD, 24)); // Modern font and size
label1.setForeground(Color.decode("#333333")); // Dark gray text
label1.setHorizontalAlignment(SwingConstants.CENTER);
label1.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10)); // Add padding
frame.getContentPane().add(label1, BorderLayout.NORTH);

// Panel for Buttons
JPanel panel = new JPanel();
panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 30, 30)); // Add padding
int columns = 3;
int rows = (data.length + columns - 1) / columns; // Calculate rows dynamically
panel.setLayout(new GridLayout(rows, columns, 15, 15)); // 3 columns with spacing
panel.setBackground(Color.WHITE); // Match frame background

// Add Buttons
for (int i = 0; i < data.length; i++) {
    JButton button = new JButton(data[i]);
   button.setFont(new Font("Tahoma", Font.BOLD, 17));
    button.setForeground(Color.white);
   
    button.setBackground(Color.decode("#800020")); // Burgundy color
    button.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.decode("#CCCCCC"), 1), // Light gray border
        BorderFactory.createEmptyBorder(10, 20, 10, 20) // Padding inside the button
    ));
    button.setFocusPainted(false); // Remove focus border
    button.setPreferredSize(new Dimension(150, 40)); // Consistent button size

    // Hover Effect
    button.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseEntered(java.awt.event.MouseEvent evt) {
            button.setBackground(Color.decode("#E0E0E0")); // Slightly darker gray on hover
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#999999"), 1), // Darker border on hover
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
        }
        public void mouseExited(java.awt.event.MouseEvent evt) {
            button.setBackground(Color.decode("#800020")); // Burgundy color
            button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#CCCCCC"), 1), // Restore original border
                BorderFactory.createEmptyBorder(10, 20, 10, 20)
            ));
        }
    });

    // Button Action
    int index = i;
    button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            operations[index].oper(database, user);
            if (data[index].matches("Exit") || data[index].matches("Delete all data")) {
                frame.dispose();
            }
        }
    });

    panel.add(button);
}
   backgroundPanel.add(panel, BorderLayout.CENTER);
frame.getContentPane().add(panel, BorderLayout.CENTER);
frame.setVisible(true);
return frame;
	}
	
}
