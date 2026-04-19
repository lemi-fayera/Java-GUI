package model;

public class Reservation {

    private Customer customer;
    private Room room;
    private int nights;

    // Constructor
    public Reservation(Customer customer, Room room, int nights) {
        this.customer = customer;
        this.room = room;
        this.nights = nights;
    }

    // Getters
    public Customer getCustomer() {
        return customer;
    }

    public Room getRoom() {
        return room;
    }

    public int getNights() {
        return nights;
    }

    // Setters (important for beginner level)
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setNights(int nights) {
        this.nights = nights;
    }

    // Calculate total cost when needed
    public double getTotalCost() {
        return room.getPricePerNight() * nights;
    }

    // Optional: display info (useful for UI lists)
    public String toString() {
        return customer.getFullName() + " - Room " + room.getRoomNumber()
                + " (" + nights + " nights)";
    }
}