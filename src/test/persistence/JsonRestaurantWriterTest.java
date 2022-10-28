/*
Note: this file is modelled after the JSonSerializationDemo project provided by the course coordinators.
 */

package persistence;

import model.Recipe;
import model.RecipeBook;
import model.Restaurant;
import model.RestaurantList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonRestaurantWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            RestaurantList rl = new RestaurantList("restaurantList");
            JsonRecipeWriter writer = new JsonRecipeWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyWorkroom() {
        try {
            RestaurantList rl = new RestaurantList("Restaurant List");
            JsonRestaurantWriter writer =
                    new JsonRestaurantWriter("./data/testWriterEmptyRestaurantList.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonRestaurantReader reader = new JsonRestaurantReader("./data/testWriterEmptyRestaurantList.json");
            rl = reader.read();
            assertEquals("Restaurant List", rl.getName());
            assertEquals(0, rl.getRestaurantList().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralRecipeBook() {
        try {
            RestaurantList rl = new RestaurantList("Restaurant List");
            rl.addRestaurant(new Restaurant("a","b"));
            JsonRestaurantWriter writer =
                    new JsonRestaurantWriter("./data/testWriterGeneralRestaurantList.json");
            writer.open();
            writer.write(rl);
            writer.close();

            JsonRestaurantReader reader =
                    new JsonRestaurantReader("./data/testWriterGeneralRestaurantList.json");
            rl = reader.read();
            assertEquals("Restaurant List", rl.getName());
            ArrayList<Restaurant> restaurants = rl.getRestaurantList();
            assertEquals(1, restaurants.size());
            checkRestaurant("a","b", restaurants.get(0));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}