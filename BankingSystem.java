import java.util.*;

public class BankingSystem {

    // Customer class
    static class Customer {
        private int customerId;
        private String name;
        private String address;

        public Customer(int customerId, String name, String address) {
            this.customerId = customerId;
            this.name = name;
            this.address = address;
        }

        public void viewDetails() {
            System.out.println("Customer ID: " + customerId + ", Name: " + name + ", Address: " + address);
        }

        public String getName() {
            return name;
        }
    }

    // Account class
    static class Account {
        private String accountNo;
        private double balance;
        private Customer customer;

        public Account(String accountNo, Customer customer) {
            this.accountNo = accountNo;
            this.customer = customer;
            this.balance = 0.0;
        }

        public void deposit(double amount) {
            balance += amount;
            System.out.println("Deposited $" + amount + " to " + accountNo);
        }

        public void withdraw(double amount) {
            if (amount > balance) {
                System.out.println("Insufficient funds.");
            } else {
                balance -= amount;
                System.out.println("Withdrew $" + amount + " from " + accountNo);
            }
        }

        public double getBalance() {
            return balance;
        }

        public String getAccountNo() {
            return accountNo;
        }

        public Customer getCustomer() {
            return customer;
        }
    }

    // Bank class
    static class Bank {
        private String bankName;
        private String bankCode;
        private List<Account> accounts;

        public Bank(String bankName, String bankCode) {
            this.bankName = bankName;
            this.bankCode = bankCode;
            this.accounts = new ArrayList<>();
        }

        public void addAccount(Account account) {
            accounts.add(account);
            System.out.println("Account " + account.getAccountNo() + " added for " + account.getCustomer().getName());
        }

        public Account getAccount(String accNo) {
            for (Account acc : accounts) {
                if (acc.getAccountNo().equals(accNo)) {
                    return acc;
                }
            }
            return null;
        }
    }

    // Main method
    public static void main(String[] args) {
        Bank bank = new Bank("OpenAI Bank", "OAI123");

        Customer customer1 = new Customer(1, "Alice", "123 Park Street");
        Account account1 = new Account("ACC1001", customer1);
        bank.addAccount(account1);

        account1.deposit(1000);
        account1.withdraw(200);
        System.out.println("Balance: $" + account1.getBalance());
    }
}
