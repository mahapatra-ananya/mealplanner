package persistence;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class JsonTest {
    protected void checkIngredient(String name, double quantity, Ingredient ingredient) {
        assertEquals(name, ingredient.getName());
        assertEquals(quantity, ingredient.getQuantity());
    }

    protected void checkMeal(String type, String name, ArrayList<Ingredient> ingredients, Meal meal) {
        assertEquals(name, meal.getName());
        assertEquals(ingredients, meal.getIngredients());
        assertEquals(type, meal.getType());
    }

    protected void checkPantry(ArrayList<Ingredient> ingredients, Pantry pantry) {
        assertEquals(ingredients, pantry.getIngredients());
    }

    protected void checkShoppingList(ArrayList<Ingredient> ingredients, ShoppingList shoppingList) {
        assertEquals(ingredients, shoppingList.getIngredients());
    }

    protected void checkMealList(ArrayList<Meal> meals, MealList mealList) {
        assertEquals(meals, mealList.getMeals());
    }

    protected void checkDay(String name, ArrayList<Meal> meals, Day day) {
        assertEquals(meals, day.getMeals());
        assertEquals(name, day.getName());
    }


}
