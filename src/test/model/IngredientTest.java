package model;

import model.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    private Ingredient i;


    @BeforeEach
    void runBefore() {
        i = new Ingredient("i", 100);
    }

    @Test
    void testConstructor() {
        assertEquals("i", i.getName());
        assertEquals(100, i.getQuantity());
    }

    @Test
    void testIncreaseQuantity() {
        i.increaseQuantity(1);
        assertEquals(101, i.getQuantity());
        i.increaseQuantity(35);
        assertEquals(136, i.getQuantity());
        i.increaseQuantity(0);
        assertEquals(136, i.getQuantity());
        i.increaseQuantity(136);
        assertEquals(272, i.getQuantity());
    }

    @Test
    void testDecreaseQuantity() {
        i.decreaseQuantity(1);
        assertEquals(99, i.getQuantity());
        i.decreaseQuantity(35);
        assertEquals(64, i.getQuantity());
        i.decreaseQuantity(0);
        assertEquals(64, i.getQuantity());
        i.decreaseQuantity(64);
        assertEquals(0, i.getQuantity());
    }


}
