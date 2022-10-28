package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

// Represents a list of restaurants
public class RestaurantList implements Writable {
    private final Random random = new Random();
    private String name;
    private ArrayList<Restaurant> restaurantList;

    public RestaurantList(String name) {
        this.name = name;
        restaurantList = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Restaurant> getRestaurantList() {
        return this.restaurantList;
    }

    public void setRestaurantList(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    // MODIFIES: this
    // EFFECTS: adds a restaurant to the restaurant list
    public void addRestaurant(Restaurant restaurant) {
        restaurantList.add(restaurant);
    }

    // MODIFIES: this
    // REQUIRES: a non-empty restaurant list
    // EFFECTS: deletes a restaurant from the restaurant list
    public void removeRestaurant(int index) {
        if (index == 0) {
            restaurantList.remove(0);
        } else {
            restaurantList.remove(index);
        }
    }

    // REQUIRES: a non-empty restaurant list
    // EFFECTS:  returns a random recipe from restaurant list
    public Restaurant randomRestaurant() {
        try {
            return restaurantList.get(random.nextInt(restaurantList.size()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("restaurantList", restaurantListToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray restaurantListToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant r : restaurantList) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
