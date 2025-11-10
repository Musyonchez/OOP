package models;

import java.time.LocalDate;

/**
 * Equipment class representing physical assets and tools used in PAWET-B operations
 */
public class Equipment {
    private String id;
    private String name;
    private String type;
    private String serialNumber;
    private String departmentId;
    private String assignedToId;
    private LocalDate purchaseDate;
    private String status;  // AVAILABLE, IN_USE, MAINTENANCE, RETIRED

    /**
     * Constructor for Equipment
     */
    public Equipment(String id, String name, String type, String serialNumber,
                    String departmentId, String assignedToId, LocalDate purchaseDate,
                    String status) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.serialNumber = serialNumber;
        this.departmentId = departmentId;
        this.assignedToId = assignedToId;
        this.purchaseDate = purchaseDate;
        this.status = status;
    }

    /**
     * Display equipment information
     */
    public void displayInfo() {
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("Equipment ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Serial Number: " + serialNumber);
        System.out.println("Department ID: " + departmentId);
        System.out.println("Assigned To: " + (assignedToId != null ? assignedToId : "Not assigned"));
        System.out.println("Purchase Date: " + purchaseDate);
        System.out.println("Age: " + getAgeYears() + " years");
        System.out.println("Status: " + status);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Check if equipment is available
     */
    public boolean isAvailable() {
        return "AVAILABLE".equalsIgnoreCase(status);
    }

    /**
     * Check if equipment is in use
     */
    public boolean isInUse() {
        return "IN_USE".equalsIgnoreCase(status);
    }

    /**
     * Check if equipment is in maintenance
     */
    public boolean isInMaintenance() {
        return "MAINTENANCE".equalsIgnoreCase(status);
    }

    /**
     * Get equipment age in years
     */
    public int getAgeYears() {
        return LocalDate.now().getYear() - purchaseDate.getYear();
    }

    /**
     * Assign equipment to technician
     */
    public void assign(String technicianId) {
        this.assignedToId = technicianId;
        this.status = "IN_USE";
    }

    /**
     * Unassign equipment
     */
    public void unassign() {
        this.assignedToId = null;
        this.status = "AVAILABLE";
    }

    /**
     * Send equipment to maintenance
     */
    public void sendToMaintenance() {
        this.status = "MAINTENANCE";
    }

    /**
     * Return equipment from maintenance
     */
    public void returnFromMaintenance() {
        this.status = assignedToId != null ? "IN_USE" : "AVAILABLE";
    }

    /**
     * Retire equipment
     */
    public void retire() {
        this.status = "RETIRED";
        this.assignedToId = null;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getAssignedToId() {
        return assignedToId;
    }

    public void setAssignedToId(String assignedToId) {
        this.assignedToId = assignedToId;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
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
        return String.format("%s|%s|%s|%s|%s|%s|%s|%s",
                id, name, type, serialNumber, departmentId,
                assignedToId != null ? assignedToId : "", purchaseDate, status);
    }
}
