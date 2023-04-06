package ui;

import model.Ingredient;
import model.Meal;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


// ORACLE list demo used as reference
public class GraphicalWeeklyPlanner extends JFrame implements ActionListener, ListSelectionListener {

    private JTextField ingredientNameTextField;
    private JTextField ingredientQuantityTextField;

    public GraphicalWeeklyPlanner() throws FileNotFoundException {
        // {
        //  JsonWriter writer = new JsonWriter("");
        //writer.open();
        //} finally {
        //  System.out.println("Hi");
        // }

        DefaultListModel listModel = new DefaultListModel();

        //Create the list and put it in a scroll pane.
        JList list = new JList(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setSelectedIndex(0);
        list.addListSelectionListener(this);
        list.setVisibleRowCount(5);
        JScrollPane listScrollPane = new JScrollPane(list);

        Icon addIcon = new ImageIcon("addIcon.png");

        JButton addButton = new JButton(addIcon);
        HireListener hireListener = new HireListener(addButton);
        addButton.setActionCommand("add");
        addButton.addActionListener(hireListener);
        addButton.setEnabled(false);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setActionCommand("delete");
        deleteButton.addActionListener(new FireListener());


        ingredientNameTextField = new JTextField(8);
        ingredientNameTextField.addActionListener(hireListener);
        ingredientNameTextField.getDocument().addDocumentListener(hireListener);
        String name = listModel.getElementAt(
                list.getSelectedIndex()).toString();

        ingredientQuantityTextField = new JTextField(8);
        ingredientQuantityTextField.addActionListener(hireListener);
        ingredientQuantityTextField.getDocument().addDocumentListener(hireListener);
        String quantity = listModel.getElementAt(
                list.getSelectedIndex()).toString();


        //JPanel ingredientPane = new JPanel();
        //ingredientPane.add(ingredientNameTextField);


        //Create a panel that uses BoxLayout.
        JPanel buttonPane = new JPanel();
        buttonPane.setLayout(new BoxLayout(buttonPane,
                BoxLayout.LINE_AXIS));
        buttonPane.add(deleteButton);
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(new JSeparator(SwingConstants.VERTICAL));
        buttonPane.add(Box.createHorizontalStrut(5));
        buttonPane.add(ingredientNameTextField);
        buttonPane.add(ingredientQuantityTextField);
        buttonPane.add(addButton);
        buttonPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        add(listScrollPane, BorderLayout.CENTER);
        add(buttonPane, BorderLayout.PAGE_END);


        JFrame frame = new JFrame("Your Pantry Application");
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


    }

    public void actionPerformed(ActionEvent e) {
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
    }

}
