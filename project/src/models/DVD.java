package models;

/**
 * DVD class demonstrating Inheritance (extends MediaItem) and Polymorphism
 * Represents a DVD in the library catalog
 */
public class DVD extends MediaItem {
    private String director;
    private int duration; // in minutes
    private String genre;
    private String releaseYear;

    /**
     * Constructor for DVD
     * Calls parent constructor using super()
     */
    public DVD(String id, String title, String director, int duration,
               String genre, String releaseYear) {
        super(id, title);
        this.director = director;
        this.duration = duration;
        this.genre = genre;
        this.releaseYear = releaseYear;
    }

    // Getters and setters
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    /**
     * Override abstract method from MediaItem
     * Demonstrates Polymorphism
     */
    @Override
    public String getMediaType() {
        return "DVD";
    }

    /**
     * Override abstract method from MediaItem
     * DVDs have higher late fee: $2.00 per day
     * Demonstrates Polymorphism - different behavior than Book and Magazine
     */
    @Override
    public double calculateLateFee(int daysLate) {
        return BASE_LATE_FEE_PER_DAY * 2.0 * daysLate;
    }

    /**
     * Override abstract method from MediaItem
     * Displays DVD-specific information
     */
    @Override
    public void displayDetails() {
        System.out.println("\n--- DVD Details ---");
        System.out.println("ID: " + getId());
        System.out.println("Title: " + getTitle());
        System.out.println("Director: " + director);
        System.out.println("Duration: " + duration + " minutes");
        System.out.println("Genre: " + genre);
        System.out.println("Release Year: " + releaseYear);
        System.out.println("Status: " + (isAvailable() ? "Available" : "Borrowed"));
        if (!isAvailable()) {
            System.out.println("Borrowed by: " + getBorrowedBy());
        }
        System.out.println("------------------");
    }

    /**
     * Override toString for file storage
     */
    @Override
    public String toString() {
        return getId() + "|" + getTitle() + "|" + getMediaType() + "|" +
               isAvailable() + "|" + (getBorrowedBy() != null ? getBorrowedBy() : "") + "|" +
               director + "|" + duration + "|" + genre + "|" + releaseYear;
    }
}
