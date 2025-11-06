import java.util.ArrayList;

public class GradeManager {
    private ArrayList<Student> students;

    public GradeManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student " + student.getName() + " added successfully.");
    }

    public Student findStudent(String studentId) {
        // Search for student by ID
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students in the system.");
            return;
        }

        System.out.println("\n" + "=".repeat(50));
        System.out.println("All Students Summary");
        System.out.println("=".repeat(50));

        for (Student student : students) {
            double average = student.calculateAverage();
            String status = student.getStatus();
            System.out.printf("%s (ID: %s) - Avg: %.2f - %s\n",
                    student.getName(), student.getId(), average, status);
        }

        System.out.println("=".repeat(50) + "\n");
    }

    public void getStatistics() {
        if (students.isEmpty()) {
            System.out.println("No students to analyze.");
            return;
        }

        int passingCount = 0;
        int failingCount = 0;

        // Loop through students to count pass/fail
        for (Student student : students) {
            double average = student.calculateAverage();
            if (average >= Student.PASSING_GRADE) {
                passingCount++;
            } else {
                failingCount++;
            }
        }

        System.out.println("\nClass Statistics:");
        System.out.println("  Total Students: " + students.size());
        System.out.println("  Passing: " + passingCount);
        System.out.println("  Failing: " + failingCount);
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
