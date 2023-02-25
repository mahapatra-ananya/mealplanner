package model;

import model.Ingredient;
import model.Pantry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PantryTest {

    private Pantry p;
    private Ingredient i1;
    private Ingredient i2;
    private Ingredient i3;

    @BeforeEach
    void runBefore() {
        p = new Pantry();
        i1 = new Ingredient("i1", 7);
        i2 = new Ingredient("i2", 1);
        i3 = new Ingredient("i3", 100.55);
    }

    @Test
    void testConstructor() {
        assertEquals(0, p.getIngredientListSize());
    }

    @Test
    void testAddIngredientNotAlreadyThere() {
        assertEquals(0, p.getIngredientListSize());
        p.addIngredient(i1);
        assertEquals(1, p.getIngredientListSize());
        p.addIngredient(i2);
        p.addIngredient(i3);
        assertEquals(3, p.getIngredientListSize());
    }

    @Test
    void testAddIngredientAlreadyThere() {
        assertEquals(0, p.getIngredientListSize());
        p.addIngredient(i1);
        assertEquals(1, p.getIngredientListSize());
        p.addIngredient(i1);
        assertEquals(1, p.getIngredientListSize());
        assertEquals(14, p.getSpecificIngredient(i1).getQuantity());

    }

    @Test
    void testRemoveIngredient() {
        p.addIngredient(i1);
        p.addIngredient(i2);
        p.addIngredient(i3);
        assertEquals(3, p.getIngredientListSize());
        p.removeIngredient(i2);
        assertEquals(2, p.getIngredientListSize());
        p.removeIngredient(i1);
        p.removeIngredient(i3);
        assertEquals(0, p.getIngredientListSize());
        p.removeIngredient(i3);
        assertEquals(0, p.getIngredientListSize());
    }

    /*@Test
    void testRemoveIngredientOnlyDecreasingQuantity() {
        p.addIngredient(i1);
        p.addIngredient(i2);
        p.addIngredient(i3);
        assertEquals(3, p.getIngredientListSize());
        Ingredient anotheri3 = new Ingredient("i3", 10);
        p.removeIngredient(anotheri3);
        assertEquals(90.5, p.getSpecificIngredient(anotheri3).getQuantity());
    }*/

    @Test
    void testGetSpecificIngredient() {
        p.addIngredient(i1);
        p.addIngredient(i2);
        p.addIngredient(i3);
        assertEquals(i1, p.getSpecificIngredient(i1));
        assertEquals(i3, p.getSpecificIngredient(i3));
    }

    @Test
    void testPrintIngredientList() {
        p.addIngredient(i1);
        assertEquals("The ingredients you already have are: [i1: 7.0]", p.printIngredientList(""));
        p.addIngredient(i2);
        p.addIngredient(i3);
        assertEquals("The ingredients you already have are: [i1: 7.0, i2: 1.0, i3: 100.55]",
                p.printIngredientList(""));
    }

    @Test
    void testContains() {
        assertFalse(p.contains(i1));
        p.addIngredient(i1);
        p.addIngredient(i2);
        assertTrue(p.contains(i1));
        assertTrue(p.contains(i2));
        assertFalse(p.contains(i3));
    }

}
