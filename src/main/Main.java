package main;

import system.HotelSystem;
import ui.DashboardWindow;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {

        // Create system (you can keep it for now)
        HotelSystem system = new HotelSystem();

        // Create main frame
        JFrame frame = new JFrame("Hotel Reservation System");

        // Set size and close operation
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add dashboard panel
        DashboardWindow dashboard = new DashboardWindow(system);
        frame.add(dashboard);

        // Show window
        frame.setVisible(true);
    }
}