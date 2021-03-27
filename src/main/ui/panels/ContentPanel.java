package ui.panels;

import model.Passenger;
import ui.ServiceAppGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ContentPanel extends JPanel {
    // Controller
    protected ServiceAppGUI gui;
    protected Passenger customer;

    // For search
    protected JPanel searchArea;
    protected JLabel time;
    protected JLabel destination;
    protected JTextField timeInput;
    protected JTextField destinationInput;
    protected JButton search;

    protected JList listArea;
    protected DefaultListModel<String> flightList;

    // For passenger info input
    protected JLabel name;
    protected JLabel id;
    protected JTextField nameInput;
    protected JTextField idInput;

    // For option area
    protected JPanel optionPanel;
    protected JButton proceed;
    protected JButton back;

    // For confirmation
    protected JLabel finalMessage;
    protected ImageIcon okIcon;


    // EFFECTS: constructing a generic content panel with controller
    public ContentPanel(ServiceAppGUI gui) {
        this.gui = gui;
        this.customer = gui.getCustomer();
        this.setLayout(new BorderLayout());
    }


    // MODIFIES: this
    // EFFECTS: reload a given JPanel from current JPanel
    public void switchPanels(JPanel panel) {
        this.removeAll();
        this.add(panel);
        this.validate();
        this.repaint();
    }



    // MODIFIES: this
    // EFFECTS: load a JList panel in a content panel by a given list model
    public void loadListPanel(DefaultListModel listModel) {
        listArea = new JList<>(listModel);
        listArea.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listArea.setSelectedIndex(0);
        listArea.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
//                if (e.getValueIsAdjusting() == false) {
//
//                    if (listArea.getSelectedIndex() == -1) {
//                        //No selection, disable fire button.
//                        proceed.setEnabled(false);
//
//                    } else {
//                        //Selection, enable the fire button.
//                        proceed.setEnabled(true);
//                    }
//                }
            }
        });
        listArea.setVisibleRowCount(3);
        JScrollPane listScrollPane = new JScrollPane(listArea);
        listScrollPane.setViewportView(listArea);

        add(listScrollPane, BorderLayout.CENTER);

    }


    // MODIFIES: this
    // EFFECTS: load a flight searching area in a content panel
    public void loadSearchArea() {
        searchArea = new JPanel();
        searchArea.setLayout(new GridLayout(3, 10, 30,10));

        time = new JLabel("time");
        searchArea.add(time);
        timeInput = new JTextField();
        searchArea.add(timeInput);
        destination = new JLabel("destination");
        searchArea.add(destination);
        destinationInput = new JTextField();
        searchArea.add(destinationInput);

        flightList = new DefaultListModel<>();

        search = new JButton("Search Flight");
        search.addActionListener(new SearchFlightListener());
        searchArea.add(search);

        add(searchArea, BorderLayout.NORTH);

        loadListPanel(flightList);

    }


    // MODIFIES: this
    // EFFECTS: define a new ActionListener related to flight-search when clicking search button
    public class SearchFlightListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int time = Integer.parseInt(timeInput.getText());
            String destination = destinationInput.getText();
            ArrayList<String> results = gui.searchFlight(time, destination);
            for (String flightInfo : results) {
                flightList.addElement(flightInfo);
            }
        }
    }


    // MODIFIES: this
    // EFFECTS: load images from file and resize
    public void loadImage() {
        String sep = System.getProperty("file.separator");
        ImageIcon rawIcon = new ImageIcon(System.getProperty("user.dir") + sep
                + "image" + sep + "ok.png");
        Image rawImage = rawIcon.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        okIcon = new ImageIcon(rawImage);

    }






}
