package ui;

import model.Recipe;
import model.Restaurant;
import model.RecipeBook;
import model.RestaurantList;

import java.util.Scanner;
import java.util.ArrayList;

// Recipe-managing application called "What's Cookin'"
public class WhatsCookinApp {
    private RecipeBook recipeBook;
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
    // EFFECTS:  adds a recipe to the recipe book, lets user input and edit recipe information
    private void addRecipe() {
        Recipe recipe = new Recipe("", "",0,null);

        System.out.println("Recipe name: ");
        recipe.setName(key.next()); //try nextLine if buggy

        System.out.println("Recipe description: \n Input 's' to skip");
        recipe.setDescription(key.next().equals("s") ? "No description." : key.next());

        System.out.println("Recipe duration: \n Input 's' to skip (will default to 0 minutes)");
        recipe.setDuration(key.next().equals("s") ? 0 : key.nextInt());

        System.out.println("Number of recipe ingredients: \n Input 's' to skip");
        if (key.next().equals("s")) {
            recipe.setIngredients(null);
        } else if (key.hasNextInt()) {
            ArrayList<String> listedIngredients = new ArrayList<>(key.nextInt());

            for (String i : listedIngredients) { // probably buggy!
                System.out.println("Input ingredient #" + i);
                listedIngredients.add(key.next());
            }
            recipe.setIngredients(listedIngredients);
        }

        recipeBook.addRecipe(recipe);
        System.out.println("Recipe added successfully!");


        //ceebs for now
        /*System.out.println("----------------------------------------");
        System.out.println("Is this information correct?");
        System.out.println("Recipe name: " + recipe.getName());
        System.out.println("       description: " + recipe.getDescription());
        System.out.println("       duration: " + recipe.getDuration());
        System.out.println("       ingredients: ");
        for (String i : recipe.getIngredients()) {
            System.out.println(i);
        }

        System.out.println("Input '1' to accept, '2' to edit, or '3' to cancel");
        switch (key.nextInt()) {
            case 1:
                recipeBook.addRecipe(recipe);
                break;
            case 2:
                System.out.println("What would you like to edit?");
                System.out.println("1: Recipe name");
                System.out.println("2: Recipe description");
                System.out.println("3: Recipe duration");
                System.out.println("4: Recipe ingredients");

                switch () {

                }
            case 3:
                System.out.println("Recipe addition cancelled.");
                recipeBook.removeRecipe(recipe);
                break;
        } */

    }

    // EFFECTS:  displays all the details of a recipe
    private void viewRecipe(Recipe recipe) {
        System.out.println("Name: " + recipe.getName());
        System.out.println("Description: " + recipe.getDescription());
        System.out.println("Duration: " + recipe.getDuration());
        System.out.println("Ingredients: ");
        for (String i : recipe.getIngredients()) { //might be buggy?
            System.out.println(i);
        }
    }

    // EFFECTS:  displays a restaurant's name and cuisine type
    private void viewRestaurant(Restaurant restaurant) {
        System.out.println("Name: " + restaurant.getName());
        System.out.println("Cuisine Type: " + restaurant.getCuisine());
    }

}
