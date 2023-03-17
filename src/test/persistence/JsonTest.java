package persistence;

import model.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class JsonTest {
    protected void checkIngredient(String name, double quantity, Ingredient ingredient) {
        assertEquals(name, ingredient.getName());
        assertEquals(quantity, ingredient.getQuantity());
    }

    protected void checkMeal(String name, String type, double length, Meal meal) {
        assertEquals(name, meal.getName());
        assertEquals(type, meal.getType());
        assertEquals(length, meal.getIngredients().size());
    }


}
