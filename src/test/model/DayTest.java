package model;
import model.Meal;
import model.Day;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DayTest {

    Day day;
    Meal m1;
    Meal m2;
    Meal m3;

    @BeforeEach
    void runBefore() {
        day = new Day("Sunday");
        m1 = new Meal("m1");
        m2 = new Meal("m2");
        m3 = new Meal("m3");
    }

    @Test
    void testConstructor() {
        assertEquals("Sunday", day.getDay());
        assertEquals(0, day.getMeals().size());
    }

    @Test
    void testAddMeals() {
        assertEquals(0, day.getMeals().size());
        day.addMeal(m1);
        assertEquals(1, day.getMeals().size());
        day.addMeal(m2);
        day.addMeal(m3);
        assertEquals(3, day.getMeals().size());
        day.addMeal(m1);
        assertEquals(4, day.getMeals().size());
    }

    @Test
    void testPrintMealList() {
        day.addMeal(m1);
        //assertEquals("[m1]", day.printMealList());
        day.addMeal(m2);
        day.addMeal(m3);
        assertEquals("[m1, m2, m3]", day.printMealList());
        day.addMeal(m1);
        assertEquals("[m1, m2, m3, m1]", day.printMealList());
    }

}