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
    }

    @Override
    // EFFECTS: inherited from superclass IngredientList
    //          formatted to be printed in UI specifically for a pantry
    public String printableIngredientList(String s) {
        s = "The ingredients you already have are: ";
        return super.printableIngredientList(s);
    }

}
