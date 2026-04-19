package ui;

import system.HotelSystem;
import javax.swing.*;
import java.awt.*;

public class DashboardWindow extends JFrame {

    private HotelSystem system;
    private JLabel totalLabel, availLabel, bookedLabel;

    public DashboardWindow(HotelSystem system) {
        this.system = system;

        setTitle("LuxeStay | Management Suite");
        setSize(1000, 650); // Slightly wider for better proportions
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // ===== 1. TOP HEADER =====
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(Color.WHITE);
        header.setPreferredSize(new Dimension(getWidth(), 70));
        header.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(220, 220, 220)));

        JLabel logo = new JLabel("  LUXESTAY");
        logo.setFont(new Font("Segoe UI", Font.BOLD, 24));
        logo.setForeground(new Color(44, 62, 80));
        
        JLabel userRole = new JLabel("Admin Session: Active  ");
        userRole.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        userRole.setForeground(Color.GRAY);

        header.add(logo, BorderLayout.WEST);
        header.add(userRole, BorderLayout.EAST);

        // ===== 2. SIDEBAR =====
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(44, 62, 80)); // Slightly lighter navy than pure black
        sidebar.setLayout(new GridLayout(8, 1, 0, 10)); // Added gaps between buttons
        sidebar.setBorder(BorderFactory.createEmptyBorder(20, 15, 20, 15));
        sidebar.setPreferredSize(new Dimension(230, getHeight()));

        // Buttons with specific background colors
        JButton btnReg = createStyledButton("Register Guest", new Color(52, 73, 94));
        JButton btnRoom = createStyledButton("Manage Rooms", new Color(52, 73, 94));
        JButton btnBook = createStyledButton("New Booking", new Color(41, 128, 185)); // Standout color
        JButton btnOut = createStyledButton("Check-out", new Color(52, 73, 94));
        JButton btnRefresh = createStyledButton("Refresh Stats", new Color(39, 174, 96)); // Green for refresh

        sidebar.add(btnReg);
        sidebar.add(btnRoom);
        sidebar.add(btnBook);
        sidebar.add(btnOut);
        sidebar.add(new JLabel("")); // Spacer
        sidebar.add(btnRefresh);

        // ===== 3. MAIN CONTENT =====
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 247, 250));

        JLabel title = new JLabel("System Overview");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(25, 35, 10, 0));

        JPanel statsPanel = new JPanel(new GridLayout(1, 3, 25, 25));
        statsPanel.setBorder(BorderFactory.createEmptyBorder(20, 35, 30, 35));
        statsPanel.setBackground(new Color(245, 247, 250));

        totalLabel = createStatCard(statsPanel, "TOTAL ROOMS", new Color(71, 82, 94));
        availLabel = createStatCard(statsPanel, "AVAILABLE", new Color(46, 204, 113));
        bookedLabel = createStatCard(statsPanel, "BOOKED", new Color(231, 76, 60));

        mainPanel.add(title, BorderLayout.NORTH);
        mainPanel.add(statsPanel, BorderLayout.CENTER);

        // Assemble everything
        add(header, BorderLayout.NORTH);
        add(sidebar, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        // ===== EVENTS =====
        btnReg.addActionListener(e -> new RegisterCustomerWindow(system).setVisible(true));
        btnRoom.addActionListener(e -> new RoomManagementWindow(system).setVisible(true));
        btnBook.addActionListener(e -> new BookingWindow(system).setVisible(true));
        btnOut.addActionListener(e -> new CheckoutWindow(system).setVisible(true));
        btnRefresh.addActionListener(e -> updateStats());

        updateStats();
    }

    // ===== ENHANCED BUTTON STYLE =====
    private JButton createStyledButton(String text, Color bgColor) {
        JButton btn = new JButton(text);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setForeground(Color.WHITE);
        btn.setBackground(bgColor);

        btn.setOpaque(true);
        btn.setContentAreaFilled(true);
        btn.setBorderPainted(false);
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        // Rounded-look padding
        btn.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Hover effect based on the original background color
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bgColor);
            }
        });

        return btn;
    }

    // ===== STAT CARD =====
    private JLabel createStatCard(JPanel parent, String title, Color color) {
        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(color);
        card.setBorder(BorderFactory.createEmptyBorder(25, 25, 25, 25));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setForeground(new Color(255, 255, 255, 200)); // Slight transparency for sub-text
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 12));

        JLabel lblVal = new JLabel("0");
        lblVal.setFont(new Font("Segoe UI", Font.BOLD, 48));
        lblVal.setForeground(Color.WHITE);

        card.add(lblTitle, BorderLayout.NORTH);
        card.add(lblVal, BorderLayout.CENTER);

        parent.add(card);
        return lblVal;
    }

    public void updateStats() {
        int total = system.getAllRooms().size();
        int booked = (int) system.getAllRooms().stream().filter(r -> !r.isAvailable()).count();
        totalLabel.setText(String.valueOf(total));
        bookedLabel.setText(String.valueOf(booked));
        availLabel.setText(String.valueOf(total - booked));
    }
}