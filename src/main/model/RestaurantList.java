package model;

import java.util.HashMap;

// Represents a list of restaurants
public class RestaurantList {

    private Restaurant restaurant;
    private HashMap restaurantList; //(String name, String cuisine)

    public RestaurantList() {
        this.restaurantList = restaurantList;
    }

    public HashMap getRestaurantList() {
        return this.restaurantList;
    }

    // MODIFIES: this
    // EFFECTS: adds a restaurant to the restaurant list
    public void addRestaurant(Restaurant restaurant) {
        restaurantList.put(restaurant.getName(), restaurant.getCuisine());
    }

    // MODIFIES: this
    // REQUIRES: a non-empty restaurant list
    // EFFECTS: deletes a restaurant from the restaurant list
    public void deleteRestaurant(Restaurant restaurant) {
        restaurantList.remove(restaurant.getName());
    }
}
