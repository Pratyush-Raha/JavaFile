import java.util.*;

public class StudentRegistrationSystem {

    // Account class
    static class Account {
        private String username;
        private String password;

        public Account(String username, String password) {
            this.username = username;
            this.password = password;
        }

        public boolean login(String uname, String pwd) {
            return username.equals(uname) && password.equals(pwd);
        }
    }

    // Student class
    static class Student {
        private int id;
        private String name;
        private Account account;

        public Student(int id, String name, Account account) {
            this.id = id;
            this.name = name;
            this.account = account;
        }

        public void view() {
            System.out.println("Student ID: " + id + ", Name: " + name);
        }

        public String getName() {
            return name;
        }
    }

    // Course class
    static class Course {
        private String courseId;
        private String title;

        public Course(String courseId, String title) {
            this.courseId = courseId;
            this.title = title;
        }

        public void info() {
            System.out.println("Course ID: " + courseId + ", Title: " + title);
        }

        public String getCourseId() {
            return courseId;
        }
    }

    // RegistrationManager class
    static class RegistrationManager {
        private Map<Student, List<Course>> registrations = new HashMap<>();

        public void registerStudent(Student student, Course course) {
            registrations.putIfAbsent(student, new ArrayList<>());
            registrations.get(student).add(course);
            System.out.println("Registered " + student.getName() + " for course " + course.getCourseId());
        }

        public List<Course> getCourses(Student student) {
            return registrations.getOrDefault(student, Collections.emptyList());
        }
    }

    // Main method
    public static void main(String[] args) {
        Account acc = new Account("student01", "pass123");
        Student student = new Student(1, "Md Asif", acc);

        Course javaCourse = new Course("C101", "Java Programming");
        Course ethicsCourse = new Course("C102", "Engineering Ethics");

        RegistrationManager manager = new RegistrationManager();
        manager.registerStudent(student, javaCourse);
        manager.registerStudent(student, ethicsCourse);

        System.out.println("\nCourses registered for " + student.getName() + ":");
        for (Course c : manager.getCourses(student)) {
            c.info();
        }
    }
}
