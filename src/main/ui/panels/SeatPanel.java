package ui.panels;

import model.Flight;
import model.Passenger;
import ui.ServiceAppGUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeatPanel extends ContentPanel {

    JPanel seatInfoArea;

    JTable seats;



    JButton select;

    public SeatPanel(ServiceAppGUI gui) {
        super(gui);

        loadSeatInfoArea();

        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        proceed = new JButton("Proceed to confirm");
        proceed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new ConfirmBookingPanel(gui));
            }
        });
        optionPanel.add(proceed, BorderLayout.WEST);

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new PassengerIdentityPanel(gui));
            }
        });
        optionPanel.add(back, BorderLayout.EAST);

        add(optionPanel, BorderLayout.PAGE_END);
    }



    public class SelectSeatListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = seats.getSelectedRow();
            int col = seats.getSelectedColumn();
            Passenger p = gui.getCustomer();
            p.chooseSeat(row, col);

        }
    }


    public void loadSeatInfoArea() {
        seatInfoArea = new JPanel();
        seatInfoArea.setLayout(new BorderLayout());

        Passenger p = gui.getCustomer();
        Flight f = gui.getAirlines().getFlight(p.getFlightNum());

        Object[][] seatsModel = new Object[5][2];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 2; j++) {
                seatsModel[i][j] = f.getSeats().get(i).get(j);
            }
        }

        String[] header = {"", ""};
        seats = new JTable(seatsModel, header);
        seats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane tableScrollPane = new JScrollPane(seats);
        tableScrollPane.setViewportView(seats);

        seatInfoArea.add(tableScrollPane, BorderLayout.CENTER);
        select = new JButton("Select seat");
        select.addActionListener(new SelectSeatListener());
        seatInfoArea.add(select, BorderLayout.PAGE_END);

        add(seatInfoArea, BorderLayout.CENTER);


    }




}
