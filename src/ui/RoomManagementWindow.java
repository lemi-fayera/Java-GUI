package ui;

import model.Room;
import system.HotelSystem;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RoomManagementWindow extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public RoomManagementWindow(HotelSystem system) {
        setTitle("Room Management");
        setSize(700, 500); // Slightly larger for table visibility
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        // --- 1. Table Styling ---
        String[] columns = {"Room #", "Type", "Price", "Status"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only for a cleaner feel
            }
        };
        
        table = new JTable(model);
        table.setRowHeight(30); // More space for each row
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(240, 240, 240));
        table.setSelectionBackground(new Color(174, 214, 241)); // Light blue selection
        
        // Center text in cells
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        refreshTable(system);

        // --- 2. Form Panel Styling ---
        JPanel form = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        form.setBackground(new Color(245, 247, 250));
        form.setBorder(new EmptyBorder(10, 10, 10, 10));

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        JTextField numF = new JTextField(5);
        JComboBox<String> typeF = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        JTextField priceF = new JTextField(5);
        JButton addBtn = new JButton("Add Room");

        // Style the inputs
        numF.setFont(font);
        typeF.setFont(font);
        typeF.setBackground(Color.WHITE);
        priceF.setFont(font);

        // Style the button
        addBtn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        addBtn.setBackground(new Color(41, 128, 185)); // Matches dashboard blue
        addBtn.setForeground(Color.WHITE);
        addBtn.setFocusPainted(false);
        addBtn.setPreferredSize(new Dimension(120, 35));

        form.add(new JLabel("Room Num:")); form.add(numF);
        form.add(new JLabel("Type:")); form.add(typeF);
        form.add(new JLabel("Price ($):")); form.add(priceF);
        form.add(addBtn);

        // --- 3. Logic ---
        addBtn.addActionListener(e -> {
            try {
                if (numF.getText().isEmpty() || priceF.getText().isEmpty()) {
                    throw new Exception();
                }
                system.addRoom(new Room(numF.getText(), (String)typeF.getSelectedItem(), Double.parseDouble(priceF.getText())));
                refreshTable(system);
                
                // Clear fields after adding
                numF.setText("");
                priceF.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Please enter valid room details!", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Add components to frame
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove clunky borders
        add(scrollPane, BorderLayout.CENTER);
        add(form, BorderLayout.SOUTH);
    }

    private void refreshTable(HotelSystem system) {
        model.setRowCount(0);
        for (Room r : system.getAllRooms()) {
            model.addRow(new Object[]{
                r.getRoomNumber(), 
                r.getType(), 
                String.format("$%.2f", r.getPricePerNight()), 
                r.isAvailable() ? "Available" : "Booked"
            });
        }
    }
}