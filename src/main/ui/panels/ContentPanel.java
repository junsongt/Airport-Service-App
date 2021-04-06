package ui.panels;

import exception.InvalidInputException;
import ui.ServiceAppGUI;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

// Represent a panel with some common functionalities
public class ContentPanel extends JPanel {
    // Controller
    protected ServiceAppGUI gui;

    // For search flight
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

    // For information
    protected JLabel message;
    protected JLabel warning;
    protected ImageIcon okIcon;


    // EFFECTS: constructing a generic content panel with controller
    public ContentPanel(ServiceAppGUI gui) {
        this.gui = gui;
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

        time = new JLabel("Time");
        searchArea.add(time);
        timeInput = new JTextField();
        searchArea.add(timeInput);
        destination = new JLabel("Destination");
        searchArea.add(destination);
        destinationInput = new JTextField();
        searchArea.add(destinationInput);

        flightList = new DefaultListModel<>();

        search = new JButton("Search Flight");
        search.addActionListener(new SearchFlightListener());
        searchArea.add(search);

        warning = new JLabel("", SwingConstants.CENTER);
//        warning.setForeground(Color.RED);
        searchArea.add(warning);

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
            flightList.removeAllElements();
            ArrayList<String> results = null;
            try {
                results = gui.searchFlight(time, destination);
                for (String flightInfo : results) {
                    flightList.addElement(flightInfo);
                }
                if (results.isEmpty()) {
                    warning.setText("No flight found!");
                } else {
                    warning.setText("");
                }
            } catch (InvalidInputException invalidInputException) {
                warning.setText(invalidInputException.getMessage());
//                System.out.println(invalidInputException.getMessage());
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


    // MODIFIES: this, button
    // EFFECTS: enable the button if selecting an item in the list
    public void listSelectionToEnableButton(JButton button) {
        listArea.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                button.setEnabled(true);
            }
        });
    }

    // MODIFIES: this, button
    // EFFECTS: load the option area at the end of the page
    public void loadOptionPanel() {
        optionPanel = new JPanel();
        optionPanel.setLayout(new BorderLayout());

        add(optionPanel, BorderLayout.PAGE_END);
    }


}
