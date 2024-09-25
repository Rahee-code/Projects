package New1;

import java.util.Scanner;

public class ATM {
    private User user;

    public ATM(User user) {
        this.user = user;
    }

    public void showMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nATM Menu:");
            System.out.println("1. Withdraw");
            System.out.println("2. Deposit");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction History");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    withdraw(scanner);
                    break;
                case 2:
                    deposit(scanner);
                    break;
                case 3:
                    transfer(scanner);
                    break;
                case 4:
                    transactionHistory();
                    break;
                case 5:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private void withdraw(Scanner scanner) {
        System.out.print("Enter amount to withdraw: ");
        double amount = scanner.nextDouble();
        if (user.getAccount().withdraw(amount)) {
            user.addTransaction(new Transaction("Withdraw", amount));
            System.out.println("Withdrawal successful! New balance: " + user.getAccount().getBalance());
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    private void deposit(Scanner scanner) {
        System.out.print("Enter amount to deposit: ");
        double amount = scanner.nextDouble();
        user.getAccount().deposit(amount);
        user.addTransaction(new Transaction("Deposit", amount));
        System.out.println("Deposit successful! New balance: " + user.getAccount().getBalance());
    }

    private void transfer(Scanner scanner) {
        System.out.print("Enter recipient User ID: ");
        String recipientId = scanner.next();
        System.out.print("Enter amount to transfer: ");
        double amount = scanner.nextDouble();

        User recipient = ATMSystem.getUserById(recipientId);
        if (recipient != null) {
            if (user.getAccount().withdraw(amount)) {
                recipient.getAccount().deposit(amount);
                user.addTransaction(new Transaction("Transfer to " + recipientId, amount));
                recipient.addTransaction(new Transaction("Transfer from " + user.getUserId(), amount));
                System.out.println("Transfer successful! New balance: " + user.getAccount().getBalance());
            } else {
                System.out.println("Insufficient balance for the transfer.");
            }
        } else {
            System.out.println("Recipient User ID not found.");
        }
    }

    private void transactionHistory() {
        System.out.println("Transaction History:");
        for (Transaction transaction : user.getTransactionHistory()) {
            System.out.println(transaction);
        }
    }
}
