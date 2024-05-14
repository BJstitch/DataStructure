/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bookssystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CircularLinkedListGUI extends JFrame {
    private CircularLinkedList<String> circularLinkedList;
    private CircularLinkedList<String> availableBooksList; // List of books available for purchase
    private JTextArea outputTextArea;

    public CircularLinkedListGUI(boolean isAdmin) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(isAdmin ? "Admin Interface" : "Customer Interface");
        setSize(400, isAdmin ? 300 : 500);
        setLayout(new GridLayout(isAdmin ? 4 : 8, 1));

        circularLinkedList = new CircularLinkedList<>();
        availableBooksList = new CircularLinkedList<>();
        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        if (isAdmin) {
            JButton addBookButton = new JButton("Add Book");//batool
            addBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookTitle = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Book Title:");
                    circularLinkedList.addLast(bookTitle);
                    availableBooksList.addLast(bookTitle);
                    outputTextArea.append("Book added successfully: " + bookTitle + "\n");
                }
 });

            JButton removeBookButton = new JButton("Remove Book");//batool
            removeBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookTitle = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Book Title:");
                    circularLinkedList.removeNode(bookTitle);
                    availableBooksList.removeNode(bookTitle);
                    outputTextArea.append("Book removed successfully: " + bookTitle + "\n");
                }
            });

            add(addBookButton);
            add(removeBookButton);
        } else {
            JButton buyBookButton = new JButton("Buy Book");//zahra
            buyBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookTitle = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Book Title:");
                    if (availableBooksList.findNode(bookTitle) != null) {
                        double price = 10.0; 
                        String invoice = "Book purchased: " + bookTitle + "\nPrice: $" + price + "\nDate: " + getCurrentDate();
                        outputTextArea.append(invoice + "\n");
                    } else {
                        outputTextArea.append("Sorry, the book is not available for purchase.\n");
                    }
                }
            });

JButton borrowBookButton = new JButton("Borrow Book");//zahra
            borrowBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookTitle = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Book Title:");
                    double fee = 5.0; 
                    String invoice = "Book borrowed: " + bookTitle + "\nBorrowing Fee: $" + fee + "\nDate: " + getCurrentDate();
                    outputTextArea.append(invoice + "\n");
                }
            });

            JButton orderBookButton = new JButton("Order Book");//zahra
            orderBookButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String bookTitle = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Book Title:");
                    String phoneNumber = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Your Phone Number:");
                    String address = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Your Address:");
                    double deliveryFee = 3.0; 
                    String invoice = "Book ordered: " + bookTitle + "\nDelivery Fee: $" + deliveryFee +
                            "\nPhone Number: " + phoneNumber + "\nAddress: " + address + "\nDate: " + getCurrentDate();
                    outputTextArea.append(invoice + "\n");
                }
            });
   add(buyBookButton);
            add(borrowBookButton);
            add(orderBookButton);
        }

        JButton displayButton = new JButton("Display Books");//aisha
        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                outputTextArea.setText(circularLinkedList.display());
            }
        });

        JButton rotateButton = new JButton("Rotate");//aisha
        rotateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                circularLinkedList.rotate();
                outputTextArea.append("List rotated successfully!\n");
            }
        });

        JButton findBookButton = new JButton("Find Book");//aisha
        findBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String bookTitle = JOptionPane.showInputDialog(CircularLinkedListGUI.this, "Enter Book Title:");
                String result = circularLinkedList.findNode(bookTitle);
                outputTextArea.append(result + "\n");
                outputTextArea.setCaretPosition(outputTextArea.getDocument().getLength());
            }
        });

        add(displayButton);
        add(rotateButton);
        add(findBookButton);
        add(scrollPane);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {//batool
            String[] options = {"Admin", "Visitor"};
            int choice = JOptionPane.showOptionDialog(null, "Are you an admin or a visitor?", "Login",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

            boolean isAdmin = choice == 0;

            if (isAdmin || choice == 1) {
                String username = null;
                String password = null;
                if (isAdmin) {
                    username = JOptionPane.showInputDialog(null, "Enter username:");
                    password = JOptionPane.showInputDialog(null, "Enter password:");
                }
                if (!isAdmin || (username != null && username.equals("admin") && password != null && password.equals("123"))) {
                    JOptionPane.showMessageDialog(null, isAdmin ? "Welcome, Admin!" : "Welcome, Visitor!");
                    CircularLinkedListGUI gui = new CircularLinkedListGUI(isAdmin);
                    gui.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid username or password.");
                }
            }
        });
    }
 private static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}
