import managers.Organization;
import models.*;
import utils.SearchAlgorithms;
import utils.SortAlgorithms;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

/**
 * Main class - Entry point for PAWET-B Organizational Design System
 * Provides interactive console menu for all system operations
 */
public class Main {
    private static Organization organization;
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        organization = new Organization();

        boolean running = true;

        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    employeeManagementMenu();
                    break;
                case 2:
                    departmentManagementMenu();
                    break;
                case 3:
                    projectManagementMenu();
                    break;
                case 4:
                    equipmentManagementMenu();
                    break;
                case 5:
                    searchOperationsMenu();
                    break;
                case 6:
                    sortOperationsMenu();
                    break;
                case 7:
                    reportsMenu();
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("\n❌ Invalid choice. Please try again.");
            }
        }

        // Cleanup
        organization.closeDatabase();
        scanner.close();
        System.out.println("\n✓ System shutdown complete. Thank you for using PAWET-B!\n");
    }

    // ==================== MAIN MENU ====================

    private static void displayMainMenu() {
        System.out.println("\n════════════════════════════════════════════════════════");
        System.out.println("                    MAIN MENU");
        System.out.println("════════════════════════════════════════════════════════");
        System.out.println("1. Employee Management");
        System.out.println("2. Department Management");
        System.out.println("3. Project Management");
        System.out.println("4. Equipment Management");
        System.out.println("5. Search Operations");
        System.out.println("6. Sort Operations");
        System.out.println("7. Reports & Statistics");
        System.out.println("0. Exit System");
        System.out.println("════════════════════════════════════════════════════════");
    }

    // ==================== EMPLOYEE MANAGEMENT ====================

    private static void employeeManagementMenu() {
        while (true) {
            System.out.println("\n──────────────────────────────────────────");
            System.out.println("       EMPLOYEE MANAGEMENT");
            System.out.println("──────────────────────────────────────────");
            System.out.println("1. Add Manager");
            System.out.println("2. Add Scientist");
            System.out.println("3. Add Technician");
            System.out.println("4. Remove Employee");
            System.out.println("5. View Employee Details");
            System.out.println("6. View All Employees");
            System.out.println("0. Back to Main Menu");
            System.out.println("──────────────────────────────────────────");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addManager();
                    break;
                case 2:
                    addScientist();
                    break;
                case 3:
                    addTechnician();
                    break;
                case 4:
                    removeEmployee();
                    break;
                case 5:
                    viewEmployeeDetails();
                    break;
                case 6:
                    organization.displayAllEmployees();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        }
    }

    private static void addManager() {
        System.out.println("\n--- Add New Manager ---");

        String id = organization.generateNextEmployeeId("MANAGER");
        System.out.println("Generated ID: " + id);

        String name = getStringInput("Name: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone: ");

        // Show departments
        System.out.println("\nAvailable Departments:");
        for (Department dept : organization.getAllDepartments()) {
            System.out.println("  " + dept.getId() + " - " + dept.getName());
        }
        String departmentId = getStringInput("Department ID: ");

        String role = getStringInput("Role/Position: ");
        double salary = getDoubleInput("Salary: $");
        LocalDate hireDate = getDateInput("Hire Date (YYYY-MM-DD): ");
        double budgetAuthority = getDoubleInput("Budget Authority: $");

        organization.addManager(id, name, email, phone, departmentId, role,
                salary, hireDate, budgetAuthority);
    }

    private static void addScientist() {
        System.out.println("\n--- Add New Scientist ---");

        String id = organization.generateNextEmployeeId("SCIENTIST");
        System.out.println("Generated ID: " + id);

        String name = getStringInput("Name: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone: ");

        System.out.println("\nAvailable Departments:");
        for (Department dept : organization.getAllDepartments()) {
            System.out.println("  " + dept.getId() + " - " + dept.getName());
        }
        String departmentId = getStringInput("Department ID: ");

        String role = getStringInput("Role/Position: ");
        double salary = getDoubleInput("Salary: $");
        LocalDate hireDate = getDateInput("Hire Date (YYYY-MM-DD): ");
        String specialization = getStringInput("Specialization: ");

        organization.addScientist(id, name, email, phone, departmentId, role,
                salary, hireDate, specialization);
    }

    private static void addTechnician() {
        System.out.println("\n--- Add New Technician ---");

        String id = organization.generateNextEmployeeId("TECHNICIAN");
        System.out.println("Generated ID: " + id);

        String name = getStringInput("Name: ");
        String email = getStringInput("Email: ");
        String phone = getStringInput("Phone: ");

        System.out.println("\nAvailable Departments:");
        for (Department dept : organization.getAllDepartments()) {
            System.out.println("  " + dept.getId() + " - " + dept.getName());
        }
        String departmentId = getStringInput("Department ID: ");

        String role = getStringInput("Role/Position: ");
        double salary = getDoubleInput("Salary: $");
        LocalDate hireDate = getDateInput("Hire Date (YYYY-MM-DD): ");
        String certifications = getStringInput("Certifications (comma-separated): ");

        organization.addTechnician(id, name, email, phone, departmentId, role,
                salary, hireDate, certifications);
    }

    private static void removeEmployee() {
        System.out.println("\n--- Remove Employee ---");
        String employeeId = getStringInput("Enter Employee ID to remove: ");
        organization.removeEmployee(employeeId);
    }

    private static void viewEmployeeDetails() {
        System.out.println("\n--- View Employee Details ---");
        String employeeId = getStringInput("Enter Employee ID: ");

        Employee employee = organization.searchEmployeeById(employeeId);
        SearchAlgorithms.displaySingleResult(employee, employeeId);
    }

    // ==================== DEPARTMENT MANAGEMENT ====================

    private static void departmentManagementMenu() {
        while (true) {
            System.out.println("\n──────────────────────────────────────────");
            System.out.println("       DEPARTMENT MANAGEMENT");
            System.out.println("──────────────────────────────────────────");
            System.out.println("1. Add Department");
            System.out.println("2. View Department Details");
            System.out.println("3. View All Departments");
            System.out.println("4. View Department Employees");
            System.out.println("0. Back to Main Menu");
            System.out.println("──────────────────────────────────────────");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addDepartment();
                    break;
                case 2:
                    viewDepartmentDetails();
                    break;
                case 3:
                    organization.displayAllDepartments();
                    break;
                case 4:
                    viewDepartmentEmployees();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        }
    }

    private static void addDepartment() {
        System.out.println("\n--- Add New Department ---");

        String id = getStringInput("Department ID (e.g., D001): ");
        String name = getStringInput("Department Name: ");
        String region = getStringInput("Region: ");
        double budget = getDoubleInput("Budget: $");
        String managerId = getStringInput("Manager ID (or press Enter for none): ");
        if (managerId.isEmpty()) managerId = null;

        organization.addDepartment(id, name, region, budget, managerId);
    }

    private static void viewDepartmentDetails() {
        System.out.println("\n--- View Department Details ---");
        String departmentId = getStringInput("Enter Department ID: ");

        Department dept = organization.searchDepartmentById(departmentId);
        if (dept != null) {
            dept.displayInfo();
        } else {
            System.out.println("❌ Department not found");
        }
    }

    private static void viewDepartmentEmployees() {
        System.out.println("\n--- View Department Employees ---");
        String departmentId = getStringInput("Enter Department ID: ");

        List<Employee> employees = organization.searchEmployeesByDepartment(departmentId);
        SearchAlgorithms.displaySearchResults(employees, "Department " + departmentId);
    }

    // ==================== PROJECT MANAGEMENT ====================

    private static void projectManagementMenu() {
        while (true) {
            System.out.println("\n──────────────────────────────────────────");
            System.out.println("       PROJECT MANAGEMENT");
            System.out.println("──────────────────────────────────────────");
            System.out.println("1. Add Project");
            System.out.println("2. View All Projects");
            System.out.println("3. View Active Projects");
            System.out.println("0. Back to Main Menu");
            System.out.println("──────────────────────────────────────────");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addProject();
                    break;
                case 2:
                    organization.displayAllProjects();
                    break;
                case 3:
                    viewActiveProjects();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        }
    }

    private static void addProject() {
        System.out.println("\n--- Add New Project ---");

        String id = getStringInput("Project ID (e.g., P001): ");
        String name = getStringInput("Project Name: ");
        String description = getStringInput("Description: ");

        System.out.println("\nAvailable Departments:");
        for (Department dept : organization.getAllDepartments()) {
            System.out.println("  " + dept.getId() + " - " + dept.getName());
        }
        String departmentId = getStringInput("Department ID: ");

        System.out.println("\nAvailable Scientists:");
        List<Employee> scientists = organization.searchEmployeesByRoleType("SCIENTIST");
        for (Employee emp : scientists) {
            System.out.println("  " + emp.getId() + " - " + emp.getName());
        }
        String leadScientistId = getStringInput("Lead Scientist ID: ");

        double budget = getDoubleInput("Budget: $");
        LocalDate startDate = getDateInput("Start Date (YYYY-MM-DD): ");
        LocalDate endDate = getDateInput("End Date (YYYY-MM-DD): ");

        organization.addProject(id, name, description, departmentId, leadScientistId,
                budget, startDate, endDate);
    }

    private static void viewActiveProjects() {
        System.out.println("\n--- Active Projects ---");
        List<Project> allProjects = organization.getAllProjects();
        int activeCount = 0;

        for (Project project : allProjects) {
            if (project.isActive()) {
                System.out.printf("\n%-8s | %-30s | $%,10.2f\n",
                        project.getId(), project.getName(), project.getBudget());
                activeCount++;
            }
        }

        if (activeCount == 0) {
            System.out.println("\n❌ No active projects found");
        } else {
            System.out.println("\nTotal active projects: " + activeCount);
        }
    }

    // ==================== EQUIPMENT MANAGEMENT ====================

    private static void equipmentManagementMenu() {
        while (true) {
            System.out.println("\n──────────────────────────────────────────");
            System.out.println("       EQUIPMENT MANAGEMENT");
            System.out.println("──────────────────────────────────────────");
            System.out.println("1. Add Equipment");
            System.out.println("2. Assign Equipment to Technician");
            System.out.println("3. View All Equipment");
            System.out.println("4. View Available Equipment");
            System.out.println("0. Back to Main Menu");
            System.out.println("──────────────────────────────────────────");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    addEquipment();
                    break;
                case 2:
                    assignEquipment();
                    break;
                case 3:
                    organization.displayAllEquipment();
                    break;
                case 4:
                    viewAvailableEquipment();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        }
    }

    private static void addEquipment() {
        System.out.println("\n--- Add New Equipment ---");

        String id = getStringInput("Equipment ID (e.g., E001): ");
        String name = getStringInput("Equipment Name: ");
        String type = getStringInput("Type: ");
        String serialNumber = getStringInput("Serial Number: ");

        System.out.println("\nAvailable Departments:");
        for (Department dept : organization.getAllDepartments()) {
            System.out.println("  " + dept.getId() + " - " + dept.getName());
        }
        String departmentId = getStringInput("Department ID: ");

        LocalDate purchaseDate = getDateInput("Purchase Date (YYYY-MM-DD): ");

        organization.addEquipment(id, name, type, serialNumber, departmentId, purchaseDate);
    }

    private static void assignEquipment() {
        System.out.println("\n--- Assign Equipment ---");

        String equipmentId = getStringInput("Equipment ID: ");

        System.out.println("\nAvailable Technicians:");
        List<Employee> technicians = organization.searchEmployeesByRoleType("TECHNICIAN");
        for (Employee emp : technicians) {
            System.out.println("  " + emp.getId() + " - " + emp.getName());
        }
        String technicianId = getStringInput("Technician ID: ");

        organization.assignEquipment(equipmentId, technicianId);
    }

    private static void viewAvailableEquipment() {
        System.out.println("\n--- Available Equipment ---");
        List<Equipment> allEquipment = organization.getAllEquipment();
        int availableCount = 0;

        for (Equipment eq : allEquipment) {
            if (eq.isAvailable()) {
                System.out.printf("%-8s | %-25s | %-15s\n",
                        eq.getId(), eq.getName(), eq.getType());
                availableCount++;
            }
        }

        if (availableCount == 0) {
            System.out.println("\n❌ No available equipment");
        } else {
            System.out.println("\nTotal available: " + availableCount);
        }
    }

    // ==================== SEARCH OPERATIONS ====================

    private static void searchOperationsMenu() {
        while (true) {
            System.out.println("\n──────────────────────────────────────────");
            System.out.println("       SEARCH OPERATIONS");
            System.out.println("──────────────────────────────────────────");
            System.out.println("1. Search Employee by ID (Linear)");
            System.out.println("2. Search Employee by ID (Binary)");
            System.out.println("3. Search Employee by Name");
            System.out.println("4. Search by Department");
            System.out.println("5. Search by Role Type");
            System.out.println("6. Search by Salary Range");
            System.out.println("0. Back to Main Menu");
            System.out.println("──────────────────────────────────────────");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    searchByIdLinear();
                    break;
                case 2:
                    searchByIdBinary();
                    break;
                case 3:
                    searchByName();
                    break;
                case 4:
                    searchByDepartment();
                    break;
                case 5:
                    searchByRoleType();
                    break;
                case 6:
                    searchBySalaryRange();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        }
    }

    private static void searchByIdLinear() {
        System.out.println("\n--- Linear Search by ID ---");
        String employeeId = getStringInput("Enter Employee ID: ");
        Employee employee = organization.searchEmployeeById(employeeId);
        SearchAlgorithms.displaySingleResult(employee, employeeId);
    }

    private static void searchByIdBinary() {
        System.out.println("\n--- Binary Search by ID ---");
        System.out.println("(Note: This requires the list to be sorted first)");
        String employeeId = getStringInput("Enter Employee ID: ");
        Employee employee = organization.binarySearchEmployeeById(employeeId);
        SearchAlgorithms.displaySingleResult(employee, employeeId);
    }

    private static void searchByName() {
        System.out.println("\n--- Search by Name ---");
        String name = getStringInput("Enter name (or partial name): ");
        List<Employee> results = organization.searchEmployeesByName(name);
        SearchAlgorithms.displaySearchResults(results, name);
    }

    private static void searchByDepartment() {
        System.out.println("\n--- Search by Department ---");
        String departmentId = getStringInput("Enter Department ID: ");
        List<Employee> results = organization.searchEmployeesByDepartment(departmentId);
        SearchAlgorithms.displaySearchResults(results, "Department " + departmentId);
    }

    private static void searchByRoleType() {
        System.out.println("\n--- Search by Role Type ---");
        System.out.println("Available role types: MANAGER, SCIENTIST, TECHNICIAN");
        String roleType = getStringInput("Enter Role Type: ");
        List<Employee> results = organization.searchEmployeesByRoleType(roleType);
        SearchAlgorithms.displaySearchResults(results, roleType);
    }

    private static void searchBySalaryRange() {
        System.out.println("\n--- Search by Salary Range ---");
        double minSalary = getDoubleInput("Minimum Salary: $");
        double maxSalary = getDoubleInput("Maximum Salary: $");
        List<Employee> results = organization.searchEmployeesBySalaryRange(minSalary, maxSalary);
        SearchAlgorithms.displaySearchResults(results, "Salary range");
    }

    // ==================== SORT OPERATIONS ====================

    private static void sortOperationsMenu() {
        while (true) {
            System.out.println("\n──────────────────────────────────────────");
            System.out.println("       SORT OPERATIONS");
            System.out.println("──────────────────────────────────────────");
            System.out.println("1. Sort by Name (Bubble Sort)");
            System.out.println("2. Sort by Name (Quick Sort)");
            System.out.println("3. Sort by Salary (Ascending)");
            System.out.println("4. Sort by Salary (Descending)");
            System.out.println("5. Compare Algorithm Performance");
            System.out.println("0. Back to Main Menu");
            System.out.println("──────────────────────────────────────────");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    sortByNameBubble();
                    break;
                case 2:
                    sortByNameQuick();
                    break;
                case 3:
                    sortBySalary(true);
                    break;
                case 4:
                    sortBySalary(false);
                    break;
                case 5:
                    compareAlgorithms();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        }
    }

    private static void sortByNameBubble() {
        System.out.println("\n--- Bubble Sort by Name ---");
        List<Employee> sorted = organization.sortEmployeesByNameBubble();
        SortAlgorithms.displaySortedList(sorted, "Name (Bubble Sort)");
    }

    private static void sortByNameQuick() {
        System.out.println("\n--- Quick Sort by Name ---");
        List<Employee> sorted = organization.sortEmployeesByNameQuick();
        SortAlgorithms.displaySortedList(sorted, "Name (Quick Sort)");
    }

    private static void sortBySalary(boolean ascending) {
        System.out.println("\n--- Sort by Salary ---");
        List<Employee> sorted = organization.sortEmployeesBySalary(ascending);
        SortAlgorithms.displaySortedList(sorted,
                "Salary (" + (ascending ? "Ascending" : "Descending") + ")");
    }

    private static void compareAlgorithms() {
        System.out.println("\n--- Algorithm Performance Comparison ---");
        SortAlgorithms.compareAlgorithms(organization.getAllEmployees());
    }

    // ==================== REPORTS ====================

    private static void reportsMenu() {
        while (true) {
            System.out.println("\n──────────────────────────────────────────");
            System.out.println("       REPORTS & STATISTICS");
            System.out.println("──────────────────────────────────────────");
            System.out.println("1. System Statistics");
            System.out.println("2. Employee Summary");
            System.out.println("3. Department Summary");
            System.out.println("4. Project Summary");
            System.out.println("0. Back to Main Menu");
            System.out.println("──────────────────────────────────────────");

            int choice = getIntInput("Enter your choice: ");

            switch (choice) {
                case 1:
                    organization.displaySystemStatistics();
                    break;
                case 2:
                    organization.displayAllEmployees();
                    break;
                case 3:
                    organization.displayAllDepartments();
                    break;
                case 4:
                    organization.displayAllProjects();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("\n❌ Invalid choice.");
            }
        }
    }

    // ==================== INPUT HELPERS ====================

    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = Double.parseDouble(scanner.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
            }
        }
    }

    private static LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String dateStr = scanner.nextLine().trim();
                return LocalDate.parse(dateStr);
            } catch (DateTimeParseException e) {
                System.out.println("❌ Invalid date format. Please use YYYY-MM-DD.");
            }
        }
    }
}
