package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Transaction class to track borrowing and returning of media items
 * Encapsulates transaction data and provides methods for calculations
 */
public class Transaction {
    private String transactionId;
    private String userId;
    private String mediaId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double lateFee;

    // Constants
    public static final int LOAN_PERIOD_DAYS = 14;

    /**
     * Constructor for new transaction (borrowing)
     */
    public Transaction(String transactionId, String userId, String mediaId) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.mediaId = mediaId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(LOAN_PERIOD_DAYS);
        this.returnDate = null;
        this.lateFee = 0.0;
    }

    /**
     * Constructor for loading from file
     */
    public Transaction(String transactionId, String userId, String mediaId,
                       LocalDate borrowDate, LocalDate dueDate, LocalDate returnDate, double lateFee) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.mediaId = mediaId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.lateFee = lateFee;
    }

    // Getters and setters (Encapsulation)
    public String getTransactionId() {
        return transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getLateFee() {
        return lateFee;
    }

    public void setLateFee(double lateFee) {
        this.lateFee = lateFee;
    }

    /**
     * Calculate days late based on return date or current date
     */
    public int calculateDaysLate() {
        LocalDate compareDate = (returnDate != null) ? returnDate : LocalDate.now();

        if (compareDate.isAfter(dueDate)) {
            return (int) ChronoUnit.DAYS.between(dueDate, compareDate);
        }
        return 0;
    }

    /**
     * Check if transaction is overdue
     */
    public boolean isOverdue() {
        return returnDate == null && LocalDate.now().isAfter(dueDate);
    }

    /**
     * Check if transaction is completed (returned)
     */
    public boolean isCompleted() {
        return returnDate != null;
    }

    /**
     * Display transaction details
     */
    public void displayTransaction() {
        System.out.println("\n--- Transaction Details ---");
        System.out.println("Transaction ID: " + transactionId);
        System.out.println("User ID: " + userId);
        System.out.println("Media ID: " + mediaId);
        System.out.println("Borrow Date: " + borrowDate);
        System.out.println("Due Date: " + dueDate);
        System.out.println("Return Date: " + (returnDate != null ? returnDate : "Not returned"));
        System.out.println("Status: " + (isCompleted() ? "Completed" : (isOverdue() ? "Overdue" : "Active")));
        System.out.println("Late Fee: $" + String.format("%.2f", lateFee));
        if (!isCompleted()) {
            int daysLate = calculateDaysLate();
            if (daysLate > 0) {
                System.out.println("Days Late: " + daysLate);
            }
        }
        System.out.println("-------------------------");
    }

    /**
     * Convert transaction to string for file storage
     */
    @Override
    public String toString() {
        return transactionId + "|" + userId + "|" + mediaId + "|" +
               borrowDate + "|" + dueDate + "|" +
               (returnDate != null ? returnDate : "") + "|" + lateFee;
    }
}
