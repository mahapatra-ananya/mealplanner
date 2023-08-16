package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Meal meal = new Meal("My meal");
            meal.setTypeForJson("Meal type");
            meal.addIngredient(new Ingredient("i", 1));
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }

        try {
            Pantry pantry = new Pantry();
            pantry.addIngredient(new Ingredient("i", 1));
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }

        try {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.addIngredient(new Ingredient("i", 1));
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }

        try {
            Day day = new Day("My day");
            Meal meal = new Meal("My meal");
            meal.setTypeForJson("Meal type");
            meal.addIngredient(new Ingredient("i", 1));
            day.addMeal(meal);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }

        try {
            DayList days = new DayList();
            Meal meal = new Meal("My meal");
            Meal mealAgain = new Meal("My meal again");
            Day day = new Day("d1");
            day.addMeal(meal);
            day.addMeal(mealAgain);
            days.addDay(day);
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }

    }


    @Test
    void testWriterEmptyMealFile() {
        try {
            Meal meal = new Meal("My meal");
            meal.setTypeForJson("Meal type");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMeal.json");
            writer.open();
            writer.write(meal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMeal.json");
            meal = reader.readMeal();
            assertEquals("My meal", meal.getName());
            assertEquals("Meal type", meal.getType());
            assertEquals(0, meal.getIngredientListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyPantryFile() {
        try {
            Pantry pantry = new Pantry();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPantry.json");
            writer.open();
            writer.write(pantry);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPantry.json");
            pantry = reader.readPantry();
            assertEquals(0, pantry.getIngredientListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyShoppingListFile() {
        try {
            ShoppingList shoppingList = new ShoppingList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyShoppingList.json");
            writer.open();
            writer.write(shoppingList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyShoppingList.json");
            shoppingList = reader.readShoppingList();
            assertEquals(0, shoppingList.getIngredientListSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyDayFile() {
        try {
            Day day = new Day("My day");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDay.json");
            day = reader.readDay();
            assertEquals("My day", day.getName());
            assertEquals(0, day.getMeals().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptyDayListFile() {
        try {
            DayList days = new DayList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDayList.json");
            writer.open();
            writer.write(days);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDayList.json");
            days = reader.readDayList();
            assertEquals(0, days.getDays().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralMealFile() {
        try {
            Meal meal = new Meal("My meal");
            meal.setTypeForJson("Meal type");
            meal.addIngredient(new Ingredient("i1", 1));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMeal.json");
            writer.open();
            writer.write(meal);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMeal.json");
            meal = reader.readMeal();
            assertEquals("My meal", meal.getName());
            assertEquals("Meal type", meal.getType());
            assertEquals(1, meal.getIngredientListSize());
            checkIngredient("i1", 1, meal.getIngredients().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralPantryFile() {
        try {
            Pantry pantry = new Pantry();
            pantry.addIngredient(new Ingredient("i", 1));
            pantry.addIngredient(new Ingredient("ii", 77));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPantry.json");
            writer.open();
            writer.write(pantry);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPantry.json");
            pantry = reader.readPantry();
            assertEquals(2, pantry.getIngredientListSize());
            checkIngredient("i", 1, pantry.getIngredients().get(0));
            checkIngredient("ii", 77, pantry.getIngredients().get(1));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralShoppingListFile() {
        try {
            ShoppingList shoppingList = new ShoppingList();
            shoppingList.addIngredient(new Ingredient("ing1", 3.33));
            shoppingList.addIngredient(new Ingredient("ing2", 0));
            shoppingList.addIngredient(new Ingredient("ing3", 1383480));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralShoppingList.json");
            writer.open();
            writer.write(shoppingList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralShoppingList.json");
            shoppingList = reader.readShoppingList();
            assertEquals(3, shoppingList.getIngredientListSize());
            checkIngredient("ing1", 3.33, shoppingList.getIngredients().get(0));
            checkIngredient("ing2", 0, shoppingList.getIngredients().get(1));
            checkIngredient("ing3", 1383480, shoppingList.getIngredients().get(2));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDayFile() {
        try {
            Day day = new Day("My day");
            Meal m1 = new Meal("m1");
            m1.setTypeForJson("t1");
            Meal m2 = new Meal("m2");
            m2.setTypeForJson("t2");
            m2.addIngredient(new Ingredient("ingredient", 20));
            day.addMeal(m1);
            day.addMeal(m2);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDay.json");
            writer.open();
            writer.write(day);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDay.json");
            day = reader.readDay();
            assertEquals("My day", day.getName());
            assertEquals(2, day.getMeals().size());
            checkMeal("m1", "t1", 0, day.getMeals().get(0));
            checkMeal("m2", "t2", 1, day.getMeals().get(1));;
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDayListFile() {
        try {
            DayList days = new DayList();
            Meal meal = new Meal("My meal");
            meal.setTypeForJson("b");
            Meal mealAgain = new Meal("My meal again");
            mealAgain.setTypeForJson("l");
            Day d1 = new Day("d1");
            d1.addMeal(meal);
            d1.addMeal(mealAgain);
            Day d2 = new Day("d2");
            Day d3 = new Day("d3");
            d3.addMeal(meal);
            d3.addMeal(mealAgain);
            days.addDay(d1);
            days.addDay(d2);
            days.addDay(d3);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDayList.json");
            writer.open();
            writer.write(days);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDayList.json");
            days = reader.readDayList();
            List<Day> theDays = days.getDays();
            assertEquals(3, theDays.size());
            checkDay("d1", 2, theDays.get(0));
            checkDay("d2", 0, theDays.get(1));
            checkDay("d3", 2, theDays.get(2));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
