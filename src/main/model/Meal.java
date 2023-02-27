package model;

import java.util.ArrayList;

public class Meal extends IngredientList {

    private String name;
    private String type;

    // EFFECTS: creates a meal with the given name and an empty ingredient list
    public Meal(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: sets the meal's type according to inputted type if valid
    //          and returns whether the input is valid or not
    public Boolean setValidType(String s) {
        Boolean validType = false;
        if (s.equals("b")) {
            this.type = "Breakfast";
            validType = true;
        } else if (s.equals("l")) {
            this.type = "Lunch";
            validType = true;
        } else if (s.equals("d")) {
            this.type = "Dinner";
            validType = true;
        } else if (s.equals("s")) {
            this.type = "Snack";
            validType = true;
        }
        return validType;
    }

   /* @Override
    // MODIFIES and EFFECTS: inherited from superclass IngredientList
    public void addIngredient(Ingredient inputIngredient) {
        super.addIngredient(inputIngredient);
    } */

    @Override
    // EFFECTS: inherited from superclass IngredientList
    //          formatted to be printed in UI specifically for a meal
    public String printableIngredientList(String s) {
        s = "The ingredients for this meal are: ";
        return super.printableIngredientList(s);
    }

    // EFFECTS: returns the name of the meal
    public String getName() {
        return name;
    }

    // EFFECTS: returns the type of the meal
    public String getType() {
        return type;
    }

    /*@Override
    public int getIngredientListSize() {
        return super.getIngredientListSize();
    }

    @Override
    public List<Ingredient> getIngredients() {
        return super.getIngredients();
    } */
}
