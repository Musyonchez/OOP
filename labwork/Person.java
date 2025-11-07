public class Person {
    // Instance variables
    private String id;
    private String name;
    private int age;

    public Person(String id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Method to be overridden by subclasses
    public void displayInfo() {
        System.out.println("\n--- Person Information ---");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("------------------------");
    }

    // Method overloading: same name, different parameters
    public void displayInfo(boolean detailed) {
        if (detailed) {
            System.out.println("\n=== Detailed Person Information ===");
            System.out.println("Identification Number: " + id);
            System.out.println("Full Name: " + name);
            System.out.println("Age in years: " + age);
            System.out.println("==================================");
        } else {
            displayInfo();
        }
    }
}
