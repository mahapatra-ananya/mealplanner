package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public abstract class IngredientList implements Writable {

    protected ArrayList<Ingredient> ingredients;

    // MODIFIES: this
    // EFFECTS: if the input ingredient is already in the ingredient list, its quantity in the list is increased
    //          by the given quantity
    //          otherwise, it is simply added to the ingredient list
    public void addIngredient(Ingredient inputIngredient) {
        if (this.contains(inputIngredient)) {
            Ingredient existingIngredient = this.getSpecificIngredient(inputIngredient);
            existingIngredient.increaseQuantity(inputIngredient.getQuantity());
        } else {
            this.ingredients.add(inputIngredient);
        }
    }


    // REQUIRES: the ingredient must exist in the ingredient list
    // MODIFIES: this
    // EFFECTS: removes the given ingredient from the ingredient list
    protected void removeIngredient(Ingredient inputIngredient) {
        if (this.contains(inputIngredient)) {
            this.ingredients.remove(inputIngredient);
        }
    }


    // EFFECTS: returns all the ingredients in the IngredientList;
    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    // EFFECTS: returns the size of the ingredient list
    public int getIngredientListSize() {
        return getIngredients().size();
    }

    // EFFECTS: if the given ingredient is present in the ingredient list, returns it from the list
    //          otherwise, returns null
    public Ingredient getSpecificIngredient(Ingredient i) {
        Ingredient returnIngredient = null;
        for (Ingredient ingredient: this.ingredients) {
            if (ingredient.getName().equals(i.getName())) {
                returnIngredient = ingredient;
            }
        }
        return returnIngredient;
    }

    // EFFECTS: returns true if the ingredient is in the IngredientList,
    //          false otherwise
    public Boolean contains(Ingredient i) {
        for (Ingredient ingredient: this.ingredients) {
            if (i.getName().equals(ingredient.getName())) {
                return true;
            }
        }
        return false;
    }

    // EFFECTS: returns a list of the ingredients formatted to be printed in UI
    protected String printableIngredientList(String s) {
        List statement = new ArrayList<>();
        for (Ingredient i: this.ingredients) {
            String ingredientWithQuantity = i.getName() + ": " + i.getQuantity();
            statement.add(ingredientWithQuantity);
        }
        return s + statement;
    }

    // EFFECTS: returns ingredients in this meal as a JSON array
    protected JSONArray ingredientsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Ingredient i : ingredients) {
            jsonArray.put(i.toJson());
        }

        return jsonArray;
    }

    // EFFECTS: inherited from Writable
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("ingredients", ingredientsToJson());
        return json;
    }

}
