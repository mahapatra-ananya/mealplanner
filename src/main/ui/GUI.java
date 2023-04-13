package ui;

import java.io.FileNotFoundException;
import javax.swing.*;

public class GUI {

    // EFFECTS: Opens a new window for the application with a new graphical pantry planner
    public static void main(String[] args) {
        try {

            JFrame frame = new JFrame("Your Pantry");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JComponent panel = new GraphicalPantryPlanner();
            panel.setOpaque(true); //content panes must be opaque
            frame.setContentPane(panel);

            //Display the window.
            frame.pack();
            frame.setVisible(true);
        } catch (FileNotFoundException e) {
            JDialog dialog = new JDialog();
            dialog.setSize(600, 300);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setTitle("File Not Found");
            JLabel label = new JLabel("Oops! Unable to run application because file not found.");
            label.setSize(100, 100);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setVerticalAlignment(JLabel.CENTER);
            dialog.add(label);
            dialog.setVisible(true);
        }
    }
}
