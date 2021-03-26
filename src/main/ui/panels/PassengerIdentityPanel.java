package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerIdentityPanel extends ContentPanel {

    private JPanel passengerInfoArea;

    public PassengerIdentityPanel(ServiceAppGUI gui) {
        super(gui);

        loadPassengerInfoArea();

        loadOptionPanel();

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


    public void loadPassengerInfoArea() {
        passengerInfoArea = new JPanel();
        passengerInfoArea.setLayout(new GridLayout(3, 10, 30,10));

        name = new JLabel("Name");
        passengerInfoArea.add(name);
        nameInput = new JTextField();
        passengerInfoArea.add(nameInput);
        id = new JLabel("ID");
        passengerInfoArea.add(id);
        idInput = new JTextField();
        passengerInfoArea.add(idInput);

        add(passengerInfoArea, BorderLayout.NORTH);

    }


    public void loadOptionPanel() {
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
}
