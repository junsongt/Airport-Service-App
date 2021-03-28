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


public class ServiceAppGUI extends JFrame {

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
    // EFFECTS: load the main window for app to run
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
    // EFFECTS: load the tabbed pane embed in the main window frame
    private void loadServicePanels() {
        JPanel homePanel = new HomePanel(this);
        JPanel bookingPanel = new BookingPanel(this);
        JPanel searchPanel = new SearchPanel(this);
        JPanel cancelPanel = new CancelPanel(this);

        sidebar.addTab("Home", homePanel);
        sidebar.addTab("New Booking", bookingPanel);
        sidebar.addTab("Search Flight", searchPanel);
        sidebar.addTab("Cancel Booking", cancelPanel);
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

    // getters
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
// Search functionalities

    // EFFECTS: auto-search flight with given time & destination. show all the results
    public ArrayList<String> searchFlight(int time, String destination) {
        ArrayList<String> results = new ArrayList<>();
        for (Flight f : airlines.getFlightList()) {
            if ((time <= f.getTime()) && (destination.equals(f.getDestination()))) {
                results.add(f.generateFlightInfo());
            }
        }
        return results;
    }


    // EFFECTS: search the previous booking(s) by passenger's name & ID
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


//=================================================================================================
// Other functionalities

    // MODIFIES: this
    // EFFECTS: construct a new passenger with the given time and destination user inputs
    // TODO: could throw exception: invalid input (implement later)
    public void createPassenger(int time, String destination) {
        customer = new Passenger(time, destination);
    }

    


    // MODIFIES: this
    // EFFECTS: make booking by given passenger's name & ID, and set departing time
    // TODO: could throw exception: if the passenger's id has already made a book on this flight,
    //       even runService() will throw it to kill the method, but serviceApp() will catch it
    //       (implement later)
    public void makeBook(String name, String id) {
        Flight f = airlines.getFlight(customer.getFlightNum());
        customer.setName(name);
        customer.setID(id);
        customer.setTime(f.getTime());
    }


    // MODIFIES: this
    // EFFECTS: choose seat by setting row & col if seat available and return true, else false
    public boolean chooseSeat(int row, int col) {
        Flight f = airlines.getFlight(customer.getFlightNum());

        if (!f.isSeatOccupied(row, col)) {
            customer.setSeat(row, col);
            return true;
        } else {
            return false;
        }
    }

    // MODIFIES: this
    // EFFECTS: confirm the current booking
    // TODO: could throw exception: invalid input (implement later)
    public void confirmBook(Passenger p) {
        String flightNum = p.getFlightNum();
        airlines.getFlight(flightNum).addPassenger(p);
        airlines.getFlight(flightNum).setSeat(p.getRow(), p.getCol());
    }




    // MODIFIES: this
    // EFFECTS: cancel user's booking and release seat
    public void cancelBooking(String flightNum, String name, String id) {
        Flight flight = airlines.getFlight(flightNum);
        Passenger p = flight.findPassenger(name, id);

        flight.getPassengerList().remove(p);
        flight.releaseSeat(p.getRow(), p.getCol());
    }





}
