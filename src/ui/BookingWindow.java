package ui;

import model.*;
import system.HotelSystem;
import javax.swing.*;
import java.awt.*;

public class BookingWindow extends JFrame {
    public BookingWindow(HotelSystem system) {
        setTitle("New Reservation");
        setSize(450, 350);
        setLayout(new GridLayout(6, 2, 10, 10));
        setLocationRelativeTo(null);

        JComboBox<Customer> custBox = new JComboBox<>(system.getAllCustomers().toArray(new Customer[0]));
        
        // Only show available rooms
        java.util.List<Room> availRooms = system.getAllRooms().stream().filter(Room::isAvailable).toList();
        JComboBox<Room> roomBox = new JComboBox<>(availRooms.toArray(new Room[0]));
        
        JTextField nightsF = new JTextField("1");
        JLabel totalLbl = new JLabel("Total: $0.00");

        add(new JLabel(" Select Guest:")); add(custBox);
        add(new JLabel(" Select Room:")); add(roomBox);
        add(new JLabel(" Nights:")); add(nightsF);
        add(new JLabel(" Total Cost:")); add(totalLbl);

        JButton calcBtn = new JButton("Calculate Cost");
        calcBtn.addActionListener(e -> {
            Room r = (Room) roomBox.getSelectedItem();
            if(r != null) {
                double total = r.getPricePerNight() * Integer.parseInt(nightsF.getText());
                totalLbl.setText("Total: $" + total);
            }
        });

        JButton bookBtn = new JButton("Confirm Booking");
        bookBtn.setBackground(new Color(41, 128, 185));
        bookBtn.setForeground(Color.WHITE);
        bookBtn.addActionListener(e -> {
            Customer c = (Customer) custBox.getSelectedItem();
            Room r = (Room) roomBox.getSelectedItem();
            if(c != null && r != null) {
                system.createReservation(c, r, Integer.parseInt(nightsF.getText()));
                JOptionPane.showMessageDialog(this, "Booking Confirmed!");
                dispose();
            }
        });

        add(calcBtn); add(bookBtn);
    }
}