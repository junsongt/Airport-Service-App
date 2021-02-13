package model;

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
    public Flight getFlight(String flightNum) {
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


}
