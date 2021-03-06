package model;

import org.json.JSONObject;

import java.util.ArrayList;


// represent a passenger with name, id, departing time, destination, and chosen seat coordinates
public class Passenger {
    private String name;
    private String id;
    private int time;
    private String destination;
    private String flightNum;
    private int seatR;
    private int seatC;


    // REQUIRES: 100<=time<=2459
    // EFFECTS: initialize a passenger with given departing time, destination
    public Passenger(int time, String destination) {
        this.time = time;
        this.destination = destination;
    }

    // getters
    public String getName() {
        return this.name;
    }

    public String getID() {
        return this.id;
    }

    public int getTime() {
        return this.time;
    }

    public String getDestination() {
        return this.destination;
    }

    public int getRow() {
        return this.seatR;
    }

    public int getCol() {
        return this.seatC;
    }

    public String getFlightNum() {
        return this.flightNum;
    }


    // MODIFIES: this
    // EFFECTS: set the seat coordinate at given row and column number
    public void setSeat(int r, int c) {
        this.seatR = r;
        this.seatC = c;


    }

    // MODIFIES: this
    // EFFECTS: set the flight number at given flight number
    public void setFlight(String flightNum) {
        this.flightNum = flightNum;
    }

    // MODIFIES: this
    // EFFECTS: set the passenger's name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: set the passenger's id
    public void setID(String id) {
        this.id = id;
    }


    // REQUIRES: 100<=time<=2459
    // MODIFIES: this
    // EFFECTS: set the passenger's departing time
    public void setTime(int time) {
        this.time = time;
    }


    // EFFECTS: produce a list containing passenger's information
    public ArrayList<String> generatePassengerInfo() {
        ArrayList<String> passengerInfo = new ArrayList<>();
        String name = "Passenger Name: " + getName();
        String id = "Passenger ID: " + getID();
        String flightNum = "Flight No: " + getFlightNum();
        String destination = "Destination: " + getDestination();
        String departure = "Departure: " + getTime();
        String row = "Row: " + getRow();
        String col = "Column: " + getCol();
        passengerInfo.add(name);
        passengerInfo.add(id);
        passengerInfo.add(flightNum);
        passengerInfo.add(destination);
        passengerInfo.add(departure);
        passengerInfo.add(row);
        passengerInfo.add(col);
        return passengerInfo;
    }


    // TODO citation: code taken and modified from model package in JsonSerializationDemo
    // EFFECTS: generate a passenger as json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("ID", id);
        json.put("Time", time);
        json.put("Destination", destination);
        json.put("Flight No.", flightNum);
        json.put("Row", seatR);
        json.put("Col", seatC);
        return json;
    }

}
