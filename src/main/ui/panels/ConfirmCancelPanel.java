package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmCancelPanel extends ContentPanel {


    // EFFECTS: construct a panel with controller for user to see the confirmation of booking canceled
    public ConfirmCancelPanel(ServiceAppGUI gui) {
        super(gui);

        loadImage();

        message = new JLabel("You have successfully canceled your booking", SwingConstants.CENTER);
        message.setIcon(okIcon);
        add(message, BorderLayout.CENTER);

        loadOptionPanel();

    }


    // MODIFIES: this
    // EFFECTS: load option area with back button
    @Override
    public void loadOptionPanel() {
        back = new JButton("Back to Main");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new CancelPanel(gui));
            }
        });

        add(back, BorderLayout.PAGE_END);

    }


}
