package ui;

import model.Flight;
import model.Passenger;

import java.io.FileNotFoundException;
import java.util.ArrayList;

// TODO citation: code taken and modified from main.java class in JsonSerializationDemo
public class Main {
    public static void main(String[] args) {
        try {
//            new ServiceApp();
            new ServiceAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

//        Passenger p = new Passenger(700, "vancouver");
//        p.setName("jason");
//        p.setID("12345");
//        p.chooseSeat(1,2);
//        p.chooseFlight("CA110");
//        System.out.println(p.generatePassengerInfo());
    }
}
