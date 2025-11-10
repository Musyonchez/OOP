package utils;

import models.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SortAlgorithms class implementing various sorting algorithms
 * Demonstrates algorithm complexity analysis and sorting strategies
 */
public class SortAlgorithms {

    // ==================== BUBBLE SORT ====================

    /**
     * Bubble Sort by Name
     * Time Complexity: O(n²) - nested loops
     * Space Complexity: O(1) - sorts in place
     * Best Case: O(n) - when already sorted (with optimization)
     * Worst Case: O(n²) - when reverse sorted
     * Best for: Small datasets, educational purposes, nearly sorted data
     *
     * Algorithm: Repeatedly steps through list, compares adjacent elements and swaps
     * them if they're in wrong order. Pass through list is repeated until sorted.
     *
     * @param employees List of employees to sort
     * @return Sorted list (ascending by name)
     */
    public static List<Employee> bubbleSortByName(List<Employee> employees) {
        List<Employee> sortedList = new ArrayList<>(employees);
        int n = sortedList.size();
        int comparisons = 0;
        int swaps = 0;

        System.out.println("→ Starting Bubble Sort on " + n + " employees...");

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                comparisons++;
                Employee emp1 = sortedList.get(j);
                Employee emp2 = sortedList.get(j + 1);

                // Compare names (case-insensitive)
                if (emp1.getName().compareToIgnoreCase(emp2.getName()) > 0) {
                    // Swap
                    sortedList.set(j, emp2);
                    sortedList.set(j + 1, emp1);
                    swaps++;
                    swapped = true;
                }
            }

            // If no swaps occurred, list is already sorted
            if (!swapped) {
                System.out.println("→ Early termination at pass " + (i + 1) + " (already sorted)");
                break;
            }
        }

        System.out.println("→ Bubble Sort completed:");
        System.out.println("   Comparisons: " + comparisons);
        System.out.println("   Swaps: " + swaps);
        System.out.println("   Theoretical worst case: " + (n * (n - 1) / 2) + " comparisons");

        return sortedList;
    }

    /**
     * Bubble Sort by ID
     * Time Complexity: O(n²)
     *
     * @param employees List of employees to sort
     * @return Sorted list (ascending by ID)
     */
    public static List<Employee> bubbleSortById(List<Employee> employees) {
        List<Employee> sortedList = new ArrayList<>(employees);
        int n = sortedList.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                Employee emp1 = sortedList.get(j);
                Employee emp2 = sortedList.get(j + 1);

                if (emp1.getId().compareToIgnoreCase(emp2.getId()) > 0) {
                    sortedList.set(j, emp2);
                    sortedList.set(j + 1, emp1);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("→ Sorted " + n + " employees by ID");
        return sortedList;
    }

    /**
     * Bubble Sort by Salary
     * Time Complexity: O(n²)
     *
     * @param employees List of employees to sort
     * @param ascending true for ascending, false for descending
     * @return Sorted list
     */
    public static List<Employee> bubbleSortBySalary(List<Employee> employees, boolean ascending) {
        List<Employee> sortedList = new ArrayList<>(employees);
        int n = sortedList.size();

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < n - i - 1; j++) {
                Employee emp1 = sortedList.get(j);
                Employee emp2 = sortedList.get(j + 1);

                boolean shouldSwap = ascending ?
                        emp1.getSalary() > emp2.getSalary() :
                        emp1.getSalary() < emp2.getSalary();

                if (shouldSwap) {
                    sortedList.set(j, emp2);
                    sortedList.set(j + 1, emp1);
                    swapped = true;
                }
            }

            if (!swapped) break;
        }

        System.out.println("→ Sorted " + n + " employees by salary (" +
                (ascending ? "ascending" : "descending") + ")");
        return sortedList;
    }

    // ==================== QUICK SORT ====================

    /**
     * Quick Sort by Name
     * Time Complexity: O(n log n) average, O(n²) worst case
     * Space Complexity: O(log n) - due to recursion stack
     * Best for: Large datasets, general-purpose sorting
     *
     * Algorithm: Divide-and-conquer approach. Picks a pivot element and partitions
     * array around pivot, recursively sorts subarrays.
     *
     * Performance Comparison with Bubble Sort (n = 1000):
     * - Bubble Sort: ~500,000 comparisons
     * - Quick Sort: ~10,000 comparisons (50x faster)
     *
     * @param employees List of employees to sort
     * @return Sorted list (ascending by name)
     */
    public static List<Employee> quickSortByName(List<Employee> employees) {
        List<Employee> sortedList = new ArrayList<>(employees);
        System.out.println("→ Starting Quick Sort on " + sortedList.size() + " employees...");

        long startTime = System.nanoTime();
        quickSortByNameHelper(sortedList, 0, sortedList.size() - 1);
        long endTime = System.nanoTime();

        double duration = (endTime - startTime) / 1_000_000.0; // Convert to milliseconds
        System.out.println("→ Quick Sort completed in " + String.format("%.2f", duration) + " ms");

        return sortedList;
    }

    /**
     * Quick Sort recursive helper
     */
    private static void quickSortByNameHelper(List<Employee> list, int low, int high) {
        if (low < high) {
            // Partition the array and get pivot index
            int pivotIndex = partitionByName(list, low, high);

            // Recursively sort elements before and after partition
            quickSortByNameHelper(list, low, pivotIndex - 1);
            quickSortByNameHelper(list, pivotIndex + 1, high);
        }
    }

    /**
     * Partition function for Quick Sort
     * Places pivot in correct position and rearranges smaller/larger elements
     */
    private static int partitionByName(List<Employee> list, int low, int high) {
        // Choose rightmost element as pivot
        Employee pivot = list.get(high);
        int i = low - 1; // Index of smaller element

        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (list.get(j).getName().compareToIgnoreCase(pivot.getName()) <= 0) {
                i++;
                // Swap list[i] and list[j]
                Employee temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        // Swap list[i+1] and list[high] (pivot)
        Employee temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    /**
     * Quick Sort by ID
     * Time Complexity: O(n log n) average
     *
     * @param employees List of employees to sort
     * @return Sorted list (ascending by ID)
     */
    public static List<Employee> quickSortById(List<Employee> employees) {
        List<Employee> sortedList = new ArrayList<>(employees);
        quickSortByIdHelper(sortedList, 0, sortedList.size() - 1);
        System.out.println("→ Quick sorted " + sortedList.size() + " employees by ID");
        return sortedList;
    }

    /**
     * Quick Sort by ID recursive helper
     */
    private static void quickSortByIdHelper(List<Employee> list, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionById(list, low, high);
            quickSortByIdHelper(list, low, pivotIndex - 1);
            quickSortByIdHelper(list, pivotIndex + 1, high);
        }
    }

    /**
     * Partition function for Quick Sort by ID
     */
    private static int partitionById(List<Employee> list, int low, int high) {
        Employee pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (list.get(j).getId().compareToIgnoreCase(pivot.getId()) <= 0) {
                i++;
                Employee temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        Employee temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    /**
     * Quick Sort by Salary
     * Time Complexity: O(n log n) average
     *
     * @param employees List of employees to sort
     * @param ascending true for ascending, false for descending
     * @return Sorted list
     */
    public static List<Employee> quickSortBySalary(List<Employee> employees, boolean ascending) {
        List<Employee> sortedList = new ArrayList<>(employees);
        quickSortBySalaryHelper(sortedList, 0, sortedList.size() - 1, ascending);
        System.out.println("→ Quick sorted " + sortedList.size() + " employees by salary");
        return sortedList;
    }

    /**
     * Quick Sort by Salary recursive helper
     */
    private static void quickSortBySalaryHelper(List<Employee> list, int low, int high, boolean ascending) {
        if (low < high) {
            int pivotIndex = partitionBySalary(list, low, high, ascending);
            quickSortBySalaryHelper(list, low, pivotIndex - 1, ascending);
            quickSortBySalaryHelper(list, pivotIndex + 1, high, ascending);
        }
    }

    /**
     * Partition function for Quick Sort by Salary
     */
    private static int partitionBySalary(List<Employee> list, int low, int high, boolean ascending) {
        Employee pivot = list.get(high);
        int i = low - 1;

        for (int j = low; j < high; j++) {
            boolean condition = ascending ?
                    list.get(j).getSalary() <= pivot.getSalary() :
                    list.get(j).getSalary() >= pivot.getSalary();

            if (condition) {
                i++;
                Employee temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }

        Employee temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);

        return i + 1;
    }

    // ==================== DISPLAY HELPERS ====================

    /**
     * Display sorted list
     */
    public static void displaySortedList(List<Employee> employees, String sortedBy) {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("SORTED EMPLOYEES (" + sortedBy + ")");
        System.out.println("Total: " + employees.size() + " employees");
        System.out.println("═══════════════════════════════════════════\n");

        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            System.out.printf("%2d. %-15s | %-25s | %-12s | $%,10.2f\n",
                    (i + 1),
                    emp.getId(),
                    emp.getName(),
                    emp.getRoleType(),
                    emp.getSalary());
        }

        System.out.println("\n═══════════════════════════════════════════");
    }

    /**
     * Compare sorting algorithms performance
     */
    public static void compareAlgorithms(List<Employee> employees) {
        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("SORTING ALGORITHM PERFORMANCE COMPARISON");
        System.out.println("Dataset size: " + employees.size() + " employees");
        System.out.println("═══════════════════════════════════════════\n");

        // Bubble Sort
        long startBubble = System.nanoTime();
        bubbleSortByName(new ArrayList<>(employees));
        long endBubble = System.nanoTime();
        double bubbleTime = (endBubble - startBubble) / 1_000_000.0;

        System.out.println();

        // Quick Sort
        long startQuick = System.nanoTime();
        quickSortByName(new ArrayList<>(employees));
        long endQuick = System.nanoTime();
        double quickTime = (endQuick - startQuick) / 1_000_000.0;

        System.out.println("\n───────────────────────────────────────────");
        System.out.println("Results:");
        System.out.printf("  Bubble Sort: %.2f ms\n", bubbleTime);
        System.out.printf("  Quick Sort:  %.2f ms\n", quickTime);
        System.out.printf("  Speed Improvement: %.1fx faster\n", bubbleTime / quickTime);
        System.out.println("═══════════════════════════════════════════\n");
    }

    // ==================== DEPARTMENT SORTING ====================

    /**
     * Sort departments by budget (descending)
     */
    public static List<Department> sortDepartmentsByBudget(List<Department> departments) {
        List<Department> sorted = new ArrayList<>(departments);
        sorted.sort((d1, d2) -> Double.compare(d2.getBudget(), d1.getBudget()));
        return sorted;
    }

    /**
     * Sort departments by employee count
     */
    public static List<Department> sortDepartmentsByEmployeeCount(List<Department> departments) {
        List<Department> sorted = new ArrayList<>(departments);
        sorted.sort((d1, d2) -> Integer.compare(d2.getEmployeeCount(), d1.getEmployeeCount()));
        return sorted;
    }
}
