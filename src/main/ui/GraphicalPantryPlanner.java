package ui;

import model.Event;
import model.EventLog;
import model.Ingredient;
import model.Pantry;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;


// ORACLE list demo used as reference
public class GraphicalPantryPlanner extends JPanel implements ListSelectionListener {

    private JTextField ingredientNameTextField;
    private JTextField ingredientQuantityTextField;
    private JLabel ingredientNameLabel;
    private JLabel ingredientQuantityLabel;
    private static final String JSON_ELEMENTS_FILE = "./data/elements.json";
    private Pantry pantry;
    private JPanel namePanel;
    private JPanel quantityPanel;
    private JPanel addPanel;
    private JPanel bottomPanel;
    private JButton addButton;
    private JButton deleteButton;
    private JButton saveButton;
    private JButton loadButton;
   // private JsonWriter jsonStringsWriter;
    //private JsonReader jsonStringsReader;
    private JsonWriter jsonPantryWriter;
    private JsonReader jsonPantryReader;
    private DefaultListModel listModel;

    private boolean alreadyEnabled = false;
    private JList list;
  //  private ArrayList<String> strings;
    private static final Icon addIcon = new ImageIcon("src/main/images/Untitled.png");
    private static final Icon deleteIcon = new ImageIcon("src/main/images/deleteicon.png");

    // EFFECTS: creates a new graphical weekly planner with an empty list of ingredients and a panel of buttons
    public GraphicalPantryPlanner() throws FileNotFoundException {

        jsonPantryWriter = new JsonWriter(JSON_ELEMENTS_FILE);
        jsonPantryReader = new JsonReader(JSON_ELEMENTS_FILE);

        pantry = new Pantry();

        listModel = new DefaultListModel();
        //strings = new ArrayList<>();

        list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(30);
        list.setFixedCellWidth(500);
        JScrollPane listScrollPane = new JScrollPane(list);

        createPanels();
        JLabel pantryImage = new JLabel(new ImageIcon("src/main/images/pantry.png"));

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(BorderLayout.CENTER, bottomPanel);
        mainPanel.add(BorderLayout.NORTH, addPanel);
        mainPanel.add(BorderLayout.SOUTH, pantryImage);

        add(listScrollPane, BorderLayout.CENTER);
        add(mainPanel, BorderLayout.PAGE_END);

       // addWindowListener(this);
    }


    // EFFECTS: creates all the panels to be placed within the main panel next to the list model
    public void createPanels() {
        textFieldAndLabel();

        addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.LINE_AXIS));
        addPanel.add(namePanel);
        addPanel.add(quantityPanel);
        addPanel.add(addButton);

        bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(BorderLayout.WEST, deleteButton);
        bottomPanel.add(BorderLayout.CENTER, saveButton);
        bottomPanel.add(BorderLayout.EAST, loadButton);
    }

    // EFFECTS: creates the panel containing text fields and their labels
    public void textFieldAndLabel() {
        panelComponents();

        namePanel = new JPanel(new BorderLayout());
        namePanel.add(BorderLayout.NORTH, ingredientNameLabel);
        namePanel.add(BorderLayout.SOUTH, ingredientNameTextField);

        quantityPanel = new JPanel(new BorderLayout());
        quantityPanel.add(BorderLayout.NORTH, ingredientQuantityLabel);
        quantityPanel.add(BorderLayout.SOUTH, ingredientQuantityTextField);
    }

    // EFFECTS: creates all the components to be added to various panels
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

    // REQUIRES: the quantity entered must be a positive number
    // EFFECTS: creates an Ingredient object according to the user inputted name and quantity
    private Ingredient createIngredient(String name, String quantity) {
        double doubleQuantity = Double.parseDouble(quantity);
        Ingredient ingredient = new Ingredient(name, doubleQuantity);
        return ingredient;
    }

    /*private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.getDate());
            System.out.println("    " + next.getDescription());
            System.out.println("");
        }
    }

    public void windowClosed(WindowEvent e) {
        //This will only be seen on standard output.
        printLog(EventLog.getInstance());
        System.out.println("hey");
    }

    public void windowOpened(WindowEvent e) {
       // displayMessage("WindowListener method called: windowOpened.");
    }

    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
        System.out.println("hey");
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }*/

    class SaveListener implements ActionListener {

        // EFFECTS: saves the ingredients to file and notifies the user that this has been done
        public void actionPerformed(ActionEvent e) {
            try {
                //jsonStringsWriter.open();
                //jsonStringsWriter.write(strings);
                //jsonStringsWriter.close();
                jsonPantryWriter.open();;
                jsonPantryWriter.write(pantry);
                jsonPantryWriter.close();
                JDialog saveDialog = new JDialog();
                saveDialog.setSize(600, 300);
                saveDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                saveDialog.setTitle("Saved");
                JLabel label = new JLabel("Your ingredients have been saved successfully.");
                label.setSize(100, 100);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setVerticalAlignment(JLabel.CENTER);
                saveDialog.add(label);
                saveDialog.setVisible(true);
            } catch (FileNotFoundException ex) {
                handleException();
            }
        }

        // Informs users if there is an IOException
        protected void handleException() {
            JDialog dialog = new JDialog();
            dialog.setSize(600, 300);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setTitle("File Not Found");
            JLabel label = new JLabel("Oops! Unable to read from file: " + JSON_ELEMENTS_FILE);
            label.setSize(100, 100);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            dialog.add(label);
            dialog.setVisible(true);
        }
    }

    class LoadListener implements ActionListener {

        // MODIFIES: strings, listModel
        // EFFECTS: loads the ingredients from file and notifies the user that this has been done
        public void actionPerformed(ActionEvent e) {
            try {
                //ArrayList<String> strings1 = jsonStringsReader.readStrings();
                //strings.addAll(strings1);
                addAllIngredients(jsonPantryReader.readPantry().getIngredients(), pantry);
                for (Ingredient i : pantry.getIngredients()) {
                    if (!alreadyInList(i.getName() + ": " + i.getQuantity())) {

                        int index = list.getSelectedIndex();
                        if (index == -1) {
                            index = 0;
                        } else {
                            index++;
                        }

                        listModel.addElement(i.getName() + ": " + i.getQuantity());
                        resetAndMakeVisible(index);

                    }
                }
                loadDialog();
            } catch (IOException ex) {
                handleException();
            }
        }

        // MODIFIES: pantry p
        // EFFECTS: adds all given ingredients to the pantry
        protected void addAllIngredients(ArrayList<Ingredient> ingredients, Pantry p) {
            for (Ingredient i: ingredients) {
                p.addIngredient(i);
            }
        }

        // EFFECTS: resets text fields and makes the added elements visible
        protected void resetAndMakeVisible(int index) {
            ingredientNameTextField.requestFocusInWindow();
            ingredientNameTextField.setText("");
            ingredientQuantityTextField.requestFocusInWindow();
            ingredientQuantityTextField.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // EFFECTS: checks if the given string is already in the list
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        // EFFECTS: creates the dialog box to inform users that ingredients have been loaded
        protected void loadDialog() {
            JDialog loadDialog = new JDialog();
            loadDialog.setSize(600, 300);
            loadDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            loadDialog.setTitle("Loaded");
            JLabel label = new JLabel("Your ingredients have been loaded successfully.");
            label.setSize(100, 100);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            loadDialog.add(label);
            loadDialog.setVisible(true);
        }

        // Informs users if there is an IOException
        protected void handleException() {
            JDialog dialog = new JDialog();
            dialog.setSize(600, 300);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setTitle("File Not Found");
            JLabel label = new JLabel("Oops! Unable to read from file: " + JSON_ELEMENTS_FILE);
            label.setSize(100, 100);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            dialog.add(label);
            dialog.setVisible(true);
        }

    }

    class DeleteListener implements ActionListener {

        // EFFECTS: deletes an ingredient from the list
        public void actionPerformed(ActionEvent e) {

            int index = list.getSelectedIndex();
            listModel.remove(index);
            //strings.remove(index);
            pantry.removeIngredientAt(index);

            int size = listModel.getSize();

            if (size == 0) {
                deleteButton.setEnabled(false);

            } else {
                if (index == listModel.getSize()) {
                    index--;
                }

                list.setSelectedIndex(index);
                list.ensureIndexIsVisible(index);
            }
        }
    }


    class AddListener implements ActionListener, DocumentListener {
        private boolean alreadyEnabled = false;
        private JButton button;

        // EFFECTS: creates a new AddListener
        public AddListener(JButton button) {
            this.button = button;
        }

        // MODIFIES: list
        // EFFECTS: adds a new ingredient to the list
        public void actionPerformed(ActionEvent e) {
            String nameField = ingredientNameTextField.getText();
            String quantityField = ingredientQuantityTextField.getText();
            Ingredient i = createIngredient(nameField, quantityField);

            //User didn't type in a unique name...
            if (nameField.equals("") || alreadyInList(nameField)) {
                Toolkit.getDefaultToolkit().beep();
                ingredientNameTextField.requestFocusInWindow();
                ingredientNameTextField.selectAll();
                return;
            }

            int index = list.getSelectedIndex();
            if (index == -1) {
                index = 0;
            } else {
                index++;
            }

            //Ingredient i = createIngredient(nameField, quantityField);
            listModel.addElement(i.getName() + ": " + i.getQuantity());
           // strings.add(nameField + ": " + quantityField);
            pantry.addIngredient(i);


            resetAndMakeVisible(index);
        }

        // EFFECTS: resets text fields and makes the added elements visible
        protected void resetAndMakeVisible(int index) {
            ingredientNameTextField.requestFocusInWindow();
            ingredientNameTextField.setText("");
            ingredientQuantityTextField.requestFocusInWindow();
            ingredientQuantityTextField.setText("");

            list.setSelectedIndex(index);
            list.ensureIndexIsVisible(index);
        }

        // EFFECTS: checks if the given string is already in the list
        protected boolean alreadyInList(String name) {
            return listModel.contains(name);
        }

        // MODIFIES: this
        // EFFECTS: enables the button if not already enabled
        public void insertUpdate(DocumentEvent e) {
            enableButton();
        }

        // MODIFIES: this
        // EFFECTS: handles empty text fields by disabling button
        public void removeUpdate(DocumentEvent e) {
            handleEmptyTextField(e);
        }

        // MODIFIES: this
        // EFFECTS: enables button if text fields are not empty
        public void changedUpdate(DocumentEvent e) {
            if (!handleEmptyTextField(e)) {
                enableButton();
            }
        }

        // MODIFIES: this
        // EFFECTS: enables button
        private void enableButton() {
            if (!alreadyEnabled) {
                button.setEnabled(true);
            }
        }


        // MODIFIES: this
        // EFFECTS: disables button & returns true if text field not empty
        private boolean handleEmptyTextField(DocumentEvent e) {
            if (e.getDocument().getLength() <= 0) {
                button.setEnabled(false);
                alreadyEnabled = false;
                return true;
            }
            return false;
        }
    }

    // EFFECTS: allows delete button to be enabled only if list is not empty
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

}