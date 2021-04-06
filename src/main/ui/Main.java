package ui;

import java.io.FileNotFoundException;

// TODO citation: code taken and modified from main.java class in JsonSerializationDemo
public class Main {
    public static void main(String[] args) {
        try {
//            new ServiceApp();
            new ServiceAppGUI();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }

    }
}
