package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ConfirmCancelPanel extends ContentPanel {

    public ConfirmCancelPanel(ServiceAppGUI gui) {
        super(gui);

        finalMessage = new JLabel("You have successfully canceled your booking", SwingConstants.CENTER);
        add(finalMessage, BorderLayout.CENTER);

        loadOptionPanel();

    }


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
