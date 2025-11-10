package utils;

import models.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SearchAlgorithms class implementing various search algorithms
 * Demonstrates algorithm complexity analysis and search strategies
 */
public class SearchAlgorithms {

    // ==================== LINEAR SEARCH ====================

    /**
     * Linear Search by ID
     * Time Complexity: O(n) - checks each element sequentially
     * Space Complexity: O(1) - no additional space needed
     * Best for: Small datasets, unsorted data
     *
     * @param employees List of employees to search
     * @param targetId ID to search for
     * @return Employee if found, null otherwise
     */
    public static Employee linearSearchById(List<Employee> employees, String targetId) {
        int comparisons = 0;

        for (Employee employee : employees) {
            comparisons++;
            if (employee.getId().equalsIgnoreCase(targetId)) {
                System.out.println("→ Linear search completed in " + comparisons + " comparison(s)");
                return employee;
            }
        }

        System.out.println("→ Linear search completed in " + comparisons + " comparison(s) - Not found");
        return null;
    }

    /**
     * Linear Search by Name (partial matching)
     * Time Complexity: O(n)
     * Supports case-insensitive substring matching
     *
     * @param employees List of employees to search
     * @param searchTerm Name or partial name to search for
     * @return List of matching employees
     */
    public static List<Employee> linearSearchByName(List<Employee> employees, String searchTerm) {
        List<Employee> results = new ArrayList<>();
        int comparisons = 0;
        String lowerSearchTerm = searchTerm.toLowerCase();

        for (Employee employee : employees) {
            comparisons++;
            if (employee.getName().toLowerCase().contains(lowerSearchTerm)) {
                results.add(employee);
            }
        }

        System.out.println("→ Linear search completed in " + comparisons + " comparison(s)");
        System.out.println("→ Found " + results.size() + " match(es)");
        return results;
    }

    /**
     * Search by Department ID
     * Time Complexity: O(n)
     *
     * @param employees List of employees to search
     * @param departmentId Department ID to filter by
     * @return List of employees in that department
     */
    public static List<Employee> searchByDepartment(List<Employee> employees, String departmentId) {
        List<Employee> results = new ArrayList<>();

        for (Employee employee : employees) {
            if (departmentId.equalsIgnoreCase(employee.getDepartmentId())) {
                results.add(employee);
            }
        }

        System.out.println("→ Found " + results.size() + " employee(s) in department " + departmentId);
        return results;
    }

    /**
     * Search by Salary Range
     * Time Complexity: O(n)
     *
     * @param employees List of employees to search
     * @param minSalary Minimum salary (inclusive)
     * @param maxSalary Maximum salary (inclusive)
     * @return List of employees within salary range
     */
    public static List<Employee> searchBySalaryRange(List<Employee> employees, double minSalary, double maxSalary) {
        List<Employee> results = new ArrayList<>();

        for (Employee employee : employees) {
            double salary = employee.getSalary();
            if (salary >= minSalary && salary <= maxSalary) {
                results.add(employee);
            }
        }

        System.out.println("→ Found " + results.size() + " employee(s) earning between $" +
                String.format("%,.2f", minSalary) + " and $" + String.format("%,.2f", maxSalary));
        return results;
    }

    /**
     * Search by Role Type (Manager, Scientist, Technician)
     * Time Complexity: O(n)
     *
     * @param employees List of employees to search
     * @param roleType Role type to filter by
     * @return List of employees with that role type
     */
    public static List<Employee> searchByRoleType(List<Employee> employees, String roleType) {
        List<Employee> results = new ArrayList<>();

        for (Employee employee : employees) {
            if (roleType.equalsIgnoreCase(employee.getRoleType())) {
                results.add(employee);
            }
        }

        System.out.println("→ Found " + results.size() + " " + roleType + "(s)");
        return results;
    }

    // ==================== BINARY SEARCH ====================

    /**
     * Binary Search by ID (requires sorted list)
     * Time Complexity: O(log n) - divides search space in half each iteration
     * Space Complexity: O(1) - iterative implementation
     * Best for: Large sorted datasets, exact matching
     *
     * Comparison with Linear Search:
     * - 10 items: Linear = 10, Binary = 4 (2.5x faster)
     * - 100 items: Linear = 100, Binary = 7 (14x faster)
     * - 1000 items: Linear = 1000, Binary = 10 (100x faster)
     * - 1,000,000 items: Linear = 1,000,000, Binary = 20 (50,000x faster)
     *
     * @param sortedEmployees List of employees sorted by ID
     * @param targetId ID to search for
     * @return Employee if found, null otherwise
     */
    public static Employee binarySearchById(List<Employee> sortedEmployees, String targetId) {
        int left = 0;
        int right = sortedEmployees.size() - 1;
        int comparisons = 0;

        while (left <= right) {
            comparisons++;
            int mid = left + (right - left) / 2;
            Employee midEmployee = sortedEmployees.get(mid);
            int comparison = targetId.compareToIgnoreCase(midEmployee.getId());

            if (comparison == 0) {
                // Found the employee
                System.out.println("→ Binary search completed in " + comparisons + " comparison(s)");
                System.out.println("→ Efficiency gain: Would take up to " + sortedEmployees.size() +
                        " comparisons with linear search");
                return midEmployee;
            } else if (comparison < 0) {
                // Target is in left half
                right = mid - 1;
            } else {
                // Target is in right half
                left = mid + 1;
            }
        }

        System.out.println("→ Binary search completed in " + comparisons + " comparison(s) - Not found");
        return null;
    }

    /**
     * Verify if list is sorted (helper for binary search)
     */
    public static boolean isSorted(List<Employee> employees) {
        for (int i = 0; i < employees.size() - 1; i++) {
            if (employees.get(i).getId().compareToIgnoreCase(employees.get(i + 1).getId()) > 0) {
                return false;
            }
        }
        return true;
    }

    // ==================== DISPLAY HELPERS ====================

    /**
     * Display search results
     */
    public static void displaySearchResults(List<Employee> results, String searchTerm) {
        if (results.isEmpty()) {
            System.out.println("\n❌ No employees found matching: " + searchTerm);
            return;
        }

        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("SEARCH RESULTS: " + results.size() + " employee(s) found");
        System.out.println("═══════════════════════════════════════════");

        for (Employee employee : results) {
            System.out.println("\n" + employee.getId() + " - " + employee.getName() +
                    " (" + employee.getRoleType() + ")");
            System.out.println("  Department: " + employee.getDepartmentId() +
                    " | Salary: $" + String.format("%,.2f", employee.getSalary()));
        }
        System.out.println("\n═══════════════════════════════════════════");
    }

    /**
     * Display single employee result
     */
    public static void displaySingleResult(Employee employee, String searchTerm) {
        if (employee == null) {
            System.out.println("\n❌ Employee not found: " + searchTerm);
            return;
        }

        System.out.println("\n✓ Employee found!");
        employee.displayInfo();
    }

    // ==================== DEPARTMENT SEARCH ====================

    /**
     * Linear search for department by ID
     */
    public static Department searchDepartmentById(List<Department> departments, String departmentId) {
        for (Department dept : departments) {
            if (dept.getId().equalsIgnoreCase(departmentId)) {
                return dept;
            }
        }
        return null;
    }

    /**
     * Search departments by region
     */
    public static List<Department> searchDepartmentsByRegion(List<Department> departments, String region) {
        List<Department> results = new ArrayList<>();

        for (Department dept : departments) {
            if (region.equalsIgnoreCase(dept.getRegion())) {
                results.add(dept);
            }
        }

        return results;
    }

    // ==================== PROJECT SEARCH ====================

    /**
     * Search projects by status
     */
    public static List<Project> searchProjectsByStatus(List<Project> projects, String status) {
        List<Project> results = new ArrayList<>();

        for (Project project : projects) {
            if (status.equalsIgnoreCase(project.getStatus())) {
                results.add(project);
            }
        }

        System.out.println("→ Found " + results.size() + " " + status + " project(s)");
        return results;
    }

    /**
     * Search projects by department
     */
    public static List<Project> searchProjectsByDepartment(List<Project> projects, String departmentId) {
        List<Project> results = new ArrayList<>();

        for (Project project : projects) {
            if (departmentId.equalsIgnoreCase(project.getDepartmentId())) {
                results.add(project);
            }
        }

        return results;
    }

    // ==================== EQUIPMENT SEARCH ====================

    /**
     * Search equipment by status
     */
    public static List<Equipment> searchEquipmentByStatus(List<Equipment> equipmentList, String status) {
        List<Equipment> results = new ArrayList<>();

        for (Equipment equipment : equipmentList) {
            if (status.equalsIgnoreCase(equipment.getStatus())) {
                results.add(equipment);
            }
        }

        System.out.println("→ Found " + results.size() + " " + status + " equipment item(s)");
        return results;
    }

    /**
     * Search equipment by type
     */
    public static List<Equipment> searchEquipmentByType(List<Equipment> equipmentList, String type) {
        List<Equipment> results = new ArrayList<>();

        for (Equipment equipment : equipmentList) {
            if (equipment.getType().toLowerCase().contains(type.toLowerCase())) {
                results.add(equipment);
            }
        }

        return results;
    }
}
