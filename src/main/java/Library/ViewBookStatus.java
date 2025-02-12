/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;


public class ViewBookStatus implements IOOperation{
       @Override
    public void oper(Database database, User user) {
        // Step 1: Get the list of books with statuses
        ArrayList<String> bestsellerBooks = new ArrayList<>();
        ArrayList<String> newArrivalBooks = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(database.getStatusFile()));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("<N/>");
                String bookName = parts[0];
                String status = parts[1].replace("<NewStatus/>", "");
                if (status.equals("Bestseller")) {
                    bestsellerBooks.add(bookName);
                } else if (status.equals("New Arrival")) {
                    newArrivalBooks.add(bookName);
                }
            }
            br.close();
        } catch (Exception e) {
            System.err.println("Failed to read status file: " + e.toString());
        }

        // Step 2: Create a JFrame to display the tables
        JFrame frame = new JFrame("Decorated Books");
        frame.setSize(800, 400);
        frame.setLayout(new GridLayout(1, 2)); // Two tables side by side

        // Step 3: Create a table for Bestseller books
        String[] bestsellerColumns = {"Bestseller Books"};
        DefaultTableModel bestsellerModel = new DefaultTableModel(bestsellerColumns, 0);
        for (String book : bestsellerBooks) {
            bestsellerModel.addRow(new Object[]{book});
        }
        JTable bestsellerTable = new JTable(bestsellerModel);
        JScrollPane bestsellerScrollPane = new JScrollPane(bestsellerTable);

        // Step 4: Create a table for New Arrival books
        String[] newArrivalColumns = {"New Arrival Books"};
        DefaultTableModel newArrivalModel = new DefaultTableModel(newArrivalColumns, 0);
        for (String book : newArrivalBooks) {
            newArrivalModel.addRow(new Object[]{book});
        }
        JTable newArrivalTable = new JTable(newArrivalModel);
        JScrollPane newArrivalScrollPane = new JScrollPane(newArrivalTable);

        // Step 5: Add the tables to the frame
        frame.add(bestsellerScrollPane);
        frame.add(newArrivalScrollPane);

        // Step 6: Display the frame
        frame.setVisible(true);
    }
    
}
