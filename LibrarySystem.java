// LibrarySystem.java
import java.util.*;

// Account class
class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}

// Book class
class Book {
    private int bookId;
    private String title;
    private String author;
    private boolean isAvailable;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isAvailable = true;
    }

    public void borrow() {
        if (isAvailable) {
            isAvailable = false;
            System.out.println(title + " has been borrowed.");
        } else {
            System.out.println(title + " is not available.");
        }
    }

    public void returnBook() {
        isAvailable = true;
        System.out.println(title + " has been returned.");
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public String getTitle() {
        return title;
    }
}

// User base class
class User {
    protected int userId;
    protected String name;
    protected Account account;

    public User(int userId, String name, Account account) {
        this.userId = userId;
        this.name = name;
        this.account = account;
    }

    public void viewBooks(List<Book> books) {
        System.out.println("\nAvailable Books:");
        for (Book book : books) {
            System.out.println(book.getTitle() + " - " + (book.isAvailable() ? "Available" : "Not Available"));
        }
    }

    public void borrowBook(Book book) {
        book.borrow();
    }
}

// Student class
class Student extends User {
    private int rollNo;

    public Student(int userId, String name, Account account, int rollNo) {
        super(userId, name, account);
        this.rollNo = rollNo;
    }

    public void requestBook(Book book) {
        if (book.isAvailable()) {
            borrowBook(book);
        } else {
            System.out.println("Book is currently not available.");
        }
    }
}

// Librarian class
class Librarian extends User {
    private int employeeId;

    public Librarian(int userId, String name, Account account, int employeeId) {
        super(userId, name, account);
        this.employeeId = employeeId;
    }

    public void addBook(List<Book> books, Book newBook) {
        books.add(newBook);
        System.out.println("Book added: " + newBook.getTitle());
    }

    public void removeBook(List<Book> books, Book book) {
        books.remove(book);
        System.out.println("Book removed: " + book.getTitle());
    }
}

// Main class
public class LibrarySystem {
    public static void main(String[] args) {
        Account studentAcc = new Account("student1", "pass123");
        Student student = new Student(1, "Alice", studentAcc, 101);

        Account librarianAcc = new Account("lib1", "admin123");
        Librarian librarian = new Librarian(2, "Mr. Bob", librarianAcc, 201);

        List<Book> books = new ArrayList<>();
        Book book1 = new Book(1, "Java Programming", "John Doe");
        Book book2 = new Book(2, "Python Basics", "Jane Smith");

        librarian.addBook(books, book1);
        librarian.addBook(books, book2);

        student.viewBooks(books);
        student.requestBook(book1);
        student.viewBooks(books);

        book1.returnBook();
        student.viewBooks(books);
    }
}
