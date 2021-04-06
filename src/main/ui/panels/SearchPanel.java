package ui.panels;

import model.Flight;
import model.Passenger;
import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represent a panel with single search flight functionality
public class SearchPanel extends ContentPanel {

    private JButton select;
    private JButton clearResult;

    // EFFECTS: construct a panel with controller for user to purely search the flight & details related
    public SearchPanel(ServiceAppGUI gui) {
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


    // EFFECTS: define a new ActionListener related to the flight selection for details inside
    public class SelectFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listArea.getSelectedIndex();
            String flightInfo = flightList.get(index);
            String flightNum = flightInfo.substring(11,16);
            Flight flight = gui.getAirlines().findFlight(flightNum);
            ArrayList<ArrayList<String>> passengerInfoList = new ArrayList<>();
            for (Passenger p : flight.getPassengerList()) {
                passengerInfoList.add(p.generatePassengerInfo());
            }

            FlightInfoPanel info = new FlightInfoPanel(gui);
            switchPanels(info);
            info.loadPassengerInfo(passengerInfoList);
        }
    }


    // MODIFIES: this
    // EFFECTS: load option area with select button & clear search result button
    @Override
    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        select = new JButton("Select flight for detail");
        select.addActionListener(new SelectFlightListener());
        select.setEnabled(false);
        optionPanel.add(select, BorderLayout.WEST);

        clearResult = new JButton("Clear search results");
        clearResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new SearchPanel(gui));
            }
        });

        optionPanel.add(clearResult, BorderLayout.EAST);

        add(optionPanel, BorderLayout.PAGE_END);

    }
}

