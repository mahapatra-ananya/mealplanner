package model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends IngredientList {

    public ShoppingList() {
        this.ingredients = new ArrayList<>();
    }

    @Override
    public void addIngredient(Ingredient inputIngredient) {
        super.addIngredient(inputIngredient);
    }

    @Override
    public Ingredient getSpecificIngredient(Ingredient i) {
        return super.getSpecificIngredient(i);
    }

    @Override
    public String printIngredientList(String s) {
        s = "Your shopping list is: ";
        return super.printIngredientList(s);
    }

    @Override
    public Boolean contains(Ingredient i) {
        return super.contains(i);
    }

    @Override
    public void removeIngredient(Ingredient inputIngredient) {
        super.removeIngredient(inputIngredient);
    }

    @Override
    public int getIngredientListSize() {
        return super.getIngredientListSize();
    }
}
