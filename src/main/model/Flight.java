package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;


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


    // MODIFIES: this
    // EFFECTS: set the seat as open("O") at given row index and column index
    public void releaseSeat(int r, int c) {
        this.seats.get(r).set(c, "O");
    }


    // EFFECTS: find the passenger in this flight by given passenger's name & ID
    public Passenger findPassenger(String name, String id) {
        for (Passenger p : passengerList) {
            if (p.getName().equals(name) && p.getID().equals(id)) {
                return p;
            }
        }
        return null;
    }

    // EFFECTS: determine if the seat on this flight is occupied
    public boolean isSeatOccupied(int r, int c) {
        return seats.get(r).get(c).equals("X");
    }







    // TODO citation: code taken and modified from model package in JsonSerializationDemo
    // EFFECTS: generate a flight as json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Flight No.", flightNum);
        json.put("Departure", time);
        json.put("Destination", destination);
        json.put("Seats", seatsToJson());
        json.put("Passengers", passengersToJson());
        return json;
    }



    // EFFECTS: generate seats as json array of json array
    public JSONArray seatsToJson() {
        JSONArray jsonSeats = new JSONArray();
        for (ArrayList<String> row : seats) {
            JSONArray jsonRow = new JSONArray();
            for (String seat : row) {
                jsonRow.put(seat);
            }
            jsonSeats.put(jsonRow);
        }
        return jsonSeats;
    }



    // TODO citation: code taken and modified from model package in JsonSerializationDemo
    // EFFECTS: generate passenger list as json array
    public JSONArray passengersToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Passenger p : passengerList) {
            jsonArray.put(p.toJson());
        }
        return jsonArray;
    }
}
