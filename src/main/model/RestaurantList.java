package model;

import java.util.ArrayList;
import java.util.Random;

// Represents a list of restaurants
public class RestaurantList {

    private Restaurant restaurant;
    private Random random = new Random();
    private ArrayList<Restaurant> restaurantList;

    public RestaurantList() {
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
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantList.remove(restaurant);
    }

    // REQUIRES: a non-empty restaurant list
    // EFFECTS:  returns a random recipe from restaurant list
    public Restaurant randomRestaurant() {
        return restaurantList.get(random.nextInt(restaurantList.size()));
    }
}
