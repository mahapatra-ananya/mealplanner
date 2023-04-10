package ui;

import persistence.JsonReader;
import persistence.JsonWriter;

import javax.lang.model.element.Element;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


// ORACLE list demo used as reference
public class GraphicalWeeklyPlanner extends JPanel implements ListSelectionListener {

    private JTextField ingredientNameTextField;
    private JTextField ingredientQuantityTextField;
    private JLabel ingredientNameLabel;
    private JLabel ingredientQuantityLabel;
    private static final String JSON_ELEMENTS_FILE = "./data/elements.json";
    private JPanel namePanel;
    private JPanel quantityPanel;
    private JPanel addPanel;
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JButton loadButton;
    private JsonWriter jsonStringsWriter;
    private JsonReader jsonStringsReader;
    private DefaultListModel listModel;

    private boolean alreadyEnabled = false;
    private JList list;
    private ArrayList<String> strings;
    private static final Icon addIcon = new ImageIcon("src/main/images/Untitled.png");
    private static final Icon deleteIcon = new ImageIcon("src/main/images/deleteicon.png");

    public GraphicalWeeklyPlanner() throws FileNotFoundException {

        jsonStringsWriter = new JsonWriter(JSON_ELEMENTS_FILE);
        jsonStringsReader = new JsonReader(JSON_ELEMENTS_FILE);

        listModel = new DefaultListModel();
        listModel.addElement("Onion: 2.5");
        strings = new ArrayList<>();
        strings.add("Onion: 2.5");

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(15);
        list.setFixedCellWidth(500);
        JScrollPane listScrollPane = new JScrollPane(list);

        createPanels();

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(BorderLayout.SOUTH, bottomPanel);
        mainPanel.add(BorderLayout.NORTH, addPanel);

        add(listScrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.PAGE_END);
    }

    public void createPanels() {
        textFieldAndLabel();

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
        //addPanel.add(Box.createHorizontalStrut(5));
        // addPanel.add(Box.createHorizontalStrut(5));
        addPanel.add(namePanel);
        addPanel.add(quantityPanel);
        addPanel.add(addButton);
        //addPanel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(BorderLayout.WEST, deleteButton);
        bottomPanel.add(BorderLayout.CENTER, saveButton);
        bottomPanel.add(BorderLayout.EAST, loadButton);
    }

    public void textFieldAndLabel() {
        panelComponents();

        namePanel = new JPanel(new BorderLayout());
        namePanel.add(BorderLayout.NORTH, ingredientNameLabel);
        namePanel.add(BorderLayout.SOUTH, ingredientNameTextField);

        quantityPanel = new JPanel(new BorderLayout());
        quantityPanel.add(BorderLayout.NORTH, ingredientQuantityLabel);
        quantityPanel.add(BorderLayout.SOUTH, ingredientQuantityTextField);
    }

    public void panelComponents() {
        addButton = new JButton(addIcon);
        AddListener addListener = new AddListener(addButton);
        addButton.setActionCommand("add");
        addButton.addActionListener(addListener);
        addButton.setEnabled(false);

        deleteButton = new JButton(deleteIcon);
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(new DeleteListener());

        saveButton = new JButton("Save");
        saveButton.setActionCommand("save");
        saveButton.addActionListener(new SaveListener());

        loadButton = new JButton("Load");
        loadButton.setActionCommand("load");
        loadButton.addActionListener(new LoadListener());

        ingredientNameTextField = new JTextField(8);
        ingredientNameTextField.addActionListener(addListener);
        ingredientNameTextField.getDocument().addDocumentListener(addListener);

        ingredientQuantityTextField = new JTextField(8);
        ingredientQuantityTextField.addActionListener(addListener);
        ingredientQuantityTextField.getDocument().addDocumentListener(addListener);

        ingredientNameLabel = new JLabel("Name");
        ingredientQuantityLabel = new JLabel("Quantity");
    }

    class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                jsonStringsWriter.open();
                jsonStringsWriter.write(strings);
                jsonStringsWriter.close();
                //System.out.println("Saved pantry to " + JSON_ELEMENTS_FILE);
            } catch (FileNotFoundException ex) {
                System.out.println("Unable to write to file: " + JSON_ELEMENTS_FILE);
            }
        }
    }

    class LoadListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                strings = jsonStringsReader.readStrings();
                for (String s : strings) {
                    if (!alreadyInList(s)) {

                        int index = list.getSelectedIndex(); //get selected index
                        if (index == -1) { //no selection, so insert at beginning
                            index = 0;
                        } else {           //add after the selected item
                            index++;
                        }

                        listModel.addElement(s);

                        ingredientNameTextField.requestFocusInWindow();
                        ingredientNameTextField.setText("");
                        ingredientQuantityTextField.requestFocusInWindow();
                        ingredientQuantityTextField.setText("");

                        //Select the new item and make it visible.
                        list.setSelectedIndex(index);
                        list.ensureIndexIsVisible(index);
                    }
                }
            } catch (IOException ex) {
                System.out.println("Unable to read from file: " + JSON_ELEMENTS_FILE);
            }
        }

        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

    }

    class DeleteListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            //This method can be called only if
            //there's a valid selection
            //so go ahead and remove whatever's selected.
            int index = list.getSelectedIndex();
            listModel.remove(index);
            strings.remove(index);

            int size = listModel.getSize();

            if (size == 0) { //Nobody's left, disable firing.
                deleteButton.setEnabled(false);

            } else { //Select an index.
                if (index == listModel.getSize()) {
                    //removed item in last position
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }

    //This listener is shared by the text field and the hire button.
    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        //Required by ActionListener.
        public void actionPerformed(ActionEvent e) {
            String name = ingredientNameTextField.getText();

            //User didn't type in a unique name...
            if (name.equals("") || alreadyInList(name)) {
                Toolkit.getDefaultToolkit().beep();
                ingredientNameTextField.requestFocusInWindow();
                ingredientNameTextField.selectAll();
                return;
            }

            int index = list.getSelectedIndex(); //get selected index
            if (index == -1) { //no selection, so insert at beginning
                index = 0;
            } else {           //add after the selected item
                index++;
            }

            String nameField = ingredientNameTextField.getText();
            String quantityField = ingredientQuantityTextField.getText();
            listModel.addElement(nameField + ": " + quantityField);
            strings.add(nameField + ": " + quantityField);
            //If we just wanted to add to the end, we'd do this:
            //listModel.addElement(employeeName.getText());

            //Reset the text field.
            ingredientNameTextField.requestFocusInWindow();
            ingredientNameTextField.setText("");
            ingredientQuantityTextField.requestFocusInWindow();
            ingredientQuantityTextField.setText("");

            //Select the new item and make it visible.
            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        //This method tests for string equality. You could certainly
        //get more sophisticated about the algorithm.  For example,
        //you might want to ignore white space and capitalization.
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        //Required by DocumentListener.
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        //Required by DocumentListener.
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        //Required by DocumentListener.
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }

        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    //This method is required by ListSelectionListener.
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting() == false) {

            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                deleteButton.setEnabled(false);

            } else {
                //Selection, enable the fire button.
                deleteButton.setEnabled(true);
            }
        }
    }

    /* JFrame frame = new JFrame("Your Pantry Application");
        frame.setSize(1000,500);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JLabel label = new JLabel("Ingredients in your Pantry");


    //JButton addButton = new JButton(addIcon);
    //addButton.setActionCommand("myButton");
    //addButton.addActionListener(this);

    ingredientNameTextField = new JTextField(8);
    JLabel ingredientNameLabel = new JLabel("Ingredient Name");
    ingredientQuantityTextField = new JTextField(8);
    JLabel ingredientQuantityLabel = new JLabel("Ingredient Quantity");

    // TODO 1: each new ingredient will be displayed with name, quantity, and delete button
    // TODO 2: options on main frame to add ingredients
    // TODO 3: visual component

        topPanel.add(label);

        bottomPanel.add(ingredientNameLabel);
        bottomPanel.add(ingredientNameTextField);
        bottomPanel.add(ingredientQuantityLabel);
        bottomPanel.add(ingredientQuantityTextField);
        bottomPanel.add(addButton);

        frame.getContentPane().add(BorderLayout.CENTER, topPanel);
        frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
        frame.setVisible(true);



    //public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("myButton")) {

            Ingredient i = new Ingredient(ingredientNameTextField.getText(), ingredientQuantityTextField.getText());
            // TODO: new ingredient formed with given name and quantity
        }
    }

    public void createIngredient(Ingredient i) {
        JPanel ingredientPanel = new JPanel(new BorderLayout());
        JLabel name = new JLabel(i.getName());
        JLabel quantity = new JLabel(i.getQuantity());
        //createDeleteButton();
        ingredientPanel.add(BorderLayout.WEST, createDeleteButton());
        ingredientPanel.add(BorderLayout.CENTER, name);
        ingredientPanel.add(BorderLayout.EAST, quantity);

    }

    public JButton createDeleteButton() {
        JButton deleteButton = new JButton();
        return deleteButton;
    }*/

}
