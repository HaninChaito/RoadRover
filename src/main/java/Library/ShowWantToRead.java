package Library;
import javax.swing.*;
import java.util.ArrayList;

public class ShowWantToRead implements IOOperation {
     @Override
    public void oper(Database database, User user) {
        ArrayList<WantToRead> wantToReads = database.getWantToReads(user);
        StringBuilder list = new StringBuilder();
        for (WantToRead wantToRead : wantToReads) {
            list.append(wantToRead.getBook().getName()).append("\n");
        }
        JOptionPane.showMessageDialog(null, "Your 'Want to Read' list:\n" + list.toString());
    }
    
}
