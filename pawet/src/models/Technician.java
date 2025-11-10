package models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Technician class representing technical and field staff
 * Demonstrates Inheritance and Polymorphism
 */
public class Technician extends Employee {
    // Additional fields specific to Technicians
    private List<String> equipmentHandled;  // IDs of equipment they operate/maintain
    private String certifications;          // Certifications held

    /**
     * Constructor for Technician
     */
    public Technician(String id, String name, String email, String phone,
                     String departmentId, String role, double salary, LocalDate hireDate,
                     String certifications) {
        super(id, name, email, phone, departmentId, role, salary, hireDate);
        this.equipmentHandled = new ArrayList<>();
        this.certifications = certifications;
    }

    /**
     * Constructor with equipment list
     */
    public Technician(String id, String name, String email, String phone,
                     String departmentId, String role, double salary, LocalDate hireDate,
                     List<String> equipmentHandled, String certifications) {
        super(id, name, email, phone, departmentId, role, salary, hireDate);
        this.equipmentHandled = equipmentHandled != null ? new ArrayList<>(equipmentHandled) : new ArrayList<>();
        this.certifications = certifications;
    }

    /**
     * Override abstract method (Polymorphism)
     * Displays Technician-specific information
     */
    @Override
    public void displayInfo() {
        displayBasicInfo();
        System.out.println("Role Type: TECHNICIAN");
        System.out.println("Certifications: " + certifications);
        System.out.println("Equipment Handled: " + equipmentHandled.size() + " items");
        if (!equipmentHandled.isEmpty()) {
            System.out.println("Equipment IDs: " + String.join(", ", equipmentHandled));
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Override getRoleType
     */
    @Override
    public String getRoleType() {
        return "TECHNICIAN";
    }

    /**
     * Add equipment to technician's responsibility
     */
    public void addEquipment(String equipmentId) {
        if (!equipmentHandled.contains(equipmentId)) {
            equipmentHandled.add(equipmentId);
        }
    }

    /**
     * Remove equipment from technician's responsibility
     */
    public void removeEquipment(String equipmentId) {
        equipmentHandled.remove(equipmentId);
    }

    /**
     * Get equipment count
     */
    public int getEquipmentCount() {
        return equipmentHandled.size();
    }

    /**
     * Check if technician is overloaded (more than 5 equipment items)
     */
    public boolean isOverloaded() {
        return equipmentHandled.size() > 5;
    }

    /**
     * Check if has specific certification
     */
    public boolean hasCertification(String cert) {
        return certifications != null && certifications.toLowerCase().contains(cert.toLowerCase());
    }

    // Getters and Setters
    public List<String> getEquipmentHandled() {
        return new ArrayList<>(equipmentHandled);
    }

    public void setEquipmentHandled(List<String> equipmentHandled) {
        this.equipmentHandled = equipmentHandled != null ? new ArrayList<>(equipmentHandled) : new ArrayList<>();
    }

    public String getCertifications() {
        return certifications;
    }

    public void setCertifications(String certifications) {
        this.certifications = certifications;
    }

    /**
     * Convert equipment list to string for database storage
     */
    public String getEquipmentListAsString() {
        return String.join(",", equipmentHandled);
    }

    /**
     * Parse equipment list from string
     */
    public static List<String> parseEquipmentList(String equipmentListString) {
        List<String> list = new ArrayList<>();
        if (equipmentListString != null && !equipmentListString.isEmpty()) {
            String[] ids = equipmentListString.split(",");
            for (String id : ids) {
                if (!id.trim().isEmpty()) {
                    list.add(id.trim());
                }
            }
        }
        return list;
    }
}
