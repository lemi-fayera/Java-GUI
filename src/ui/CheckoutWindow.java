package ui;

import model.Room;
import system.HotelSystem;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CheckoutWindow extends JFrame {
    public CheckoutWindow(HotelSystem system) {
        setTitle("Process Guest Check-out");
        setSize(450, 250);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(245, 247, 250));
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // 1. Get ONLY booked rooms
        List<Room> bookedRooms = system.getAllRooms().stream()
                .filter(r -> !r.isAvailable())
                .toList();

        if (bookedRooms.isEmpty()) {
            JLabel emptyMsg = new JLabel("No rooms are currently occupied.");
            emptyMsg.setFont(new Font("Segoe UI", Font.ITALIC, 14));
            add(emptyMsg);
            
            JButton closeBtn = new JButton("Close");
            gbc.gridy = 1;
            add(closeBtn, gbc);
            closeBtn.addActionListener(e -> dispose());
        } else {
            // 2. UI Elements
            JLabel label = new JLabel("Select Occupied Room:");
            label.setFont(new Font("Segoe UI", Font.BOLD, 14));
            
            JComboBox<Room> roomBox = new JComboBox<>(bookedRooms.toArray(new Room[0]));
            roomBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            roomBox.setPreferredSize(new Dimension(200, 30));

            JButton outBtn = new JButton("CONFIRM CHECK-OUT");
            outBtn.setBackground(new Color(231, 76, 60)); // Red for checkout
            outBtn.setForeground(Color.WHITE);
            outBtn.setFont(new Font("Segoe UI", Font.BOLD, 12));
            outBtn.setFocusPainted(false);
            outBtn.setOpaque(true);
            outBtn.setBorderPainted(false);
            outBtn.setPreferredSize(new Dimension(200, 40));

            // 3. Positioning
            gbc.gridx = 0; gbc.gridy = 0;
            add(label, gbc);

            gbc.gridy = 1;
            add(roomBox, gbc);

            gbc.gridy = 2;
            gbc.insets = new Insets(20, 10, 10, 10);
            add(outBtn, gbc);

            // 4. Action
            outBtn.addActionListener(e -> {
                Room selected = (Room) roomBox.getSelectedItem();
                if (selected != null) {
                    int confirm = JOptionPane.showConfirmDialog(this, 
                        "Are you sure you want to check out Room " + selected.getRoomNumber() + "?",
                        "Confirm Action", JOptionPane.YES_NO_OPTION);
                        
                    if (confirm == JOptionPane.YES_OPTION) {
                        system.checkout(selected.getRoomNumber());
                        JOptionPane.showMessageDialog(this, "Check-out successful! Room is now available.");
                        dispose();
                    }
                }
            });
        }
    }
}