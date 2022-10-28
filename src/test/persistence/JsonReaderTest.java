package persistence;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            RecipeBook rb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyWorkRoom.json");
        try {
            RecipeBook rb = reader.read();
            assertEquals(0, rb.getRecipeBook().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralWorkRoom.json");
        try {
            RecipeBook rb = reader.read();
            ArrayList<Recipe> recipes = rb.getRecipeBook();
            assertEquals(2, recipes.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}