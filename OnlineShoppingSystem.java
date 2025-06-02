import java.util.*;

public class OnlineShoppingSystem {

    // Account class
    static class Account {
        protected String username;
        protected String password;

        public Account(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public boolean login(String uname, String pwd) {
            return username.equals(uname) && password.equals(pwd);
        }

        public void logout() {
            System.out.println("Logged out successfully.");
        }
    }

    // User class
    static class User {
        private int userId;
        private String name;
        private String email;
        private Account account;

        public User(int userId, String name, String email, Account account) {
            this.userId = userId;
            this.name = name;
            this.email = email;
            this.account = account;
        }

        public void browseProducts(List<Product> products) {
            System.out.println("\nAvailable Products:");
            for (Product p : products) {
                System.out.println(p);
            }
        }

        public Order placeOrder(List<Product> products) {
            Order order = new Order(this);
            for (Product p : products) {
                order.addProduct(p);
            }
            System.out.println("Order placed.");
            return order;
        }
    }

    // Product class
    static class Product {
        private int productId;
        private String name;
        private double price;

        public Product(int productId, String name, double price) {
            this.productId = productId;
            this.name = name;
            this.price = price;
        }

        public double getPrice() {
            return price;
        }

        public String toString() {
            return productId + ": " + name + " ($" + price + ")";
        }
    }

    // Order class
    static class Order {
        private static int count = 0;
        private int orderId;
        private List<Product> products;
        private User user;
        private Payment payment;
        private Shipment shipment;

        public Order(User user) {
            this.user = user;
            this.products = new ArrayList<>();
            this.orderId = ++count;
        }

        public void addProduct(Product product) {
            products.add(product);
        }

        public void makePayment(String method) {
            payment = new Payment(this, method);
            payment.processPayment();
        }

        public void shipOrder(String address) {
            shipment = new Shipment(this, address);
            shipment.ship();
        }

        public double getTotalAmount() {
            double total = 0;
            for (Product p : products) {
                total += p.getPrice();
            }
            return total;
        }
    }

    // Payment class
    static class Payment {
        private Order order;
        private String method;

        public Payment(Order order, String method) {
            this.order = order;
            this.method = method;
        }

        public void processPayment() {
            System.out.println("Payment of $" + order.getTotalAmount() + " done via " + method + ".");
        }
    }

    // Shipment class
    static class Shipment {
        private Order order;
        private String address;

        public Shipment(Order order, String address) {
            this.order = order;
            this.address = address;
        }

        public void ship() {
            System.out.println("Order shipped to: " + address);
        }
    }

    // Main method
    public static void main(String[] args) {
        List<Product> productList = Arrays.asList(
            new Product(1, "Laptop", 999.99),
            new Product(2, "Smartphone", 499.99),
            new Product(3, "Headphones", 79.99)
        );

        Account acc = new Account("john_doe", "pass123");
        User user = new User(101, "John Doe", "john@example.com", acc);

        user.browseProducts(productList);
        Order order = user.placeOrder(Arrays.asList(productList.get(0), productList.get(2)));
        order.makePayment("Credit Card");
        order.shipOrder("123 Main St, Cityville");
    }
}
