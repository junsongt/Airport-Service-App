package ui.panels;

import model.Flight;
import model.Passenger;
import ui.ServiceAppGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContentPanel extends JPanel {
    // Controller
    ServiceAppGUI gui;
    Passenger customer;

    JLabel message;

    // For search
    JPanel searchArea;
    JLabel time;
    JLabel destination;
    JTextField timeInput;
    JTextField destinationInput;
    JButton search;

    JScrollPane listScrollPane;
    JList listArea;
    DefaultListModel<String> flightList;
    DefaultListModel<ArrayList<String>> bookingList;



    // For passenger info input
    JPanel passengerInfoArea;
    JPanel findBookingArea;

    JLabel name;
    JLabel id;
    JTextField nameInput;
    JTextField idInput;
    JButton find;


    // For seat info


    JPanel optionPanel;
    JButton proceed;
    JButton back;


    // For save & load
    JButton save;
    JButton load;



    public ContentPanel(ServiceAppGUI gui) {
        this.gui = gui;
        this.customer = gui.getCustomer();
        this.setLayout(new BorderLayout());
    }





    public void loadSearchArea() {
        searchArea = new JPanel();
        searchArea.setLayout(new GridLayout(3, 10, 30,10));

        time = new JLabel("time");
        searchArea.add(time);
        timeInput = new JTextField();
        searchArea.add(timeInput);
        destination = new JLabel("destination");
        searchArea.add(destination);
        destinationInput = new JTextField();
        searchArea.add(destinationInput);

        flightList = new DefaultListModel<>();


        search = new JButton("Search Flight");
        search.addActionListener(new SearchFlightListener());
        searchArea.add(search);

        add(searchArea, BorderLayout.NORTH);


        loadListPanel(flightList);

    }

    public void loadFindBookingArea() {
        findBookingArea = new JPanel();
        findBookingArea.setLayout(new GridLayout(3, 10, 30,10));

        name = new JLabel("Name");
        findBookingArea.add(name);
        nameInput = new JTextField();
        findBookingArea.add(nameInput);
        id = new JLabel("ID");
        findBookingArea.add(id);
        idInput = new JTextField();
        findBookingArea.add(idInput);

        bookingList = new DefaultListModel<>();

        find = new JButton("Find previous booking");
        find.addActionListener(new SearchBookingListener());
        findBookingArea.add(find);

        add(findBookingArea, BorderLayout.NORTH);

        loadListPanel(bookingList);

    }


    public void loadPassengerInfoArea() {
        passengerInfoArea = new JPanel();
        passengerInfoArea.setLayout(new GridLayout(3, 10, 30,10));

        name = new JLabel("Name");
        passengerInfoArea.add(name);
        nameInput = new JTextField();
        passengerInfoArea.add(nameInput);
        id = new JLabel("ID");
        passengerInfoArea.add(id);
        idInput = new JTextField();
        passengerInfoArea.add(idInput);

        add(passengerInfoArea, BorderLayout.NORTH);

    }



    public void switchPanels(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.validate();
        this.repaint();
    }




    public void loadListPanel(DefaultListModel listModel) {
        listArea = new JList<>(listModel);
        listArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listArea.setSelectedIndex(0);
        listArea.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                if (e.getValueIsAdjusting() == false) {
//                    if (listArea.getSelectedIndex() == -1) {
//                        //No selection, disable search button.
//                        search.setEnabled(false);
//                    } else {
//                        //Selection, enable the search button.
//                        search.setEnabled(true);
//                    }
//                }
            }
        });
        listArea.setVisibleRowCount(3);
        JScrollPane listScrollPane = new JScrollPane(listArea);
        listScrollPane.setViewportView(listArea);

        add(listScrollPane, BorderLayout.CENTER);

    }




    // ActionListeners:
    public class SearchFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int time = Integer.parseInt(timeInput.getText());
            String destination = destinationInput.getText();
            ArrayList<String> results = gui.searchFlight(time, destination);
            for (String flightInfo : results) {
                flightList.addElement(flightInfo);
            }
        }
    }

    public class SearchBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameInput.getText();
            String id = idInput.getText();
            ArrayList<ArrayList<String>> results = gui.searchBooking(name, id);
            for (ArrayList<String> passengerInfo : results) {
                bookingList.addElement(passengerInfo);
            }
        }
    }






}
