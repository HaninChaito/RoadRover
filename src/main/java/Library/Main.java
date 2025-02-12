package Library;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Main {
	
	static Scanner s;
	static Database database;

	public static void main(String[] args) {
		
		database = Database.getInstance();
		
		JFrame frame = frame(500, 300);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(3, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		JLabel title = label("Welcome to Library Management System");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#800020"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		
		JLabel label1 = label("Email:");
                JLabel label2 = label("Password:");
		
		JTextField email = textfield();
                JTextField password = textfield();
		JButton login = button("Login");
		JButton newUser = button("New User");
                               // Image for second column
JLabel imageLabel = new JLabel();
//ImageIcon imageIcon = new ImageIcon("C:\\Users\\User\\Desktop\\ReVogue\\suwar\\image.jpg"); // Add the path to your image file
ImageIcon imageIcon = new ImageIcon(Library.class.getResource("/libraryimage.jpg"));
imageLabel.setIcon(imageIcon);
		
		login.addActionListener((ActionEvent e) -> {
                    if (password.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Phone number cannot be empty!");
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
                frame.getContentPane().add(imageLabel, BorderLayout.EAST); // Add the image to the East side (second column)

		frame.setVisible(true);
		
	}

	private static void login( String email,String password, JFrame frame) {
		int n = database.login( email,password);
		if (n != -1) {
			User user = database.getUser(n);
			user.menu(database, user);
			frame.dispose();
		} else {
			JOptionPane.showMessageDialog(new JFrame(), "User doesn't exist");
		}
	}
	
	private static void newuser() {
		
		JFrame frame = frame(500, 400);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(5, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(10, 15, 20, 15));
		panel.setBackground(null);
		
		JLabel title = label("Create new account");
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JLabel label0 = label("Name:");
		JLabel label1 = label("Phone Number:");
		JLabel label2 = label("Email:");
                JLabel label3 = label("Password:");
                JLabel label4 = label("Confirm Password:");
		JTextField name = textfield();
		JTextField phonenumber = textfield();
		JTextField email = textfield();
                JTextField password = textfield();
                JTextField confirmpassword = textfield();
		JRadioButton admin = radioButton("Admin");
		JRadioButton normaluser = radioButton("Normal User");
		JButton createacc = button("Create Account");
		JButton cancel = button("Cancel");
		
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
	
	public static JFrame frame(int width, int height) {
		JFrame frame = new JFrame();
		frame.setSize(width, height);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setTitle("BookWaves Library");
		frame.setLayout(new BorderLayout());
		frame.setBackground(Color.white);
		frame.getContentPane().setBackground(Color.white);
		return frame;
	}
	
	public static JLabel label(String text) {
			JLabel label = new JLabel(text);
    label.setFont(new Font("Segoe UI", Font.PLAIN, 16)); // Modern font and size
    label.setForeground(Color.decode("#333333")); // Dark gray text color
    label.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
    label.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add padding (top, left, bottom, right)
    return label;
	}
	
	public static JTextField textfield() {
		JTextField textfield = new JTextField();
    textfield.setFont(new Font("Tahoma", Font.PLAIN, 16)); // Slightly smaller font for a cleaner look
    textfield.setForeground(Color.decode("#333333")); // Dark gray text color
    textfield.setHorizontalAlignment(SwingConstants.LEFT); // Align text to the left
    textfield.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(Color.decode("#CCCCCC"), 1), // Light gray border
        BorderFactory.createEmptyBorder(5, 10, 5, 10) // Internal padding (top, left, bottom, right)
    ));
    textfield.setPreferredSize(new Dimension(250, 35)); // Set a consistent size for all text fields
    textfield.setBackground(Color.WHITE); // White background

    // Add focus listener to change border color when focused
    textfield.addFocusListener(new java.awt.event.FocusAdapter() {
        public void focusGained(java.awt.event.FocusEvent evt) {
            textfield.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#800020"), 2), // Burgundy border when focused
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }
        public void focusLost(java.awt.event.FocusEvent evt) {
            textfield.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#CCCCCC"), 1), // Restore light gray border
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
            ));
        }
    });

    return textfield;
	}
	
	public static JButton button(String text) {
		JButton button = new JButton(text);
    button.setFont(new Font("Tahoma", Font.BOLD, 17));
    button.setForeground(Color.white);
    button.setHorizontalAlignment(SwingConstants.CENTER);
    button.setBackground(Color.decode("#800020")); // Burgundy color
    button.setBorder(null);
    button.setPreferredSize(new Dimension(120, 40)); // Set smaller button size (width, height)
    return button;
	}
	
	public static JRadioButton radioButton(String text) {
		JRadioButton btn = new JRadioButton();
		btn.setForeground(Color.black);
		btn.setText(text);
		btn.setHorizontalAlignment(SwingConstants.CENTER);
		btn.setFont(new Font("Tahoma", Font.BOLD, 17));
		btn.setBackground(null);
		return btn;
	}
	
	public static JLabel title(String text) {
		JLabel title = Main.label(text);
		title.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
		title.setFont(new Font("Tahoma", Font.BOLD, 21));
		title.setForeground(Color.decode("#1da1f2"));
		return title;
	}

}
