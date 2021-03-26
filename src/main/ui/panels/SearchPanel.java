package ui.panels;

import model.Flight;
import model.Passenger;
import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SearchPanel extends ContentPanel {

    private JButton clearResult;
    private JButton select;

    public SearchPanel(ServiceAppGUI gui) {
        super(gui);

        loadSearchArea();

        loadOptionPanel();

    }


    public class SelectFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listArea.getSelectedIndex();
            String flightInfo = flightList.get(index);
            String flightNum = flightInfo.substring(11,16);
            Flight flight = gui.getAirlines().getFlight(flightNum);
            ArrayList<ArrayList<String>> passengerInfoList = new ArrayList<>();
            for (Passenger p : flight.getPassengerList()) {
                passengerInfoList.add(p.generatePassengerInfo());
            }

            FlightInfoPanel info = new FlightInfoPanel(gui);
            switchPanels(info);
            info.loadPassengerInfo(passengerInfoList);
        }
    }

    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        select = new JButton("Select flight for detail");
        select.addActionListener(new SelectFlightListener());
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

