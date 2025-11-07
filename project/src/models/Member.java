package models;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Member class demonstrating Inheritance (extends User) and Polymorphism
 * Represents a library member who can borrow items
 */
public class Member extends User {
    private LocalDate membershipDate;
    private ArrayList<String> borrowedItems;
    private double fines;

    /**
     * Constructor for Member
     * Calls parent constructor using super()
     */
    public Member(String id, String name, String email, String phone) {
        super(id, name, email, phone);
        this.membershipDate = LocalDate.now();
        this.borrowedItems = new ArrayList<>();
        this.fines = 0.0;
    }

    /**
     * Constructor with membership date (for loading from file)
     */
    public Member(String id, String name, String email, String phone,
                  LocalDate membershipDate, double fines) {
        super(id, name, email, phone);
        this.membershipDate = membershipDate;
        this.borrowedItems = new ArrayList<>();
        this.fines = fines;
    }

    // Getters and setters
    public LocalDate getMembershipDate() {
        return membershipDate;
    }

    public ArrayList<String> getBorrowedItems() {
        return borrowedItems;
    }

    public double getFines() {
        return fines;
    }

    public void setFines(double fines) {
        this.fines = fines;
    }

    public void addFine(double amount) {
        this.fines += amount;
    }

    public void payFine(double amount) {
        this.fines -= amount;
        if (this.fines < 0) {
            this.fines = 0;
        }
    }

    /**
     * Add borrowed item to member's list
     */
    public void borrowItem(String mediaId) {
        borrowedItems.add(mediaId);
    }

    /**
     * Remove item from borrowed list when returned
     */
    public void returnItem(String mediaId) {
        borrowedItems.remove(mediaId);
    }

    /**
     * Check if member can borrow (no outstanding fines)
     */
    public boolean canBorrow() {
        return fines == 0;
    }

    /**
     * Override abstract method from User class
     * Demonstrates Polymorphism - different implementation than Librarian
     */
    @Override
    public void displayInfo() {
        System.out.println("\n--- Member Information ---");
        System.out.println("Member ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Email: " + getEmail());
        System.out.println("Phone: " + getPhone());
        System.out.println("Member Since: " + membershipDate);
        System.out.println("Borrowed Items: " + borrowedItems.size());
        System.out.println("Outstanding Fines: $" + String.format("%.2f", fines));
        System.out.println("Borrowing Status: " + (canBorrow() ? "Active" : "Suspended - Pay fines"));
        System.out.println("------------------------");
    }

    /**
     * Override abstract method from User class
     */
    @Override
    public String getUserType() {
        return "MEMBER";
    }

    /**
     * Override toString for file storage
     */
    @Override
    public String toString() {
        return getId() + "|" + getName() + "|" + getEmail() + "|" + getPhone() + "|" +
               getUserType() + "|" + membershipDate + "|" + fines;
    }
}
