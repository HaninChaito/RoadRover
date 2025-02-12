package Library;

import javax.swing.JOptionPane;


public class RemoveStatus implements IOOperation{
     @Override
    public void oper(Database database, User user) {
      
        String bookName = JOptionPane.showInputDialog("Enter book name:");
        if (bookName == null || bookName.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Book name cannot be empty!");
            return;
        }

        
        database.removeBookStatus(bookName);

       
        JOptionPane.showMessageDialog(null, "Status removed successfully!");
    }
}
