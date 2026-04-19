package model;

/**
 * Represents a hotel room.
 */
public class Room {
    private String roomNumber;
    private String type; // Single, Double, Suite
    private double pricePerNight;
    private boolean isAvailable;

    public Room(String roomNumber, String type, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getRoomNumber() { return roomNumber; }
    public String getType() { return type; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return "Room " + roomNumber + " [" + type + "] - $" + pricePerNight + "/night";
    }
}