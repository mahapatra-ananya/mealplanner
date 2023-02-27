package model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ShoppingListTest {

    private ShoppingList s;
    private Ingredient i1;
    private Ingredient i2;
    private Ingredient i3;

    @BeforeEach
    void runBefore() {
        s = new ShoppingList();
        i1 = new Ingredient("i1", 7);
        i2 = new Ingredient("i2", 1);
        i3 = new Ingredient("i3", 100.55);
    }

    @Test
    void testConstructor() {
        assertEquals(0, s.getIngredientListSize());
    }

    @Test
    void testAddIngredientNotAlreadyThere() {
        assertEquals(0, s.getIngredientListSize());
        s.addIngredient(i1);
        assertEquals(1, s.getIngredientListSize());
        s.addIngredient(i2);
        s.addIngredient(i3);
        assertEquals(3, s.getIngredientListSize());
    }

    @Test
    void testAddIngredientAlreadyThere() {
        assertEquals(0, s.getIngredientListSize());
        s.addIngredient(i1);
        assertEquals(1, s.getIngredientListSize());
        s.addIngredient(i1);
        assertEquals(1, s.getIngredientListSize());
        assertEquals(14, s.getSpecificIngredient(i1).getQuantity());

    }

    @Test
    void testRemoveIngredient() {
        s.addIngredient(i1);
        s.addIngredient(i2);
        s.addIngredient(i3);
        assertEquals(3, s.getIngredientListSize());
        s.removeIngredient(i2);
        assertEquals(2, s.getIngredientListSize());
        s.removeIngredient(i1);
        s.removeIngredient(i3);
        assertEquals(0, s.getIngredientListSize());
        s.removeIngredient(i3);
        assertEquals(0, s.getIngredientListSize());
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
        s.addIngredient(i1);
        s.addIngredient(i2);
        s.addIngredient(i3);
        assertEquals(i1, s.getSpecificIngredient(i1));
        assertEquals(i3, s.getSpecificIngredient(i3));
    }

    @Test
    void testPrintIngredientList() {
        s.addIngredient(i1);
        assertEquals("Your shopping list is: [i1: 7.0]", s.printableIngredientList(""));
        s.addIngredient(i2);
        s.addIngredient(i3);
        assertEquals("Your shopping list is: [i1: 7.0, i2: 1.0, i3: 100.55]",
                s.printableIngredientList(""));
    }

    @Test
    void testContains() {
        assertFalse(s.contains(i1));
        s.addIngredient(i1);
        s.addIngredient(i2);
        assertTrue(s.contains(i1));
        assertTrue(s.contains(i2));
        assertFalse(s.contains(i3));
    }

}
