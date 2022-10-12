package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

    private Recipe toast;
    private ArrayList<String> toastIngredients;

    @BeforeEach
    void runBefore() {
        toastIngredients = new ArrayList<>();
        toastIngredients.add("Bread");
        toastIngredients.add("Butter");
        toast = new Recipe("Toast", "Meh", 5, toastIngredients);
    }

    @Test
    void testConstructor() {
        assertEquals("Toast", toast.getName());
        assertEquals("Meh", toast.getDescription());
        assertEquals(5, toast.getDuration());
        assertEquals("Bread", toastIngredients.get(0));
        assertEquals("Butter", toastIngredients.get(1));
    }

    @Test
    void testSetName() {
        toast.setName("Better Bread");
        assertEquals("Better Bread", toast.getName());
    }

    @Test
    void testSetDescription() {
        toast.setDescription("Bread toasted in a toaster");
        assertEquals("Bread toasted in a toaster", toast.getDescription());
    }

    @Test
    void testSetDuration() {
        toast.setDuration(10);
        assertEquals(10, toast.getDuration());
    }

    @Test
    void testSetIngredients() {
        ArrayList<String> betterIngredients = new ArrayList<>();
        betterIngredients.add("Whole Wheat Bread");
        betterIngredients.add("Unsalted Butter");
        toast.setIngredients(betterIngredients);
        assertEquals("Whole Wheat Bread", toast.getIngredients().get(0));
        assertEquals("Unsalted Butter", toast.getIngredients().get(1));
    }

}
