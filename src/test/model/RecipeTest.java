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
        toast = new Recipe("Toast", "Bread toasted in a toaster", 5, toastIngredients);
    }

    @Test
    void testConstructor() {
        assertEquals("Toast", toast.getName());
        assertEquals("Bread toasted in a toaster", toast.getDescription());
        assertEquals(5, toast.getDuration());
        assertEquals("Bread", toastIngredients.get(0));
        assertEquals("Butter", toastIngredients.get(1));
    }

}
