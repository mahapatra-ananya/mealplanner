package persistence;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class JsonTest {
    protected void checkIngredient(String name, double quantity, Ingredient ingredient) {
        assertEquals(name, ingredient.getName());
        assertEquals(quantity, ingredient.getQuantity());
    }

    /*private void checkIngredientList(ArrayList<Ingredient> ingList1, IngredientList ingList2) {
        int index = 0;
        for (Ingredient i: ingList2.getIngredients()) {
            Ingredient currentIngredient = ingList2.getIngredients().get(index);
            checkIngredient(currentIngredient.getName(), currentIngredient.getQuantity(), ingList1.get(index));
            index++;
        }
    } */

    protected void checkMeal(String name, String type, double length, Meal meal) {
        assertEquals(name, meal.getName());
        assertEquals(type, meal.getType());
        assertEquals(length, meal.getIngredients().size());
       // checkIngredientList(ingredients, meal);
    }

   /* protected void checkPantry(ArrayList<Ingredient> ingredients, Pantry pantry) {
        assertEquals(ingredients.size(), pantry.getIngredients().size());
        checkIngredientList(ingredients, pantry);
    }

    protected void checkShoppingList(ArrayList<Ingredient> ingredients, ShoppingList shoppingList) {
        assertEquals(ingredients.size(), shoppingList.getIngredients().size());
        checkIngredientList(ingredients, shoppingList);
    }

    protected void checkMealList(ArrayList<Meal> meals, MealList mealList) {
        assertEquals(meals, mealList.getMeals());
    }

    protected void checkDay(String name, ArrayList<Meal> meals, Day day) {
        assertEquals(meals, day.getMeals());
        assertEquals(name, day.getName());
    } */


}
