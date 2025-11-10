package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Manager class representing management-level employees
 * Demonstrates Inheritance and Polymorphism
 */
public class Manager extends Employee {
    // Additional fields specific to Managers
    private List<String> teamList;        // IDs of employees managed
    private double budgetAuthority;       // Maximum budget they can approve

    /**
     * Constructor for Manager
     */
    public Manager(String id, String name, String email, String phone,
                   String departmentId, String role, double salary, LocalDate hireDate,
                   double budgetAuthority) {
        super(id, name, email, phone, departmentId, role, salary, hireDate);
        this.teamList = new ArrayList<>();
        this.budgetAuthority = budgetAuthority;
    }

    /**
     * Constructor with team list
     */
    public Manager(String id, String name, String email, String phone,
                   String departmentId, String role, double salary, LocalDate hireDate,
                   List<String> teamList, double budgetAuthority) {
        super(id, name, email, phone, departmentId, role, salary, hireDate);
        this.teamList = teamList != null ? new ArrayList<>(teamList) : new ArrayList<>();
        this.budgetAuthority = budgetAuthority;
    }

    /**
     * Override abstract method (Polymorphism)
     * Displays Manager-specific information
     */
    @Override
    public void displayInfo() {
        displayBasicInfo();
        System.out.println("Role Type: MANAGER");
        System.out.println("Budget Authority: $" + String.format("%,.2f", budgetAuthority));
        System.out.println("Team Size: " + teamList.size() + " employees");
        if (!teamList.isEmpty()) {
            System.out.println("Team Members: " + String.join(", ", teamList));
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Override getRoleType
     */
    @Override
    public String getRoleType() {
        return "MANAGER";
    }

    /**
     * Add team member
     */
    public void addTeamMember(String employeeId) {
        if (!teamList.contains(employeeId)) {
            teamList.add(employeeId);
        }
    }

    /**
     * Remove team member
     */
    public void removeTeamMember(String employeeId) {
        teamList.remove(employeeId);
    }

    /**
     * Get team size
     */
    public int getTeamSize() {
        return teamList.size();
    }

    /**
     * Check if can approve budget
     */
    public boolean canApproveBudget(double amount) {
        return amount <= budgetAuthority;
    }

    // Getters and Setters
    public List<String> getTeamList() {
        return new ArrayList<>(teamList);
    }

    public void setTeamList(List<String> teamList) {
        this.teamList = teamList != null ? new ArrayList<>(teamList) : new ArrayList<>();
    }

    public double getBudgetAuthority() {
        return budgetAuthority;
    }

    public void setBudgetAuthority(double budgetAuthority) {
        if (budgetAuthority >= 0) {
            this.budgetAuthority = budgetAuthority;
        }
    }

    /**
     * Convert team list to string for database storage
     */
    public String getTeamListAsString() {
        return String.join(",", teamList);
    }

    /**
     * Parse team list from string
     */
    public static List<String> parseTeamList(String teamListString) {
        List<String> list = new ArrayList<>();
        if (teamListString != null && !teamListString.isEmpty()) {
            String[] ids = teamListString.split(",");
            for (String id : ids) {
                if (!id.trim().isEmpty()) {
                    list.add(id.trim());
                }
            }
        }
        return list;
    }
}
