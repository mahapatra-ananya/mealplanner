package model;

import java.util.ArrayList;
import java.util.List;

public class Meal extends IngredientList {

    private String name;
    private String type;

    public Meal(String name) {
        this.name = name;
        this.ingredients = new ArrayList<>();
    }

    // REQUIRES: type input is either b, l, d, s
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void addIngredient(Ingredient inputIngredient) {
        super.addIngredient(inputIngredient);
    }

    @Override
    public String printIngredientList(String s) {
        s = "The ingredients for this meal are: ";
        return super.printIngredientList(s);
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public int getIngredientListSize() {
        return super.getIngredientListSize();
    }

    @Override
    public List<Ingredient> getIngredients() {
        return super.getIngredients();
    }
}
