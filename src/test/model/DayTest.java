package model;
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
        m1.setValidType("b");
        m2.setValidType("s");
        m3.setValidType("d");
    }

    @Test
    void testConstructor() {
        assertEquals("Sunday", day.getName());
        assertEquals(0, day.getMeals().size());
    }

    @Test
    void testPrintMealList() {
        day.addMeal(m1);
        assertEquals("[Breakfast - m1]", day.printableMealList());
        day.addMeal(m2);
        day.addMeal(m3);
        assertEquals("[Breakfast - m1, Snack - m2, Dinner - m3]", day.printableMealList());
        day.addMeal(m1);
        assertEquals("[Breakfast - m1, Snack - m2, Dinner - m3, Breakfast - m1]", day.printableMealList());
    }

}
