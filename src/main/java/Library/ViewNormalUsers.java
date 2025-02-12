
package Library;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewNormalUsers implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        // Create a frame for the table
        JFrame frame = Main.frame(800, 500);

        // Title
        JLabel title = Main.title("View Normal Users");
        title.setForeground(Color.decode("#800020")); // Burgundy color
        frame.getContentPane().add(title, BorderLayout.NORTH);

        // Create a table model
        String[] columns = {"Name", "Email", "Phone Number"};
        DefaultTableModel model = new DefaultTableModel(columns, 0);

        // Populate the table with normal users
        for (User u : database.getAllUsers()) {
            if (u instanceof NormalUser) {
                String[] row = {u.getName(), u.getEmail(), u.getPhoneNumber()};
                model.addRow(row);
            }
        }

        // Create the table
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the table to the frame
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Close button
        JButton closeButton = Main.button("Close");
        closeButton.addActionListener(e -> frame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        // Display the frame
        frame.setVisible(true);
    }
}
