package ui;

import java.io.FileNotFoundException;

public class ConsoleUI {
    public static void main(String[] args) {
        try {
            new ConsoleWeeklyPlanner();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application as the file is not found.");
        }
    }
}
