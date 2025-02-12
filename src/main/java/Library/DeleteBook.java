package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DeleteBook implements IOOperation {

	@Override
	public void oper(Database database, User user) {
		
		JFrame frame = Main.frame(400, 210);
		frame.setLayout(new BorderLayout());
		
		JLabel title = Main.title("Delete book");
                title.setForeground(Color.decode("#800020")); // Burgundy color
		frame.getContentPane().add(title, BorderLayout.NORTH);
		
		JPanel panel = new JPanel(new GridLayout(2, 2, 15, 15));
		panel.setBorder(BorderFactory.createEmptyBorder(0, 20, 20, 20));
		panel.setBackground(null);
		JLabel label = Main.label("Book Name:");
		JTextField name = Main.textfield();
		JButton delete = Main.button("Delete Book");
		JButton cancel = Main.button("Cancel");
		panel.add(label);
		panel.add(name);
		panel.add(delete);
		panel.add(cancel);
		
		delete.addActionListener((ActionEvent e) -> {
                    if (name.getText().toString().matches("")) {
                        JOptionPane.showMessageDialog(new JFrame(), "Book name cannot be empty!");
                        return;
                    }
                    int i = database.getBook(name.getText().toString());
                    if (i > -1) {
                        // Use the BookProxy to delete the book
                        Book realBook = database.getBook(i);
                        BookProxy bookProxy = new BookProxy(realBook, user);
                        bookProxy.deleteBook(database);
                        
                        JOptionPane.showMessageDialog(new JFrame(), "Book deletion attempted. Check console for details.");
                        frame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(new JFrame(), "Book doesn't exist!");
                    }
                });
		cancel.addActionListener((ActionEvent e) -> {
                    frame.dispose();
                });
		
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setVisible(true);
		
	}

}
