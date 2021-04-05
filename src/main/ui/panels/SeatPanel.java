package ui.panels;

import model.Flight;
import model.Passenger;
import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatPanel extends ContentPanel {

    private JPanel seatInfoArea;
    private JTable seats;
    private JButton select;

    private Passenger passenger;
    private Flight flight;
    private int maxRow;
    private int maxCol;


    // EFFECTS: construct a panel with controller for user to choose a seat
    public SeatPanel(ServiceAppGUI gui) {
        super(gui);
        passenger = gui.getCustomer();
        flight = gui.getAirlines().findFlight(passenger.getFlightNum());
        maxRow = flight.ROW;
        maxCol = flight.COL;

        loadSeatInfoArea();

        loadOptionPanel();

    }


    // MODIFIES: this
    // EFFECTS: define a new ActionListener related to seat selection when clicking select button
    public class SelectSeatListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int row = seats.getSelectedRow();
            int col = seats.getSelectedColumn();

            if (gui.chooseSeat(row, col)) {
                proceed.setEnabled(true);
                warning.setText("");
            } else {
                proceed.setEnabled(false);
                warning.setText("Seat already chosen, pick another one!");

            }

//            if (flight.isSeatOccupied(row, col)) {
//                proceed.setEnabled(false);
//            } else {
//                passenger.setSeat(row, col);
//                proceed.setEnabled(true);
//            }
        }
    }


    // MODIFIES: this
    // EFFECTS: load the seats layout as a table
    public void loadSeatInfoArea() {
        seatInfoArea = new JPanel();
        seatInfoArea.setLayout(new BorderLayout());

        warning = new JLabel("", SwingConstants.CENTER);
        seatInfoArea.add(warning, BorderLayout.PAGE_START);

        String[][] seatsModel = buildSeatsModel();
        String[] header = loadHeader();
        seats = new JTable(seatsModel, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        seats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        seats.setCellSelectionEnabled(true);

        JScrollPane tableScrollPane = new JScrollPane(seats);
        tableScrollPane.setViewportView(seats);
        seatInfoArea.add(tableScrollPane, BorderLayout.CENTER);

        select = new JButton("Select seat");
        select.addActionListener(new SelectSeatListener());
        seatInfoArea.add(select, BorderLayout.PAGE_END);

        add(seatInfoArea, BorderLayout.CENTER);

    }


    // MODIFIES: this
    // EFFECTS: load the option area with proceed button & back button
    @Override
    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        proceed = new JButton("Proceed to confirm");
        proceed.setEnabled(false);
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


    // EFFECTS: build up the table model for the seats layout JTable
    public String[][] buildSeatsModel() {
        String[][] seatsModel = new String[maxRow][maxCol];
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                seatsModel[i][j] = flight.getSeats().get(i).get(j);
            }
        }
        return seatsModel;
    }


    // EFFECTS: generate fixed length array for the header of table
    public String[] loadHeader() {
        String[] header = new String[maxCol];
        for (int i = 0; i < maxCol; i++) {
            header[i] = "";
        }
        return header;
//        ArrayList<String> header = new ArrayList<>();
//        for (int i = 0; i < maxCol; i++) {
//            header.add("");
//        }
//        return header.toArray(new String[0]);
//        return header.toArray(new String[header.size()]);

    }






}
