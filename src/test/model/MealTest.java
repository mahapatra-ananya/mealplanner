package model;

import model.Ingredient;
import model.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MealTest {

    private Meal testMeal;
    private Ingredient i1;
    private Ingredient i2;
    private Ingredient i3;

    @BeforeEach
    void runBefore() {

        testMeal = new Meal("testMeal");
        i1 = new Ingredient("i1", 7);
        i2 = new Ingredient("i2", 1);
        i3 = new Ingredient("i3", 100.55);

    }

    @Test
    void testConstructor() {
        assertEquals("testMeal", testMeal.getName());
        assertEquals(0, testMeal.getIngredientListSize());
    }

    @Test
    void testSetType() {
        testMeal.setType("lunch");
        assertEquals("lunch", testMeal.getType());
        testMeal.setType("halloween candy");
        assertEquals("halloween candy", testMeal.getType());
    }

    @Test
    void testAddIngredient() {
        assertEquals(0, testMeal.getIngredientListSize());
        testMeal.addIngredient(i1);
        assertEquals(1, testMeal.getIngredientListSize());
        testMeal.addIngredient(i2);
        testMeal.addIngredient(i3);
        assertEquals(3, testMeal.getIngredientListSize());
    }

    @Test
    void testPrintIngredientList() {
        testMeal.addIngredient(i1);
        assertEquals("The ingredients for this meal are: [i1: 7.0]", testMeal.printIngredientList(""));
        testMeal.addIngredient(i2);
        testMeal.addIngredient(i3);
        assertEquals("The ingredients for this meal are: [i1: 7.0, i2: 1.0, i3: 100.55]",
                testMeal.printIngredientList(""));
    }


}
