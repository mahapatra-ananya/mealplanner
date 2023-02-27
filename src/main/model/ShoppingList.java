package model;

import java.util.ArrayList;

public class ShoppingList extends IngredientList {

    // EFFECTS: creates a new shopping list with an empty ingredient list
    public ShoppingList() {
        this.ingredients = new ArrayList<>();
    }

   /* @Override
    public void addIngredient(Ingredient inputIngredient) {
        super.addIngredient(inputIngredient);
    }

    @Override
    public Ingredient getSpecificIngredient(Ingredient i) {
        return super.getSpecificIngredient(i);
    } */

    @Override
    // EFFECTS: inherited from superclass IngredientList
    //          formatted to be printed in UI specifically for a shopping list
    public String printableIngredientList(String s) {
        s = "Your shopping list is: ";
        return super.printableIngredientList(s);
    }

   /* @Override
    public Boolean contains(Ingredient i) {
        return super.contains(i);
    } */

    @Override
    public void removeIngredient(Ingredient inputIngredient) {
        super.removeIngredient(inputIngredient);
    }

    /*@Override
    public int getIngredientListSize() {
        return super.getIngredientListSize();
    } */
}
