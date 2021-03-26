package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConfirmBookingPanel extends ContentPanel {

    private JButton confirm;

    public ConfirmBookingPanel(ServiceAppGUI gui) {
        super(gui);

        finalMessage = new JLabel(printBookingInfoBrief(), SwingConstants.CENTER);
        add(finalMessage, BorderLayout.CENTER);

        loadOptionPanel();

    }



    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        confirm = new JButton("Confirm");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gui.confirmBook(gui.getCustomer());
                finalMessage.setText("You have successfully booked your flight!");
                add(finalMessage, BorderLayout.CENTER);
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
