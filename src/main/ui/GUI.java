package ui;

import model.Event;
import model.EventLog;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;
import javax.swing.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;

public class GUI implements WindowListener {

    // EFFECTS: Opens a new window for the application with a new graphical pantry planner

    private void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next.getDate());
            System.out.println(next.getDescription());
            System.out.println("");
        }
    }

    public void windowClosed(WindowEvent e) {
        //This will only be seen on standard output.

    }

    public void windowOpened(WindowEvent e) {
        // displayMessage("WindowListener method called: windowOpened.");
    }

    public void windowClosing(WindowEvent e) {
        printLog(EventLog.getInstance());
    }

    public void windowIconified(WindowEvent e) {

    }

    public void windowDeiconified(WindowEvent e) {

    }

    public void windowActivated(WindowEvent e) {

    }

    public void windowDeactivated(WindowEvent e) {

    }


    public GUI() throws FileNotFoundException {
        JFrame frame = new JFrame("Your Pantry");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JComponent panel = new GraphicalPantryPlanner();
        frame.addWindowListener(this);
        panel.setOpaque(true); //content panes must be opaque
        frame.setContentPane(panel);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        try {
            new GUI();
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
