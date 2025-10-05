import java.util.*;

class Reservation {
    String username;
    String trainNumber;
    String trainName;
    String classType;
    String journeyDate;
    String fromPlace;
    String toPlace;
    String pnr;

    public Reservation(String username, String trainNumber, String trainName, String classType, String journeyDate, String fromPlace, String toPlace, String pnr) {
        this.username = username;
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.classType = classType;
        this.journeyDate = journeyDate;
        this.fromPlace = fromPlace;
        this.toPlace = toPlace;
        this.pnr = pnr;
    }

    @Override
    public String toString() {
        return "PNR: " + pnr + "\nUser: " + username + "\nTrain: " + trainNumber + " - " + trainName +
               "\nClass: " + classType + "\nDate: " + journeyDate + "\nFrom: " + fromPlace + " To: " + toPlace;
    }
}

public class OnlineReservationSystem {

    static Scanner sc = new Scanner(System.in);
    static Map<String, String> users = new HashMap<>();
    static List<Reservation> reservations = new ArrayList<>();
    static String currentUser = null;

    public static void main(String[] args) {
        users.put("admin", "admin123"); // default user

        System.out.println("=== ONLINE RESERVATION SYSTEM ===");
        login();

        int choice;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Reserve Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    reserveTicket();
                    break;
                case 2:
                    cancelTicket();
                    break;
                case 3:
                    System.out.println("Thank you for using the Online Reservation System.");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (choice != 3);
    }

    static void login() {
        while (true) {
            System.out.print("Enter username: ");
            String username = sc.nextLine();
            System.out.print("Enter password: ");
            String password = sc.nextLine();

            if (users.containsKey(username) && users.get(username).equals(password)) {
                currentUser = username;
                System.out.println("Login successful. Welcome, " + username + "!");
                break;
            } else {
                System.out.println("Invalid credentials. Try again.");
            }
        }
    }

    static void reserveTicket() {
        System.out.print("Train Number: ");
        String trainNumber = sc.nextLine();

        System.out.print("Train Name: ");
        String trainName = sc.nextLine();

        System.out.print("Class Type: ");
        String classType = sc.nextLine();

        System.out.print("Journey Date (YYYY-MM-DD): ");
        String journeyDate = sc.nextLine();

        System.out.print("From: ");
        String from = sc.nextLine();

        System.out.print("To: ");
        String to = sc.nextLine();

        String pnr = generatePNR();

        Reservation res = new Reservation(currentUser, trainNumber, trainName, classType, journeyDate, from, to, pnr);
        reservations.add(res);

        System.out.println("Reservation successful. Your PNR is: " + pnr);
    }

    static void cancelTicket() {
        System.out.print("Enter PNR to cancel: ");
        String pnr = sc.nextLine();

        Reservation found = null;
        for (Reservation r : reservations) {
            if (r.pnr.equals(pnr)) {
                found = r;
                break;
            }
        }

        if (found != null) {
            reservations.remove(found);
            System.out.println("Reservation cancelled successfully:");
            System.out.println(found);
        } else {
            System.out.println("PNR not found.");
        }
    }

    static String generatePNR() {
        return "PNR" + (int)(Math.random() * 1000000);
    }
}