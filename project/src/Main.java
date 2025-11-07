import managers.Library;
import models.*;
import java.util.Scanner;

/**
 * Main class - Entry point for Library Management System
 * Provides interactive console menu for all operations
 */
public class Main {
    private static Library library;
    private static Scanner scanner;
    private static User currentUser;

    public static void main(String[] args) {
        library = new Library();
        scanner = new Scanner(System.in);

        System.out.println("\n╔═══════════════════════════════════════════════════╗");
        System.out.println("║    LIBRARY MANAGEMENT SYSTEM v1.0                 ║");
        System.out.println("║    Demonstrating OOP Concepts & Algorithms        ║");
        System.out.println("╚═══════════════════════════════════════════════════╝\n");

        mainMenu();

        scanner.close();
    }

    /**
     * Main menu - entry point for system
     */
    private static void mainMenu() {
        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════╗");
            System.out.println("║                  MAIN MENU                        ║");
            System.out.println("╠═══════════════════════════════════════════════════╣");
            System.out.println("║  1. User Management                               ║");
            System.out.println("║  2. Media Management (Librarian Only)             ║");
            System.out.println("║  3. Borrowing Operations                          ║");
            System.out.println("║  4. Search & Sort                                 ║");
            System.out.println("║  5. Reports & Statistics                          ║");
            System.out.println("║  6. Exit                                          ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    userManagementMenu();
                    break;
                case 2:
                    mediaManagementMenu();
                    break;
                case 3:
                    borrowingMenu();
                    break;
                case 4:
                    searchSortMenu();
                    break;
                case 5:
                    reportsMenu();
                    break;
                case 6:
                    System.out.println("\nThank you for using Library Management System!");
                    System.out.println("Goodbye!\n");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    /**
     * User Management submenu
     */
    private static void userManagementMenu() {
        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════╗");
            System.out.println("║              USER MANAGEMENT                      ║");
            System.out.println("╠═══════════════════════════════════════════════════╣");
            System.out.println("║  1. Register New Member                           ║");
            System.out.println("║  2. Register New Librarian                        ║");
            System.out.println("║  3. View User Profile                             ║");
            System.out.println("║  4. Login (Set Current User)                      ║");
            System.out.println("║  5. Back to Main Menu                             ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    registerMember();
                    break;
                case 2:
                    registerLibrarian();
                    break;
                case 3:
                    viewUserProfile();
                    break;
                case 4:
                    loginUser();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Media Management submenu
     */
    private static void mediaManagementMenu() {
        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════╗");
            System.out.println("║              MEDIA MANAGEMENT                     ║");
            System.out.println("╠═══════════════════════════════════════════════════╣");
            System.out.println("║  1. Add New Book                                  ║");
            System.out.println("║  2. Add New Magazine                              ║");
            System.out.println("║  3. Add New DVD                                   ║");
            System.out.println("║  4. Remove Media Item                             ║");
            System.out.println("║  5. View All Media                                ║");
            System.out.println("║  6. Back to Main Menu                             ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    addMagazine();
                    break;
                case 3:
                    addDVD();
                    break;
                case 4:
                    removeMedia();
                    break;
                case 5:
                    library.viewAllMedia();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Borrowing Operations submenu
     */
    private static void borrowingMenu() {
        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════╗");
            System.out.println("║           BORROWING OPERATIONS                    ║");
            System.out.println("╠═══════════════════════════════════════════════════╣");
            System.out.println("║  1. Borrow Media Item                             ║");
            System.out.println("║  2. Return Media Item                             ║");
            System.out.println("║  3. View My Borrowed Items                        ║");
            System.out.println("║  4. Back to Main Menu                             ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    borrowMedia();
                    break;
                case 2:
                    returnMedia();
                    break;
                case 3:
                    viewBorrowedItems();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Search & Sort submenu
     */
    private static void searchSortMenu() {
        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════╗");
            System.out.println("║              SEARCH & SORT                        ║");
            System.out.println("╠═══════════════════════════════════════════════════╣");
            System.out.println("║  1. Search by Title (Linear Search)               ║");
            System.out.println("║  2. Search by Author (Linear Search)              ║");
            System.out.println("║  3. Sort by Title (Quick Sort)                    ║");
            System.out.println("║  4. Sort by Availability                          ║");
            System.out.println("║  5. Back to Main Menu                             ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    searchByTitle();
                    break;
                case 2:
                    searchByAuthor();
                    break;
                case 3:
                    library.sortMediaByTitle();
                    break;
                case 4:
                    library.sortMediaByAvailability();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    /**
     * Reports & Statistics submenu
     */
    private static void reportsMenu() {
        while (true) {
            System.out.println("\n╔═══════════════════════════════════════════════════╗");
            System.out.println("║           REPORTS & STATISTICS                    ║");
            System.out.println("╠═══════════════════════════════════════════════════╣");
            System.out.println("║  1. View Overdue Items                            ║");
            System.out.println("║  2. System Statistics                             ║");
            System.out.println("║  3. Back to Main Menu                             ║");
            System.out.println("╚═══════════════════════════════════════════════════╝");
            System.out.print("Enter choice: ");

            int choice = getIntInput();

            switch (choice) {
                case 1:
                    library.viewOverdueItems();
                    break;
                case 2:
                    library.displayStatistics();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    // ==================== USER OPERATIONS ====================

    private static void registerMember() {
        System.out.println("\n--- Register New Member ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        library.registerMember(name, email, phone);
    }

    private static void registerLibrarian() {
        System.out.println("\n--- Register New Librarian ---");
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();
        System.out.print("Enter employee ID: ");
        String employeeId = scanner.nextLine();
        System.out.print("Enter position: ");
        String position = scanner.nextLine();

        library.registerLibrarian(name, email, phone, employeeId, position);
    }

    private static void viewUserProfile() {
        System.out.print("\nEnter user ID: ");
        String userId = scanner.nextLine();

        User user = library.findUserById(userId);
        if (user != null) {
            user.displayInfo();
        } else {
            System.out.println("User not found.");
        }
    }

    private static void loginUser() {
        System.out.print("\nEnter your user ID: ");
        String userId = scanner.nextLine();

        User user = library.findUserById(userId);
        if (user != null) {
            currentUser = user;
            System.out.println("Logged in as: " + user.getName());
        } else {
            System.out.println("User not found.");
        }
    }

    // ==================== MEDIA OPERATIONS ====================

    private static void addBook() {
        System.out.println("\n--- Add New Book ---");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author: ");
        String author = scanner.nextLine();
        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter pages: ");
        int pages = getIntInput();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();

        library.addBook(title, author, isbn, pages, publisher);
    }

    private static void addMagazine() {
        System.out.println("\n--- Add New Magazine ---");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter issue number: ");
        int issueNumber = getIntInput();
        System.out.print("Enter publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter publication date (YYYY-MM-DD): ");
        String publicationDate = scanner.nextLine();

        library.addMagazine(title, issueNumber, publisher, publicationDate);
    }

    private static void addDVD() {
        System.out.println("\n--- Add New DVD ---");
        System.out.print("Enter title: ");
        String title = scanner.nextLine();
        System.out.print("Enter director: ");
        String director = scanner.nextLine();
        System.out.print("Enter duration (minutes): ");
        int duration = getIntInput();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter release year: ");
        String releaseYear = scanner.nextLine();

        library.addDVD(title, director, duration, genre, releaseYear);
    }

    private static void removeMedia() {
        System.out.print("\nEnter media ID to remove: ");
        String mediaId = scanner.nextLine();

        library.removeMedia(mediaId);
    }

    // ==================== BORROWING OPERATIONS ====================

    private static void borrowMedia() {
        System.out.print("\nEnter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter media ID: ");
        String mediaId = scanner.nextLine();

        library.borrowMedia(userId, mediaId);
    }

    private static void returnMedia() {
        System.out.print("\nEnter your user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter media ID: ");
        String mediaId = scanner.nextLine();

        library.returnMedia(userId, mediaId);
    }

    private static void viewBorrowedItems() {
        System.out.print("\nEnter user ID: ");
        String userId = scanner.nextLine();

        library.viewBorrowedItems(userId);
    }

    // ==================== SEARCH OPERATIONS ====================

    private static void searchByTitle() {
        System.out.print("\nEnter title to search: ");
        String title = scanner.nextLine();

        library.searchByTitle(title);
    }

    private static void searchByAuthor() {
        System.out.print("\nEnter author name: ");
        String author = scanner.nextLine();

        library.searchByAuthor(author);
    }

    // ==================== HELPER METHODS ====================

    /**
     * Get integer input with error handling
     */
    private static int getIntInput() {
        while (true) {
            try {
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
}
