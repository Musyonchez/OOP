package models;

import java.time.LocalDate;

/**
 * Abstract base class representing all PAWET-B employees
 * Demonstrates Abstraction and Encapsulation principles
 */
public abstract class Employee {
    // Private fields (Encapsulation)
    private String id;
    private String name;
    private String email;
    private String phone;
    private String departmentId;
    private String role;
    private double salary;
    private LocalDate hireDate;

    /**
     * Constructor for Employee
     */
    public Employee(String id, String name, String email, String phone,
                   String departmentId, String role, double salary, LocalDate hireDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.departmentId = departmentId;
        this.role = role;
        this.salary = salary;
        this.hireDate = hireDate;
    }

    /**
     * Abstract method - must be implemented by subclasses (Polymorphism)
     * Displays employee information based on their specific type
     */
    public abstract void displayInfo();

    /**
     * Get the specific role type (Manager, Scientist, Technician)
     */
    public abstract String getRoleType();

    // Getters and Setters (Controlled access - Encapsulation)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary >= 0) {
            this.salary = salary;
        }
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    /**
     * Calculate years of service
     */
    public int getYearsOfService() {
        return LocalDate.now().getYear() - hireDate.getYear();
    }

    /**
     * String representation for database storage
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%s|%.2f|%s",
                id, name, email, phone, departmentId, role, salary, hireDate);
    }

    /**
     * Display basic employee information
     */
    public void displayBasicInfo() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Employee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phone);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Role: " + role);
        System.out.println("Salary: $" + String.format("%,.2f", salary));
        System.out.println("Hire Date: " + hireDate);
        System.out.println("Years of Service: " + getYearsOfService());
    }
}
