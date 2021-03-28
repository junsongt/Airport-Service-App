package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingPanel extends ContentPanel {

    private JButton select;
    private JButton clearResult;

    // EFFECTS: constructing a booking panel with controller & search area & option area
    public BookingPanel(ServiceAppGUI gui) {
        super(gui);

        loadSearchArea();
//        listArea.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                select.setEnabled(true);
//            }
//        });

        loadOptionPanel();

        listSelectionToEnableButton(select);

    }


    // EFFECTS: define a new ActionListener related to selection of flight
    // when select flight button is clicked
    public class SelectFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listArea.getSelectedIndex();
            String flightInfo = flightList.get(index);
            String flightNum = flightInfo.substring(11,16);


            int time = Integer.parseInt(timeInput.getText());
            String destination = destinationInput.getText();
            gui.createPassenger(time, destination);
            gui.getCustomer().setFlight(flightNum);

            switchPanels(new PassengerIdentityPanel(gui));
        }
    }


    // MODIFIES: this
    // EFFECTS: load a option area with select button & clear button
    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        select = new JButton("Select flight");
        select.addActionListener(new SelectFlightListener());
        select.setEnabled(false);
        optionPanel.add(select, BorderLayout.WEST);

        clearResult = new JButton("Clear results");
        clearResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new BookingPanel(gui));
            }
        });

        optionPanel.add(clearResult, BorderLayout.EAST);

        add(optionPanel, BorderLayout.PAGE_END);
    }



}

