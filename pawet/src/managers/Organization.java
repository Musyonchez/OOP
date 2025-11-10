package managers;

import models.*;
import database.DatabaseManager;
import utils.SearchAlgorithms;
import utils.SortAlgorithms;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Organization class - Central coordinator for PAWET-B system
 * Manages all employees, departments, projects, and equipment
 * Demonstrates Manager/Facade pattern
 */
public class Organization {
    private List<Employee> employees;
    private List<Department> departments;
    private List<Project> projects;
    private List<Equipment> equipmentList;
    private DatabaseManager dbManager;

    /**
     * Constructor - initializes organization and loads data from database
     */
    public Organization() {
        System.out.println("\n════════════════════════════════════════════════════════");
        System.out.println("   PAWET-B ORGANIZATIONAL MANAGEMENT SYSTEM");
        System.out.println("   Pan-African Wildlife, Environment, and Tourism Bureau");
        System.out.println("════════════════════════════════════════════════════════\n");

        // Initialize collections
        employees = new ArrayList<>();
        departments = new ArrayList<>();
        projects = new ArrayList<>();
        equipmentList = new ArrayList<>();

        // Initialize database
        dbManager = new DatabaseManager();

        // Load existing data
        loadAllData();
    }

    /**
     * Load all data from database
     */
    private void loadAllData() {
        System.out.println("Loading data from database...");
        employees = dbManager.loadEmployees();
        departments = dbManager.loadDepartments();
        projects = dbManager.loadProjects();
        equipmentList = dbManager.loadEquipment();

        System.out.println("✓ Loaded " + employees.size() + " employees");
        System.out.println("✓ Loaded " + departments.size() + " departments");
        System.out.println("✓ Loaded " + projects.size() + " projects");
        System.out.println("✓ Loaded " + equipmentList.size() + " equipment items\n");
    }

    // ==================== EMPLOYEE OPERATIONS ====================

    /**
     * Add new Manager
     */
    public void addManager(String id, String name, String email, String phone,
                          String departmentId, String role, double salary,
                          LocalDate hireDate, double budgetAuthority) {
        Manager manager = new Manager(id, name, email, phone, departmentId,
                role, salary, hireDate, budgetAuthority);
        employees.add(manager);
        dbManager.saveEmployee(manager);

        // Update department employee count
        updateDepartmentEmployeeCount(departmentId, 1);

        System.out.println("✓ Manager added successfully: " + name);
    }

    /**
     * Add new Scientist
     */
    public void addScientist(String id, String name, String email, String phone,
                            String departmentId, String role, double salary,
                            LocalDate hireDate, String specialization) {
        Scientist scientist = new Scientist(id, name, email, phone, departmentId,
                role, salary, hireDate, specialization);
        employees.add(scientist);
        dbManager.saveEmployee(scientist);

        updateDepartmentEmployeeCount(departmentId, 1);

        System.out.println("✓ Scientist added successfully: " + name);
    }

    /**
     * Add new Technician
     */
    public void addTechnician(String id, String name, String email, String phone,
                             String departmentId, String role, double salary,
                             LocalDate hireDate, String certifications) {
        Technician technician = new Technician(id, name, email, phone, departmentId,
                role, salary, hireDate, certifications);
        employees.add(technician);
        dbManager.saveEmployee(technician);

        updateDepartmentEmployeeCount(departmentId, 1);

        System.out.println("✓ Technician added successfully: " + name);
    }

    /**
     * Remove employee
     */
    public boolean removeEmployee(String employeeId) {
        Employee employee = searchEmployeeById(employeeId);
        if (employee == null) {
            System.out.println("❌ Employee not found: " + employeeId);
            return false;
        }

        employees.remove(employee);
        dbManager.deleteEmployee(employeeId);
        updateDepartmentEmployeeCount(employee.getDepartmentId(), -1);

        System.out.println("✓ Employee removed: " + employee.getName());
        return true;
    }

    /**
     * Search employee by ID (uses linear search)
     */
    public Employee searchEmployeeById(String employeeId) {
        return SearchAlgorithms.linearSearchById(employees, employeeId);
    }

    /**
     * Search employee by ID using binary search (requires sorted list)
     */
    public Employee binarySearchEmployeeById(String employeeId) {
        List<Employee> sortedEmployees = SortAlgorithms.quickSortById(employees);
        return SearchAlgorithms.binarySearchById(sortedEmployees, employeeId);
    }

    /**
     * Search employees by name
     */
    public List<Employee> searchEmployeesByName(String name) {
        return SearchAlgorithms.linearSearchByName(employees, name);
    }

    /**
     * Search employees by department
     */
    public List<Employee> searchEmployeesByDepartment(String departmentId) {
        return SearchAlgorithms.searchByDepartment(employees, departmentId);
    }

    /**
     * Search employees by role type
     */
    public List<Employee> searchEmployeesByRoleType(String roleType) {
        return SearchAlgorithms.searchByRoleType(employees, roleType);
    }

    /**
     * Search employees by salary range
     */
    public List<Employee> searchEmployeesBySalaryRange(double minSalary, double maxSalary) {
        return SearchAlgorithms.searchBySalaryRange(employees, minSalary, maxSalary);
    }

    /**
     * Sort employees by name (bubble sort)
     */
    public List<Employee> sortEmployeesByNameBubble() {
        return SortAlgorithms.bubbleSortByName(employees);
    }

    /**
     * Sort employees by name (quick sort)
     */
    public List<Employee> sortEmployeesByNameQuick() {
        return SortAlgorithms.quickSortByName(employees);
    }

    /**
     * Sort employees by salary
     */
    public List<Employee> sortEmployeesBySalary(boolean ascending) {
        return SortAlgorithms.quickSortBySalary(employees, ascending);
    }

    /**
     * Display all employees
     */
    public void displayAllEmployees() {
        if (employees.isEmpty()) {
            System.out.println("\n❌ No employees in the system");
            return;
        }

        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("ALL EMPLOYEES (" + employees.size() + " total)");
        System.out.println("═══════════════════════════════════════════\n");

        for (Employee employee : employees) {
            System.out.printf("%-10s | %-25s | %-12s | %-8s | $%,10.2f\n",
                    employee.getId(),
                    employee.getName(),
                    employee.getRoleType(),
                    employee.getDepartmentId(),
                    employee.getSalary());
        }
        System.out.println("\n═══════════════════════════════════════════");
    }

    // ==================== DEPARTMENT OPERATIONS ====================

    /**
     * Add new department
     */
    public void addDepartment(String id, String name, String region,
                             double budget, String managerId) {
        Department department = new Department(id, name, region, budget, managerId, 0);
        departments.add(department);
        dbManager.saveDepartment(department);

        System.out.println("✓ Department added successfully: " + name);
    }

    /**
     * Search department by ID
     */
    public Department searchDepartmentById(String departmentId) {
        return SearchAlgorithms.searchDepartmentById(departments, departmentId);
    }

    /**
     * Update department employee count
     */
    private void updateDepartmentEmployeeCount(String departmentId, int change) {
        Department dept = searchDepartmentById(departmentId);
        if (dept != null) {
            if (change > 0) {
                dept.incrementEmployeeCount();
            } else if (change < 0) {
                dept.decrementEmployeeCount();
            }
            dbManager.saveDepartment(dept);
        }
    }

    /**
     * Display all departments
     */
    public void displayAllDepartments() {
        if (departments.isEmpty()) {
            System.out.println("\n❌ No departments in the system");
            return;
        }

        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("ALL DEPARTMENTS (" + departments.size() + " total)");
        System.out.println("═══════════════════════════════════════════\n");

        for (Department dept : departments) {
            System.out.printf("%-8s | %-30s | %-15s | $%,12.2f | %3d employees\n",
                    dept.getId(),
                    dept.getName(),
                    dept.getRegion(),
                    dept.getBudget(),
                    dept.getEmployeeCount());
        }
        System.out.println("\n═══════════════════════════════════════════");
    }

    // ==================== PROJECT OPERATIONS ====================

    /**
     * Add new project
     */
    public void addProject(String id, String name, String description,
                          String departmentId, String leadScientistId,
                          double budget, LocalDate startDate, LocalDate endDate) {
        Project project = new Project(id, name, description, departmentId,
                leadScientistId, budget, startDate, endDate, "ACTIVE");
        projects.add(project);
        dbManager.saveProject(project);

        // Add project to scientist's project list
        Employee emp = searchEmployeeById(leadScientistId);
        if (emp instanceof Scientist) {
            Scientist scientist = (Scientist) emp;
            scientist.addProject(id);
            dbManager.updateEmployee(scientist);
        }

        System.out.println("✓ Project added successfully: " + name);
    }

    /**
     * Display all projects
     */
    public void displayAllProjects() {
        if (projects.isEmpty()) {
            System.out.println("\n❌ No projects in the system");
            return;
        }

        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("ALL PROJECTS (" + projects.size() + " total)");
        System.out.println("═══════════════════════════════════════════\n");

        for (Project project : projects) {
            System.out.printf("%-8s | %-30s | %-12s | $%,10.2f | %s\n",
                    project.getId(),
                    project.getName(),
                    project.getStatus(),
                    project.getBudget(),
                    project.getDepartmentId());
        }
        System.out.println("\n═══════════════════════════════════════════");
    }

    // ==================== EQUIPMENT OPERATIONS ====================

    /**
     * Add new equipment
     */
    public void addEquipment(String id, String name, String type, String serialNumber,
                            String departmentId, LocalDate purchaseDate) {
        Equipment equipment = new Equipment(id, name, type, serialNumber,
                departmentId, null, purchaseDate, "AVAILABLE");
        equipmentList.add(equipment);
        dbManager.saveEquipment(equipment);

        System.out.println("✓ Equipment added successfully: " + name);
    }

    /**
     * Assign equipment to technician
     */
    public boolean assignEquipment(String equipmentId, String technicianId) {
        // Find equipment
        Equipment equipment = null;
        for (Equipment eq : equipmentList) {
            if (eq.getId().equalsIgnoreCase(equipmentId)) {
                equipment = eq;
                break;
            }
        }

        if (equipment == null) {
            System.out.println("❌ Equipment not found: " + equipmentId);
            return false;
        }

        if (!equipment.isAvailable()) {
            System.out.println("❌ Equipment not available: " + equipment.getName());
            return false;
        }

        // Find technician
        Employee emp = searchEmployeeById(technicianId);
        if (!(emp instanceof Technician)) {
            System.out.println("❌ Employee is not a technician: " + technicianId);
            return false;
        }

        // Assign equipment
        Technician technician = (Technician) emp;
        equipment.assign(technicianId);
        technician.addEquipment(equipmentId);

        dbManager.saveEquipment(equipment);
        dbManager.updateEmployee(technician);

        System.out.println("✓ Equipment assigned: " + equipment.getName() + " → " + technician.getName());
        return true;
    }

    /**
     * Display all equipment
     */
    public void displayAllEquipment() {
        if (equipmentList.isEmpty()) {
            System.out.println("\n❌ No equipment in the system");
            return;
        }

        System.out.println("\n═══════════════════════════════════════════");
        System.out.println("ALL EQUIPMENT (" + equipmentList.size() + " total)");
        System.out.println("═══════════════════════════════════════════\n");

        for (Equipment eq : equipmentList) {
            System.out.printf("%-8s | %-25s | %-15s | %-12s | %s\n",
                    eq.getId(),
                    eq.getName(),
                    eq.getType(),
                    eq.getStatus(),
                    eq.getAssignedToId() != null ? eq.getAssignedToId() : "Not assigned");
        }
        System.out.println("\n═══════════════════════════════════════════");
    }

    // ==================== REPORTING ====================

    /**
     * Display system statistics
     */
    public void displaySystemStatistics() {
        System.out.println("\n════════════════════════════════════════════════════════");
        System.out.println("PAWET-B SYSTEM STATISTICS");
        System.out.println("════════════════════════════════════════════════════════\n");

        // Employee statistics
        int managerCount = 0;
        int scientistCount = 0;
        int technicianCount = 0;
        double totalSalary = 0;

        for (Employee emp : employees) {
            if (emp instanceof Manager) managerCount++;
            else if (emp instanceof Scientist) scientistCount++;
            else if (emp instanceof Technician) technicianCount++;
            totalSalary += emp.getSalary();
        }

        System.out.println("PERSONNEL:");
        System.out.println("  Total Employees: " + employees.size());
        System.out.println("  - Managers: " + managerCount);
        System.out.println("  - Scientists: " + scientistCount);
        System.out.println("  - Technicians: " + technicianCount);
        System.out.println("  Total Payroll: $" + String.format("%,.2f", totalSalary));
        if (employees.size() > 0) {
            System.out.println("  Average Salary: $" + String.format("%,.2f", totalSalary / employees.size()));
        }

        // Department statistics
        System.out.println("\nORGANIZATION:");
        System.out.println("  Total Departments: " + departments.size());

        double totalBudget = 0;
        for (Department dept : departments) {
            totalBudget += dept.getBudget();
        }
        System.out.println("  Total Budget: $" + String.format("%,.2f", totalBudget));

        // Project statistics
        int activeProjects = 0;
        for (Project project : projects) {
            if (project.isActive()) activeProjects++;
        }
        System.out.println("\nPROJECTS:");
        System.out.println("  Total Projects: " + projects.size());
        System.out.println("  Active Projects: " + activeProjects);

        // Equipment statistics
        int availableEquipment = 0;
        for (Equipment eq : equipmentList) {
            if (eq.isAvailable()) availableEquipment++;
        }
        System.out.println("\nEQUIPMENT:");
        System.out.println("  Total Equipment: " + equipmentList.size());
        System.out.println("  Available: " + availableEquipment);
        System.out.println("  In Use: " + (equipmentList.size() - availableEquipment));

        System.out.println("\n════════════════════════════════════════════════════════\n");
    }

    // ==================== UTILITY METHODS ====================

    /**
     * Generate next employee ID
     */
    public String generateNextEmployeeId(String roleType) {
        String prefix = roleType.substring(0, 1); // M, S, or T
        int maxNum = 0;

        for (Employee emp : employees) {
            if (emp.getId().startsWith(prefix)) {
                try {
                    int num = Integer.parseInt(emp.getId().substring(1));
                    if (num > maxNum) maxNum = num;
                } catch (NumberFormatException e) {
                    // Skip invalid IDs
                }
            }
        }

        return prefix + String.format("%03d", maxNum + 1);
    }

    /**
     * Get all employees
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    /**
     * Get all departments
     */
    public List<Department> getAllDepartments() {
        return new ArrayList<>(departments);
    }

    /**
     * Get all projects
     */
    public List<Project> getAllProjects() {
        return new ArrayList<>(projects);
    }

    /**
     * Get all equipment
     */
    public List<Equipment> getAllEquipment() {
        return new ArrayList<>(equipmentList);
    }

    /**
     * Close database connection
     */
    public void closeDatabase() {
        dbManager.close();
    }
}
