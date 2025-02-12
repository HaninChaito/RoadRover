
package Library;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddWantToRead implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        String bookName = JOptionPane.showInputDialog("Enter the name of the book you want to read:");
        if (bookName != null && !bookName.isEmpty()) {
            int bookIndex = database.getBook(bookName);
            if (bookIndex != -1) {
                Book book = database.getBook(bookIndex);
                database.addWantToRead(user, book);
                JOptionPane.showMessageDialog(null, "Book added to your 'Want to Read' list!");
            } else {
                JOptionPane.showMessageDialog(null, "Book not found!");
            }
        }
    }
}
