package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;


public class Pantry extends IngredientList implements Writable {

    // EFFECTS: creates a new pantry with an empty ingredient list
    public Pantry() {
        this.ingredients = new ArrayList<>();
    }

    // EFFECTS: inherited from superclass IngredientList
    @Override
    public void removeIngredient(Ingredient inputIngredient) {
        super.removeIngredient(inputIngredient);
        EventLog.getInstance().logEvent(new Event(inputIngredient.getQuantity() + " "
                + inputIngredient.getName() + " removed from pantry"));
    }

    @Override
    public void addIngredient(Ingredient i) {
        super.addIngredient(i);
        EventLog.getInstance().logEvent(new Event(i.getQuantity() + " " + i.getName() + " added to pantry"));
    }

    @Override
    public void removeIngredientAt(int i) {
        EventLog.getInstance().logEvent(new Event(ingredients.get(i).getQuantity() + " "
                + ingredients.get(i).getName() + " removed from pantry"));
        super.removeIngredientAt(i);
    }

    @Override
    // EFFECTS: inherited from superclass IngredientList
    //          formatted to be printed in UI specifically for a pantry
    public String printableIngredientList(String s) {
        s = "The ingredients you already have are: ";
        EventLog.getInstance().logEvent(new Event("Ingredients in pantry printed to console"));
        return super.printableIngredientList(s);
    }

}
