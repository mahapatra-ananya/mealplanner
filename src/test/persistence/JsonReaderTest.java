package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader mealReader = new JsonReader("./data/noSuchFile.json");
        try {
            Meal meal = mealReader.readMeal();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }

        JsonReader pantryReader = new JsonReader("./data/noSuchFile.json");
        try {
            Pantry pantry = pantryReader.readPantry();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }

        JsonReader shoppingListReader = new JsonReader("./data/noSuchFile.json");
        try {
            ShoppingList shoppingList = shoppingListReader.readShoppingList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }

        JsonReader dayReader = new JsonReader("./data/noSuchFile.json");
        try {
            Day day = dayReader.readDay();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }

        JsonReader dayListReader = new JsonReader("./data/noSuchFile.json");
        try {
            DayList days = dayListReader.readDayList();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDataFile() {
        JsonReader mealReader = new JsonReader("./data/testReaderEmptyMeal.json");
        try {
            Meal meal = mealReader.readMeal();
            assertEquals("My meal", meal.getName());
            assertEquals("Meal type", meal.getType());
            assertEquals(0, meal.getIngredientListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader pantryReader = new JsonReader("./data/testReaderEmptyPantry.json");
        try {
            Pantry pantry = pantryReader.readPantry();
            assertEquals(0, pantry.getIngredientListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader shoppingListReader = new JsonReader("./data/testReaderEmptyShoppingList.json");
        try {
            ShoppingList shoppingList = shoppingListReader.readShoppingList();
            assertEquals(0, shoppingList.getIngredientListSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader dayReader = new JsonReader("./data/testReaderEmptyDay.json");
        try {
            Day day = dayReader.readDay();
            assertEquals("My day", day.getName());
            assertEquals(0, day.getMeals().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader dayListReader = new JsonReader("./data/testReaderEmptyDayList.json");
        try {
            DayList days = dayListReader.readDayList();
            assertEquals(0, days.getDays().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }

    @Test
    void testReaderGeneralDataFile() {
        JsonReader mealReader = new JsonReader("./data/testReaderGeneralMeal.json");
        try {
            Meal meal = mealReader.readMeal();
            assertEquals("My meal", meal.getName());
            assertEquals("Meal type", meal.getType());
            List<Ingredient> ingredients = meal.getIngredients();
            assertEquals(2, ingredients.size());
            checkIngredient("i1", 0, ingredients.get(0));
            checkIngredient("i2", 33, ingredients.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader pantryReader = new JsonReader("./data/testReaderGeneralPantry.json");
        try {
            Pantry pantry = pantryReader.readPantry();
            List<Ingredient> ingredients = pantry.getIngredients();
            assertEquals(3, ingredients.size());
            checkIngredient("i1", 0, ingredients.get(0));
            checkIngredient("i2", 35, ingredients.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader shoppingListReader = new JsonReader("./data/testReaderGeneralShoppingList.json");
        try {
            ShoppingList shoppingList = shoppingListReader.readShoppingList();
            List<Ingredient> ingredients = shoppingList.getIngredients();
            assertEquals(2, ingredients.size());
            checkIngredient("i1", 0, ingredients.get(0));
            checkIngredient("random ingredient", 22, ingredients.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader dayReader = new JsonReader("./data/testReaderGeneralDay.json");
        try {
            Day day = dayReader.readDay();
            assertEquals("My Day", day.getName());
            List<Meal> meals = day.getMeals();
            assertEquals(3, meals.size());
            checkMeal("m1", "t1", 2, meals.get(0));
            checkMeal("m2", "t2", 0, meals.get(1));
            checkMeal("m3", "t3", 2, meals.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

        JsonReader dayListReader = new JsonReader("./data/testReaderGeneralDayList.json");
        try {
            DayList days = dayListReader.readDayList();
            ArrayList<Day> theDays = days.getDays();
            assertEquals(3, theDays.size());
            checkDay("d1", 2, theDays.get(0));
            checkDay("d2", 0, theDays.get(1));
            checkDay("d3", 2, theDays.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
