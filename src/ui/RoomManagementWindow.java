package ui;

import model.Room;
import system.HotelSystem;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class RoomManagementWindow extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    public RoomManagementWindow(HotelSystem system) {
        setTitle("Room Management");
        setSize(600, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // Table
        String[] columns = {"Room #", "Type", "Price", "Status"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        refreshTable(system);

        // Form Panel
        JPanel form = new JPanel(new FlowLayout());
        JTextField numF = new JTextField(5);
        JComboBox<String> typeF = new JComboBox<>(new String[]{"Single", "Double", "Suite"});
        JTextField priceF = new JTextField(5);
        JButton addBtn = new JButton("Add Room");

        form.add(new JLabel("Num:")); form.add(numF);
        form.add(new JLabel("Type:")); form.add(typeF);
        form.add(new JLabel("Price:")); form.add(priceF);
        form.add(addBtn);

        addBtn.addActionListener(e -> {
            try {
                system.addRoom(new Room(numF.getText(), (String)typeF.getSelectedItem(), Double.parseDouble(priceF.getText())));
                refreshTable(system);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Data!");
            }
        });

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(form, BorderLayout.SOUTH);
    }

    private void refreshTable(HotelSystem system) {
        model.setRowCount(0);
        for (Room r : system.getAllRooms()) {
            model.addRow(new Object[]{r.getRoomNumber(), r.getType(), "$" + r.getPricePerNight(), r.isAvailable() ? "Available" : "Booked"});
        }
    }
}