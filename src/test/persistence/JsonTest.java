package persistence;

import model.Airlines;
import model.Flight;
import model.Passenger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


// TODO citation: code taken and modified from test package in JsonSerializationDemo
public class JsonTest {
    protected void checkPassenger(String name,
                                  String id,
//                                  int time,
//                                  String destination,
//                                  String flightNum,
//                                  int seatR,
//                                  int seatC,
                                  Passenger passenger) {
        assertEquals(name, passenger.getName());
        assertEquals(id, passenger.getID());
//        assertEquals(time, passenger.getTime());
//        assertEquals(destination, passenger.getDestination());
//        assertEquals(flightNum, passenger.getFlightNum());
//        assertEquals(seatR, passenger.getRow());
//        assertEquals(seatC, passenger.getCol());
    }


    protected void checkEmptyBooking(Airlines airlines) {
        int result = 0;
        for (Flight f : airlines.getFlightList()) {
            result = result + f.getPassengerList().size();
        }
        assertEquals(0, result);
    }

}
