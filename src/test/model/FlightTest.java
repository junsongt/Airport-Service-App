package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightTest {
    private Flight testFlight;
    private Passenger testPassenger;

    @BeforeEach
    public void setUp() {
        this.testFlight = new Flight("CA210", 800, "shanghai");
        this.testPassenger = new Passenger(800, "shanghai");
    }


    @Test
    public void testConstructor() {
        assertEquals("CA210", testFlight.getFlightNum());
        assertEquals(800, testFlight.getTime());
        assertEquals("shanghai", testFlight.getDestination());
        assertTrue(testFlight.getPassengerList().isEmpty());
        assertEquals("O", testFlight.getSeat(1,1));
        assertEquals(Flight.ROW, testFlight.getSeats().size());
        assertEquals(Flight.COL, testFlight.getSeats().get(0).size());
    }


    @Test
    public void testAddPassengerSuccess() {
        testFlight.addPassenger(testPassenger);
        assertFalse(testFlight.getPassengerList().isEmpty());
        assertTrue(testFlight.getPassengerList().contains(testPassenger));
        assertEquals(1, testFlight.getPassengerList().size());
    }

    @Test
    public void testAddPassengerFailure() {
        testFlight.addPassenger(testPassenger);
        testFlight.addPassenger(testPassenger);
        assertTrue(testFlight.getPassengerList().contains(testPassenger));
        assertEquals(1, testFlight.getPassengerList().size());

    }

    @Test
    public void testSetSeat() {
        testPassenger.setSeat(2,1);
        testFlight.setSeat(2,1);
        assertEquals("X", testFlight.getSeat(2,1));
        assertEquals("O", testFlight.getSeat(1,1));
    }


    @Test
    public void testReleaseSeat() {
        testPassenger.setSeat(2,1);
        testFlight.setSeat(2,1);
        assertEquals("X", testFlight.getSeat(2,1));
        testFlight.releaseSeat(2,1);
        assertEquals("O", testFlight.getSeat(2,1));
    }

    @Test
    public void testFindPassenger() {
        testFlight.addPassenger(testPassenger);
        testPassenger.setName("Jason");
        testPassenger.setID("z6k8l");
        assertEquals(testPassenger, testFlight.findPassenger("Jason", "z6k8l"));
        assertNull(testFlight.findPassenger("Jason", "12345"));
        assertNull(testFlight.findPassenger("Jack", "z6k8l"));
        assertNull(testFlight.findPassenger("Jack", "12345"));
    }

    @Test
    public void testIsSeatOccupied() {
        testFlight.setSeat(2,1);
        assertTrue(testFlight.isSeatOccupied(2,1));
        assertFalse(testFlight.isSeatOccupied(0, 0));
    }


    @Test
    public void testToJson() {
        JSONObject json = new JSONObject();
        json.put("Flight No.", "CA210");
        json.put("Departure", 800);
        json.put("Destination", "shanghai");
        json.put("Seats", testFlight.seatsToJson());
        json.put("Passengers", testFlight.passengersToJson());
        assertEquals(json.toString(), testFlight.toJson().toString());
    }


    @Test
    public void testSeatToJson() {
        JSONArray jsonSeats = new JSONArray();
        for (ArrayList<String> row : testFlight.getSeats()) {
            JSONArray jsonRow = new JSONArray();
            for (String seat : row) {
                jsonRow.put(seat);
            }
            jsonSeats.put(jsonRow);
        }
        assertEquals(jsonSeats.toString(), testFlight.seatsToJson().toString());

    }

    @Test
    public void testPassengerToJson() {
        testFlight.addPassenger(testPassenger);
        JSONArray jsonArray = new JSONArray();
        for (Passenger p : testFlight.getPassengerList()) {
            jsonArray.put(p.toJson());
        }
        assertEquals(jsonArray.toString(), testFlight.passengersToJson().toString());

    }


    @Test
    public void testGenerateFlightInfo() {
        assertEquals("Flight No: CA210, Departure: 800, Destination: shanghai",
                testFlight.generateFlightInfo());
    }
}
