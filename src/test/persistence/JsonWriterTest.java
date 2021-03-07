package persistence;

import model.Airlines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;


// TODO citation: code taken and modified from test package in JsonSerializationDemo
public class JsonWriterTest extends JsonTest {
//    private Airlines testAirlines;
//
//    @BeforeEach
//    public void setUp() {
//        testAirlines = new Airlines();
//    }
//
//    @Test
//    public void testWriterInvalidFile() {
//        try {
//            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
//            writer.open();
//            fail("IOException was expected");
//        } catch (IOException e) {
//            // pass
//        }
//    }
//
//    @Test
//    public void testWriterEmptyWorkroom() {
//        try {
//            JsonWriter writer = new JsonWriter("./data/testWriterEmptyBooking.json");
//            writer.open();
//            writer.write(testAirlines);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterEmptyBooking.json");
//            testAirlines = reader.readAirlines();
//            assertEquals("My work room", wr.getName());
//            assertEquals(0, wr.numThingies());
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
//
//    @Test
//    public void testWriterGeneralWorkroom() {
//        try {
//            wr.addThingy(new Thingy("saw", Category.METALWORK));
//            wr.addThingy(new Thingy("needle", Category.STITCHING));
//            JsonWriter writer = new JsonWriter("./data/testWriterBookings.json");
//            writer.open();
//            writer.write(wr);
//            writer.close();
//
//            JsonReader reader = new JsonReader("./data/testWriterBookings.json");
//            testAirlines = reader.readAirlines();
//            assertEquals("My work room", wr.getName());
//            List<Thingy> thingies = wr.getThingies();
//            assertEquals(2, thingies.size());
//            checkThingy("saw", Category.METALWORK, thingies.get(0));
//            checkThingy("needle", Category.STITCHING, thingies.get(1));
//
//        } catch (IOException e) {
//            fail("Exception should not have been thrown");
//        }
//    }
}
