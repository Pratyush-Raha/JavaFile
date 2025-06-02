import java.util.*;

public class HospitalManagementSystem {

    // Base Person class
    static class Person {
        protected int id;
        protected String name;
        protected int age;

        public Person(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public void getDetails() {
            System.out.println("ID: " + id + ", Name: " + name + ", Age: " + age);
        }
    }

    // Patient class
    static class Patient extends Person {
        private String patientId;
        private String disease;
        private Staff assignedDoctor;

        public Patient(int id, String name, int age, String patientId, String disease) {
            super(id, name, age);
            this.patientId = patientId;
            this.disease = disease;
        }

        public void admit(Staff doctor) {
            this.assignedDoctor = doctor;
            System.out.println("Patient " + name + " admitted under Dr. " + doctor.name);
        }

        public String getDisease() {
            return disease;
        }

        public Staff getDoctor() {
            return assignedDoctor;
        }
    }

    // Staff class
    static class Staff extends Person {
        private String staffId;
        private String role;
        private String department;

        public Staff(int id, String name, int age, String staffId, String role, String department) {
            super(id, name, age);
            this.staffId = staffId;
            this.role = role;
            this.department = department;
        }

        public void assignDuty() {
            System.out.println("Staff " + name + " is assigned to " + department + " department as " + role);
        }
    }

    // Treatment class
    static class Treatment {
        private String treatmentId;
        private String description;
        private Patient patient;
        private Staff doctor;

        public Treatment(String treatmentId, String description, Patient patient, Staff doctor) {
            this.treatmentId = treatmentId;
            this.description = description;
            this.patient = patient;
            this.doctor = doctor;
        }

        public void startTreatment() {
            System.out.println("Treatment started for " + patient.name + " by Dr. " + doctor.name + ": " + description);
        }
    }

    // Main method
    public static void main(String[] args) {
        Staff doctor = new Staff(1, "Dr.Mitu", 45, "D001", "Physician", "Medicine");
        Patient patient = new Patient(2, "Arif Islam", 30, "P1001", "Fever");

        doctor.assignDuty();
        patient.admit(doctor);

        Treatment treatment = new Treatment("T001", "Paracetamol for 5 days", patient, doctor);
        treatment.startTreatment();
    }
}
