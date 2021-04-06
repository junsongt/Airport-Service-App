package ui;


import exception.EmptySearchResultException;
import exception.InvalidInputException;
import exception.InvalidSeatException;
import exception.SameIDException;
import model.Airlines;
import model.Flight;
import model.Passenger;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// Represent an airport self service app
public class ServiceApp {
    private Scanner input;
    private Airlines airlines;
    private static final String JSON_STORE = "./data/airlines.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private Passenger customer;

    // EFFECTS: constructor with building today's airlines, and show main menu
    public ServiceApp() throws FileNotFoundException {
        this.airlines = new Airlines();
        buildAirlines();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        this.input = new Scanner(System.in);
        processMainMenu();
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


//===========================================================================================
// Main menu & Sub-menu

    // EFFECTS: show the main menu on app
    public void mainMenu() {
        System.out.println("Hello! Welcome to the airport!");
        System.out.println("Start service => Press 's'");
        System.out.println("Load the current booking info => Press 'l'");
        System.out.println("Save the current booking info => Press 'v'");
    }

    // EFFECTS: show the sub-menu within the startService functionality on app
    public void subMenu() {
        System.out.println("Start new booking => Press 'n'");
        System.out.println("Search a flight => Press 's'");
        System.out.println("Cancel your previous booking => Press 'c'");
        System.out.println("Back to main menu => Press 'b'");
    }


    // EFFECTS: process the different inputs to corresponding functionalities on main-menu
    public void processMainMenu() {
        boolean reset = false;
        while (!reset) {
            mainMenu();
            String command = input.next();
            if (command.equals("s")) {
                startService();
            } else if (command.equals("l")) {
                loadBooking();
            } else if (command.equals("v")) {
                saveBooking();
            } else if (command.equals("reset")) {
                reset = true;
            } else {
                System.out.println("Please enter 's' to start service");
            }
        }
    }

    // EFFECTS: process the different inputs to corresponding functionalities on sub-menu
    public void processSubMenu() {
        boolean backToMainMenu = false;
        while (!backToMainMenu) {
            subMenu();
            String command = input.next();
            if (command.equals("n")) {
//                try {
                newBooking();
//                } catch (SameIDException e) {
//                    System.out.println(e.getMessage());
//                } catch (InvalidInputException e) {
//                    System.out.println(e.getMessage());
//                }
            } else if (command.equals("s")) {
                justSearch();
            } else if (command.equals("c")) {
                cancelBooking();
            } else if (command.equals("b")) {
                backToMainMenu = true;
            } else {
                System.out.println("Please press the corresponding button");
            }
        }
    }


    // EFFECTS: return to the main menu
    public void backToMainMenuHint() {
        System.out.println("Back to main menu => Press 'b'");
        String command = input.next();
        while (!command.equals("b")) {
            System.out.println("Please press 'b' to get back to the main menu");
            command = input.next();
        }
    }

    // EFFECTS: return to the sub menu
    public void backToSubMenuHint() {
        System.out.println("Back to sub menu => Press 'b'");
        String command = input.next();
        while (!command.equals("b")) {
            System.out.println("Please press 'b' to get back to the sub menu");
            command = input.next();
        }
    }


//============================================================================================
// Main menu functionalities


    // EFFECTS: just search the flight
    public void justSearch() {
        try {
            search();
            backToSubMenuHint();
        } catch (EmptySearchResultException e) {
            System.out.println(e.getMessage());
            backToSubMenuHint();
        }
    }

    // MODIFIES: this
    // EFFECTS: start the actual service by going into a sub-menu
    public void startService() {
        processSubMenu();
    }

    // TODO citation: code taken and modified from WorkRoomApp.java class in JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: load the total booking in airlines previously saved from json file
    public void loadBooking() {
        try {
            airlines = jsonReader.readAirlines();
            System.out.println("Loaded previously saved booking from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        } finally {
            backToMainMenuHint();
        }
    }

    // TODO citation: code taken and modified from WorkRoomApp.java class in JsonSerializationDemo
    // EFFECTS: save the current total booking in airlines to json file
    public void saveBooking() {
        try {
            jsonWriter.open();
            jsonWriter.write(airlines);
            jsonWriter.close();
            System.out.println("Saved current booking to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        } finally {
            backToMainMenuHint();
        }
    }


//=============================================================================================
// Sub-menu functionalities


    // MODIFIES: this
    // EFFECTS: start a new booking by creating passenger info
    public void newBooking() {
        try {
            search();
            chooseFlight();
            makeBook();
            chooseSeat();
            confirmBook();
            backToSubMenuHint();
        } catch (EmptySearchResultException e) {
            System.out.println(e.getMessage());
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        } catch (SameIDException e) {
            System.out.println(e.getMessage());
        }
    }

    // MODIFIES: this
    // EFFECTS: construct a new passenger with the given time and destination user inputs
    public void customerInit(int time, String destination) {
        customer = new Passenger(time, destination);
    }

    // EFFECTS: do the search job by user inputs
    public void search() throws EmptySearchResultException {
        System.out.println("Please input your departing time: (format: 900 for 9:00 am; 1600 for 4:00 pm)");
        int time = input.nextInt();
        while (!(100 <= time && time <= 2459)) {
            System.out.println("Invalid time input! Please re-enter your time");
            time = input.nextInt();
        }
        System.out.println("Please input your destination:");
        String destination = input.next();
        customerInit(time, destination);
        ArrayList<Flight> results = searchFlight(time, destination);
        if (results.isEmpty()) {
            throw new EmptySearchResultException();
        } else {
            for (Flight f : results) {
                printFlightInfo(f);
            }
        }

    }

    // EFFECTS: auto-search flight with given time & destination. If found, show all found, else false
    public ArrayList<Flight> searchFlight(int time, String destination) {
        ArrayList<Flight> results = new ArrayList<>();
        for (Flight f : airlines.getFlightList()) {
            if ((time <= f.getTime()) && (destination.equals(f.getDestination()))) {
                results.add(f);
            }
        }
        return results;
    }


    // MODIFIES: this
    // EFFECTS: choose flight by flight No
    public void chooseFlight() throws InvalidInputException {
        System.out.println("Please choose a flight by entering flight number:");
        String flightNum = input.next();
        ArrayList<Flight> results = searchFlight(customer.getTime(), customer.getDestination());
        boolean check = false;
        for (Flight f : results) {
            if (f.getFlightNum().equals(flightNum)) {
                customer.setFlight(flightNum);
                check = check || true;
            }
        }
        if (!check) {
            throw new InvalidInputException();

        }

    }


    // MODIFIES: this
    // EFFECTS: make booking by inputting name & ID
    public void makeBook() throws SameIDException {
        System.out.println("Please enter your name:");
        String name = input.next();
        System.out.println("Please enter your ID:");
        String id = input.next();
        Flight f = airlines.findFlight(customer.getFlightNum());
        for (Passenger i : f.getPassengerList()) {
            if (i.getID().equals(id)) {
                throw new SameIDException();
            }
        }
        customer.setName(name);
        customer.setID(id);
        customer.setTime(f.getTime());
    }


    // MODIFIES: this
    // EFFECTS: choose seat by choose row & col No
    public void chooseSeat() {
        System.out.println("Please choose your seat by entering row number and column number:");
        Flight f = airlines.findFlight(customer.getFlightNum());
        printSeats(f);
        int row = input.nextInt();
        int col = input.nextInt();
        try {
            while (f.isSeatOccupied(row, col)) {
                System.out.println("This seat is booked. Please choose another seat:");
                row = input.nextInt();
                col = input.nextInt();
            }
            customer.setSeat(row, col);
        } catch (InvalidSeatException e) {
            System.out.println(e.getMessage());
            chooseSeat();
        }


    }

    // MODIFIES: this
    // EFFECTS: confirm the current booking
    public void confirmBook() {
        System.out.println("Confirm booking?  Y/N");
        String decision = input.next();
        while ((!decision.equals("y")) && (!decision.equals("n"))) {
            System.out.println("Confirm booking?  Y/N");
            decision = input.next();
        }
        String flightNum = customer.getFlightNum();
        if (decision.equals("y")) {
            System.out.println("You have successfully booked a flight!");
            airlines.findFlight(flightNum).addPassenger(customer);
            airlines.findFlight(flightNum).setSeat(customer.getRow(), customer.getCol());
            printPassengerInfo(customer);
        } else {
            System.out.println("You have canceled your booking");
        }
    }


    // MODIFIES: this
    // EFFECTS: showing user booking canceled
    public void cancelBooking() {
        ArrayList<Passenger> results = searchBooking();
        if (results.isEmpty()) {
            System.out.println("No such passenger found!");
        } else {
            System.out.println("Select the booking you want to cancel by entering the flight No.");
            String flightNum = input.next();
            System.out.println("Confirm cancel?  Y/N");
            String decision = input.next();
            if (decision.equals("y")) {
                for (Passenger p : results) {
                    if (p.getFlightNum().equals(flightNum)) {
                        airlines.findFlight(flightNum).getPassengerList().remove(p);
                        airlines.findFlight(flightNum).releaseSeat(p.getRow(), p.getCol());
                    }
                }
                System.out.println("You have successfully canceled your flight!");
            }
        }
        backToSubMenuHint();
    }


    // EFFECTS: search the previous booking(s) by passenger name & ID
    public ArrayList<Passenger> searchBooking() {
        System.out.println("Please enter your name:");
        String name = input.next();
        System.out.println("Please enter your id");
        String id = input.next();
        ArrayList<Passenger> results = new ArrayList<>();
        for (Flight f : airlines.getFlightList()) {
            Passenger p = f.findPassenger(name, id);
            if (p != null) {
                results.add(p);
                printPassengerInfo(p);
            }
        }
        return results;
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
        System.out.println("Passenger ID: " + p.getID());
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
