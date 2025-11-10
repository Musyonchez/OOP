package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Student class - represents a student with personal info and grades
 * Demonstrates encapsulation with private fields and public methods
 */
public class Student {
    private String studentId;
    private String name;
    private String email;
    private String major;
    private List<Grade> grades;

    /**
     * Constructor - creates a new student
     */
    public Student(String studentId, String name, String email, String major) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.major = major;
        this.grades = new ArrayList<>();
    }

    /**
     * Add a grade for this student
     */
    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    /**
     * Remove a grade for a specific subject
     */
    public void removeGrade(String subject) {
        grades.removeIf(g -> g.getSubject().equals(subject));
    }

    /**
     * Calculate overall average across all subjects
     */
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double sum = 0.0;
        for (Grade grade : grades) {
            sum += grade.getScore();
        }
        return sum / grades.size();
    }

    /**
     * Get grade status (Pass/Fail based on 50% threshold)
     */
    public String getStatus() {
        double average = calculateAverage();
        return average >= 50.0 ? "Pass" : "Fail";
    }

    /**
     * Get letter grade based on average
     */
    public String getLetterGrade() {
        double average = calculateAverage();

        if (average >= 90) return "A+";
        else if (average >= 85) return "A";
        else if (average >= 80) return "A-";
        else if (average >= 75) return "B+";
        else if (average >= 70) return "B";
        else if (average >= 65) return "B-";
        else if (average >= 60) return "C+";
        else if (average >= 55) return "C";
        else if (average >= 50) return "C-";
        else return "F";
    }

    /**
     * Find grade for a specific subject
     */
    public Grade getGradeForSubject(String subject) {
        for (Grade grade : grades) {
            if (grade.getSubject().equalsIgnoreCase(subject)) {
                return grade;
            }
        }
        return null;
    }

    // Getters
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMajor() {
        return major;
    }

    public List<Grade> getGrades() {
        return new ArrayList<>(grades); // Return copy for encapsulation
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * Convert to string for file storage
     * Format: ID|Name|Email|Major
     */
    @Override
    public String toString() {
        return studentId + "|" + name + "|" + email + "|" + major;
    }

    /**
     * Display student info (for debugging)
     */
    public void displayInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Major: " + major);
        System.out.println("Average: " + String.format("%.2f", calculateAverage()));
        System.out.println("Letter Grade: " + getLetterGrade());
        System.out.println("Status: " + getStatus());
    }
}
