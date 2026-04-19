package ui;

import model.Customer;
import system.HotelSystem;
import javax.swing.*;
import java.awt.*;

public class RegisterCustomerWindow extends JFrame {
    public RegisterCustomerWindow(HotelSystem system) {
        setTitle("Register New Guest");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null);

        JTextField nameF = new JTextField();
        JTextField phoneF = new JTextField();
        JTextField emailF = new JTextField();
        JTextField idF = new JTextField();
        add(new JLabel(" Full Name:")); add(nameF);
        add(new JLabel(" Phone:")); add(phoneF);
        add(new JLabel(" Email:")); add(emailF);
        add(new JLabel(" ID Number:")); add(idF);

        JButton saveBtn = new JButton("Save Customer");
        saveBtn.setBackground(new Color(39, 174, 96));
        saveBtn.setForeground(Color.WHITE);
        
        saveBtn.addActionListener(e -> {
            if(nameF.getText().isEmpty() || idF.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Name and ID are required!");
                return;
            }
            system.addCustomer(new Customer(nameF.getText(), phoneF.getText(), emailF.getText(), idF.getText()));
            JOptionPane.showMessageDialog(this, "Guest Registered Successfully!");
            dispose();
        });

        add(new JLabel("")); add(saveBtn);
    }
}