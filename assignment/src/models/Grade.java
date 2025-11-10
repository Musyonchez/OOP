package models;

/**
 * Grade class - represents a grade for a specific subject
 * Simple data class with validation
 */
public class Grade {
    private String subject;
    private double score;
    private String semester;

    /**
     * Constructor with validation
     */
    public Grade(String subject, double score, String semester) {
        this.subject = subject;
        setScore(score); // Use setter for validation
        this.semester = semester;
    }

    /**
     * Validate and set score (0-100 range)
     */
    public void setScore(double score) {
        if (score < 0) {
            this.score = 0;
        } else if (score > 100) {
            this.score = 100;
        } else {
            this.score = score;
        }
    }

    /**
     * Get letter grade for this score
     */
    public String getLetterGrade() {
        if (score >= 90) return "A+";
        else if (score >= 85) return "A";
        else if (score >= 80) return "A-";
        else if (score >= 75) return "B+";
        else if (score >= 70) return "B";
        else if (score >= 65) return "B-";
        else if (score >= 60) return "C+";
        else if (score >= 55) return "C";
        else if (score >= 50) return "C-";
        else return "F";
    }

    /**
     * Check if this is a passing grade
     */
    public boolean isPassing() {
        return score >= 50.0;
    }

    // Getters
    public String getSubject() {
        return subject;
    }

    public double getScore() {
        return score;
    }

    public String getSemester() {
        return semester;
    }

    // Setters
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    /**
     * Convert to string for file storage
     * Format: Subject|Score|Semester
     */
    @Override
    public String toString() {
        return subject + "|" + score + "|" + semester;
    }

    /**
     * Display grade info (for debugging)
     */
    public void displayInfo() {
        System.out.println("Subject: " + subject);
        System.out.println("Score: " + score);
        System.out.println("Letter Grade: " + getLetterGrade());
        System.out.println("Semester: " + semester);
        System.out.println("Status: " + (isPassing() ? "Pass" : "Fail"));
    }
}
