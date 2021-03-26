package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerIdentityPanel extends ContentPanel {

    public PassengerIdentityPanel(ServiceAppGUI gui) {
        super(gui);

        loadPassengerInfoArea();

        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        proceed = new JButton("Proceed to choose seat");
        proceed.addActionListener(new PassengerIdentityListener());
        optionPanel.add(proceed, BorderLayout.WEST);

        back = new JButton("Back");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new BookingPanel(gui));
            }
        });
        optionPanel.add(back, BorderLayout.EAST);

        add(optionPanel, BorderLayout.PAGE_END);

    }


    public class PassengerIdentityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameInput.getText();
            String id = idInput.getText();
            gui.makeBook(name, id);

            switchPanels(new SeatPanel(gui));
        }
    }
}
