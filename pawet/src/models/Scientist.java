package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Scientist class representing research and scientific staff
 * Demonstrates Inheritance and Polymorphism
 */
public class Scientist extends Employee {
    // Additional fields specific to Scientists
    private String specialization;        // Area of expertise
    private List<String> projectList;     // IDs of projects they lead or contribute to

    /**
     * Constructor for Scientist
     */
    public Scientist(String id, String name, String email, String phone,
                    String departmentId, String role, double salary, LocalDate hireDate,
                    String specialization) {
        super(id, name, email, phone, departmentId, role, salary, hireDate);
        this.specialization = specialization;
        this.projectList = new ArrayList<>();
    }

    /**
     * Constructor with project list
     */
    public Scientist(String id, String name, String email, String phone,
                    String departmentId, String role, double salary, LocalDate hireDate,
                    String specialization, List<String> projectList) {
        super(id, name, email, phone, departmentId, role, salary, hireDate);
        this.specialization = specialization;
        this.projectList = projectList != null ? new ArrayList<>(projectList) : new ArrayList<>();
    }

    /**
     * Override abstract method (Polymorphism)
     * Displays Scientist-specific information
     */
    @Override
    public void displayInfo() {
        displayBasicInfo();
        System.out.println("Role Type: SCIENTIST");
        System.out.println("Specialization: " + specialization);
        System.out.println("Active Projects: " + projectList.size());
        if (!projectList.isEmpty()) {
            System.out.println("Projects: " + String.join(", ", projectList));
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Override getRoleType
     */
    @Override
    public String getRoleType() {
        return "SCIENTIST";
    }

    /**
     * Add project to scientist's portfolio
     */
    public void addProject(String projectId) {
        if (!projectList.contains(projectId)) {
            projectList.add(projectId);
        }
    }

    /**
     * Remove project from scientist's portfolio
     */
    public void removeProject(String projectId) {
        projectList.remove(projectId);
    }

    /**
     * Get project count
     */
    public int getProjectCount() {
        return projectList.size();
    }

    /**
     * Check if scientist is overloaded (more than 3 projects)
     */
    public boolean isOverloaded() {
        return projectList.size() > 3;
    }

    // Getters and Setters
    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public List<String> getProjectList() {
        return new ArrayList<>(projectList);
    }

    public void setProjectList(List<String> projectList) {
        this.projectList = projectList != null ? new ArrayList<>(projectList) : new ArrayList<>();
    }

    /**
     * Convert project list to string for database storage
     */
    public String getProjectListAsString() {
        return String.join(",", projectList);
    }

    /**
     * Parse project list from string
     */
    public static List<String> parseProjectList(String projectListString) {
        List<String> list = new ArrayList<>();
        if (projectListString != null && !projectListString.isEmpty()) {
            String[] ids = projectListString.split(",");
            for (String id : ids) {
                if (!id.trim().isEmpty()) {
                    list.add(id.trim());
                }
            }
        }
        return list;
    }
}
