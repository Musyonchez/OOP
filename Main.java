public class Main {
    public static void main(String[] args) {
        GradeManager manager = new GradeManager();

        // Create and add students
        Student student1 = new Student("S001", "Alice Johnson", 20, "Computer Science");
        Student student2 = new Student("S002", "Bob Smith", 19, "Engineering");
        Student student3 = new Student("S003", "Carol White", 21, "Mathematics");

        manager.addStudent(student1);
        manager.addStudent(student2);
        manager.addStudent(student3);

        // Add grades to students
        addStudentGrades(student1, new String[]{"Mathematics", "Physics", "Chemistry", "English"},
                         new double[]{85, 78, 92, 88});
        addStudentGrades(student2, new String[]{"Mathematics", "Physics", "Chemistry", "English"},
                         new double[]{45, 52, 48, 55});
        addStudentGrades(student3, new String[]{"Mathematics", "Physics", "Chemistry", "English"},
                         new double[]{72, 68, 75, 70});

        // Display reports and statistics
        displayStudentReports(student1, student2, student3);
        manager.displayAllStudents();
        manager.getStatistics();

        // Demonstrate exception handling
        demonstrateExceptionHandling(student1);

        // Demonstrate search and loops
        demonstrateSearchAndLoops(manager);

        // Demonstrate polymorphism
        demonstratePolymorphism(student1);
    }

    // Helper method to add grades with exception handling
    private static void addStudentGrades(Student student, String[] subjects, double[] grades) {
        try {
            for (int i = 0; i < subjects.length; i++) {
                student.addGrade(subjects[i], grades[i]);
            }
        } catch (InvalidGradeException e) {
            System.out.println("Error adding grade: " + e.getMessage());
        }
    }

    private static void displayStudentReports(Student... students) {
        for (Student student : students) {
            student.displayReport();
        }
    }

    private static void demonstrateExceptionHandling(Student student) {
        System.out.println("\nTesting grade validation with exception handling:");

        try {
            student.addGrade("History", 105);
        } catch (InvalidGradeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            student.addGrade("History", -10);
        } catch (InvalidGradeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            student.addGrade("History", 82);
            System.out.println("History grade added successfully!");
        } catch (InvalidGradeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }
    }

    private static void demonstrateSearchAndLoops(GradeManager manager) {
        System.out.println("\nSearching for student S002:");
        Student foundStudent = manager.findStudent("S002");

        if (foundStudent != null) {
            foundStudent.displayReport();

            // Demonstrate do-while loop
            int failingCount = foundStudent.countFailingGrades();
            System.out.println("Number of failing grades: " + failingCount);
        } else {
            System.out.println("Student not found.");
        }

        // Demonstrate switch statement with while loop
        System.out.println("\nGrade Category Examples:");
        double[] testGrades = {95, 82, 73, 65, 58, 42};
        int index = 0;

        while (index < testGrades.length) {
            String category = foundStudent.getGradeCategory(testGrades[index]);
            System.out.printf("Grade %.0f falls in category: %s\n",
                testGrades[index], category);
            index++;
        }
    }

    private static void demonstratePolymorphism(Student student) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("POLYMORPHISM DEMONSTRATION");
        System.out.println("=".repeat(50));

        // Runtime polymorphism
        Person person1 = new Student("S004", "David Lee", 22, "Physics");
        Person person2 = new Person("P001", "John Doe", 45);

        System.out.println("\nCalling displayInfo() on Person reference:");
        person2.displayInfo();

        System.out.println("\nCalling displayInfo() on Student object (via Person reference):");
        person1.displayInfo();

        // Method overloading demonstration
        System.out.println("\n" + "=".repeat(50));
        System.out.println("METHOD OVERLOADING DEMONSTRATION");
        System.out.println("=".repeat(50));

        System.out.println("\n1. Person.displayInfo() - no parameters:");
        person2.displayInfo();

        System.out.println("\n2. Person.displayInfo(boolean) - with parameter:");
        person2.displayInfo(true);

        System.out.println("\n3. Student.displayInfo(String) - different parameter:");
        student.displayInfo("short");
    }
}
