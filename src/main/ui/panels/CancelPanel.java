package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CancelPanel extends ContentPanel {

    private JPanel findBookingArea;
    private JButton cancel;
    private JButton clearResult;
    private JButton find;
    private DefaultListModel<ArrayList<String>> bookingList;

    // EFFECTS: construct a panel with controller for user to cancel the previous booking
    public CancelPanel(ServiceAppGUI gui) {
        super(gui);

        loadFindBookingArea();
//        listArea.addListSelectionListener(new ListSelectionListener() {
//            @Override
//            public void valueChanged(ListSelectionEvent e) {
//                cancel.setEnabled(true);
//            }
//        });
        loadOptionPanel();

        listSelectionToEnableButton(cancel);

    }


    // MODIFIES: this
    // EFFECTS: load a previous booking searching area
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

        warning = new JLabel("", SwingConstants.CENTER);
//        warning.setForeground(Color.RED);
        findBookingArea.add(warning);

        add(findBookingArea, BorderLayout.NORTH);

        loadListPanel(bookingList);
    }


    // EFFECTS: define a new ActionListener related to display previous booking when search is clicked
    public class SearchBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameInput.getText();
            String id = idInput.getText();
            bookingList.removeAllElements();
            ArrayList<ArrayList<String>> results = gui.searchBooking(name, id);
            for (ArrayList<String> passengerInfo : results) {
                bookingList.addElement(passengerInfo);
            }
            if (results.isEmpty()) {
                warning.setText("No booking found!");
            } else {
                warning.setText("");
            }
        }
    }


    // EFFECTS: define a new ActionListener related to canceling previous booking when cancel is clicked
    public class CancelBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listArea.getSelectedIndex();
            ArrayList<String> bookingInfo = bookingList.get(index);

            String name = bookingInfo.get(0).substring(16);
            String id = bookingInfo.get(1).substring(14);
            String flightNum = bookingInfo.get(2).substring(11);

            gui.cancelBooking(flightNum, name, id);

            switchPanels(new ConfirmCancelPanel(gui));
        }
    }

    // MODIFIES: this
    // EFFECTS: load option area with cancel button & clear search results button
    @Override
    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        cancel = new JButton("Cancel booking");
        cancel.addActionListener(new CancelBookingListener());
        cancel.setEnabled(false);
        optionPanel.add(cancel, BorderLayout.WEST);

        clearResult = new JButton("Clear results");
        clearResult.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new CancelPanel(gui));
            }
        });

        optionPanel.add(clearResult, BorderLayout.EAST);

        add(optionPanel, BorderLayout.PAGE_END);
    }

}

