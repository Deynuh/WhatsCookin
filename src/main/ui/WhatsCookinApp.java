package ui;

import model.Recipe;
import model.Restaurant;
import model.RecipeBook;
import model.RestaurantList;

import java.util.Scanner;

// Recipe-managing application called "What's Cookin'"
public class WhatsCookinApp {
    private RecipeBook recipeList;
    private RestaurantList restaurantList;
    private Scanner key;

    // EFFECTS: runs the application
    public WhatsCookinApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {

    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {

    }

    // MODIFIES: this
    // EFFECTS: initializes recipe book and recipe list
    private void init() {

    }

    // EFFECTS: displays menu of options
    private void displayMenu() {

    }

    // EFFECTS: !!!
    private void chooseRandom() {

    }

    // REQUIRES: a non-empty recipe book
    // EFFECTS:  returns a random recipe from recipe book
    private Recipe randomRecipe() {

    }

    // REQUIRES: a non-empty restaurant list
    // EFFECTS:  returns a random recipe from restaurant list
    private Restaurant randomRestaurant() {

    }

    // MODIFIES: this
    // EFFECTS:  adds a restaurant to the restaurant list
    private void addRestaurant(Restaurant restaurant) {

    }

    // MODIFIES: this
    // EFFECTS:  adds a recipe to the recipe book
    private void addRecipe(Recipe recipe) {
        System.out.println("Recipe name: ");
        Recipe newRecipe = new Recipe();
        recipe.setName(key.next()); //try nextLine if buggy

        System.out.println("Recipe description: \n Input 's' to skip");
        recipe.setDescription(key.next().equals("s") ? "No description." : key.next());

        System.out.println("Recipe duration: \n Input 's' to skip (will default to 0 minutes)");
        recipe.setDuration(key.next().equals("s") ? 0 : key.nextInt());

        System.out.println("Recipe ingredients: \n Input 's' to skip");
        recipe.setDescription(key.next().equals("s") ? "No ingredients listed." : key.next());

        //do i ceebs this
        System.out.println("----------------------------------------");
        System.out.println("Confirm information:");
        System.out.println("Recipe name: " + recipe.getName());
        System.out.println("       description: " + recipe.getDescription());
        System.out.println("       duration: " + recipe.getDuration());
        System.out.println("       ingredients: " + recipe.getIngredients());


    }

    // EFFECTS:  displays all the details of a recipe
    private void viewRecipe(Recipe recipe) {
        System.out.println("Name: " + recipe.getName());
        System.out.println("Description: " + recipe.getDescription());
        System.out.println("Duration: " + recipe.getDuration());
        System.out.println("Ingredients: ");
        for (String i : recipe.getIngredients()) {
            System.out.println(i);
        }
    }

    // EFFECTS:  displays a restaurant's name and cuisine type
    private void viewRestaurant(Restaurant restaurant) {
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Cuisine Type: " + restaurant.getCuisine());
    }

}
