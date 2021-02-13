package model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


// represent a flight with flight num, departing time, destination, passenger list, seats layout
public class Flight {
    public static final int ROW = 5;  // represent the cabin # of rows
    public static final int COL = 2;  // represent the cabin # of column
    private ArrayList<ArrayList<String>> seats;
    private ArrayList<Passenger> passengerList;
    private String flightNum;
    private int time;
    private String destination;


    // EFFECTS: initialize the flight with an empty passengerList, and an all-open seat layout table
    public Flight(String flightNum, int time, String destination) {
        this.flightNum = flightNum;
        this.time = time;
        this.destination = destination;
        this.passengerList = new ArrayList<>();
        this.seats = new ArrayList<>();
        for (int i = 0; i <= (ROW - 1); i++) {
            ArrayList<String> row = new ArrayList<>();
            for (int j = 0; j <= (COL - 1); j++) {
                row.add("O");
            }
            this.seats.add(row);
        }

    }


    //getters
    public String getFlightNum() {
        return this.flightNum;
    }

    public int getTime() {
        return this.time;
    }

    public String getDestination() {
        return this.destination;
    }

    public ArrayList<Passenger> getPassengerList() {
        return this.passengerList;
    }

    public String getSeat(int r, int c) {
        return seats.get(r).get(c);
    }

    public ArrayList<ArrayList<String>> getSeats() {
        return this.seats;
    }



    // MODIFIES: this
    // EFFECTS: add the passenger if it is not in the passenger list
    public void addPassenger(Passenger p) {
        if (!this.passengerList.contains(p)) {
            this.passengerList.add(p);
        }
    }

    // REQUIRES: 0<=row index<=ROW, 0<=column index<=COL
    // MODIFIES: this
    // EFFECTS: set the seat as chosen("X") at given row index and column index
    public void setSeat(int r, int c) {
        this.seats.get(r).set(c, "X");
    }



}
