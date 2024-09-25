package New1;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String pin;
    private Account account;
    private List<Transaction> transactionHistory;

    public User(String userId, String pin, double initialBalance) {
        this.userId = userId;
        this.pin = pin;
        this.account = new Account(initialBalance);
        this.transactionHistory = new ArrayList<>();
    }

    public String getUserId() {
        return userId;
    }

    public String getPin() {
        return pin;
    }

    public Account getAccount() {
        return account;
    }

    public void addTransaction(Transaction transaction) {
        transactionHistory.add(transaction);
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}
