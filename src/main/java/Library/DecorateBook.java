
package Library;

import javax.swing.JOptionPane;


public class DecorateBook implements IOOperation{
    @Override
    public void oper(Database database, User user) {
   // Step 1: Get the book name from the user
    String bookName = JOptionPane.showInputDialog("Enter book name:");
    if (bookName == null || bookName.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Book name cannot be empty!");
        return;
    }

    // Step 2: Find the book in the database
    int bookIndex = database.getBook(bookName);
    if (bookIndex == -1) {
        JOptionPane.showMessageDialog(null, "Book not found!");
        return;
    }

    // Step 3: Let the user choose the decorator type
    String[] options = {"New Arrival", "Bestseller"};
    String decoratorType = (String) JOptionPane.showInputDialog(
        null, 
        "Choose decorator type:", 
        "Decorate Book", 
        JOptionPane.QUESTION_MESSAGE, 
        null, 
        options, 
        options[0] // Default selection
    );

    // Step 4: Validate the decorator type
    if (decoratorType == null) {
        JOptionPane.showMessageDialog(null, "No decorator type selected. Operation canceled.");
        return;
    }

    // Step 5: Save the status to the separate table
    database.addBookStatus(bookName, decoratorType);

    // Step 6: Show success message
    JOptionPane.showMessageDialog(null, "Book decorated successfully!");
}
}
