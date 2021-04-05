package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConfirmBookingPanel extends ContentPanel {

    private JButton confirm;

    // EFFECTS: construct a panel with controller for user to confirm the booking
    public ConfirmBookingPanel(ServiceAppGUI gui) {
        super(gui);

        loadImage();

        message = new JLabel(printBookingInfoBrief(), SwingConstants.CENTER);
        add(message, BorderLayout.CENTER);

        loadOptionPanel();

    }


    // MODIFIES: this
    // EFFECTS: load the option area with confirm button & back button
    @Override
    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.confirmBook(gui.getCustomer());
                message.setText("You have successfully booked your flight!");
                message.setIcon(okIcon);
            }
        });
        optionPanel.add(confirm, BorderLayout.WEST);

        back = new JButton("Back to Main");
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchPanels(new BookingPanel(gui));
            }
        });
        optionPanel.add(back, BorderLayout.EAST);

        add(optionPanel, BorderLayout.PAGE_END);
    }



    // EFFECTS: display the passenger's info line by line on the JLabel
    public String printBookingInfoBrief() {
        ArrayList<String> textList = gui.getCustomer().generatePassengerInfo();
        String text = "<html>";
        for (String s : textList) {
            text += s + "<br/>";
        }
        text += "Please confirm the booking" + "<br/>";
        text += "</html>";
        return (text);

    }


}
