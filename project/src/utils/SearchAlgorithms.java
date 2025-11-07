package utils;

import models.MediaItem;
import models.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchAlgorithms class implementing Linear and Binary search
 * Demonstrates algorithm implementation for extra points
 */
public class SearchAlgorithms {

    /**
     * Linear Search - searches for media by title (case-insensitive)
     * Time Complexity: O(n)
     * Best for: Unsorted data, small datasets, substring matching
     *
     * @param mediaList List of media items to search
     * @param searchTerm Title or partial title to search for
     * @return List of matching media items
     */
    public static List<MediaItem> linearSearchByTitle(List<MediaItem> mediaList, String searchTerm) {
        List<MediaItem> results = new ArrayList<>();
        searchTerm = searchTerm.toLowerCase();

        // Iterate through each item in the list
        for (MediaItem item : mediaList) {
            // Check if title contains search term (case-insensitive)
            if (item.getTitle().toLowerCase().contains(searchTerm)) {
                results.add(item);
            }
        }

        return results;
    }

    /**
     * Linear Search - searches for media by author (for books)
     * Time Complexity: O(n)
     *
     * @param mediaList List of media items to search
     * @param author Author name to search for
     * @return List of matching books
     */
    public static List<MediaItem> linearSearchByAuthor(List<MediaItem> mediaList, String author) {
        List<MediaItem> results = new ArrayList<>();
        author = author.toLowerCase();

        for (MediaItem item : mediaList) {
            // Only search books for author
            if (item instanceof Book) {
                Book book = (Book) item;
                if (book.getAuthor().toLowerCase().contains(author)) {
                    results.add(book);
                }
            }
        }

        return results;
    }

    /**
     * Binary Search - searches for book by ISBN (requires sorted list)
     * Time Complexity: O(log n)
     * Best for: Sorted data, exact matches, large datasets
     *
     * @param bookList Sorted list of books (by ISBN)
     * @param isbn ISBN to search for
     * @return Book if found, null otherwise
     */
    public static Book binarySearchByISBN(List<Book> bookList, String isbn) {
        // Ensure list is sorted first
        bookList.sort((b1, b2) -> b1.getIsbn().compareTo(b2.getIsbn()));

        int left = 0;
        int right = bookList.size() - 1;

        // Binary search algorithm
        while (left <= right) {
            int mid = left + (right - left) / 2;
            Book midBook = bookList.get(mid);

            int comparison = midBook.getIsbn().compareTo(isbn);

            if (comparison == 0) {
                // Found exact match
                return midBook;
            } else if (comparison < 0) {
                // Search in right half
                left = mid + 1;
            } else {
                // Search in left half
                right = mid - 1;
            }
        }

        // Not found
        return null;
    }

    /**
     * Search by ID - finds any media item by exact ID match
     * Time Complexity: O(n)
     *
     * @param mediaList List of media items
     * @param id Media ID to search for
     * @return MediaItem if found, null otherwise
     */
    public static MediaItem searchById(List<MediaItem> mediaList, String id) {
        for (MediaItem item : mediaList) {
            if (item.getId().equals(id)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Filter available media items
     * Time Complexity: O(n)
     *
     * @param mediaList List of media items
     * @return List of available items only
     */
    public static List<MediaItem> filterAvailable(List<MediaItem> mediaList) {
        List<MediaItem> available = new ArrayList<>();

        for (MediaItem item : mediaList) {
            if (item.isAvailable()) {
                available.add(item);
            }
        }

        return available;
    }

    /**
     * Filter borrowed media items
     * Time Complexity: O(n)
     *
     * @param mediaList List of media items
     * @return List of borrowed items only
     */
    public static List<MediaItem> filterBorrowed(List<MediaItem> mediaList) {
        List<MediaItem> borrowed = new ArrayList<>();

        for (MediaItem item : mediaList) {
            if (!item.isAvailable()) {
                borrowed.add(item);
            }
        }

        return borrowed;
    }

    /**
     * Display search results
     *
     * @param results List of search results
     * @param searchTerm What was searched for
     */
    public static void displaySearchResults(List<MediaItem> results, String searchTerm) {
        System.out.println("\n=================================================");
        System.out.println("Search Results for: \"" + searchTerm + "\"");
        System.out.println("=================================================");

        if (results.isEmpty()) {
            System.out.println("No matches found.");
        } else {
            System.out.println("Found " + results.size() + " result(s):\n");
            for (int i = 0; i < results.size(); i++) {
                System.out.println((i + 1) + ". " + results.get(i).getTitle() +
                                 " [" + results.get(i).getMediaType() + "] - " +
                                 (results.get(i).isAvailable() ? "Available" : "Borrowed"));
            }
        }
        System.out.println("=================================================\n");
    }
}
