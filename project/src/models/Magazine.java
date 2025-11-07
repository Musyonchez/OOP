package models;

/**
 * Magazine class demonstrating Inheritance (extends MediaItem) and Polymorphism
 * Represents a magazine in the library catalog
 */
public class Magazine extends MediaItem {
    private int issueNumber;
    private String publisher;
    private String publicationDate;

    /**
     * Constructor for Magazine
     * Calls parent constructor using super()
     */
    public Magazine(String id, String title, int issueNumber,
                    String publisher, String publicationDate) {
        super(id, title);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
    }

    // Getters and setters
    public int getIssueNumber() {
        return issueNumber;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    /**
     * Override abstract method from MediaItem
     * Demonstrates Polymorphism
     */
    @Override
    public String getMediaType() {
        return "MAGAZINE";
    }

    /**
     * Override abstract method from MediaItem
     * Magazines have lower late fee: $0.50 per day
     * Demonstrates Polymorphism - different behavior than Book
     */
    @Override
    public double calculateLateFee(int daysLate) {
        return BASE_LATE_FEE_PER_DAY * 0.5 * daysLate;
    }

    /**
     * Override abstract method from MediaItem
     * Displays magazine-specific information
     */
    @Override
    public void displayDetails() {
        System.out.println("\n--- Magazine Details ---");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Issue Number: " + issueNumber);
        System.out.println("Publisher: " + publisher);
        System.out.println("Publication Date: " + publicationDate);
        System.out.println("Status: " + (isAvailable() ? "Available" : "Borrowed"));
        if (!isAvailable()) {
            System.out.println("Borrowed by: " + getBorrowedBy());
        }
        System.out.println("-----------------------");
    }

    /**
     * Override toString for file storage
     */
    @Override
    public String toString() {
        return getId() + "|" + getTitle() + "|" + getMediaType() + "|" +
               isAvailable() + "|" + (getBorrowedBy() != null ? getBorrowedBy() : "") + "|" +
               issueNumber + "|" + publisher + "|" + publicationDate;
    }
}
