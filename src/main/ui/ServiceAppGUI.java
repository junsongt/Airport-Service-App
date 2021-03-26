package ui;

import model.Airlines;
import model.Flight;
import model.Passenger;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.panels.BookingPanel;
import ui.panels.CancelPanel;
import ui.panels.HomePanel;
import ui.panels.SearchPanel;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ServiceAppGUI extends JFrame {
    private Scanner input;
    private Airlines airlines;
    private static final String JSON_STORE = "./data/airlines.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    private JTabbedPane sidebar;

    private Passenger customer;


    // EFFECTS: constructor with generating today's airlines,
    // preparing JSON reader & writer,
    // and show main window
    public ServiceAppGUI() throws FileNotFoundException {

        this.airlines = new Airlines();
        buildAirlines();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        loadMainWindow();

    }

    // MODIFIES: this
    // EFFECTS: TODO
    public void loadMainWindow() {

        this.setTitle("Airport Service");
        this.setPreferredSize(new Dimension(500, 500));

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadServicePanels();
        add(sidebar);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: TODO
    private void loadServicePanels() {
        JPanel homePanel = new HomePanel(this);
        JPanel bookingPanel = new BookingPanel(this);
        JPanel searchPanel = new SearchPanel(this);
        JPanel cancelPanel = new CancelPanel(this);

        sidebar.addTab("Home", homePanel);
        sidebar.addTab("New Booking", bookingPanel);
//        sidebar.setTitleAt(0,"New Booking");
        sidebar.addTab("Search Flight", searchPanel);
//        sidebar.setTitleAt(1, "Search Flight");
        sidebar.addTab("Cancel Booking", cancelPanel);
//        sidebar.setTitleAt(2, "Cancel Booking");
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

    public Airlines getAirlines() {
        return airlines;
    }

    public Passenger getCustomer() {
        return customer;
    }

//============================================================================================
// Save & Load

    // TODO citation: code taken and modified from WorkRoomApp.java class in JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: load the total booking in airlines previously saved from json file
    public void loadBooking() {
        try {
            airlines = jsonReader.readAirlines();
            System.out.println("Loaded previously saved booking from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
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
        }
    }


//=============================================================================================
// Sub-menu functionalities

    // EFFECTS: auto-search flight with given time & destination. If found, show all found, else false
    public ArrayList<String> searchFlight(int time, String destination) {
        ArrayList<String> results = new ArrayList<>();
        for (Flight f : airlines.getFlightList()) {
            if ((time <= f.getTime()) && (destination.equals(f.getDestination()))) {
                results.add(f.generateFlightInfo());
            }
        }
        if (results.isEmpty()) {
            return new ArrayList<>();
        } else {
            return results;
        }
    }


    // MODIFIES: this
    // EFFECTS: start a new booking by creating passenger info
//    public void newBooking() {
//        Passenger me = createPassenger(time, destination);
//
//        chooseFlight(me);
//        String flightNum = me.getFlightNum();
//        makeBook(me);
//        chooseSeat(me);
//        confirmBook(me, flightNum);
//        backToSubMenuHint();
//    }

    // EFFECTS: construct a new passenger with the given time and destination user inputs
    // TODO: could throw exception: invalid input (implement later phase)
    public void createPassenger(int time, String destination) {
        customer = new Passenger(time, destination);
    }

    


    // MODIFIES: p
    // EFFECTS: make booking by inputting name & ID
    // TODO: could throw exception: if the passenger's id has already made a book on this flight,
    //       even runService() will throw it to kill the method, but serviceApp() will catch it
    //       (implement later phase)
    public void makeBook(String name, String id) {
        Flight f = airlines.getFlight(customer.getFlightNum());
//        for (Passenger i : f.getPassengerList()) {
//            if (i.getId().equals(id)) {
//                System.out.println("This ID has already made a book on this flight.");
//                return false;
//            }
//        }
        customer.setName(name);
        customer.setID(id);
        customer.setTime(f.getTime());
//        return true;
    }


//    // MODIFIES: p
//    // EFFECTS: choose seat by choose row & col No
//    public void chooseSeat(Passenger p) {
//        System.out.println("Please choose your seat by entering row number and column number:");
//        Flight f = airlines.getFlight(p.getFlightNum());
//        printSeats(f);
//        int row = input.nextInt();
//        int col = input.nextInt();
//        while (f.isSeatOccupied(row, col)) {
//            System.out.println("This seat is booked. Please choose another seat:");
//            row = input.nextInt();
//            col = input.nextInt();
//        }
//        p.chooseSeat(row, col);
//    }

    // MODIFIES: this, p
    // EFFECTS: confirm the current booking
    // TODO: could throw exception: invalid input (implement later phase)
    public void confirmBook(Passenger p) {
        String flightNum = p.getFlightNum();
        airlines.getFlight(flightNum).addPassenger(p);
        airlines.getFlight(flightNum).setSeat(p.getRow(), p.getCol());

    }




    // EFFECTS: search the previous booking(s) by passenger name & ID
    public ArrayList<ArrayList<String>> searchBooking(String name, String id) {
        ArrayList<ArrayList<String>> results = new ArrayList<>();
        for (Flight f : airlines.getFlightList()) {
            Passenger p = f.findPassenger(name, id);
            if (p != null) {
                results.add(p.generatePassengerInfo());

            }
        }
        return results;
    }


    // MODIFIES: this
    // EFFECTS: showing user booking canceled
    public void cancelBooking(String flightNum, String name, String id) {
        Flight flight = airlines.getFlight(flightNum);
        Passenger p = flight.findPassenger(name, id);

        flight.getPassengerList().remove(p);
        flight.releaseSeat(p.getRow(), p.getCol());

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
