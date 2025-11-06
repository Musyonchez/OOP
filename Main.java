public class Main {
    public static void main(String[] args) {
        // Create grade manager object
        GradeManager manager = new GradeManager();

        // Create student objects with inheritance
        Student student1 = new Student("S001", "Alice Johnson", 20, "Computer Science");
        Student student2 = new Student("S002", "Bob Smith", 19, "Engineering");
        Student student3 = new Student("S003", "Carol White", 21, "Mathematics");

        // Add students to manager
        manager.addStudent(student1);
        manager.addStudent(student2);
        manager.addStudent(student3);

        // Add grades with exception handling
        try {
            student1.addGrade("Mathematics", 85);
            student1.addGrade("Physics", 78);
            student1.addGrade("Chemistry", 92);
            student1.addGrade("English", 88);
        } catch (InvalidGradeException e) {
            System.out.println("Error adding grade: " + e.getMessage());
        }

        try {
            student2.addGrade("Mathematics", 45);
            student2.addGrade("Physics", 52);
            student2.addGrade("Chemistry", 48);
            student2.addGrade("English", 55);
        } catch (InvalidGradeException e) {
            System.out.println("Error adding grade: " + e.getMessage());
        }

        try {
            student3.addGrade("Mathematics", 72);
            student3.addGrade("Physics", 68);
            student3.addGrade("Chemistry", 75);
            student3.addGrade("English", 70);
        } catch (InvalidGradeException e) {
            System.out.println("Error adding grade: " + e.getMessage());
        }

        // Display individual reports
        student1.displayReport();
        student2.displayReport();
        student3.displayReport();

        // Display all students summary
        manager.displayAllStudents();

        // Display statistics
        manager.getStatistics();

        // Demonstrate exception handling with invalid grades
        System.out.println("\nTesting grade validation with exception handling:");
        try {
            student1.addGrade("History", 105);
        } catch (InvalidGradeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            student1.addGrade("History", -10);
        } catch (InvalidGradeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        try {
            student1.addGrade("History", 82);
            System.out.println("History grade added successfully!");
        } catch (InvalidGradeException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Search functionality demonstration
        System.out.println("\nSearching for student S002:");
        Student foundStudent = manager.findStudent("S002");
        if (foundStudent != null) {
            foundStudent.displayReport();

            // Demonstrate do-while loop usage
            int failingCount = foundStudent.countFailingGrades();
            System.out.println("Number of failing grades: " + failingCount);
        } else {
            System.out.println("Student not found.");
        }

        // Demonstrate switch statement with different grade values
        System.out.println("\nGrade Category Examples:");
        double[] testGrades = {95, 82, 73, 65, 58, 42};
        int index = 0;

        // While loop to display grade categories
        while (index < testGrades.length) {
            String category = student1.getGradeCategory(testGrades[index]);
            System.out.printf("Grade %.0f falls in category: %s\n",
                testGrades[index], category);
            index++;
        }

        // Demonstrate Polymorphism
        System.out.println("\n" + "=".repeat(50));
        System.out.println("POLYMORPHISM DEMONSTRATION");
        System.out.println("=".repeat(50));

        // Runtime polymorphism: Person reference to Student object
        Person person1 = new Student("S004", "David Lee", 22, "Physics");
        Person person2 = new Person("P001", "John Doe", 45);

        // Same method call, different behavior based on object type
        System.out.println("\nCalling displayInfo() on Person reference:");
        person2.displayInfo();

        System.out.println("\nCalling displayInfo() on Student object (via Person reference):");
        person1.displayInfo();  // Calls Student's overridden version

        // Method overloading demonstration
        System.out.println("\n" + "=".repeat(50));
        System.out.println("METHOD OVERLOADING DEMONSTRATION");
        System.out.println("=".repeat(50));

        System.out.println("\n1. Person.displayInfo() - no parameters:");
        person2.displayInfo();

        System.out.println("\n2. Person.displayInfo(boolean) - with parameter:");
        person2.displayInfo(true);

        System.out.println("\n3. Student.displayInfo(String) - different parameter:");
        student1.displayInfo("short");
    }
}
