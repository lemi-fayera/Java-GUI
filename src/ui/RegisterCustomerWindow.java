package ui;

import model.Customer;
import system.HotelSystem;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class RegisterCustomerWindow extends JFrame {

    public RegisterCustomerWindow(HotelSystem system) {
        setTitle("Register New Guest");
        setSize(500, 400);
        setLocationRelativeTo(null);
        
        // Set white background to match your dashboard dashboard
        getContentPane().setBackground(Color.WHITE);

        // Using a Panel to hold the GridLayout so we can add an EmptyBorder (padding)
        JPanel contentPanel = new JPanel(new GridLayout(5, 2, 15, 15));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30)); // Adds space around the form edges

        Font font = new Font("Segoe UI", Font.PLAIN, 14);
        Font boldFont = new Font("Segoe UI", Font.BOLD, 14);

        JTextField nameF = new JTextField();
        JTextField phoneF = new JTextField();
        JTextField emailF = new JTextField();
        JTextField idF = new JTextField();

        // Apply font and internal padding to fields
        JTextField[] fields = {nameF, phoneF, emailF, idF};
        for (JTextField field : fields) {
            field.setFont(font);
            field.setMargin(new Insets(5, 8, 5, 8)); // Text won't touch the box edges
        }

        JLabel nameL = new JLabel(" Full Name:");
        JLabel phoneL = new JLabel(" Phone:");
        JLabel emailL = new JLabel(" Email:");
        JLabel idL = new JLabel(" ID Number:");

        // Apply font to labels
        nameL.setFont(boldFont);
        phoneL.setFont(boldFont);
        emailL.setFont(boldFont);
        idL.setFont(boldFont);

        contentPanel.add(nameL); contentPanel.add(nameF);
        contentPanel.add(phoneL); contentPanel.add(phoneF);
        contentPanel.add(emailL); contentPanel.add(emailF);
        contentPanel.add(idL); contentPanel.add(idF);

        JButton saveBtn = new JButton("Save Customer");
        saveBtn.setBackground(new Color(68, 161, 148)); // Your teal-green
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setFont(boldFont);
        saveBtn.setFocusPainted(false); // Removes the dotted line when clicked
        saveBtn.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Cursor changes to hand on hover

        saveBtn.setContentAreaFilled(true);
        saveBtn.setOpaque(true);
        saveBtn.setBorderPainted(false);

        saveBtn.addActionListener(e -> {
            if (nameF.getText().trim().isEmpty() || idF.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and ID are required!");
                return;
            }

            system.addCustomer(new Customer(
                    nameF.getText(),
                    phoneF.getText(),
                    emailF.getText(),
                    idF.getText()
            ));

            JOptionPane.showMessageDialog(this, "Guest Registered Successfully!");
            dispose();
        });

        contentPanel.add(new JLabel("")); // Empty space
        contentPanel.add(saveBtn);

        add(contentPanel); // Add the padded panel to the frame
    }
}