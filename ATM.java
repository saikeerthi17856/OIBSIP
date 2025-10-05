import java.util.*;

// Transaction class
class Transaction {
    String type;
    double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return type + ": $" + amount;
    }
}

// User class
class User {
    String userId;
    String userPin;
    double balance;
    List<Transaction> transactions = new ArrayList<>();

    public User(String userId, String userPin) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = 1000.0; // default balance
    }

    public void addTransaction(String type, double amount) {
        transactions.add(new Transaction(type, amount));
    }

    public void showTransactions() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (Transaction t : transactions) {
                System.out.println(t);
            }
        }
    }
}

// BankOperations class
class BankOperations {
    User user;
    Scanner sc = new Scanner(System.in);

    public BankOperations(User user) {
        this.user = user;
    }

    public void start() {
        int choice;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    user.showTransactions();
                    break;
                case 2:
                    withdraw();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    transfer();
                    break;
                case 5:
                    System.out.println("Exiting. Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 5);
    }

    void withdraw() {
        System.out.print("Enter amount to withdraw: ");
        double amount = sc.nextDouble();
        if (amount <= user.balance) {
            user.balance -= amount;
            user.addTransaction("Withdraw", amount);
            System.out.println("Withdrawal successful.");
        } else {
            System.out.println("Insufficient balance.");
        }
    }

    void deposit() {
        System.out.print("Enter amount to deposit: ");
        double amount = sc.nextDouble();
        user.balance += amount;
        user.addTransaction("Deposit", amount);
        System.out.println("Deposit successful.");
    }

    void transfer() {
        System.out.print("Enter recipient user ID (for simulation): ");
        String recipient = sc.next(); // simulated recipient
        System.out.print("Enter amount to transfer: ");
        double amount = sc.nextDouble();
        if (amount <= user.balance) {
            user.balance -= amount;
            user.addTransaction("Transfer to " + recipient, amount);
            System.out.println("Transfer successful to " + recipient);
        } else {
            System.out.println("Insufficient balance.");
        }
    }
}

// Main ATM class
public class ATM {
    static Scanner sc = new Scanner(System.in);
    static User dummyUser = new User("user123", "1234");

    public static void main(String[] args) {
        System.out.println("=== Welcome to the ATM Interface ===");

        System.out.print("Enter User ID: ");
        String userId = sc.nextLine();
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine();

        if (authenticate(userId, pin)) {
            System.out.println("Login successful.");
            BankOperations ops = new BankOperations(dummyUser);
            ops.start();
        } else {
            System.out.println("Invalid User ID or PIN.");
        }
    }

    static boolean authenticate(String userId, String pin) {
        return dummyUser.userId.equals(userId) && dummyUser.userPin.equals(pin);
    }
}