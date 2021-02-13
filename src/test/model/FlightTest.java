package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FlightTest {
    private Flight testFlight;
    private Passenger testPassenger;

    @BeforeEach
    public void setUp() {
        this.testFlight = new Flight("CA210", 800, "Shanghai");
        this.testPassenger = new Passenger(800, "Shanghai");
    }


    @Test
    public void testConstructor() {
        assertEquals("CA210", testFlight.getFlightNum());
        assertEquals(800, testFlight.getTime());
        assertEquals("Shanghai", testFlight.getDestination());
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
        testPassenger.chooseSeat(2,1);
        testFlight.setSeat(2,1);
        assertEquals("X", testFlight.getSeat(2,1));
        assertEquals("O", testFlight.getSeat(1,1));


    }
}
