package model;

import java.util.ArrayList;
import java.util.List;

public abstract class IngredientList {

    protected List<Ingredient> ingredients;

    // cannot be instantiated because abstract

    protected void addIngredient(Ingredient inputIngredient) {
        Boolean alreadyExists = false;
        for (Ingredient existingIngredient: this.ingredients) {
            if (inputIngredient.getName().equals(existingIngredient.getName())) {
                alreadyExists = true;
                existingIngredient.increaseQuantity(inputIngredient.getQuantity());
            }
        }
        if (!alreadyExists) {
            this.ingredients.add(inputIngredient);
        }
    }


    protected void removeIngredient(Ingredient inputIngredient) {
        for (Ingredient existingIngredient: this.ingredients) {
            if (inputIngredient.getName().equals(existingIngredient.getName())) {
                this.ingredients.remove(inputIngredient);
                return;
            }
        }
        //return;
    }


    protected List<Ingredient> getIngredients() {
        return this.ingredients;
    }

    protected Ingredient getSpecificIngredient(Ingredient i) {
        Ingredient returnIngredient = null;
        for (Ingredient ingredient: this.ingredients) {
            if (ingredient.getName().equals(i.getName())) {
                returnIngredient = ingredient;
            }
        }
        return returnIngredient;
    }

    protected Boolean contains(Ingredient i) {
        for (Ingredient ingredient: this.ingredients) {
            if (i.getName().equals(ingredient.getName())) {
                return true;
            }
        }
        return false;
    }

    /*protected List<String> getIngredientNames() {
        List returnStatement = new ArrayList<>();
        for (Ingredient i: this.ingredients) {
            returnStatement.add(i.getName());
        }
        return returnStatement;
    }

    protected List<String> getIngredientQuantities() {
        List returnStatement = new ArrayList<>();
        for (Ingredient i: this.ingredients) {
            returnStatement.add(i.getQuantity());
        }
        return returnStatement;
    }*/

    protected String printIngredientList(String s) {
        List statement = new ArrayList<>();
        for (Ingredient i: this.ingredients) {
            String ingredientWithQuantity = i.getName() + ": " + i.getQuantity();
            statement.add(ingredientWithQuantity);
        }
        String returnValue = s + statement;
        return returnValue;
    }

    protected int getIngredientListSize() {
        return getIngredients().size();
    }

}
