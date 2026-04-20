package ui;

import model.*;
import system.HotelSystem;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BookingWindow extends JFrame {
    public BookingWindow(HotelSystem system) {
        setTitle("New Reservation");
        setSize(480, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Define consistent fonts and colors
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color primaryBlue = new Color(41, 128, 185);
        Color bgWhite = Color.WHITE;

        // Main container with padding
        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 15, 15));
        mainPanel.setBackground(bgWhite);
        mainPanel.setBorder(new EmptyBorder(25, 25, 25, 25));

        // Styling Components
        JLabel guestLbl = new JLabel("Select Guest:");
        guestLbl.setFont(labelFont);
        
        JComboBox<Customer> custBox = new JComboBox<>(system.getAllCustomers().toArray(new Customer[0]));
        custBox.setFont(inputFont);
        custBox.setBackground(bgWhite);

        JLabel roomLbl = new JLabel("Select Room:");
        roomLbl.setFont(labelFont);

        java.util.List<Room> availRooms = system.getAllRooms().stream().filter(Room::isAvailable).toList();
        JComboBox<Room> roomBox = new JComboBox<>(availRooms.toArray(new Room[0]));
        roomBox.setFont(inputFont);
        roomBox.setBackground(bgWhite);

        JLabel nightLbl = new JLabel("Number of Nights:");
        nightLbl.setFont(labelFont);

        JTextField nightsF = new JTextField("1");
        nightsF.setFont(inputFont);
        nightsF.setMargin(new Insets(5, 5, 5, 5)); // Internal padding for text

        JLabel totalTextLbl = new JLabel("Total Cost:");
        totalTextLbl.setFont(labelFont);

        JLabel totalAmountLbl = new JLabel("$0.00");
        totalAmountLbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        totalAmountLbl.setForeground(new Color(39, 174, 96)); // Green for money

        // Buttons
        JButton calcBtn = new JButton("Calculate Cost");
        calcBtn.setFont(labelFont);
        calcBtn.setFocusPainted(false);
        calcBtn.setBackground(new Color(236, 240, 241));

        JButton bookBtn = new JButton("Confirm Booking");
        bookBtn.setFont(labelFont);
        bookBtn.setFocusPainted(false);
        bookBtn.setBackground(primaryBlue);
        bookBtn.setForeground(Color.WHITE);

        // Logic
        calcBtn.addActionListener(e -> {
            try {
                Room r = (Room) roomBox.getSelectedItem();
                if (r != null) {
                    double total = r.getPricePerNight() * Integer.parseInt(nightsF.getText());
                    totalAmountLbl.setText(String.format("$%.2f", total));
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number of nights.");
            }
        });

        bookBtn.addActionListener(e -> {
            Customer c = (Customer) custBox.getSelectedItem();
            Room r = (Room) roomBox.getSelectedItem();
            if (c != null && r != null) {
                try {
                    system.createReservation(c, r, Integer.parseInt(nightsF.getText()));
                    JOptionPane.showMessageDialog(this, "Booking Confirmed Successfully!");
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Check your inputs and try again.");
                }
            }
        });

        // Add to Panel
        mainPanel.add(guestLbl); mainPanel.add(custBox);
        mainPanel.add(roomLbl); mainPanel.add(roomBox);
        mainPanel.add(nightLbl); mainPanel.add(nightsF);
        mainPanel.add(totalTextLbl); mainPanel.add(totalAmountLbl);
        mainPanel.add(calcBtn); mainPanel.add(bookBtn);

        add(mainPanel);
    }
}