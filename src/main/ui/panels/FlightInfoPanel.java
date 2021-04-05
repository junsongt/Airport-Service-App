package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FlightInfoPanel extends ContentPanel {

    private DefaultListModel<ArrayList<String>> passengerInfoListModel;

    // EFFECTS: construct a panel with controller for user to check passengers' info inside a flight
    public FlightInfoPanel(ServiceAppGUI gui) {
        super(gui);

        passengerInfoListModel = new DefaultListModel<>();
        loadListPanel(passengerInfoListModel);

        loadOptionPanel();

    }

    // MODIFIES: this
    // EFFECTS: load the passengers' info inside a selected flight
    public void loadPassengerInfo(ArrayList<ArrayList<String>> passengerInfoList) {
        for (ArrayList<String> passengerInfo : passengerInfoList) {
            passengerInfoListModel.addElement(passengerInfo);
        }
    }


    // MODIFIES: this
    // EFFECTS: load a option area with back button
    @Override
    public void loadOptionPanel() {
        back = new JButton("Back to Main");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new SearchPanel(gui));
            }
        });
        add(back, BorderLayout.PAGE_END);

    }
}
