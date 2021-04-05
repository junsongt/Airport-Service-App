package persistence;

import exception.InvalidSeatException;
import model.Airlines;
import model.Flight;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

// TODO citation: code taken and modified from test package in JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Airlines testAirlines = reader.readAirlines();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyBookingAirlines() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyBooking.json");
        try {
            Airlines testAirlines = reader.readAirlines();
            assertFalse(testAirlines.getFlightList().isEmpty());
            checkEmptyBooking(testAirlines);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderBookingAirlinesSuccess() {
        JsonReader reader = new JsonReader("./data/testReaderBookings.json");
        try {
            Airlines testAirlines = reader.readAirlines();
            assertFalse(testAirlines.getFlightList().isEmpty());

            Flight testFlight = testAirlines.getFlightList().get(0);
            checkPassenger("Jason", "z6k8l", testFlight.getPassengerList().get(0));
            assertTrue(testFlight.isSeatOccupied(0,0));
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (InvalidSeatException e) {
            fail("No exception should be thrown");
        }
    }
}
