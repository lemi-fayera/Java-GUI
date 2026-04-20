package model;

public class Customer {
    private final String fullName;
    private final String phone;
    private final String email;
    private final String idNumber;

    public Customer(String fullName, String phone, String email, String idNumber) {
        if (fullName == null || idNumber == null) {
            throw new IllegalArgumentException("Name and ID cannot be null");
        }

        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.idNumber = idNumber;
    }

    // Getters
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public String getIdNumber() { return idNumber; }

    @Override
    public String toString() {
        return fullName + " (ID: " + idNumber + ")";
    }
}