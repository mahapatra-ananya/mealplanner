package model;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private String name;
    private List<Meal> meals;

    // EFFECTS: creates a new day with the given name and an empty meal list
    public Day(String name) {
        this.name = name;
        this.meals = new ArrayList<>();
    }


    // EFFECTS: returns the name of the day
    public String getName() {
        return name;
    }

    // EFFECTS: returns a list of the meals for that day
    public List<Meal> getMeals() {
        return meals;
    }

    // MODIFIES: this
    // EFFECTS: adds a meal to the list of meals for that day
    public void addMeal(Meal meal) {
        this.meals.add(meal);
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
