package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PassengerTest {
    private Passenger testPassenger;

    @BeforeEach
    public void setUp() {
        this.testPassenger = new Passenger(900, "vancouver");
    }

    @Test
    public void testConstructor() {
        assertEquals(900, testPassenger.getTime());
        assertEquals("vancouver", testPassenger.getDestination());

    }

    @Test
    public void testSetSeat() {
        testPassenger.setSeat(2,1);
        assertEquals(2, testPassenger.getRow());
        assertEquals(1, testPassenger.getCol());
    }

    @Test
    public void testSetFlight() {
        testPassenger.setFlight("CA210");
        assertEquals("CA210", testPassenger.getFlightNum());
    }

    @Test
    public void testChangeName() {
        testPassenger.setName("Jason");
        assertEquals("Jason", testPassenger.getName());
        testPassenger.setName("Jack");
        assertEquals("Jack", testPassenger.getName());
    }

    @Test
    public void testChangeID() {
        testPassenger.setID("z6k8l");
        assertEquals("z6k8l", testPassenger.getID());
        testPassenger.setID("a1b2c");
        assertEquals("a1b2c", testPassenger.getID());
    }

    @Test
    public void testChangeTime() {
        testPassenger.setTime(700);
        assertEquals(700, testPassenger.getTime());
        testPassenger.setTime(2459);
        assertEquals(2459, testPassenger.getTime());
    }


    @Test
    public void testToJson() {
        testPassenger.setName("Jason");
        testPassenger.setID("z6k8l");
        testPassenger.setFlight("CA110");
        testPassenger.setSeat(0,0);
        JSONObject json = new JSONObject();
        json.put("Name", "Jason");
        json.put("ID", "z6k8l");
        json.put("Time", 900);
        json.put("Destination", "vancouver");
        json.put("Flight No.", "CA110");
        json.put("Row", 0);
        json.put("Col", 0);
        assertEquals(json.toString(), testPassenger.toJson().toString());
    }


    @Test
    public void testGeneratePassengerInfo() {
        testPassenger.setName("Jason");
        testPassenger.setID("z6k8l");
        testPassenger.setFlight("CA110");
        testPassenger.setSeat(0,0);
        assertEquals(7, testPassenger.generatePassengerInfo().size());
        assertTrue(testPassenger.generatePassengerInfo().contains("Passenger Name: Jason"));
        assertTrue(testPassenger.generatePassengerInfo().contains("Passenger ID: z6k8l"));
        assertTrue(testPassenger.generatePassengerInfo().contains("Flight No: CA110"));
        assertTrue(testPassenger.generatePassengerInfo().contains("Destination: vancouver"));
        assertTrue(testPassenger.generatePassengerInfo().contains("Departure: 900"));
        assertTrue(testPassenger.generatePassengerInfo().contains("Row: 0"));
        assertTrue(testPassenger.generatePassengerInfo().contains("Column: 0"));
    }
}