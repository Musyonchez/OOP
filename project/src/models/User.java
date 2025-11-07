package models;

/**
 * Abstract User class demonstrating Abstraction
 * Serves as base class for different user types (Member, Librarian)
 */
public abstract class User {
    // Encapsulation: private fields
    private String id;
    private String name;
    private String email;
    private String phone;

    /**
     * Constructor for User
     */
    public User(String id, String name, String email, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    // Getters and setters for encapsulation
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Abstract method to display user information
     * Must be implemented by subclasses (Polymorphism)
     */
    public abstract void displayInfo();

    /**
     * Abstract method to get user type
     * Different implementations for Member and Librarian
     */
    public abstract String getUserType();

    /**
     * Convert user to string for file storage
     */
    @Override
    public String toString() {
        return id + "|" + name + "|" + email + "|" + phone + "|" + getUserType();
    }
}
