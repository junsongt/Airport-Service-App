package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public void testChooseSeat() {
        testPassenger.chooseSeat(2,1);
        assertEquals(2, testPassenger.getRow());
        assertEquals(1, testPassenger.getCol());
    }

    @Test
    public void testChooseFlight() {
        testPassenger.chooseFlight("CA210");
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
        assertEquals("z6k8l", testPassenger.getId());
        testPassenger.setID("a1b2c");
        assertEquals("a1b2c", testPassenger.getId());
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
        testPassenger.chooseFlight("CA110");
        testPassenger.chooseSeat(0,0);
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

}