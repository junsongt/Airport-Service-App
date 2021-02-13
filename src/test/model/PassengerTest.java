package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PassengerTest {
    private Passenger testPassenger;

    @BeforeEach
    public void setUp() {
        this.testPassenger = new Passenger(900, "Vancouver");
    }

    @Test
    public void testConstructor() {
        assertEquals(900, testPassenger.getTime());
        assertEquals("Vancouver", testPassenger.getDestination());

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
    public void testSetID() {
        testPassenger.setID("z6k8l");
        assertEquals("z6k8l", testPassenger.getId());
        testPassenger.setID("a1b2c");
        assertEquals("a1b2c", testPassenger.getId());

    }

}