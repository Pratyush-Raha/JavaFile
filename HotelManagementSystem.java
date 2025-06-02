import java.util.ArrayList;

class Room {
    private int number;
    private String type;
    private double price;
    private boolean isBooked;

    public Room(int number, String type, double price) {
        this.number = number;
        this.type = type;
        this.price = price;
        this.isBooked = false;
    }

    public boolean isAvailable() {
        return !isBooked;
    }

    public void book() {
        isBooked = true;
    }

    public void checkout() {
        isBooked = false;
    }

    public int getNumber() {
        return number;
    }

    public String getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

    public boolean getIsBooked() {
        return isBooked;
    }

    @Override
    public String toString() {
        return "Room " + number + " (" + type + ") - $" + price + " - " + (isBooked ? "Booked" : "Available");
    }
}

class Guest {
    private String name;
    private String contact;

    public Guest(String name, String contact) {
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}

class Booking {
    private Guest guest;
    private Room room;
    private int days;

    public Booking(Guest guest, Room room, int days) {
        this.guest = guest;
        this.room = room;
        this.days = days;
        this.room.book();
    }

    public double getTotalPrice() {
        return room.getPrice() * days;
    }

    public void checkout() {
        room.checkout();
    }

    @Override
    public String toString() {
        return "Booking: " + guest.getName() + " -> Room " + room.getNumber() +
                ", Days: " + days + ", Total: $" + getTotalPrice();
    }
}

class Hotel {
    private String name;
    private String location;
    private ArrayList<Room> rooms;

    public Hotel(String name, String location) {
        this.name = name;
        this.location = location;
        this.rooms = new ArrayList<>();
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void showAvailableRooms() {
        System.out.println("Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.println(room);
            }
        }
    }

    public Room findRoomByNumber(int number) {
        for (Room room : rooms) {
            if (room.getNumber() == number) {
                return room;
            }
        }
        return null;
    }
}

public class HotelManagementSystem {
    public static void main(String[] args) {
        Hotel hotel = new Hotel("Ocean View", "Cox's Bazar");

        hotel.addRoom(new Room(101, "Single", 100.0));
        hotel.addRoom(new Room(102, "Double", 150.0));
        hotel.addRoom(new Room(201, "Suite", 300.0));

        hotel.showAvailableRooms();

        Guest guest = new Guest("Niher RoY", "012xxxxxxxx");
        Room roomToBook = hotel.findRoomByNumber(102);

        if (roomToBook != null && roomToBook.isAvailable()) {
            Booking booking = new Booking(guest, roomToBook, 3);
            System.out.println("\n" + booking);
        }

        System.out.println("\nAfter booking:");
        hotel.showAvailableRooms();
    }
}
