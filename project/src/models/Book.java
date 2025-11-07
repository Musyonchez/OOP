package models;

/**
 * Book class demonstrating Inheritance (extends MediaItem) and Polymorphism
 * Represents a book in the library catalog
 */
public class Book extends MediaItem {
    private String author;
    private String isbn;
    private int pages;
    private String publisher;

    /**
     * Constructor for Book
     * Calls parent constructor using super()
     */
    public Book(String id, String title, String author, String isbn,
                int pages, String publisher) {
        super(id, title);
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
        this.publisher = publisher;
    }

    // Getters and setters
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPages() {
        return pages;
    }

    public String getPublisher() {
        return publisher;
    }

    /**
     * Override abstract method from MediaItem
     * Demonstrates Polymorphism
     */
    @Override
    public String getMediaType() {
        return "BOOK";
    }

    /**
     * Override abstract method from MediaItem
     * Books have standard late fee: $1.00 per day
     */
    @Override
    public double calculateLateFee(int daysLate) {
        return BASE_LATE_FEE_PER_DAY * daysLate;
    }

    /**
     * Override abstract method from MediaItem
     * Displays book-specific information
     */
    @Override
    public void displayDetails() {
        System.out.println("\n--- Book Details ---");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Author: " + author);
        System.out.println("ISBN: " + isbn);
        System.out.println("Pages: " + pages);
        System.out.println("Publisher: " + publisher);
        System.out.println("Status: " + (isAvailable() ? "Available" : "Borrowed"));
        if (!isAvailable()) {
            System.out.println("Borrowed by: " + getBorrowedBy());
        }
        System.out.println("-------------------");
    }

    /**
     * Override toString for file storage
     */
    @Override
    public String toString() {
        return getId() + "|" + getTitle() + "|" + getMediaType() + "|" +
               isAvailable() + "|" + (getBorrowedBy() != null ? getBorrowedBy() : "") + "|" +
               author + "|" + isbn + "|" + pages + "|" + publisher;
    }
}
