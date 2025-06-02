import java.util.*;

public class AirlineReservationSystem {

    // Passenger class
    static class Passenger {
        private String name;
        private String passportNo;

        public Passenger(String name, String passportNo) {
            this.name = name;
            this.passportNo = passportNo;
        }

        public void getInfo() {
            System.out.println("Passenger: " + name + ", Passport No: " + passportNo);
        }

        public String getName() {
            return name;
        }
    }

    // Flight class
    static class Flight {
        private String flightNo;
        private String source;
        private String destination;

        public Flight(String flightNo, String source, String destination) {
            this.flightNo = flightNo;
            this.source = source;
            this.destination = destination;
        }

        public void getDetails() {
            System.out.println("Flight No: " + flightNo + ", From: " + source + " To: " + destination);
        }

        public String getFlightNo() {
            return flightNo;
        }
    }

    // Reservation class
    static class Reservation {
        private String reservationId;
        private Passenger passenger;
        private Flight flight;

        public Reservation(String reservationId, Passenger passenger, Flight flight) {
            this.reservationId = reservationId;
            this.passenger = passenger;
            this.flight = flight;
        }

        public void details() {
            System.out.println("Reservation ID: " + reservationId);
            passenger.getInfo();
            flight.getDetails();
            System.out.println("----------------------------");
        }

        public String getReservationId() {
            return reservationId;
        }
    }

    // ReservationSystem class
    static class ReservationSystem {
        private List<Reservation> reservations = new ArrayList<>();

        public void makeReservation(String reservationId, Passenger p, Flight f) {
            Reservation r = new Reservation(reservationId, p, f);
            reservations.add(r);
            System.out.println("Reservation successful for " + p.getName());
        }

        public void viewReservations() {
            System.out.println("\nAll Reservations:");
            for (Reservation r : reservations) {
                r.details();
            }
        }
    }

    // Main method
    public static void main(String[] args) {
        ReservationSystem system = new ReservationSystem();

        // Create flights
        Flight flight1 = new Flight("AI202", "New York", "London");
        Flight flight2 = new Flight("EM303", "Dubai", "Tokyo");

        // Create passengers
        Passenger p1 = new Passenger("Pritom", "P123456");
        Passenger p2 = new Passenger("Rohi", "P654321");

        // Make reservations
        system.makeReservation("R001", p1, flight1);
        system.makeReservation("R002", p2, flight2);

        // View all reservations
        system.viewReservations();
    }
}
