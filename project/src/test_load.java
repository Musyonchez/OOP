import database.FileManager;
import models.User;
import java.util.List;

public class test_load {
    public static void main(String[] args) {
        System.out.println("Testing file loading...");
        System.out.println("Current directory: " + System.getProperty("user.dir"));
        
        FileManager.initializeFiles();
        List<User> users = FileManager.loadUsers();
        
        System.out.println("Loaded " + users.size() + " users");
        for (User u : users) {
            System.out.println("  - " + u.getId() + ": " + u.getName());
        }
    }
}
