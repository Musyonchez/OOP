package managers;

import models.*;
import database.FileManager;
import utils.SearchAlgorithms;
import utils.SortAlgorithms;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Library class - Main manager for the library system
 * Coordinates all operations: users, media, transactions
 * Demonstrates encapsulation and business logic
 */
public class Library {
    private List<User> users;
    private List<MediaItem> mediaItems;
    private List<Transaction> transactions;
    private int nextUserId;
    private int nextMediaId;
    private int nextTransactionId;

    /**
     * Constructor - initialize library and load data
     */
    public Library() {
        FileManager.initializeFiles();
        this.users = FileManager.loadUsers();
        this.mediaItems = FileManager.loadMedia();
        this.transactions = FileManager.loadTransactions();

        // Calculate next IDs
        this.nextUserId = calculateNextId(users, "U");
        this.nextMediaId = calculateNextId(mediaItems, "M");
        this.nextTransactionId = calculateNextId(transactions, "T");
    }

    /**
     * Calculate next ID based on existing items
     */
    private int calculateNextId(List<?> items, String prefix) {
        int maxId = 0;
        for (Object item : items) {
            String id;
            if (item instanceof User) {
                id = ((User) item).getId();
            } else if (item instanceof MediaItem) {
                id = ((MediaItem) item).getId();
            } else if (item instanceof Transaction) {
                id = ((Transaction) item).getTransactionId();
            } else {
                continue;
            }

            if (id.startsWith(prefix)) {
                try {
                    int num = Integer.parseInt(id.substring(1));
                    if (num > maxId) maxId = num;
                } catch (NumberFormatException e) {
                    // Skip invalid IDs
                }
            }
        }
        return maxId + 1;
    }

    // ==================== USER MANAGEMENT ====================

    /**
     * Register new member
     */
    public Member registerMember(String name, String email, String phone) {
        String id = "U" + String.format("%03d", nextUserId++);
        Member member = new Member(id, name, email, phone);
        users.add(member);
        FileManager.saveUser(member);
        System.out.println("Member registered successfully with ID: " + id);
        return member;
    }

    /**
     * Register new librarian
     */
    public Librarian registerLibrarian(String name, String email, String phone,
                                       String employeeId, String position) {
        String id = "U" + String.format("%03d", nextUserId++);
        Librarian librarian = new Librarian(id, name, email, phone, employeeId, position);
        users.add(librarian);
        FileManager.saveUser(librarian);
        System.out.println("Librarian registered successfully with ID: " + id);
        return librarian;
    }

    /**
     * Find user by ID
     */
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Get all members
     */
    public List<Member> getAllMembers() {
        List<Member> members = new ArrayList<>();
        for (User user : users) {
            if (user instanceof Member) {
                members.add((Member) user);
            }
        }
        return members;
    }

    // ==================== MEDIA MANAGEMENT ====================

    /**
     * Add new book to catalog
     */
    public Book addBook(String title, String author, String isbn,
                        int pages, String publisher) {
        String id = "M" + String.format("%03d", nextMediaId++);
        Book book = new Book(id, title, author, isbn, pages, publisher);
        mediaItems.add(book);
        FileManager.saveMedia(book);
        System.out.println("Book added successfully with ID: " + id);
        return book;
    }

    /**
     * Add new magazine to catalog
     */
    public Magazine addMagazine(String title, int issueNumber,
                                String publisher, String publicationDate) {
        String id = "M" + String.format("%03d", nextMediaId++);
        Magazine magazine = new Magazine(id, title, issueNumber, publisher, publicationDate);
        mediaItems.add(magazine);
        FileManager.saveMedia(magazine);
        System.out.println("Magazine added successfully with ID: " + id);
        return magazine;
    }

    /**
     * Add new DVD to catalog
     */
    public DVD addDVD(String title, String director, int duration,
                      String genre, String releaseYear) {
        String id = "M" + String.format("%03d", nextMediaId++);
        DVD dvd = new DVD(id, title, director, duration, genre, releaseYear);
        mediaItems.add(dvd);
        FileManager.saveMedia(dvd);
        System.out.println("DVD added successfully with ID: " + id);
        return dvd;
    }

    /**
     * Remove media from catalog
     */
    public boolean removeMedia(String mediaId) {
        MediaItem item = SearchAlgorithms.searchById(mediaItems, mediaId);
        if (item == null) {
            System.out.println("Media not found.");
            return false;
        }

        if (!item.isAvailable()) {
            System.out.println("Cannot remove media that is currently borrowed.");
            return false;
        }

        mediaItems.remove(item);
        FileManager.updateAllMedia(mediaItems);
        System.out.println("Media removed successfully.");
        return true;
    }

    /**
     * View all media items
     */
    public void viewAllMedia() {
        if (mediaItems.isEmpty()) {
            System.out.println("No media items in catalog.");
            return;
        }

        System.out.println("\n=================================================");
        System.out.println("Library Catalog - All Media Items");
        System.out.println("=================================================");
        for (MediaItem item : mediaItems) {
            System.out.printf("[%s] %s - %s (%s)%n",
                    item.getId(),
                    item.getTitle(),
                    item.getMediaType(),
                    item.isAvailable() ? "Available" : "Borrowed");
        }
        System.out.println("=================================================\n");
    }

    // ==================== BORROWING OPERATIONS ====================

    /**
     * Borrow media item
     */
    public Transaction borrowMedia(String userId, String mediaId) {
        // Find user
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("Error: User not found.");
            return null;
        }

        if (!(user instanceof Member)) {
            System.out.println("Error: Only members can borrow items.");
            return null;
        }

        Member member = (Member) user;

        // Check for fines
        if (!member.canBorrow()) {
            System.out.println("Error: Cannot borrow. Outstanding fines: $" +
                    String.format("%.2f", member.getFines()));
            return null;
        }

        // Find media
        MediaItem item = SearchAlgorithms.searchById(mediaItems, mediaId);
        if (item == null) {
            System.out.println("Error: Media not found.");
            return null;
        }

        // Check availability
        if (!item.isAvailable()) {
            System.out.println("Error: Media is currently borrowed.");
            return null;
        }

        // Create transaction
        String transactionId = "T" + String.format("%03d", nextTransactionId++);
        Transaction transaction = new Transaction(transactionId, userId, mediaId);

        // Update media status
        item.setAvailable(false);
        item.setBorrowedBy(userId);

        // Update member
        member.borrowItem(mediaId);

        // Save changes
        transactions.add(transaction);
        FileManager.saveTransaction(transaction);
        FileManager.updateAllMedia(mediaItems);
        FileManager.updateAllUsers(users);

        System.out.println("\n✓ Borrow successful!");
        System.out.println("Due Date: " + transaction.getDueDate());
        return transaction;
    }

    /**
     * Return media item
     */
    public boolean returnMedia(String userId, String mediaId) {
        // Find transaction
        Transaction transaction = null;
        for (Transaction t : transactions) {
            if (t.getUserId().equals(userId) &&
                    t.getMediaId().equals(mediaId) &&
                    !t.isCompleted()) {
                transaction = t;
                break;
            }
        }

        if (transaction == null) {
            System.out.println("Error: No active transaction found.");
            return false;
        }

        // Find user and media
        Member member = (Member) findUserById(userId);
        MediaItem item = SearchAlgorithms.searchById(mediaItems, mediaId);

        if (member == null || item == null) {
            System.out.println("Error: User or media not found.");
            return false;
        }

        // Set return date
        transaction.setReturnDate(LocalDate.now());

        // Calculate late fee if overdue
        int daysLate = transaction.calculateDaysLate();
        if (daysLate > 0) {
            double lateFee = item.calculateLateFee(daysLate);
            transaction.setLateFee(lateFee);
            member.addFine(lateFee);
            System.out.println("\n⚠ Item returned late!");
            System.out.println("Days late: " + daysLate);
            System.out.println("Late fee: $" + String.format("%.2f", lateFee));
        } else {
            System.out.println("\n✓ Item returned on time!");
        }

        // Update media status
        item.setAvailable(true);
        item.setBorrowedBy(null);

        // Update member
        member.returnItem(mediaId);

        // Save changes
        FileManager.updateAllTransactions(transactions);
        FileManager.updateAllMedia(mediaItems);
        FileManager.updateAllUsers(users);

        return true;
    }

    /**
     * View borrowed items for a user
     */
    public void viewBorrowedItems(String userId) {
        List<Transaction> userTransactions = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.getUserId().equals(userId) && !t.isCompleted()) {
                userTransactions.add(t);
            }
        }

        if (userTransactions.isEmpty()) {
            System.out.println("No borrowed items.");
            return;
        }

        System.out.println("\n=================================================");
        System.out.println("Borrowed Items");
        System.out.println("=================================================");
        for (Transaction t : userTransactions) {
            MediaItem item = SearchAlgorithms.searchById(mediaItems, t.getMediaId());
            if (item != null) {
                System.out.printf("%s - Due: %s%s%n",
                        item.getTitle(),
                        t.getDueDate(),
                        t.isOverdue() ? " (OVERDUE)" : "");
            }
        }
        System.out.println("=================================================\n");
    }

    // ==================== SEARCH & SORT OPERATIONS ====================

    /**
     * Search media by title
     */
    public void searchByTitle(String title) {
        List<MediaItem> results = SearchAlgorithms.linearSearchByTitle(mediaItems, title);
        SearchAlgorithms.displaySearchResults(results, title);
    }

    /**
     * Search books by author
     */
    public void searchByAuthor(String author) {
        List<MediaItem> results = SearchAlgorithms.linearSearchByAuthor(mediaItems, author);
        SearchAlgorithms.displaySearchResults(results, author);
    }

    /**
     * Sort media by title (using Quick Sort)
     */
    public void sortMediaByTitle() {
        List<MediaItem> sortedList = new ArrayList<>(mediaItems);
        SortAlgorithms.quickSortByTitle(sortedList);
        SortAlgorithms.displaySortedList(sortedList, "Title (Quick Sort)");
    }

    /**
     * Sort media by availability
     */
    public void sortMediaByAvailability() {
        List<MediaItem> sortedList = new ArrayList<>(mediaItems);
        SortAlgorithms.sortByAvailability(sortedList);
        SortAlgorithms.displaySortedList(sortedList, "Availability");
    }

    // ==================== REPORTS ====================

    /**
     * View overdue items
     */
    public void viewOverdueItems() {
        List<Transaction> overdueTransactions = new ArrayList<>();
        for (Transaction t : transactions) {
            if (t.isOverdue()) {
                overdueTransactions.add(t);
            }
        }

        if (overdueTransactions.isEmpty()) {
            System.out.println("No overdue items.");
            return;
        }

        System.out.println("\n=================================================");
        System.out.println("Overdue Items");
        System.out.println("=================================================");
        for (Transaction t : overdueTransactions) {
            MediaItem item = SearchAlgorithms.searchById(mediaItems, t.getMediaId());
            User user = findUserById(t.getUserId());
            if (item != null && user != null) {
                System.out.printf("%s - Borrowed by: %s - Days late: %d%n",
                        item.getTitle(),
                        user.getName(),
                        t.calculateDaysLate());
            }
        }
        System.out.println("=================================================\n");
    }

    /**
     * Get system statistics
     */
    public void displayStatistics() {
        int totalMedia = mediaItems.size();
        int availableMedia = SearchAlgorithms.filterAvailable(mediaItems).size();
        int borrowedMedia = SearchAlgorithms.filterBorrowed(mediaItems).size();
        int totalMembers = getAllMembers().size();
        int activeTransactions = 0;
        for (Transaction t : transactions) {
            if (!t.isCompleted()) activeTransactions++;
        }

        System.out.println("\n=================================================");
        System.out.println("Library Statistics");
        System.out.println("=================================================");
        System.out.println("Total Media Items: " + totalMedia);
        System.out.println("Available: " + availableMedia);
        System.out.println("Borrowed: " + borrowedMedia);
        System.out.println("Total Members: " + totalMembers);
        System.out.println("Active Borrowings: " + activeTransactions);
        System.out.println("Total Transactions: " + transactions.size());
        System.out.println("=================================================\n");
    }

    // Getters
    public List<MediaItem> getMediaItems() {
        return mediaItems;
    }

    public List<User> getUsers() {
        return users;
    }
}
