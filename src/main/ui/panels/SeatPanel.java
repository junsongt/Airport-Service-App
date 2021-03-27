package ui.panels;

import model.Flight;
import model.Passenger;
import ui.ServiceAppGUI;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SeatPanel extends ContentPanel {

    private JPanel seatInfoArea;
    private JTable seats;
    private JButton select;


    // EFFECTS: construct a panel with controller for user to choose a seat
    public SeatPanel(ServiceAppGUI gui) {
        super(gui);

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
            Passenger p = gui.getCustomer();
            gui.chooseSeat(p, row, col);
        }
    }


    // MODIFIES: this
    // EFFECTS: load the seats layout as a table
    public void loadSeatInfoArea() {
        seatInfoArea = new JPanel();
        seatInfoArea.setLayout(new BorderLayout());

        Object[][] seatsModel = buildSeatsModel();

        String[] header = {"", ""};
        seats = new JTable(seatsModel, header) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        seats.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

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
    public void loadOptionPanel() {
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



    // EFFECTS: build up the table model for the seats layout JTable
    public Object[][] buildSeatsModel() {
        Passenger p = gui.getCustomer();
        Flight f = gui.getAirlines().getFlight(p.getFlightNum());
        int maxRow = f.ROW;
        int maxCol = f.COL;

        Object[][] seatsModel = new Object[maxRow][maxCol];
        for (int i = 0; i < maxRow; i++) {
            for (int j = 0; j < maxCol; j++) {
                seatsModel[i][j] = f.getSeats().get(i).get(j);
            }
        }
        return seatsModel;
    }

}
