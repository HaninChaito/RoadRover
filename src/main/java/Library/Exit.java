package Library;


import static Library.Main.frame;
import static Library.Main.label;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class Exit implements IOOperation {
	
	Database database;

	@Override
	public void oper(Database database, User user) {
		JFrame frame = Main.frame(1000, 600);
		
		this.database = Database.getInstance();
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		JLabel title = Main.label("Welcome to BookWaves Library");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
title.setForeground(Color.decode("#800020")); // Burgundy color
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JLabel label1 = Main.label("Email:");
		JLabel label2 = Main.label("Password:");
		JTextField email = Main.textfield();
                JTextField password = Main.textfield();
		JButton login = Main.button("Login");
		JButton newUser = Main.button("New User");
                               // Image for second column
         login.setBackground(Color.decode("#800020")); // Burgundy background
login.setForeground(Color.WHITE); // White text color



newUser.setBackground(Color.decode("#800020")); // Burgundy background
newUser.setForeground(Color.WHITE); // White text color
                
                // Image for second column
//ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Desktop\\libraryimage.jpg"); // Add the path to your image file
ImageIcon imageIcon = new ImageIcon(Library.class.getResource("/libraryimage.jpg"));

Image img = imageIcon.getImage(); // Transform the ImageIcon into an Image
Image resizedImage = img.getScaledInstance(500, 350, Image.SCALE_SMOOTH); // Resize the image to smaller dimensions
ImageIcon resizedIcon = new ImageIcon(resizedImage); // Create a new ImageIcon with the resized image

// Image label
JLabel imageLabel = new JLabel(resizedIcon);
		
		login.addActionListener((ActionEvent e) -> {
                    if (password.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Password cannot be empty!");
                        return;
                    }
                    if (email.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
                        return;
                    }
                    login(email.getText().toString(),password.getText().toString(), frame);
                });
		newUser.addActionListener((ActionEvent e) -> {
                    newuser();
                    frame.dispose();
                });
		
		panel.add(label1);
		panel.add(email);
		panel.add(label2);
		panel.add(password);	
		panel.add(login);
		panel.add(newUser);
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
                // Adding image to the second column of the grid

frame.getContentPane().add(imageLabel, BorderLayout.EAST); // Add the image to the East side (second column)

		frame.setVisible(true);
	}
	
	private void login(String email, String password,JFrame frame) {
		int n = database.login(email,password);
		if (n != -1) {
			User user = database.getUser(n);
			user.menu(database, user);
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
		}
	}
	
	private void newuser() {
		
		JFrame frame = frame(600, 500);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		  JPanel panel = new JPanel();
               panel.setLayout(new GridLayout(7, 2, 10, 10)); // 7 rows, 2 columns, with gaps
        panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		
		 JLabel title = new JLabel("Create new account");
        title.setFont(new Font("Tahoma", Font.BOLD, 21));
        title.setForeground(Color.decode("#1da1f2"));
		
		JLabel label0 = Main.label("Name:");
		JLabel label1 = Main.label("Phone Number:");  
		JLabel label2 = Main.label("Email:");
                JLabel label3 = label("Password:");
                JLabel label4 = label("Confirm Password:");
		JTextField name = Main.textfield();
		JTextField phonenumber = Main.textfield();
		JTextField email = Main.textfield();
                 JTextField password = Main.textfield();
                JTextField confirmpassword = Main.textfield();
		JRadioButton admin = Main.radioButton("Admin");
		JRadioButton normaluser = Main.radioButton("Normal User");
		JButton createacc = Main.button("Create Account");
		JButton cancel = Main.button("Cancel");
                
                   ButtonGroup group = new ButtonGroup();
        group.add(admin);
        group.add(normaluser);
		
		admin.addActionListener(e -> {
			if (normaluser.isSelected()) {
				normaluser.setSelected(false);
			}
		});
		normaluser.addActionListener(e -> {
			if (admin.isSelected()) {
				admin.setSelected(false);
			}
		});
		
		panel.add(label0);
		panel.add(name);
		panel.add(label1);
		panel.add(phonenumber);
		panel.add(label2);
		panel.add(email);
                 panel.add(label3);
		panel.add(password);
                panel.add(label4);
		panel.add(confirmpassword);
		panel.add(admin);
		panel.add(normaluser);
		panel.add(createacc);
		panel.add(cancel);
		
		createacc.addActionListener((ActionEvent e) -> {
                    if (database.userExists(name.getText().toString())) {
                        JOptionPane.showMessageDialog(new JFrame(), "Username exists!\nTry another one");
                        return;
                    }
                    if (name.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Name cannot be empty!");
                        return;
                    }
                    if (phonenumber.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
                        return;
                    }
                    if (email.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Email cannot be empty!");
                        return;
                    }
                    if (!admin.isSelected() && !normaluser.isSelected()) {
                        JOptionPane.showMessageDialog(new JFrame(), "You must choose account type!");
                        return;
                    }
                    if (!(password.getText().toString().equals(confirmpassword.getText().toString()))) {
                        JOptionPane.showMessageDialog(new JFrame(), "Wrong Password");
                        return;
                    }
                    User user;
                    if (admin.isSelected()) {
                        user = new Admin(name.getText().toString(),
                                email.getText().toString(), phonenumber.getText().toString(),password.getText().toString());
                    } else {
                        user = new NormalUser(name.getText().toString(),
                                email.getText().toString(), phonenumber.getText().toString(),password.getText().toString());
                    }
                    frame.dispose();
                    database.addUser(user);
                    user.menu(database, user);
                });
		cancel.addActionListener((ActionEvent e) -> {
                    frame.dispose();
                });
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
	}

}
