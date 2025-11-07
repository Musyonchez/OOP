package models;

/**
 * Abstract MediaItem class demonstrating Abstraction
 * Base class for different media types (Book, Magazine, DVD)
 */
public abstract class MediaItem {
    // Encapsulation: private fields
    private String id;
    private String title;
    private boolean available;
    private String borrowedBy;

    // Constant for late fee calculation
    public static final double BASE_LATE_FEE_PER_DAY = 1.0;

    /**
     * Constructor for MediaItem
     */
    public MediaItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
        this.borrowedBy = null;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    /**
     * Abstract method to get media type
     * Must be implemented by subclasses
     */
    public abstract String getMediaType();

    /**
     * Abstract method to calculate late fees
     * Different media types may have different fee structures (Polymorphism)
     */
    public abstract double calculateLateFee(int daysLate);

    /**
     * Abstract method to display media details
     * Each media type displays different information
     */
    public abstract void displayDetails();

    /**
     * Display basic availability information
     */
    public void displayAvailability() {
        System.out.println("ID: " + id);
        System.out.println("Title: " + title);
        System.out.println("Status: " + (available ? "Available" : "Borrowed"));
        if (!available) {
            System.out.println("Borrowed by: " + borrowedBy);
        }
    }

    /**
     * Convert media item to string for file storage
     * Uses | as delimiter
     */
    @Override
    public String toString() {
        return id + "|" + title + "|" + getMediaType() + "|" + available + "|" +
               (borrowedBy != null ? borrowedBy : "");
    }
}
