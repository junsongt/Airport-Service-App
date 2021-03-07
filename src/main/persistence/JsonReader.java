package persistence;


import model.Airlines;
import model.Flight;
import model.Passenger;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// TODO citation: code taken and modified from JsonReader.java class in JsonSerializationDemo
// Represents a reader that reads airlines from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Airlines from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Airlines readAirlines() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return generateAirlines(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: generate airlines from JSON object(saved one)
    private Airlines generateAirlines(JSONObject jsonObject) {
        Airlines airlines = new Airlines();
        addFlightList(airlines, jsonObject);
        return airlines;
    }

    // MODIFIES: airlines
    // EFFECTS: parses flights from JSON object and adds them to airlines
    private void addFlightList(Airlines airlines, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("Flights");
        for (Object json : jsonArray) {
            JSONObject flight = (JSONObject) json;
            addFlight(airlines, flight);
        }
    }

    // MODIFIES: airlines
    // EFFECTS: parses a flight from JSON object and adds it to airlines
    private void addFlight(Airlines airlines, JSONObject jsonObject) {
        String flightNum = jsonObject.getString("Flight No.");
        int time = jsonObject.getInt("Departure");
        String destination = jsonObject.getString("Destination");
        Flight flight = new Flight(flightNum, time, destination);

        JSONArray seats = jsonObject.getJSONArray("Seats");
        JSONArray passengerList = jsonObject.getJSONArray("Passengers");

        addSeats(flight, seats);
        addPassengerList(flight, passengerList);
        airlines.addFlight(flight);
    }



    // MODIFIES: flight
    // EFFECTS: parses seats from JSONArray and set them to the open seats in flight
    private void addSeats(Flight flight, JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++) {
            for (int j = 0; j < jsonArray.getJSONArray(i).length(); j++) {
                String jsonSeat = jsonArray.getJSONArray(i).getString(j);
                flight.getSeats().get(i).set(j, jsonSeat);
            }
        }
    }

    // MODIFIES: flight
    // EFFECTS: parses passengers from JSONArray and adds them to flight
    private void addPassengerList(Flight flight, JSONArray jsonArray) {
        for (Object json : jsonArray) {
            JSONObject passenger = (JSONObject) json;
            addPassenger(flight, passenger);
        }
    }


    // MODIFIES: flight
    // EFFECTS: generate a passenger object from JSON object and adds it to flight
    private void addPassenger(Flight flight, JSONObject jsonObject) {
        int time = jsonObject.getInt("Time");
        String destination = jsonObject.getString("Destination");
        Passenger passenger = new Passenger(time, destination);
        String name = jsonObject.getString("Name");
        String id = jsonObject.getString("ID");
        String flightNum = jsonObject.getString("Flight No.");
        int seatR = jsonObject.getInt("Row");
        int seatC = jsonObject.getInt("Col");
        passenger.setName(name);
        passenger.setID(id);
        passenger.chooseSeat(seatR, seatC);
        passenger.chooseFlight(flightNum);
        flight.addPassenger(passenger);
    }

}
