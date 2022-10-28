/*
Note: this file is modelled after the JSonSerializationDemo project provided by the course coordinators.
 */

package persistence;

import model.Restaurant;
import model.RestaurantList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class JsonRestaurantReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonRestaurantReader reader = new JsonRestaurantReader("./data/noSuchFile.json");
        try {
            RestaurantList rl = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurantList() {
        JsonRestaurantReader reader = new JsonRestaurantReader("./data/testReaderEmptyRestaurantList.json");
        try {
            RestaurantList rl = reader.read();
            assertEquals(0, rl.getRestaurantList().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralRestaurantList() {
        JsonRestaurantReader reader = new JsonRestaurantReader("./data/testReaderGeneralRestaurantList.json");
        try {
            RestaurantList rl = reader.read();
            ArrayList<Restaurant> restaurants = rl.getRestaurantList();
            assertEquals(1, restaurants.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}