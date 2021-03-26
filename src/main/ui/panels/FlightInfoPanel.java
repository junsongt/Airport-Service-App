package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FlightInfoPanel extends ContentPanel {

    DefaultListModel<ArrayList<String>> passengerInfoListModel;

    public FlightInfoPanel(ServiceAppGUI gui) {
        super(gui);

        passengerInfoListModel = new DefaultListModel<>();
        loadListPanel(passengerInfoListModel);

        back = new JButton("Back to Main");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new SearchPanel(gui));
            }
        });
        add(back, BorderLayout.PAGE_END);
    }


    public void loadPassengerInfo(ArrayList<ArrayList<String>> passengerInfoList) {
        for (ArrayList<String> passengerInfo : passengerInfoList) {
            passengerInfoListModel.addElement(passengerInfo);
        }
    }
}
