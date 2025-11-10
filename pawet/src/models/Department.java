package models;

/**
 * Department class representing organizational divisions within PAWET-B
 */
public class Department {
    private String id;
    private String name;
    private String region;
    private double budget;
    private String managerId;
    private int employeeCount;

    /**
     * Constructor for Department
     */
    public Department(String id, String name, String region, double budget,
                     String managerId, int employeeCount) {
        this.id = id;
        this.name = name;
        this.region = region;
        this.budget = budget;
        this.managerId = managerId;
        this.employeeCount = employeeCount;
    }

    /**
     * Display department information
     */
    public void displayInfo() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Department ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Region: " + region);
        System.out.println("Budget: $" + String.format("%,.2f", budget));
        System.out.println("Manager ID: " + (managerId != null ? managerId : "Not assigned"));
        System.out.println("Employee Count: " + employeeCount);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Check if budget is available
     */
    public boolean hasBudgetAvailable(double amount) {
        return amount <= budget;
    }

    /**
     * Allocate budget
     */
    public boolean allocateBudget(double amount) {
        if (hasBudgetAvailable(amount)) {
            budget -= amount;
            return true;
        }
        return false;
    }

    /**
     * Add budget
     */
    public void addBudget(double amount) {
        if (amount > 0) {
            budget += amount;
        }
    }

    /**
     * Increment employee count
     */
    public void incrementEmployeeCount() {
        employeeCount++;
    }

    /**
     * Decrement employee count
     */
    public void decrementEmployeeCount() {
        if (employeeCount > 0) {
            employeeCount--;
        }
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        if (budget >= 0) {
            this.budget = budget;
        }
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public int getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(int employeeCount) {
        if (employeeCount >= 0) {
            this.employeeCount = employeeCount;
        }
    }

    /**
     * String representation for database storage
     */
    @Override
    public String toString() {
        return String.format("%s|%s|%s|%.2f|%s|%d",
                id, name, region, budget, managerId, employeeCount);
    }
}
