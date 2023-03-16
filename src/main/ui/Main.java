package ui;

import model.Day;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        try {
            new WeeklyPlanner();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application as the file is not found.");
        }
    }
}
