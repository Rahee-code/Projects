package New1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ATMSystem {
    private static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        initializeUsers(); // Adding sample users
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        User user = authenticateUser(userId, pin);
        if (user != null) {
            ATM atm = new ATM(user);
            atm.showMenu();
        } else {
            System.out.println("Invalid User ID or PIN.");
        }

        scanner.close();
    }

    private static void initializeUsers() {
        users.put("user1", new User("user1", "1234", 1000));
        users.put("user2", new User("user2", "5678", 2000));
    }

    private static User authenticateUser(String userId, String pin) {
        User user = users.get(userId);
        if (user != null && user.getPin().equals(pin)) {
            return user;
        }
        return null;
    }

    public static User getUserById(String userId) {
        return users.get(userId);
    }
}
