package ui.panels;

import ui.ServiceAppGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CancelPanel extends ContentPanel {
    //    JLabel name;
//    JLabel id;
//    JTextField nameInput;
//    JTextField idInput;
//    JButton find;
//    JList<String> bookingList;
    JButton cancel;

    public CancelPanel(ServiceAppGUI gui) {
        super(gui);

        loadFindBookingArea();
//        loadPassengerInfoArea();
//        find = new JButton("Find previous booking");
//        add(find);
        cancel = new JButton("Cancel");
        cancel.addActionListener(new CancelBookingListener());
        add(cancel, BorderLayout.PAGE_END);

    }


    public class CancelBookingListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int index = listArea.getSelectedIndex();
            ArrayList<String> bookingInfo = bookingList.get(index);

            String name = bookingInfo.get(0).substring(16);
            String id = bookingInfo.get(1).substring(14);
            String flightNum = bookingInfo.get(2).substring(11);

            gui.cancelBooking(flightNum, name, id);

            switchPanels(new ConfirmCancelPanel(gui));
        }
    }
}

