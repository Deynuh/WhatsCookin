package persistence;

import model.Recipe;
import model.RecipeBook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            RecipeBook rb = new RecipeBook("recipeBook");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RecipeBook rb = new RecipeBook("Recipe Book");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyWorkroom.json");
            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyWorkroom.json");
            rb = reader.read();
            assertEquals("Recipe Book", rb.getName());
            assertEquals(0, rb.getRecipeBook().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRecipeBook() {
        try {
            RecipeBook rb = new RecipeBook("Recipe Book");
            rb.addRecipe(new Recipe("a","b",1,null));
            ArrayList<String> test = new ArrayList<>();
            test.add("1st ingredient");
            test.add("2nd ingredient");
            rb.addRecipe(new Recipe("D","E",2, test));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralWorkroom.json");
            writer.open();
            writer.write(rb);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralWorkroom.json");
            rb = reader.read();
            assertEquals("Recipe Book", rb.getName());
            ArrayList<Recipe> recipes = rb.getRecipeBook();
            assertEquals(2, recipes.size());
            checkRecipe("a","b",1,null, recipes.get(0));
            checkRecipe("D","E",2, recipes.get(1).getIngredients(), recipes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}