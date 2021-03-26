package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends ContentPanel {
    private JLabel welcome;
    private JLabel message;

    public HomePanel(ServiceAppGUI gui) {
        super(gui);
        welcome = new JLabel("Welcome To The Airport!", SwingConstants.CENTER);
        add(welcome, BorderLayout.PAGE_START);

        message = new JLabel("", SwingConstants.CENTER);
        add(message, BorderLayout.CENTER);

        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());


        save = new JButton("Save all the current booking information");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.saveBooking();
                message.setText("Booking saved!");
            }
        });
        optionPanel.add(save, BorderLayout.NORTH);

        load = new JButton("Load the previously saved booking information");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.loadBooking();
                message.setText("Booking loaded!");
            }
        });
        optionPanel.add(load, BorderLayout.SOUTH);

        add(optionPanel, BorderLayout.PAGE_END);

    }
}
