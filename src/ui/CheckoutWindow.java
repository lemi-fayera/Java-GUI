package ui;

import model.Room;
import system.HotelSystem;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CheckoutWindow extends JFrame {
    public CheckoutWindow(HotelSystem system) {
        // Window Setup
        setTitle("Process Guest Check-out");
        setSize(450, 320);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE); 
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 25, 10, 25); // Room to breathe
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;

        // Styling Definitions
        Font headerFont = new Font("Segoe UI", Font.BOLD, 15);
        Font regularFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color accentRed = new Color(231, 76, 60);

        // 1. Logic: Filter for Occupied Rooms
        List<Room> bookedRooms = system.getAllRooms().stream()
                .filter(r -> !r.isAvailable())
                .toList();

        if (bookedRooms.isEmpty()) {
            // "No Rooms" State
            JLabel emptyMsg = new JLabel("No rooms are currently occupied.", SwingConstants.CENTER);
            emptyMsg.setFont(new Font("Segoe UI", Font.ITALIC, 15));
            emptyMsg.setForeground(Color.GRAY);
            
            gbc.gridy = 0;
            add(emptyMsg, gbc);
            
            JButton closeBtn = new JButton("Close Window");
            closeBtn.setFont(headerFont);
            closeBtn.setFocusPainted(false);
            gbc.gridy = 1;
            add(closeBtn, gbc);
            
            closeBtn.addActionListener(e -> dispose());
        } else {
            // 2. Header
            JLabel label = new JLabel("Select Occupied Room:");
            label.setFont(headerFont);
            gbc.gridy = 0;
            add(label, gbc);

            // 3. Room Dropdown
            JComboBox<Room> roomBox = new JComboBox<>(bookedRooms.toArray(new Room[0]));
            roomBox.setFont(regularFont);
            roomBox.setBackground(Color.WHITE);
            roomBox.setPreferredSize(new Dimension(200, 35));
            gbc.gridy = 1;
            add(roomBox, gbc);

            // 4. Check-out Button
            JButton outBtn = new JButton("CONFIRM CHECK-OUT");
            outBtn.setBackground(accentRed);
            outBtn.setForeground(Color.WHITE);
            outBtn.setFont(headerFont);
            outBtn.setFocusPainted(false);
            outBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            outBtn.setPreferredSize(new Dimension(200, 45));
            
            // Add spacing specifically for the button
            gbc.gridy = 2;
            gbc.insets = new Insets(25, 25, 10, 25); 
            add(outBtn, gbc);

            // 5. Action Logic
            outBtn.addActionListener(e -> {
                Room selected = (Room) roomBox.getSelectedItem();
                if (selected != null) {
                    int confirm = JOptionPane.showConfirmDialog(this, 
                        "Check out Room " + selected.getRoomNumber() + "?",
                        "Confirm Check-out", 
                        JOptionPane.YES_NO_OPTION, 
                        JOptionPane.WARNING_MESSAGE);
                        
                    if (confirm == JOptionPane.YES_OPTION) {
                        system.checkout(selected.getRoomNumber());
                        JOptionPane.showMessageDialog(this, "Check-out successful! Room is now vacant.");
                        dispose();
                    }
                }
            });
        }
    }
}