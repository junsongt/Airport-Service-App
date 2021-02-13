package ui;


import model.Airlines;
import model.Flight;
import model.Passenger;

import java.util.ArrayList;
import java.util.Scanner;

public class ServiceApp {
    private Scanner input;
    private Airlines airlines;

    public ServiceApp() {
        this.airlines = new Airlines();

        Flight f1 = new Flight("CA110", 800, "Vancouver");
        Flight f2 = new Flight("CA210", 1200, "Toronto");
        Flight f3 = new Flight("CA310", 1600, "Shanghai");
        Flight f4 = new Flight("CA410", 2000, "Vancouver");
        Flight f5 = new Flight("CA510", 2400, "Shanghai");
        airlines.addFlight(f1);
        airlines.addFlight(f2);
        airlines.addFlight(f3);
        airlines.addFlight(f4);
        airlines.addFlight(f5);
        runService();
    }

    public void runService() {
        boolean quit = false;
        String command = null;
        System.out.println("Hello! Welcome to the airport!");
        initialze();

//        while (!quit) {
//
//        }
        System.out.println("Please input your departing time (format: 900 for 9:00 am; 1600 for 4:00 pm)");

        command = input.next();
        int time = Integer.parseInt(command);
        System.out.println("Please input your destination");
        String destination = input.next();
        Passenger me = new Passenger(time, destination);
        if (searchFlight(time, destination)) {
            System.out.println("please choose a flight by entering flight number");
            String flightNum = input.next();
            me.chooseFlight(flightNum);
            makeBook(me);
            System.out.println("Please choose your seat by entering row number and column number");
            printSeats(airlines.getFlight(flightNum));
            command = input.next();
            int row = Integer.parseInt(command);
            command = input.next();
            int col = Integer.parseInt(command);
            me.chooseSeat(row, col);
            if (confirmBook()) {
                airlines.getFlight(flightNum).addPassenger(me);
                airlines.getFlight(flightNum).setSeat(row, col);
            } else {
                cancelBook();
            }

        } else {
            System.out.println("please re-enter your departing time and destination");

        }

    }


    public void initialze() {
        input = new Scanner(System.in);
    }

    public boolean searchFlight(int time, String destination) {
        ArrayList<Flight> results = new ArrayList<>();
        for (Flight flight : airlines.getFlightList()) {
            if ((time <= flight.getTime()) && (destination.equals(flight.getDestination()))) {
                results.add(flight);
                System.out.println(flight.toString());
            }
        }
        if (results.isEmpty()) {
            System.out.println("Sorry! No flight found");
            return false;
        } else {
            return true;
        }
    }


    public void makeBook(Passenger p) {
        System.out.println("please enter your name");
        String name = input.next();
        System.out.println("please enter your ID");
        String id = input.next();
        p.setName(name);
        p.setID(id);

    }


    public void cancelBook() {
        System.out.println("you have canceled your flight");
    }


    public boolean confirmBook() {
        System.out.println("confirm or cancel?  Y/N");
        String decision = input.next();
        if (decision.equals("y")) {
            System.out.println("you have successfully booked a flight");
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: print out the current seats layout of cabin
    public void printSeats(Flight f) {
        for (int i = 0; i <= (f.ROW - 1); i++) {
            for (int j = 0; j <= (f.COL - 1); j++) {
                System.out.print(f.getSeat(i, j));
            }
            System.out.println();
        }
    }
}
