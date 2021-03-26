package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConfirmBookingPanel extends ContentPanel {
    JButton confirm;

    public ConfirmBookingPanel(ServiceAppGUI gui) {
        super(gui);

        message = new JLabel(printBookingInfoBrief(), SwingConstants.CENTER);
        add(message, BorderLayout.CENTER);

        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.confirmBook(gui.getCustomer());
                message.setText("You have successfully booked your flight!");
                add(message, BorderLayout.CENTER);
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


    public String printBookingInfoBrief() {
        ArrayList<String> textList = gui.getCustomer().generatePassengerInfo();
        String text = "<html>";
        for (String s : textList) {
            text += s + "<br/>";
        }
        text += "Please confirm the booking." + "<br/>";
        text += "</html>";
        return (text);

    }

}
