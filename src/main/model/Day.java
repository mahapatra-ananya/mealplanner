package model;

import java.util.ArrayList;
import java.util.List;

public class Day extends MealList {

    private String name;

    // EFFECTS: creates a new day with the given name and an empty meal list
    public Day(String name) {
        this.name = name;
    }

    // EFFECTS: returns the name of the day
    public String getName() {
        return name;
    }


    // EFFECTS: returns the list of meals of that day formatted to be printed in UI
    public String printableMealList() {
        List returnStatement = new ArrayList<>();
        for (Meal m: this.meals) {
            returnStatement.add(m.getType() + " - " + m.getName());
        }
        return returnStatement.toString();
    }

}
