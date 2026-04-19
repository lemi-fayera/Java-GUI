package model;

public class Customer {

    private String fullName;
    private String phone;
    private String email;
    private String idNumber;

    // Constructor
    public Customer(String fullName, String phone, String email, String idNumber) {
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.idNumber = idNumber;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    // Setters (important for beginner level)
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    // Display info
    public String toString() {
        return fullName + " (ID: " + idNumber + ")";
    }
}