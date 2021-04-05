package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


// represent airlines with different flights
public class Airlines {

    private ArrayList<Flight> flightList;

    // EFFECTS: initialize airlines with an empty flight list
    public Airlines() {
        this.flightList = new ArrayList<>();

    }

    // getters
    // EFFECTS: get the flight by given flight number
    public Flight findFlight(String flightNum) {
        for (Flight f : flightList) {
            if (f.getFlightNum().equals(flightNum)) {
                return f;
            }
        }
        return null;
    }

    public ArrayList<Flight> getFlightList() {
        return this.flightList;
    }



    // EFFECTS: check if the given flight is duplicate though different object
    public boolean checkDuplicate(Flight f) {
        for (Flight flight : flightList) {
            if ((flight.getFlightNum().equals(f.getFlightNum()))
                    && (flight.getTime() == f.getTime())) {
                return true;
            }
        }
        return false;
    }


    // MODIFIES: this
    // EFFECTS: add the flight if it is not in the flight list
    public void addFlight(Flight f) {
        if (!checkDuplicate(f)) {
            flightList.add(f);
        }
    }






    // TODO citation: code taken and modified from model package in JsonSerializationDemo
    // EFFECTS: generate airlines as json object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Flights", flightListToJson());
        return json;
    }

    // TODO citation: code taken and modified from model package in JsonSerializationDemo
    // EFFECTS: generate flight list as json array
    public JSONArray flightListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Flight f : flightList) {
            jsonArray.put(f.toJson());
        }
        return jsonArray;
    }


}
