package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
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

    public CancelPanel(ServiceAppGUI gui) {
        super(gui);

        loadFindBookingArea();

        loadOptionPanel();

    }


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

        add(findBookingArea, BorderLayout.NORTH);

        loadListPanel(bookingList);
    }


    public class SearchBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameInput.getText();
            String id = idInput.getText();
            ArrayList<ArrayList<String>> results = gui.searchBooking(name, id);
            for (ArrayList<String> passengerInfo : results) {
                bookingList.addElement(passengerInfo);
            }
        }
    }


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


    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        cancel = new JButton("Cancel booking");
        cancel.addActionListener(new CancelBookingListener());
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

