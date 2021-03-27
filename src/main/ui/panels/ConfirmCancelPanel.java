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

        finalMessage = new JLabel("You have successfully canceled your booking", SwingConstants.CENTER);
        finalMessage.setIcon(okIcon);
        add(finalMessage, BorderLayout.CENTER);

        loadOptionPanel();

    }


    // MODIFIES: this
    // EFFECTS: load option area with back button
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
