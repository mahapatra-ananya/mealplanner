package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DayListTest {

    DayList days;
    Day d1;
    Day d2;
    Meal m1;

    @BeforeEach
    void runBefore() {
        days = new DayList();
        d1 = new Day("d1");
        d2 = new Day("d2");
        m1 = new Meal("m1");
        d1.addMeal(m1);
    }

    @Test
    void testConstructor() {
        assertEquals(0, days.getDays().size());
    }

    @Test
    void testGetDays() {
        ArrayList<Day> daysGot = days.getDays();
        assertEquals(0, daysGot.size());

        days.addDay(d1);
        daysGot = days.getDays();
        assertEquals(1, daysGot.size());

        days.addDay(d2);
        daysGot = days.getDays();
        assertEquals(2, daysGot.size());
    }
}
