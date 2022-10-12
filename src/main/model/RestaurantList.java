package model;

import java.util.ArrayList;
import java.util.Random;

// Represents a list of restaurants
public class RestaurantList {
    private final Random random = new Random();
    private final ArrayList<Restaurant> restaurantList;

    public RestaurantList(ArrayList<Restaurant> restaurantList) {
        this.restaurantList = restaurantList;
    }

    public ArrayList<Restaurant> getRestaurantList() {
        return this.restaurantList;
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
        restaurantList.remove(index);
    }

    // REQUIRES: a non-empty restaurant list
    // EFFECTS:  returns a random recipe from restaurant list
    public Restaurant randomRestaurant() {
        try {
            return restaurantList.get(random.nextInt(restaurantList.size()));
        } catch (Exception e) {
            System.out.println("There was a problem with the Random Restaurant Generator.");
            return null;
        }
    }
}
