package database;

import models.*;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DatabaseManager handles all SQL database operations for PAWET-B
 * Uses SQLite for data persistence
 * Demonstrates database integration and CRUD operations
 */
public class DatabaseManager {
    private static final String DB_URL = "jdbc:sqlite:data/pawet.db";
    private Connection connection;

    /**
     * Constructor - establishes database connection
     */
    public DatabaseManager() {
        try {
            // Load SQLite JDBC driver
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("✓ Database connection established");
            initializeTables();
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: SQLite JDBC driver not found");
            System.err.println("Please ensure sqlite-jdbc jar is in classpath");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to connect to database");
            e.printStackTrace();
        }
    }

    /**
     * Initialize database tables if they don't exist
     */
    private void initializeTables() {
        try {
            Statement stmt = connection.createStatement();

            // Create employees table
            String createEmployees = "CREATE TABLE IF NOT EXISTS employees (" +
                    "id TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT, " +
                    "phone TEXT, " +
                    "department_id TEXT, " +
                    "role TEXT NOT NULL, " +
                    "salary REAL, " +
                    "hire_date TEXT, " +
                    "role_type TEXT NOT NULL, " +
                    "specialization TEXT, " +
                    "team_list TEXT, " +
                    "budget_authority REAL, " +
                    "project_list TEXT, " +
                    "equipment_list TEXT, " +
                    "certifications TEXT, " +
                    "FOREIGN KEY (department_id) REFERENCES departments(id))";
            stmt.execute(createEmployees);

            // Create departments table
            String createDepartments = "CREATE TABLE IF NOT EXISTS departments (" +
                    "id TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "region TEXT, " +
                    "budget REAL, " +
                    "manager_id TEXT, " +
                    "employee_count INTEGER DEFAULT 0, " +
                    "FOREIGN KEY (manager_id) REFERENCES employees(id))";
            stmt.execute(createDepartments);

            // Create projects table
            String createProjects = "CREATE TABLE IF NOT EXISTS projects (" +
                    "id TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "description TEXT, " +
                    "department_id TEXT, " +
                    "lead_scientist_id TEXT, " +
                    "budget REAL, " +
                    "start_date TEXT, " +
                    "end_date TEXT, " +
                    "status TEXT, " +
                    "FOREIGN KEY (department_id) REFERENCES departments(id), " +
                    "FOREIGN KEY (lead_scientist_id) REFERENCES employees(id))";
            stmt.execute(createProjects);

            // Create equipment table
            String createEquipment = "CREATE TABLE IF NOT EXISTS equipment (" +
                    "id TEXT PRIMARY KEY, " +
                    "name TEXT NOT NULL, " +
                    "type TEXT, " +
                    "serial_number TEXT, " +
                    "department_id TEXT, " +
                    "assigned_to_id TEXT, " +
                    "purchase_date TEXT, " +
                    "status TEXT, " +
                    "FOREIGN KEY (department_id) REFERENCES departments(id), " +
                    "FOREIGN KEY (assigned_to_id) REFERENCES employees(id))";
            stmt.execute(createEquipment);

            stmt.close();
            System.out.println("✓ Database tables initialized");
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to initialize tables");
            e.printStackTrace();
        }
    }

    // ==================== EMPLOYEE OPERATIONS ====================

    /**
     * Save employee to database (INSERT)
     */
    public void saveEmployee(Employee employee) {
        String sql = "INSERT INTO employees (id, name, email, phone, department_id, role, " +
                "salary, hire_date, role_type, specialization, team_list, budget_authority, " +
                "project_list, equipment_list, certifications) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, employee.getId());
            pstmt.setString(2, employee.getName());
            pstmt.setString(3, employee.getEmail());
            pstmt.setString(4, employee.getPhone());
            pstmt.setString(5, employee.getDepartmentId());
            pstmt.setString(6, employee.getRole());
            pstmt.setDouble(7, employee.getSalary());
            pstmt.setString(8, employee.getHireDate().toString());
            pstmt.setString(9, employee.getRoleType());

            // Type-specific fields
            if (employee instanceof Scientist) {
                Scientist scientist = (Scientist) employee;
                pstmt.setString(10, scientist.getSpecialization());
                pstmt.setString(11, null);
                pstmt.setDouble(12, 0);
                pstmt.setString(13, scientist.getProjectListAsString());
                pstmt.setString(14, null);
                pstmt.setString(15, null);
            } else if (employee instanceof Manager) {
                Manager manager = (Manager) employee;
                pstmt.setString(10, null);
                pstmt.setString(11, manager.getTeamListAsString());
                pstmt.setDouble(12, manager.getBudgetAuthority());
                pstmt.setString(13, null);
                pstmt.setString(14, null);
                pstmt.setString(15, null);
            } else if (employee instanceof Technician) {
                Technician technician = (Technician) employee;
                pstmt.setString(10, null);
                pstmt.setString(11, null);
                pstmt.setDouble(12, 0);
                pstmt.setString(13, null);
                pstmt.setString(14, technician.getEquipmentListAsString());
                pstmt.setString(15, technician.getCertifications());
            }

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to save employee " + employee.getId());
            e.printStackTrace();
        }
    }

    /**
     * Load all employees from database (SELECT)
     */
    public List<Employee> loadEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String departmentId = rs.getString("department_id");
                String role = rs.getString("role");
                double salary = rs.getDouble("salary");
                LocalDate hireDate = LocalDate.parse(rs.getString("hire_date"));
                String roleType = rs.getString("role_type");

                Employee employee = null;

                if ("MANAGER".equals(roleType)) {
                    String teamList = rs.getString("team_list");
                    double budgetAuthority = rs.getDouble("budget_authority");
                    employee = new Manager(id, name, email, phone, departmentId, role,
                            salary, hireDate, Manager.parseTeamList(teamList), budgetAuthority);
                } else if ("SCIENTIST".equals(roleType)) {
                    String specialization = rs.getString("specialization");
                    String projectList = rs.getString("project_list");
                    employee = new Scientist(id, name, email, phone, departmentId, role,
                            salary, hireDate, specialization, Scientist.parseProjectList(projectList));
                } else if ("TECHNICIAN".equals(roleType)) {
                    String equipmentList = rs.getString("equipment_list");
                    String certifications = rs.getString("certifications");
                    employee = new Technician(id, name, email, phone, departmentId, role,
                            salary, hireDate, Technician.parseEquipmentList(equipmentList), certifications);
                }

                if (employee != null) {
                    employees.add(employee);
                }
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to load employees");
            e.printStackTrace();
        }

        return employees;
    }

    /**
     * Update employee in database (UPDATE)
     */
    public void updateEmployee(Employee employee) {
        deleteEmployee(employee.getId());
        saveEmployee(employee);
    }

    /**
     * Delete employee from database (DELETE)
     */
    public void deleteEmployee(String employeeId) {
        String sql = "DELETE FROM employees WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, employeeId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to delete employee " + employeeId);
            e.printStackTrace();
        }
    }

    // ==================== DEPARTMENT OPERATIONS ====================

    /**
     * Save department to database
     */
    public void saveDepartment(Department department) {
        String sql = "INSERT OR REPLACE INTO departments (id, name, region, budget, manager_id, employee_count) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, department.getId());
            pstmt.setString(2, department.getName());
            pstmt.setString(3, department.getRegion());
            pstmt.setDouble(4, department.getBudget());
            pstmt.setString(5, department.getManagerId());
            pstmt.setInt(6, department.getEmployeeCount());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to save department " + department.getId());
            e.printStackTrace();
        }
    }

    /**
     * Load all departments from database
     */
    public List<Department> loadDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM departments";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Department dept = new Department(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("region"),
                        rs.getDouble("budget"),
                        rs.getString("manager_id"),
                        rs.getInt("employee_count")
                );
                departments.add(dept);
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to load departments");
            e.printStackTrace();
        }

        return departments;
    }

    /**
     * Delete department from database
     */
    public void deleteDepartment(String departmentId) {
        String sql = "DELETE FROM departments WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, departmentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to delete department " + departmentId);
            e.printStackTrace();
        }
    }

    // ==================== PROJECT OPERATIONS ====================

    /**
     * Save project to database
     */
    public void saveProject(Project project) {
        String sql = "INSERT OR REPLACE INTO projects (id, name, description, department_id, " +
                "lead_scientist_id, budget, start_date, end_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, project.getId());
            pstmt.setString(2, project.getName());
            pstmt.setString(3, project.getDescription());
            pstmt.setString(4, project.getDepartmentId());
            pstmt.setString(5, project.getLeadScientistId());
            pstmt.setDouble(6, project.getBudget());
            pstmt.setString(7, project.getStartDate().toString());
            pstmt.setString(8, project.getEndDate().toString());
            pstmt.setString(9, project.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to save project " + project.getId());
            e.printStackTrace();
        }
    }

    /**
     * Load all projects from database
     */
    public List<Project> loadProjects() {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Project project = new Project(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("department_id"),
                        rs.getString("lead_scientist_id"),
                        rs.getDouble("budget"),
                        LocalDate.parse(rs.getString("start_date")),
                        LocalDate.parse(rs.getString("end_date")),
                        rs.getString("status")
                );
                projects.add(project);
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to load projects");
            e.printStackTrace();
        }

        return projects;
    }

    /**
     * Delete project from database
     */
    public void deleteProject(String projectId) {
        String sql = "DELETE FROM projects WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, projectId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to delete project " + projectId);
            e.printStackTrace();
        }
    }

    // ==================== EQUIPMENT OPERATIONS ====================

    /**
     * Save equipment to database
     */
    public void saveEquipment(Equipment equipment) {
        String sql = "INSERT OR REPLACE INTO equipment (id, name, type, serial_number, " +
                "department_id, assigned_to_id, purchase_date, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, equipment.getId());
            pstmt.setString(2, equipment.getName());
            pstmt.setString(3, equipment.getType());
            pstmt.setString(4, equipment.getSerialNumber());
            pstmt.setString(5, equipment.getDepartmentId());
            pstmt.setString(6, equipment.getAssignedToId());
            pstmt.setString(7, equipment.getPurchaseDate().toString());
            pstmt.setString(8, equipment.getStatus());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to save equipment " + equipment.getId());
            e.printStackTrace();
        }
    }

    /**
     * Load all equipment from database
     */
    public List<Equipment> loadEquipment() {
        List<Equipment> equipmentList = new ArrayList<>();
        String sql = "SELECT * FROM equipment";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipment equipment = new Equipment(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("type"),
                        rs.getString("serial_number"),
                        rs.getString("department_id"),
                        rs.getString("assigned_to_id"),
                        LocalDate.parse(rs.getString("purchase_date")),
                        rs.getString("status")
                );
                equipmentList.add(equipment);
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to load equipment");
            e.printStackTrace();
        }

        return equipmentList;
    }

    /**
     * Delete equipment from database
     */
    public void deleteEquipment(String equipmentId) {
        String sql = "DELETE FROM equipment WHERE id = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, equipmentId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to delete equipment " + equipmentId);
            e.printStackTrace();
        }
    }

    /**
     * Close database connection
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Database connection closed");
            }
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to close database connection");
            e.printStackTrace();
        }
    }

    /**
     * Clear all data (for testing)
     */
    public void clearAllData() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("DELETE FROM equipment");
            stmt.execute("DELETE FROM projects");
            stmt.execute("DELETE FROM employees");
            stmt.execute("DELETE FROM departments");
            stmt.close();
            System.out.println("✓ All data cleared");
        } catch (SQLException e) {
            System.err.println("ERROR: Failed to clear data");
            e.printStackTrace();
        }
    }
}
