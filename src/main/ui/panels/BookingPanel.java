package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookingPanel extends ContentPanel {

    private JButton select;

    public BookingPanel(ServiceAppGUI gui) {
        super(gui);

        loadSearchArea();

        select = new JButton("Select flight");
        select.addActionListener(new SelectFlightListener());
        add(select, BorderLayout.PAGE_END);



    }

    public class SelectFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listArea.getSelectedIndex();
            String flightInfo = flightList.get(index);
            String flightNum = flightInfo.substring(11,16);


            int time = Integer.parseInt(timeInput.getText());
            String destination = destinationInput.getText();
            gui.createPassenger(time, destination);
            gui.getCustomer().chooseFlight(flightNum);

            switchPanels(new PassengerIdentityPanel(gui));
        }
    }




}

