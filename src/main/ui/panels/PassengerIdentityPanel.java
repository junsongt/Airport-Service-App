package ui.panels;

import exception.SameIDException;
import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represent a panel with setting passenger's name & ID functionality
public class PassengerIdentityPanel extends ContentPanel {

    private JPanel passengerInfoArea;


    // EFFECTS: constructing a panel with controller for user to input personal info and option
    public PassengerIdentityPanel(ServiceAppGUI gui) {
        super(gui);

        loadPassengerInfoArea();

        loadOptionPanel();

    }


    // EFFECTS: define a new ActionListener related to creating new passenger
    public class PassengerIdentityListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameInput.getText();
            String id = idInput.getText();
            try {
                gui.makeBook(name, id);
                switchPanels(new SeatPanel(gui));
            } catch (SameIDException sameIDException) {
                warning.setText(sameIDException.getMessage());
//                System.out.println(sameIDException.getMessage());
            }

        }
    }

    // MODIFIES: this
    // EFFECTS: load a panel for user input of name & id
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

        warning = new JLabel("", SwingConstants.CENTER);
        passengerInfoArea.add(warning);

        add(passengerInfoArea, BorderLayout.NORTH);

    }


    // MODIFIES: this
    // EFFECTS: load option area with proceed & back button
    @Override
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
