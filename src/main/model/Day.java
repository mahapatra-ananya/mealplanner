package model;

import java.util.ArrayList;
import java.util.List;

public class Day {

    private String day;
    private List<Meal> meals;

    public Day(String day) {
        this.day = day;
        this.meals = new ArrayList<>();
    }

    public String getDay() {
        return day;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void addMeal(Meal meal) {
        this.meals.add(meal);
    }

    public String printMealList() {
        List returnStatement = new ArrayList<>();
        for (Meal m: this.meals) {
            returnStatement.add(m.getName());
        }
        return returnStatement.toString();
    }

}
