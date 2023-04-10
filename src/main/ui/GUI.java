package ui;

import java.awt.*;
import java.io.FileNotFoundException;
import javax.swing.*;

public class GUI {
    public static void main(String[] args) {
        try {
            //Create and set up the window.
            JFrame frame = new JFrame("Your Pantry");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            //Create and set up the content pane.
            JComponent newContentPane = new GraphicalWeeklyPlanner();
            newContentPane.setOpaque(true); //content panes must be opaque
            frame.setContentPane(newContentPane);

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
