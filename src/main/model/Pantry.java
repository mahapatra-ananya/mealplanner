package model;

import java.util.ArrayList;

public class Pantry extends IngredientList {

    // EFFECTS: creates a new pantry with an empty ingredient list
    public Pantry() {
        this.ingredients = new ArrayList<>();
    }

   /* @Override
    public Boolean contains(Ingredient i) {
        return super.contains(i);
    }

    @Override
    public Ingredient getSpecificIngredient(Ingredient i) {
        return super.getSpecificIngredient(i);
    }

    @Override
    public void addIngredient(Ingredient inputIngredient) {
        super.addIngredient(inputIngredient);
    } */

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

   /* @Override
    public int getIngredientListSize() {
        return super.getIngredientListSize();
    }  */
}
