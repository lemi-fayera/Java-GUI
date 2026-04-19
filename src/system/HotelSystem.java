package system;

import model.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Controller: Manages data lists and core business logic.
 * This acts as our temporary "Database".
 */
public class HotelSystem {
    private List<Room> rooms;
    private List<Customer> customers;
    private List<Reservation> reservations;

    public HotelSystem() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        reservations = new ArrayList<>();
        seedData(); // Add some initial data
    }

    private void seedData() {
        rooms.add(new Room("101", "Single", 50.0));
        rooms.add(new Room("102", "Double", 85.0));
        rooms.add(new Room("201", "Suite", 150.0));
        customers.add(new Customer("John Doe", "555-0101", "john@example.com", "ID123"));
    }

    // Business Methods
    public void addRoom(Room r) { rooms.add(r); }
    public void removeRoom(String roomNum) {
        rooms.removeIf(r -> r.getRoomNumber().equals(roomNum));
    }
    public void addCustomer(Customer c) { customers.add(c); }
    
    public void createReservation(Customer c, Room r, int nights) {
        Reservation res = new Reservation(c, r, nights);
        reservations.add(res);
        r.setAvailable(false); // Mark as booked
    }

    public void checkout(String roomNum) {
        for (Room r : rooms) {
            if (r.getRoomNumber().equals(roomNum)) {
                r.setAvailable(true);
                reservations.removeIf(res -> res.getRoom().getRoomNumber().equals(roomNum));
                break;
            }
        }
    }

    // Getters for UI
    public List<Room> getAllRooms() { return rooms; }
    public List<Customer> getAllCustomers() { return customers; }
    public List<Reservation> getAllReservations() { return reservations; }
}