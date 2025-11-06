import java.util.ArrayList;

// Inheritance: Student extends Person
public class Student extends Person {
    // Class constants
    public static final double PASSING_GRADE = 50.0;
    public static final double MAX_GRADE = 100.0;

    // Instance variables
    private ArrayList<Grade> grades;
    private String major;

    public Student(String studentId, String name, int age, String major) {
        // Call parent constructor
        super(studentId, name, age);
        this.grades = new ArrayList<>();
        this.major = major;
    }

    public boolean addGrade(String subject, double grade) throws InvalidGradeException {
        // Exception handling for invalid grades
        if (grade < 0 || grade > MAX_GRADE) {
            throw new InvalidGradeException("Grade must be between 0 and " + MAX_GRADE);
        }

        grades.add(new Grade(subject, grade));
        return true;
    }

    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;
        int index = 0;

        // While loop for calculation
        while (index < grades.size()) {
            total += grades.get(index).getGrade();
            index++;
        }

        return total / grades.size();
    }

    public String getStatus() {
        double average = calculateAverage();

        // Multi-level decision making
        if (average >= 80) {
            return "Excellent";
        } else if (average >= 70) {
            return "Very Good";
        } else if (average >= 60) {
            return "Good";
        } else if (average >= PASSING_GRADE) {
            return "Pass";
        } else {
            return "Fail";
        }
    }

    public String getGradeCategory(double grade) {
        // Switch statement for grade categories
        int category = (int) (grade / 10);

        switch (category) {
            case 10:
            case 9:
                return "A";
            case 8:
                return "B";
            case 7:
                return "C";
            case 6:
                return "D";
            case 5:
                return "E";
            default:
                return "F";
        }
    }

    // Method overriding: Student version of displayInfo()
    @Override
    public void displayInfo() {
        System.out.println("\n--- Student Information ---");
        System.out.println("ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Age: " + getAge());
        System.out.println("Major: " + major);
        System.out.println("Average Grade: " + String.format("%.2f", calculateAverage()));
        System.out.println("Status: " + getStatus());
        System.out.println("-------------------------");
    }

    // Method overloading in Student class
    public void displayInfo(String format) {
        if (format.equalsIgnoreCase("short")) {
            System.out.printf("%s (ID: %s) - %s - Avg: %.2f\n",
                getName(), getId(), major, calculateAverage());
        } else if (format.equalsIgnoreCase("full")) {
            displayReport();
        } else {
            displayInfo();
        }
    }

    public void displayReport() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("Student Report: " + getName() + " (ID: " + getId() + ")");
        System.out.println("Major: " + major + " | Age: " + getAge());
        System.out.println("=".repeat(50));

        if (grades.isEmpty()) {
            System.out.println("No grades recorded yet.");
            return;
        }

        System.out.println("\nSubject Grades:");
        for (Grade g : grades) {
            String category = getGradeCategory(g.getGrade());
            System.out.printf("  - %s: %.1f (Grade: %s)\n",
                g.getSubject(), g.getGrade(), category);
        }

        double average = calculateAverage();
        String status = getStatus();

        System.out.printf("\nAverage Grade: %.2f\n", average);
        System.out.println("Status: " + status);
        System.out.println("=".repeat(50) + "\n");
    }

    public int countFailingGrades() {
        int failCount = 0;
        int i = 0;

        // Do-while loop to count failing grades
        if (!grades.isEmpty()) {
            do {
                if (grades.get(i).getGrade() < PASSING_GRADE) {
                    failCount++;
                }
                i++;
            } while (i < grades.size());
        }

        return failCount;
    }

    // Getters
    public ArrayList<Grade> getGrades() {
        return grades;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}
