package persistence;

import exception.InvalidSeatException;
import model.Airlines;
import model.Flight;
import model.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;


// TODO citation: code taken and modified from test package in JsonSerializationDemo
public class JsonWriterTest extends JsonTest {
    private Airlines testAirlines;
    private Flight testFlight;

    @BeforeEach
    public void setUp() {
        testAirlines = new Airlines();
        testFlight = new Flight("CA110", 800, "vancouver");
        testAirlines.addFlight(testFlight);
    }

    @Test
    public void testWriterInvalidFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    public void testWriterEmptyBookingAirlines() {
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBooking.json");
            writer.open();
            writer.write(testAirlines);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyBooking.json");
            Airlines newAirlines = reader.readAirlines();
            assertEquals(1, newAirlines.getFlightList().size());
            checkEmptyBooking(newAirlines);
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    public void testWriterGeneralFlight() {
        try {
            Passenger testPassenger = new Passenger(800, "vancouver");
            testPassenger.setName("Jason");
            testPassenger.setID("z6k8l");
            testPassenger.setFlight("CA110");
            testPassenger.setSeat(0,0);
            testFlight.setSeat(0,0);
            testFlight.addPassenger(testPassenger);
            JsonWriter writer = new JsonWriter("./data/testWriterBookings.json");
            writer.open();
            writer.write(testAirlines);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterBookings.json");
            Airlines newAirlines = reader.readAirlines();
            assertEquals(1, newAirlines.getFlightList().size());

            Flight newFlight = newAirlines.getFlightList().get(0);

            assertEquals(1, newFlight.getPassengerList().size());
            checkPassenger("Jason", "z6k8l", newFlight.getPassengerList().get(0));
            assertTrue(newFlight.isSeatOccupied(0,0));

        } catch (InvalidSeatException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
