package persistence;

import model.Recipe;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkRecipe(String name, String description, int duration,
                               ArrayList<String> ingredients, Recipe recipe) {
        assertEquals(name, recipe.getName());
        assertEquals(description, recipe.getDescription());
        assertEquals(duration, recipe.getDuration());
        assertEquals(ingredients, recipe.getIngredients());
    }
}
