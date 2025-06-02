import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private String accountNo;
    private String pin;
    private double balance;

    public Account(String accountNo, String pin, double balance) {
        this.accountNo = accountNo;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePIN(String inputPin) {
        return this.pin.equals(inputPin);
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        } else {
            return false;
        }
    }

    public double getBalance() {
        return balance;
    }

    public String getAccountNo() {
        return accountNo;
    }
}

class ATM {
    private ArrayList<Account> accounts;

    public ATM() {
        accounts = new ArrayList<>();
        // Add some sample accounts
        accounts.add(new Account("123456", "1234", 1000.0));
        accounts.add(new Account("654321", "4321", 2000.0));
    }

    public Account authenticate(String accountNo, String pin) {
        for (Account acc : accounts) {
            if (acc.getAccountNo().equals(accountNo) && acc.validatePIN(pin)) {
                return acc;
            }
        }
        return null;
    }

    public void withdraw(Account account, double amount) {
        if (account.withdraw(amount)) {
            System.out.println("Withdrawal successful. New Balance: " + account.getBalance());
        } else {
            System.out.println("Insufficient balance or invalid amount.");
        }
    }

    public void deposit(Account account, double amount) {
        account.deposit(amount);
        System.out.println("Deposit successful. New Balance: " + account.getBalance());
    }

    public void checkBalance(Account account) {
        System.out.println("Current Balance: " + account.getBalance());
    }
}

public class ATMSystem {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter account number: ");
        String accNo = scanner.nextLine();

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        Account current = atm.authenticate(accNo, pin);

        if (current != null) {
            System.out.println("Login successful!");
            int choice;
            do {
                System.out.println("\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        atm.checkBalance(current);
                        break;
                    case 2:
                        System.out.print("Enter deposit amount: ");
                        double depositAmount = scanner.nextDouble();
                        atm.deposit(current, depositAmount);
                        break;
                    case 3:
                        System.out.print("Enter withdrawal amount: ");
                        double withdrawAmount = scanner.nextDouble();
                        atm.withdraw(current, withdrawAmount);
                        break;
                    case 4:
                        System.out.println("Thank you for using the ATM.");
                        break;
                    default:
                        System.out.println("Invalid choice.");
                }
            } while (choice != 4);
        } else {
            System.out.println("Authentication failed.");
        }

        scanner.close();
    }
}
