package model;

import java.util.ArrayList;
import java.util.List;

public class Pantry extends IngredientList {

    public Pantry() {
        this.ingredients = new ArrayList<>();
    }

    @Override
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
    }

    @Override
    public void removeIngredient(Ingredient inputIngredient) {
        super.removeIngredient(inputIngredient);
    }

    @Override
    public String printIngredientList(String s) {
        s = "The ingredients you already have are: ";
        return super.printIngredientList(s);
    }

    @Override
    public int getIngredientListSize() {
        return super.getIngredientListSize();
    }
}
