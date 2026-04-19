package main;

import system.HotelSystem;
import ui.DashboardWindow;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Main {
    public static void main(String[] args) {
        // Apply a modern Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize the logic and the UI
        SwingUtilities.invokeLater(() -> {
            HotelSystem system = new HotelSystem();
            DashboardWindow dashboard = new DashboardWindow(system);
            dashboard.setVisible(true);
        });
    }
}