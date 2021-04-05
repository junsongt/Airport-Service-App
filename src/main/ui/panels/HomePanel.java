package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomePanel extends ContentPanel {
    private JLabel welcome;
    private JButton save;
    private JButton load;

    // EFFECTS: constructing a Home JPanel with controller, displaying home features
    public HomePanel(ServiceAppGUI gui) {
        super(gui);
        welcome = new JLabel("Welcome To The Airport!", SwingConstants.CENTER);
        add(welcome, BorderLayout.PAGE_START);

        loadImage();

        message = new JLabel("", SwingConstants.CENTER);
        add(message, BorderLayout.CENTER);

        loadOptionPanel();
    }


    // MODIFIES: this
    // EFFECTS: load the option area with save & load buttons
    @Override
    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        save = new JButton("Save all the current booking information");
        save.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.saveBooking();
                message.setText("Booking saved!");
                message.setIcon(okIcon);
            }
        });
        optionPanel.add(save, BorderLayout.NORTH);

        load = new JButton("Load the previously saved booking information");
        load.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.loadBooking();
                message.setText("Booking loaded!");
                message.setIcon(okIcon);
            }
        });
        optionPanel.add(load, BorderLayout.SOUTH);

        add(optionPanel, BorderLayout.PAGE_END);
    }
}
