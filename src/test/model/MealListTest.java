package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MealListTest {

    MealList testMealList;
    Meal m1;
    Meal m2;
    Meal m3;
    Ingredient i1;
    Ingredient i2;
    Ingredient i3;

    @BeforeEach
    void runBefore() {
        testMealList = new MealList();
        m1 = new Meal("m1");
        m2 = new Meal("m2");
        m3 = new Meal("m3");
        i1 = new Ingredient("i1", 7);
        i2 = new Ingredient("i2", 1);
        i3 = new Ingredient("i3", 100.5);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testMealList.getMeals().size());
    }

    @Test
    void testAddMeals() {
        assertEquals(0, testMealList.getMeals().size());
        testMealList.addMeal(m1);
        assertEquals(1, testMealList.getMeals().size());
        testMealList.addMeal(m2);
        testMealList.addMeal(m3);
        assertEquals(3, testMealList.getMeals().size());
        testMealList.addMeal(m1);
        assertEquals(4, testMealList.getMeals().size());
    }

    @Test
    void testQuantityOfIngredientsRequiredForWeek() {
        m1.addIngredient(i1);
        m2.addIngredient(i1);
        m2.addIngredient(i2);
        m3.addIngredient(i3);

        testMealList.addMeal(m1);
        assertEquals(7, testMealList.quantityOfIngredientRequiredForWeek(i1));
        assertEquals(0, testMealList.quantityOfIngredientRequiredForWeek(i2));
        assertEquals(0, testMealList.quantityOfIngredientRequiredForWeek(i3));

        testMealList.addMeal(m2);
        assertEquals(2*7, testMealList.quantityOfIngredientRequiredForWeek(i1));
        assertEquals(1, testMealList.quantityOfIngredientRequiredForWeek(i2));
        assertEquals(0, testMealList.quantityOfIngredientRequiredForWeek(i3));

        testMealList.addMeal(m3);
        assertEquals(2*7, testMealList.quantityOfIngredientRequiredForWeek(i1));
        assertEquals(1, testMealList.quantityOfIngredientRequiredForWeek(i2));
        assertEquals(1*100.5, testMealList.quantityOfIngredientRequiredForWeek(i3));

        testMealList.addMeal(m3);
        testMealList.addMeal(m3);
        assertEquals(2*7, testMealList.quantityOfIngredientRequiredForWeek(i1));
        assertEquals(1, testMealList.quantityOfIngredientRequiredForWeek(i2));
        assertEquals(3*100.5, testMealList.quantityOfIngredientRequiredForWeek(i3));

    }


}
