package models;

import java.time.LocalDate;

/**
 * Librarian class demonstrating Inheritance (extends User) and Polymorphism
 * Represents a library staff member with administrative privileges
 */
public class Librarian extends User {
    private String employeeId;
    private String position;
    private LocalDate hireDate;

    /**
     * Constructor for Librarian
     * Calls parent constructor using super()
     */
    public Librarian(String id, String name, String email, String phone,
                     String employeeId, String position) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
        this.position = position;
        this.hireDate = LocalDate.now();
    }

    /**
     * Constructor with hire date (for loading from file)
     */
    public Librarian(String id, String name, String email, String phone,
                     String employeeId, String position, LocalDate hireDate) {
        super(id, name, email, phone);
        this.employeeId = employeeId;
        this.position = position;
        this.hireDate = hireDate;
    }

    // Getters and setters
    public String getEmployeeId() {
        return employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    /**
     * Override abstract method from User class
     * Demonstrates Polymorphism - different implementation than Member
     */
    @Override
    public void displayInfo() {
        System.out.println("\n--- Librarian Information ---");
        System.out.println("User ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Position: " + position);
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Role: System Administrator");
        System.out.println("---------------------------");
    }

    /**
     * Override abstract method from User class
     */
    @Override
    public String getUserType() {
        return "LIBRARIAN";
    }

    /**
     * Override toString for file storage
     */
    @Override
    public String toString() {
        return getId() + "|" + getName() + "|" + getEmail() + "|" + getPhone() + "|" +
               getUserType() + "|" + employeeId + "|" + position + "|" + hireDate;
    }
}
