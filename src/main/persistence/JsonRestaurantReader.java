package persistence;

import model.RestaurantList;
import model.Restaurant;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonRestaurantReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonRestaurantReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads restaurant list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RestaurantList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurantList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses RecipeBook from JSON object and returns it
    private RestaurantList parseRestaurantList(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        RestaurantList rl = new RestaurantList(name);
        addRestaurants(rl, jsonObject);
        return rl;
    }

    // MODIFIES: rb
    // EFFECTS: parses Recipes from JSON object and adds them to RecipeBook
    private void addRestaurants(RestaurantList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurantList");
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(rl, nextRestaurant);
        }
    }

    // MODIFIES: rb
    // EFFECTS: parses Recipe from JSON object and adds it to RecipeBook
    private void addRestaurant(RestaurantList rl, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        String description = jsonObject.getString("Description");

        Restaurant restaurant = new Restaurant(name, description);
        rl.addRestaurant(restaurant);
    }
}
