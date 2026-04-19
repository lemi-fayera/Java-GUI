package model;

public class Room {

    private String roomNumber;
    private String type; // Single, Double, Suite
    private double pricePerNight;
    private boolean isAvailable;

    // Constructor
    public Room(String roomNumber, String type, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.pricePerNight = pricePerNight;
        this.isAvailable = true;
    }

    // Getters
    public String getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    // Setters (added for learning purposes)
    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    // Display info
    public String toString() {
        return "Room " + roomNumber + " - " + type + " - " + pricePerNight;
    }
}