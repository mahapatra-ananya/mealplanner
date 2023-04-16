package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class ShoppingList extends IngredientList implements Writable {

    // EFFECTS: creates a new shopping list with an empty ingredient list
    public ShoppingList() {
        this.ingredients = new ArrayList<>();
    }

    @Override
    // EFFECTS: inherited from superclass IngredientList
    //          formatted to be printed in UI specifically for a shopping list
    public String printableIngredientList(String s) {
        s = "Your shopping list is: ";
        EventLog.getInstance().logEvent(new Event("Ingredients in shopping list printed to console"));
        return super.printableIngredientList(s);
    }


    // EFFECTS: inherited from superclass IngredientList
    @Override
    public void removeIngredient(Ingredient inputIngredient) {
        super.removeIngredient(inputIngredient);
        EventLog.getInstance().logEvent(new Event(inputIngredient.getQuantity() + " "
                + inputIngredient.getName() + " removed from shopping list"));
    }

    @Override
    public void addIngredient(Ingredient i) {
        super.addIngredient(i);
        EventLog.getInstance().logEvent(new Event(i.getQuantity() + " " + i.getName()
                + " added to shopping list"));
    }

}
