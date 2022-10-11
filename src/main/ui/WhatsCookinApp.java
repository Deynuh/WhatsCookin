package ui;

import model.Recipe;
import model.Restaurant;
import model.RecipeBook;
import model.RestaurantList;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

// Recipe-managing application called "What's Cookin'"
public class WhatsCookinApp {
    private RecipeBook recipeBook;
    private RestaurantList restaurantList;
    private Scanner key = new Scanner(System.in);
    private Random random = new Random();

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
        boolean invalid = true;

        System.out.println("Welcome to What's Cookin! \n Select from:");
        System.out.println("1: Random Meal Suggestion \n 2: Recipes \n 3: Restaurants");

        while (invalid) {
            switch (key.nextInt()) {
                case 1:
                    chooseRandom();
                    invalid = false;
                    break;
                case 2:
                    displayRecipesMenu();
                    invalid = false;
                    break;
                case 3:
                    displayRestaurantsMenu();
                    invalid = false;
                    break;
                default:
                    System.out.println("That is not a valid input.");
                    break;
            }
        }

    }

    // EFFECTS: displays menu for recipe options
    // !!!
    private void displayRecipesMenu() {
        System.out.println("------- Recipes --------");
        System.out.println("1: Add a recipe \n 2: Delete a recipe \n 3: View all recipes \n 4: View a recipe");
    }

    // EFFECTS: displays menu for restaurant options
    // !!!
    private void displayRestaurantsMenu() {
        System.out.println("------- Restaurants --------");
        System.out.println("1: Add a restaurant \n 2: Delete a restaurant ");
        System.out.println("\n 3: View all restaurants \n 4: View a restaurant");
    }

    // REQUIRES: non-empty recipe book and restaurant list
    // EFFECTS:  gives the user either a random recipe or restaurant based on their choice
    private void chooseRandom() {
        boolean invalid = true;
        System.out.println("1: Give me a recipe \n + 2: Give me a restaurant \n 3: Choose for me");

        while (invalid) {
            switch (key.nextInt()) {
                case 1:
                    System.out.println("You should make " + recipeBook.randomRecipe().getName());
                    invalid = false;
                    break;
                case 2:
                    System.out.println("You should get " + restaurantList.randomRestaurant().getName());
                    invalid = false;
                    break;
                case 3:
                    chooseForMe();
                    invalid = false;
                    break;
                default:
                    System.out.println("That is not a valid input.");
                    chooseRandom(); //might be buggy?
                    break;
            }
        }
    }

    // REQUIRES: non-empty recipe book and restaurant list
    // EFFECTS:  gives the user a completely random recipe or restaurant
    private void chooseForMe() {
        if (random.nextInt(2) == 1) {
            System.out.println("You should make " + recipeBook.randomRecipe().getName());
        } else {
            System.out.println("You should get " + restaurantList.randomRestaurant().getName());
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds a restaurant to the restaurant list
    private void addRestaurant() {
        Restaurant restaurant = new Restaurant("", "");

        System.out.println("Restaurant name: ");
        restaurant.setName(key.next()); //try nextLine if buggy

        System.out.println("Restaurant description: \n Input 's' to skip");
        restaurant.setDescription(key.next().equals("s") ? "No description." : key.next());

        restaurantList.addRestaurant(restaurant);
        System.out.println("Restaurant added successfully!");
    }

    // MODIFIES: this
    // EFFECTS:  adds a recipe to the recipe book, lets user input and edit recipe information
    private void addRecipe() {
        Recipe recipe = new Recipe("", "", 0, null);

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
        System.out.println("Description: " + restaurant.getDescription());
    }

    //ceebs for now
    /*private boolean confirmCorrectInfo {
        System.out.println("----------------------------------------");
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
        }
    } */
}
