package system;

import model.*;
import java.util.ArrayList;

public class HotelSystem {

    private ArrayList<Room> rooms;
    private ArrayList<Customer> customers;
    private ArrayList<Reservation> reservations;

    public HotelSystem() {
        rooms = new ArrayList<>();
        customers = new ArrayList<>();
        reservations = new ArrayList<>();

        seedData();
    }

    private void seedData() {
        rooms.add(new Room("101", "Single", 50));
        rooms.add(new Room("102", "Double", 80));
        rooms.add(new Room("201", "Suite", 150));

        customers.add(new Customer("John Doe", "555", "john@mail.com", "ID1"));
    }

    public void addRoom(Room r) {
        rooms.add(r);
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public void createReservation(Customer c, Room r, int nights) {
        Reservation res = new Reservation(c, r, nights);
        reservations.add(res);
        r.setAvailable(false);
    }

    public void checkout(String roomNum) {
        for (Room r : rooms) {
            if (r.getRoomNumber().equals(roomNum)) {
                r.setAvailable(true);
            }
        }

        for (int i = 0; i < reservations.size(); i++) {
            if (reservations.get(i).getRoom().getRoomNumber().equals(roomNum)) {
                reservations.remove(i);
                break;
            }
        }
    }

    public ArrayList<Room> getAllRooms() {
        return rooms;
    }

    public ArrayList<Customer> getAllCustomers() {
        return customers;
    }

    public ArrayList<Reservation> getAllReservations() {
        return reservations;
    }
}