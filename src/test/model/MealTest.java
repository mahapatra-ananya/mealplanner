package model;

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
    void testSetTypeWithValidInput() {
        assertTrue(testMeal.setValidType("l"));
        assertEquals("Lunch", testMeal.getType());
        assertTrue(testMeal.setValidType("b"));
        assertEquals("Breakfast", testMeal.getType());
        assertTrue(testMeal.setValidType("d"));
        assertEquals("Dinner", testMeal.getType());
        assertTrue(testMeal.setValidType("s"));
        assertEquals("Snack", testMeal.getType());
    }

    @Test
    void testSetTypeWithInvalidInput() {
        assertFalse(testMeal.setValidType("halloween candy"));
        assertEquals(null, testMeal.getType());
        assertFalse(testMeal.setValidType("A"));
        assertEquals(null, testMeal.getType());
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
        assertEquals("The ingredients for this meal are: [i1: 7.0]", testMeal.printableIngredientList(""));
        testMeal.addIngredient(i2);
        testMeal.addIngredient(i3);
        assertEquals("The ingredients for this meal are: [i1: 7.0, i2: 1.0, i3: 100.55]",
                testMeal.printableIngredientList(""));
    }


}
