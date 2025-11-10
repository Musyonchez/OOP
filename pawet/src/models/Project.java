package models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Project class representing research and conservation projects within PAWET-B
 */
public class Project {
    private String id;
    private String name;
    private String description;
    private String departmentId;
    private String leadScientistId;
    private double budget;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;  // ACTIVE, COMPLETED, SUSPENDED

    /**
     * Constructor for Project
     */
    public Project(String id, String name, String description, String departmentId,
                  String leadScientistId, double budget, LocalDate startDate,
                  LocalDate endDate, String status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.departmentId = departmentId;
        this.leadScientistId = leadScientistId;
        this.budget = budget;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    /**
     * Display project information
     */
    public void displayInfo() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Project ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Lead Scientist: " + (leadScientistId != null ? leadScientistId : "Not assigned"));
        System.out.println("Budget: $" + String.format("%,.2f", budget));
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Duration: " + getDurationMonths() + " months");
        System.out.println("Status: " + status);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Check if project is currently active
     */
    public boolean isActive() {
        return "ACTIVE".equalsIgnoreCase(status);
    }

    /**
     * Check if project is completed
     */
    public boolean isCompleted() {
        return "COMPLETED".equalsIgnoreCase(status);
    }

    /**
     * Get project duration in months
     */
    public long getDurationMonths() {
        return ChronoUnit.MONTHS.between(startDate, endDate);
    }

    /**
     * Get remaining time in days
     */
    public long getRemainingDays() {
        if (isCompleted()) {
            return 0;
        }
        long days = ChronoUnit.DAYS.between(LocalDate.now(), endDate);
        return Math.max(0, days);
    }

    /**
     * Check if project is overdue
     */
    public boolean isOverdue() {
        return isActive() && LocalDate.now().isAfter(endDate);
    }

    /**
     * Complete project
     */
    public void complete() {
        this.status = "COMPLETED";
    }

    /**
     * Suspend project
     */
    public void suspend() {
        this.status = "SUSPENDED";
    }

    /**
     * Reactivate project
     */
    public void reactivate() {
        this.status = "ACTIVE";
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getLeadScientistId() {
        return leadScientistId;
    }

    public void setLeadScientistId(String leadScientistId) {
        this.leadScientistId = leadScientistId;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        if (budget >= 0) {
            this.budget = budget;
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * String representation for database storage
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%s|%s|%.2f|%s|%s|%s",
                id, name, description, departmentId, leadScientistId,
                budget, startDate, endDate, status);
    }
}
