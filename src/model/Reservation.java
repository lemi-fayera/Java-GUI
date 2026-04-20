package model;

/**
 * Links a Customer to a Room for a specific duration.
 */
public class Reservation {
    private final Customer customer;
    private final Room room;
    private final int nights;
    private final double totalCost;

    public Reservation(Customer customer, Room room, int nights) {
        if (customer == null || room == null) {
            throw new IllegalArgumentException("Customer and Room cannot be null");
        }

        this.customer = customer;
        this.room = room;
        this.nights = nights;
        this.totalCost = room.getPricePerNight() * nights;
    }

    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }
    public int getNights() { return nights; }
    public double getTotalCost() { return totalCost; }
}