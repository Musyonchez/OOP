package utils;

import models.MediaItem;
import java.util.List;

/**
 * SortAlgorithms class implementing Bubble Sort and Quick Sort
 * Demonstrates algorithm implementation for extra points
 */
public class SortAlgorithms {

    /**
     * Bubble Sort - sorts media items by title (ascending)
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     * Best for: Small datasets, educational purposes, nearly sorted data
     *
     * Algorithm:
     * - Compare adjacent elements
     * - Swap if in wrong order
     * - Repeat until no swaps needed
     *
     * @param mediaList List to be sorted
     */
    public static void bubbleSortByTitle(List<MediaItem> mediaList) {
        int n = mediaList.size();

        // Outer loop for each pass
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            // Inner loop for comparisons in this pass
            for (int j = 0; j < n - i - 1; j++) {
                // Compare adjacent elements
                if (mediaList.get(j).getTitle().compareTo(mediaList.get(j + 1).getTitle()) > 0) {
                    // Swap if in wrong order
                    MediaItem temp = mediaList.get(j);
                    mediaList.set(j, mediaList.get(j + 1));
                    mediaList.set(j + 1, temp);
                    swapped = true;
                }
            }

            // If no swaps occurred, list is sorted
            if (!swapped) {
                break;
            }
        }
    }

    /**
     * Quick Sort - sorts media items by title (ascending)
     * Time Complexity: O(n log n) average, O(n²) worst case
     * Space Complexity: O(log n)
     * Best for: Large datasets, general purpose sorting
     *
     * Algorithm:
     * - Choose pivot element
     * - Partition array around pivot
     * - Recursively sort sub-arrays
     *
     * @param mediaList List to be sorted
     */
    public static void quickSortByTitle(List<MediaItem> mediaList) {
        if (mediaList == null || mediaList.isEmpty()) {
            return;
        }
        quickSortHelper(mediaList, 0, mediaList.size() - 1);
    }

    /**
     * Helper method for Quick Sort recursion
     *
     * @param list List being sorted
     * @param low Starting index
     * @param high Ending index
     */
    private static void quickSortHelper(List<MediaItem> list, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int pivotIndex = partition(list, low, high);

            // Recursively sort elements before and after partition
            quickSortHelper(list, low, pivotIndex - 1);
            quickSortHelper(list, pivotIndex + 1, high);
        }
    }

    /**
     * Partition method for Quick Sort
     * Places pivot in correct position and arranges smaller elements to left,
     * larger elements to right
     *
     * @param list List being partitioned
     * @param low Starting index
     * @param high Ending index (pivot)
     * @return Final position of pivot
     */
    private static int partition(List<MediaItem> list, int low, int high) {
        // Choose rightmost element as pivot
        MediaItem pivot = list.get(high);
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than pivot
            if (list.get(j).getTitle().compareTo(pivot.getTitle()) <= 0) {
                i++;
                // Swap elements
                MediaItem temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Swap pivot with element at i+1
        MediaItem temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    /**
     * Sort by ID - uses bubble sort
     * Useful for organizing by registration order
     *
     * @param mediaList List to be sorted
     */
    public static void sortById(List<MediaItem> mediaList) {
        int n = mediaList.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (mediaList.get(j).getId().compareTo(mediaList.get(j + 1).getId()) > 0) {
                    MediaItem temp = mediaList.get(j);
                    mediaList.set(j, mediaList.get(j + 1));
                    mediaList.set(j + 1, temp);
                }
            }
        }
    }

    /**
     * Sort by availability - available items first
     * Uses selection sort
     *
     * @param mediaList List to be sorted
     */
    public static void sortByAvailability(List<MediaItem> mediaList) {
        int n = mediaList.size();

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < n; j++) {
                // Available items (true) come before borrowed items (false)
                if (mediaList.get(j).isAvailable() && !mediaList.get(minIndex).isAvailable()) {
                    minIndex = j;
                }
            }

            // Swap if needed
            if (minIndex != i) {
                MediaItem temp = mediaList.get(i);
                mediaList.set(i, mediaList.get(minIndex));
                mediaList.set(minIndex, temp);
            }
        }
    }

    /**
     * Display sorted list
     *
     * @param mediaList Sorted list
     * @param sortType Type of sort performed
     */
    public static void displaySortedList(List<MediaItem> mediaList, String sortType) {
        System.out.println("\n=================================================");
        System.out.println("Media Items Sorted by: " + sortType);
        System.out.println("=================================================");

        if (mediaList.isEmpty()) {
            System.out.println("No items to display.");
        } else {
            for (int i = 0; i < mediaList.size(); i++) {
                MediaItem item = mediaList.get(i);
                System.out.printf("%d. [%s] %s - %s (%s)%n",
                        i + 1,
                        item.getId(),
                        item.getTitle(),
                        item.getMediaType(),
                        item.isAvailable() ? "Available" : "Borrowed");
            }
        }
        System.out.println("=================================================\n");
    }
}
