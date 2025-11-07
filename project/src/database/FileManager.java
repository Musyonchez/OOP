package database;

import models.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * FileManager class for data persistence
 * Handles reading/writing data to text files (Database Access component)
 * Uses pipe-delimited format for data storage
 */
public class FileManager {
    // File paths
    private static final String DATA_DIR = "project/data/";
    private static final String USERS_FILE = DATA_DIR + "users.txt";
    private static final String MEDIA_FILE = DATA_DIR + "media.txt";
    private static final String TRANSACTIONS_FILE = DATA_DIR + "transactions.txt";

    /**
     * Ensure data directory and files exist
     */
    public static void initializeFiles() {
        try {
            // Create data directory if it doesn't exist
            File dataDir = new File(DATA_DIR);
            if (!dataDir.exists()) {
                dataDir.mkdirs();
            }

            // Create files if they don't exist
            createFileIfNotExists(USERS_FILE);
            createFileIfNotExists(MEDIA_FILE);
            createFileIfNotExists(TRANSACTIONS_FILE);

        } catch (IOException e) {
            System.err.println("Error initializing files: " + e.getMessage());
        }
    }

    /**
     * Create file if it doesn't exist
     */
    private static void createFileIfNotExists(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    // ==================== USER OPERATIONS ====================

    /**
     * Save user to file
     * Format: ID|Name|Email|Phone|Type|Additional fields
     */
    public static void saveUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE, true))) {
            writer.write(user.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving user: " + e.getMessage());
        }
    }

    /**
     * Load all users from file
     */
    public static List<User> loadUsers() {
        List<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                String id = parts[0];
                String name = parts[1];
                String email = parts[2];
                String phone = parts[3];
                String type = parts[4];

                if (type.equals("MEMBER") && parts.length >= 7) {
                    LocalDate membershipDate = LocalDate.parse(parts[5]);
                    double fines = Double.parseDouble(parts[6]);
                    users.add(new Member(id, name, email, phone, membershipDate, fines));
                } else if (type.equals("LIBRARIAN") && parts.length >= 8) {
                    String employeeId = parts[5];
                    String position = parts[6];
                    LocalDate hireDate = LocalDate.parse(parts[7]);
                    users.add(new Librarian(id, name, email, phone, employeeId, position, hireDate));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }

        return users;
    }

    // ==================== MEDIA OPERATIONS ====================

    /**
     * Save media item to file
     * Format: ID|Title|Type|Available|BorrowedBy|Type-specific fields
     */
    public static void saveMedia(MediaItem media) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEDIA_FILE, true))) {
            writer.write(media.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving media: " + e.getMessage());
        }
    }

    /**
     * Load all media items from file
     */
    public static List<MediaItem> loadMedia() {
        List<MediaItem> mediaList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(MEDIA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length < 5) continue;

                String id = parts[0];
                String title = parts[1];
                String type = parts[2];
                boolean available = Boolean.parseBoolean(parts[3]);
                String borrowedBy = parts[4].isEmpty() ? null : parts[4];

                MediaItem item = null;

                if (type.equals("BOOK") && parts.length >= 9) {
                    String author = parts[5];
                    String isbn = parts[6];
                    int pages = Integer.parseInt(parts[7]);
                    String publisher = parts[8];
                    item = new Book(id, title, author, isbn, pages, publisher);
                } else if (type.equals("MAGAZINE") && parts.length >= 8) {
                    int issueNumber = Integer.parseInt(parts[5]);
                    String publisher = parts[6];
                    String publicationDate = parts[7];
                    item = new Magazine(id, title, issueNumber, publisher, publicationDate);
                } else if (type.equals("DVD") && parts.length >= 9) {
                    String director = parts[5];
                    int duration = Integer.parseInt(parts[6]);
                    String genre = parts[7];
                    String releaseYear = parts[8];
                    item = new DVD(id, title, director, duration, genre, releaseYear);
                }

                if (item != null) {
                    item.setAvailable(available);
                    item.setBorrowedBy(borrowedBy);
                    mediaList.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading media: " + e.getMessage());
        }

        return mediaList;
    }

    // ==================== TRANSACTION OPERATIONS ====================

    /**
     * Save transaction to file
     * Format: ID|UserID|MediaID|BorrowDate|DueDate|ReturnDate|LateFee
     */
    public static void saveTransaction(Transaction transaction) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE, true))) {
            writer.write(transaction.toString());
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Error saving transaction: " + e.getMessage());
        }
    }

    /**
     * Load all transactions from file
     */
    public static List<Transaction> loadTransactions() {
        List<Transaction> transactions = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(TRANSACTIONS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] parts = line.split("\\|");
                if (parts.length >= 7) {
                    String transactionId = parts[0];
                    String userId = parts[1];
                    String mediaId = parts[2];
                    LocalDate borrowDate = LocalDate.parse(parts[3]);
                    LocalDate dueDate = LocalDate.parse(parts[4]);
                    LocalDate returnDate = parts[5].isEmpty() ? null : LocalDate.parse(parts[5]);
                    double lateFee = Double.parseDouble(parts[6]);

                    transactions.add(new Transaction(transactionId, userId, mediaId,
                            borrowDate, dueDate, returnDate, lateFee));
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading transactions: " + e.getMessage());
        }

        return transactions;
    }

    // ==================== UPDATE OPERATIONS ====================

    /**
     * Update all users in file
     * Overwrites entire file with current user list
     */
    public static void updateAllUsers(List<User> users) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USERS_FILE))) {
            for (User user : users) {
                writer.write(user.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating users: " + e.getMessage());
        }
    }

    /**
     * Update all media items in file
     */
    public static void updateAllMedia(List<MediaItem> mediaList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MEDIA_FILE))) {
            for (MediaItem item : mediaList) {
                writer.write(item.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating media: " + e.getMessage());
        }
    }

    /**
     * Update all transactions in file
     */
    public static void updateAllTransactions(List<Transaction> transactions) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TRANSACTIONS_FILE))) {
            for (Transaction transaction : transactions) {
                writer.write(transaction.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error updating transactions: " + e.getMessage());
        }
    }

    /**
     * Clear all data (for testing purposes)
     */
    public static void clearAllData() {
        try {
            new FileWriter(USERS_FILE, false).close();
            new FileWriter(MEDIA_FILE, false).close();
            new FileWriter(TRANSACTIONS_FILE, false).close();
            System.out.println("All data cleared successfully.");
        } catch (IOException e) {
            System.err.println("Error clearing data: " + e.getMessage());
        }
    }
}
