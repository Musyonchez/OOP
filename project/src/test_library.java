import managers.Library;
import models.User;

public class test_library {
    public static void main(String[] args) {
        System.out.println("Testing Library initialization...");
        Library library = new Library();
        
        System.out.println("\nTrying to find L001...");
        User user = library.findUserById("L001");
        
        if (user != null) {
            System.out.println("Found: " + user.getName());
            user.displayInfo();
        } else {
            System.out.println("User not found!");
        }
    }
}
