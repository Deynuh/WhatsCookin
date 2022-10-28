/*
Note: this file is modelled after the JSonSerializationDemo project provided by the course coordinators.
 */

package persistence;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonRecipeReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonRecipeReader reader = new JsonRecipeReader("./data/noSuchFile.json");
        try {
            RecipeBook rb = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyWorkRoom() {
        JsonRecipeReader reader = new JsonRecipeReader("./data/testReaderEmptyRecipeBook.json");
        try {
            RecipeBook rb = reader.read();
            assertEquals(0, rb.getRecipeBook().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralWorkRoom() {
        JsonRecipeReader reader = new JsonRecipeReader("./data/testReaderGeneralRecipeBook.json");
        try {
            RecipeBook rb = reader.read();
            ArrayList<Recipe> recipes = rb.getRecipeBook();
            assertEquals(2, recipes.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}