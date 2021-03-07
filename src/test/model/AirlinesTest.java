package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AirlinesTest {
    private Airlines testAirlines;


    @BeforeEach
    public void setUp() {
        this.testAirlines = new Airlines();
    }

    @Test
    public void testConstructor() {
        assertTrue(testAirlines.getFlightList().isEmpty());
    }

    @Test
    public void testGetFlight() {
        Flight f1 = new Flight("CA210", 800, "Shanghai");
        Flight f2 = new Flight("CA211", 2100, "Vancouver");
        testAirlines.addFlight(f1);
        testAirlines.addFlight(f2);
        assertEquals(f2, testAirlines.getFlight("CA211"));
        assertNull(testAirlines.getFlight("CA212"));
    }




    @Test
    public void testCheckDuplicate() {
        Flight f1 = new Flight("CA210", 800, "Shanghai");
        Flight f2 = new Flight("CA210", 800, "Shanghai");
        Flight f3 = new Flight("CA211", 800, "Shanghai");
        Flight f4 = new Flight("CA210", 900, "Shanghai");
        Flight f5 = new Flight("CA210", 800, "Vancouver");
        testAirlines.addFlight(f1);
        assertTrue(testAirlines.checkDuplicate(f2));
        assertFalse(testAirlines.checkDuplicate(f3));
        assertFalse(testAirlines.checkDuplicate(f4));
        assertTrue(testAirlines.checkDuplicate(f5));

    }


    @Test
    public void testAddFlight() {
        Flight f1 = new Flight("CA210", 800, "Shanghai");
        Flight f2 = new Flight("CA211", 2100, "Vancouver");
        Flight f3 = new Flight("CA210", 800, "Shanghai");
        testAirlines.addFlight(f1);
        assertEquals(1, testAirlines.getFlightList().size());
        testAirlines.addFlight(f2);
        assertEquals(2, testAirlines.getFlightList().size());
        testAirlines.addFlight(f3);
        assertEquals(2, testAirlines.getFlightList().size());
    }


    @Test
    public void testToJson() {
        Flight f1 = new Flight("CA210", 800, "Shanghai");
        Flight f2 = new Flight("CA211", 2100, "Vancouver");
        testAirlines.addFlight(f1);
        testAirlines.addFlight(f2);
        JSONObject json = new JSONObject();
        json.put("Flights", testAirlines.flightListToJson());
        assertEquals(json.toString(), testAirlines.toJson().toString());
    }

    @Test
    public void testFlightListToJson() {
        Flight f1 = new Flight("CA210", 800, "Shanghai");
        Flight f2 = new Flight("CA211", 2100, "Vancouver");
        testAirlines.addFlight(f1);
        testAirlines.addFlight(f2);
        JSONArray jsonArray = new JSONArray();
        for (Flight f : testAirlines.getFlightList()) {
            jsonArray.put(f.toJson());
        }
        assertEquals(jsonArray.toString(), testAirlines.flightListToJson().toString());
    }


}
