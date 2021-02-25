package ui;


import model.Airlines;
import model.Flight;
import model.Passenger;

import java.util.ArrayList;
import java.util.Scanner;

// represent an airport self service app
public class ServiceApp {
    private Scanner input;
    private Airlines airlines;

    // EFFECTS: constructor with building today's airlines, and building flow structure of running app
    public ServiceApp() {
        this.airlines = new Airlines();
        buildAirlines();

        boolean reset = false;
        while (!reset) {
            this.input = new Scanner(System.in);
            mainMenu();
            String command = input.next();
            if (command.equals("s")) {
                runService();
            } else if (command.equals("l")) {
                loadBooking();
            } else if (command.equals("reset")) {
                reset = true;
            } else {
                System.out.println("Please enter 's' to start service.");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: building today's airlines
    public void buildAirlines() {
        Flight f1 = new Flight("CA110", 800, "vancouver");
        Flight f2 = new Flight("CA210", 1200, "toronto");
        Flight f3 = new Flight("CA310", 1600, "shanghai");
        Flight f4 = new Flight("CA410", 2000, "vancouver");
        Flight f5 = new Flight("CA510", 2400, "shanghai");
        airlines.addFlight(f1);
        airlines.addFlight(f2);
        airlines.addFlight(f3);
        airlines.addFlight(f4);
        airlines.addFlight(f5);
    }


    // EFFECTS: show the main menu on app
    public void mainMenu() {
        System.out.println("Hello! Welcome to the airport!");
        System.out.println("Start service => Press 's'.");
        System.out.println("Load the current booking info => Press 'l'.");
    }

    // EFFECTS: return to the main menu
    // TODO: could throw exception: invalid input
    public void backToMainMenu() {
        System.out.println("Back to main menu => Press 'b'.");
        String command = input.next();
        while (!command.equals("b")) {
            System.out.println("Please press 'b' to get back to the main menu.");
            command = input.next();
        }
    }


    // TODO: To be implemented
    public void loadBooking() {
        //....
        backToMainMenu();
    }

    // TODO: To be implemented
    public void saveBooking() {

    }

    // MODIFIES: this
    // EFFECTS: the actual service running
    // TODO: To be revised
    public void runService() {
        Passenger me = createPassenger();
        while (!searchFlight(me.getTime(), me.getDestination())) {
            System.out.println("Please re-enter your departing time and destination");
            me = createPassenger();
        }
        chooseFlight(me);
        String flightNum = me.getFlightNum();
        makeBook(me);
        chooseSeat(me);
        if (confirmBook(me)) {
            airlines.getFlight(flightNum).addPassenger(me);
            airlines.getFlight(flightNum).setSeat(me.getRow(), me.getCol());
            printPassengerInfo(me);
        } else {
            cancelBook();
        }
        backToMainMenu();

    }


    // EFFECTS: construct a new passenger with the given time and destination user inputs
    // TODO: could throw exception: invalid input
    public Passenger createPassenger() {
        System.out.println("Please input your departing time: (format: 900 for 9:00 am; 1600 for 4:00 pm)");
        String command = input.next();
        int time = Integer.parseInt(command);
        System.out.println("Please input your destination:");
        String destination = input.next();
        return new Passenger(time, destination);
    }


    // EFFECTS: auto-search flight with given time & destination. If found, show all found, else false
    public boolean searchFlight(int time, String destination) {
        ArrayList<Flight> results = new ArrayList<>();
        for (Flight f : airlines.getFlightList()) {
            if ((time <= f.getTime()) && (destination.equals(f.getDestination()))) {
                results.add(f);
                printFlightInfo(f);
            }
        }
        if (results.isEmpty()) {
            System.out.println("Sorry! No flight found.");
            return false;
        } else {
            return true;
        }
    }


    // MODIFIES: p
    // EFFECTS: choose flight by flight No
    // TODO: could throw exception: invalid input
    public void chooseFlight(Passenger p) {
        System.out.println("Please choose a flight by entering flight number:");
        String flightNum = input.next();
        p.chooseFlight(flightNum);
    }


    // MODIFIES: p
    // EFFECTS: make booking by inputting name & ID
    // TODO: could throw exception: if the passenger's id has already made a book on this flight,
    //       even runService() will throw it to kill the method, but serviceApp() will catch it
    public void makeBook(Passenger p) {
        System.out.println("Please enter your name:");
        String name = input.next();
        System.out.println("Please enter your ID:");
        String id = input.next();
        Flight f = airlines.getFlight(p.getFlightNum());
//        for (Passenger i : f.getPassengerList()) {
//            if (i.getId().equals(id)) {
//                System.out.println("This ID has already made a book on this flight.");
//                return false;
//            }
//        }
        p.setName(name);
        p.setID(id);
        p.setTime(f.getTime());
//        return true;
    }


    // MODIFIES: p
    // EFFECTS: choose seat by choose row & col No
    public void chooseSeat(Passenger p) {
        System.out.println("Please choose your seat by entering row number and column number:");
        Flight f = airlines.getFlight(p.getFlightNum());
        printSeats(f);
        int row = input.nextInt();
        int col = input.nextInt();
        while (f.getSeat(row, col).equals("X")) {
            System.out.println("This seat is booked. Please choose another seat:");
            row = input.nextInt();
            col = input.nextInt();
        }
        p.chooseSeat(row, col);
    }

    // MODIFIES: p
    // EFFECTS: confirm the current booking
    public boolean confirmBook(Passenger p) {
        System.out.println("Confirm or Cancel?  Y/N");
        String decision = input.next();
        if (decision.equals("y")) {
            System.out.println("You have successfully booked a flight!");
            return true;
        } else {
            return false;
        }

    }

    // EFFECTS: showing user booking canceled
    public void cancelBook() {
        System.out.println("You have canceled your booking.");
    }


    // EFFECTS: print out the current seats layout of cabin
    public void printSeats(Flight f) {
        for (int i = 0; i <= (f.ROW - 1); i++) {
            for (int j = 0; j <= (f.COL - 1); j++) {
                System.out.print(f.getSeat(i, j));
            }
            System.out.println();
        }
    }

    // EFFECTS: print out all the passenger info after booking
    public void printPassengerInfo(Passenger p) {
        System.out.println("Passenger Name: " + p.getName());
        System.out.println("Passenger ID: " + p.getId());
        System.out.println("Flight No: " + p.getFlightNum()
                + ", " + "Destination: " + p.getDestination()
                + ", " + "Departure: " + p.getTime());
        System.out.println("Seat No: " + "Row: " + p.getRow() + ", " + "Column: " + p.getCol());
    }

    // EFFECTS: print out the brief flight info including flight No. & departure & destination
    public void printFlightInfo(Flight f) {
        System.out.println("Flight No: " + f.getFlightNum()
                + ", " + "Departure: " + f.getTime()
                + ", " + "Destination: " + f.getDestination());
    }

}
