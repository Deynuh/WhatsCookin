package persistence;

import model.Recipe;
import model.Restaurant;

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


    protected void checkRestaurant(String name, String description, Restaurant restaurant) {
        assertEquals(name, restaurant.getName());
        assertEquals(description, restaurant.getDescription());
    }
}
