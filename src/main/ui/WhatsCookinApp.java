package ui;

import model.Recipe;
import model.Restaurant;
import model.RecipeBook;
import model.RestaurantList;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

// Recipe-managing application called "What's Cookin'"
public class WhatsCookinApp extends Options {
    private RecipeBook recipeBook;
    private RestaurantList restaurantList;
    private Scanner key;
    private Random random;

    // EFFECTS: runs the application
    public WhatsCookinApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS:  processes user input
    private void runApp() {
        boolean keepGoing = true;
        int command;

        init();

        while (keepGoing) {
            try {
                displayMenu();
                command = key.nextInt();

                if (command == 4) {
                    keepGoing = false;
                } else {
                    processCommand(command);
                }
            } catch (Exception e) {
                System.out.println("Please input a valid number.");
                System.out.println("Returning to main menu...");
            }
            key.nextLine();
        }
        System.out.println("Goodbye!");
        System.exit(0);
    }

    // EFFECTS: processes user input
    private void processCommand(int command) {
        boolean keepGoing = true;

        while (keepGoing) {
            switch (command) {
                case 1:
                    chooseRandom();
                    keepGoing = false;
                    break;
                case 2:
                    displayRecipesMenu();
                    keepGoing = false;
                    break;
                case 3:
                    displayRestaurantsMenu();
                    keepGoing = false;
                    break;
                default:
                    System.out.println("That is not a valid input.");
                    break;
            }
        }
    }

    // MODIFIES: this
    // EFFECTS:  initializes recipe book and recipe list
    private void init() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        recipeBook = new RecipeBook(recipes);
        restaurantList = new RestaurantList(restaurants);
        key = new Scanner(System.in);
        //key.useDelimiter("\n"); //not sure what this does, remove if key buggy
        random = new Random();
    }

    // EFFECTS: displays menu of options
    private void displayMenu() {
        System.out.println("\nWelcome to What's Cookin'!");
        System.out.println("Select from: \n 1: Random Meal Suggestion \n 2: Recipes \n 3: Restaurants \n 4: Exit");

    }

    // EFFECTS: displays menu for recipe options
    private void displayRecipesMenu() {
        displayMenu("recipe");
        recipesMenu();
    }

    // EFFECTS: displays menu for restaurant options
    private void displayRestaurantsMenu() {
        displayMenu("restaurant");
        restaurantsMenu();
    }

    // EFFECTS: displays recipes menu
    private void recipesMenu() {
        boolean keepGoing = true;
        int input = key.nextInt();

        while (keepGoing) {
            if (input == 1) {
                addRecipe();
                keepGoing = false;
            } else if (input == 2) {
                deleteRecipeMenu();
                keepGoing = false;
            } else if (input == 3) {
                showRecipes();
                keepGoing = false;
            } else if (input == 4) {
                runApp();
            } else {
                System.out.println("That is not a valid input.");
            }
        }
    }

    // EFFECTS: displays restaurants menu
    private void restaurantsMenu() {
        boolean keepGoing = true;

        while (keepGoing) {
            switch (key.nextInt()) {
                case 1:
                    addRestaurant();
                    keepGoing = false;
                    break;
                case 2:
                    deleteRestaurantMenu();
                    keepGoing = false;
                    break;
                case 3:
                    showRestaurants();
                    keepGoing = false;
                    break;
                case 4:
                    runApp();
                    break;
                default:
                    System.out.println("That is not a valid input.");
                    break;
            }
        }
    }

    // EFFECTS: displays menu for deleting a recipe
    private void deleteRecipeMenu() {
        if (recipeBook.getRecipeBook().size() == 0) {
            System.out.println("There is nothing in your recipe book to delete.");
            System.out.println("Returning to main menu...");
        } else {
            System.out.println("Which recipe would you like to delete?");
            showRecipes();
            recipeBook.removeRecipe(key.nextInt());

            System.out.println("Would you like to delete another recipe? (y/n)");
            if (key.next().equals("y")) {
                deleteRecipeMenu();
            } else {
                System.out.println("Returning to main menu...");
            }
        }
    }


    // EFFECTS: displays menu for deleting a restaurant
    private void deleteRestaurantMenu() {
        if (restaurantList.getRestaurantList().size() == 0) {
            System.out.println("There is nothing in your restaurant list to delete.");
            System.out.println("Returning to main menu...");
        } else {
            System.out.println("Which restaurant would you like to delete?");
            showRestaurants();
            int input = key.nextInt();
            if (input >= 0 && input <= restaurantList.getRestaurantList().size()) {
                restaurantList.removeRestaurant(input);
            } else {
                System.out.println("That is not a valid number.");
            }

            System.out.println("Would you like to delete another restaurant? (y/n)");
            if (key.next().equals("y")) {
                deleteRestaurantMenu();
            } else {
                System.out.println("Returning to main menu...");
            }
        }
    }

    // REQUIRES: non-empty recipe book and restaurant list
    // EFFECTS:  gives the user either a random recipe or restaurant based on their choice
    private void chooseRandom() {
        System.out.println(" 1: Give me a recipe \n 2: Give me a restaurant \n 3: Choose for me");

        boolean keepGoing = true;
        int input = key.nextInt();

        while (keepGoing) {
            if (input == 1) {
                getRandomRecipe();
                keepGoing = false;
            } else if (input == 2) {
                getRandomRestaurant();
                keepGoing = false;
            } else if (input == 3) {
                chooseForMe();
                keepGoing = false;
            } else {
                System.out.println("That is not a valid input.");
            }
        }

        System.out.println("Would you like to try again? (y/n)");
        if (key.next().equals("y")) {
            chooseRandom();
        } else {
            System.out.println("Returning to main menu...");
        }
    }

    // REQUIRES: non-empty recipe book
    // EFFECTS:  gives the user a random recipe
    private void getRandomRecipe() {
        if (recipeBook.getRecipeBook().size() == 0) {
            System.out.println("There is nothing in your recipe book! Please add a recipe first.");
        } else {
            System.out.println("You should make " + recipeBook.randomRecipe().getName());
        }
    }

    // REQUIRES: non-empty restaurant list
    // EFFECTS:  gives the user a random restaurant
    public void getRandomRestaurant() {
        if (restaurantList.getRestaurantList().size() == 0) {
            System.out.println("There is nothing in your restaurant list! Please add a restaurant first.");
        } else {
            System.out.println("You should get " + restaurantList.randomRestaurant().getName());
        }

    }

    // REQUIRES: non-empty recipe book and restaurant list
    // EFFECTS:  gives the user a completely random recipe or restaurant
    private void chooseForMe() {
        int rand = random.nextInt(2);
        boolean noRestaurant = restaurantList.getRestaurantList().size() == 0;
        boolean noRecipe = recipeBook.getRecipeBook().size() == 0;

        if (noRestaurant && noRecipe) {
            System.out.println("There is nothing in your restaurant list and recipe book! ");
            System.out.print("Please add a recipe and/or a restaurant first. \n");
        } else if (rand == 0 && !noRecipe) {
            getRandomRecipe();
        } else if (rand == 1 && !noRestaurant) {
            getRandomRestaurant();
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds a recipe to the recipe book, lets user input and edit recipe information
    private void addRecipe() {
        Recipe recipe = new Recipe("", "", 0, null);

        System.out.println("Recipe name: ");
        recipe.setName(key.next());

        System.out.println("Recipe description: ");
        recipe.setDescription(key.next());

        System.out.println("Recipe duration (in minutes): ");
        recipe.setDuration(key.nextInt());

        System.out.println("Number of recipe ingredients: ");
        int input = key.nextInt();
        ArrayList<String> listedIngredients = new ArrayList<>(input);

        for (int i = 0; i < input; i++) {
            System.out.println("Input ingredient #" + (i + 1));
            listedIngredients.add(key.next());
        }
        recipe.setIngredients(listedIngredients);

        recipeBook.addRecipe(recipe);
        System.out.println("Recipe added successfully!");
        System.out.println("Would you like to add another recipe? (y/n)");
        if (key.next().equals("y")) {
            addRecipe();
        } else {
            System.out.println("Returning to main menu...");
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds a restaurant to the restaurant list
    private void addRestaurant() {
        Restaurant restaurant = new Restaurant("", "");

        System.out.println("Restaurant name: ");
        restaurant.setName(key.next()); //try nextLine if buggy

        System.out.println("Restaurant description: ");
        restaurant.setDescription(key.next());

        restaurantList.addRestaurant(restaurant);
        System.out.println("Restaurant added successfully!");
        System.out.println("Would you like to add another restaurant? (y/n)");
        if (key.next().equals("y")) {
            addRestaurant();
        } else {
            System.out.println("Returning to main menu...");
        }
    }

    // REQUIRES: non-empty recipe book
    // EFFECTS:  displays all recipes, numbered, and individual recipes
    private void showRecipes() {
        ArrayList<Recipe> recipes = recipeBook.getRecipeBook();
        if (recipes.size() == 0) {
            System.out.println("There are no recipes in your recipe book yet.");
            System.out.println("Returning to main menu...");
        } else {
            for (int i = 0; i < recipes.size(); i++) {
                System.out.println((i + 1) + " — " + recipes.get(i).getName());
            }

            System.out.println("Would you like to view a recipe in more detail? (y/n)");
            if (key.next().equalsIgnoreCase("y")) {
                System.out.println("Input the recipe number.");
                viewRecipe(recipes.get(key.nextInt() - 1));
                recipeOptions();
            }
            System.out.println("Returning to main menu...");
        }
    }


    // REQUIRES: non-empty restaurant list
    // EFFECTS:  displays all restaurants, numbered, and individual restaurants
    private void showRestaurants() {
        ArrayList<Restaurant> restaurants = restaurantList.getRestaurantList();
        if (restaurants.size() == 0) {
            System.out.println("There are no restaurants in your restaurant list yet.");
            System.out.println("Returning to main menu...");
        } else {
            for (int i = 0; i < restaurants.size(); i++) {
                System.out.println((i + 1) + " — " + restaurants.get(i).getName());
            }

            System.out.println("Would you like to view a restaurant in more detail? (y/n)");
            if (key.next().equalsIgnoreCase("y")) {
                System.out.println("Input the restaurant number.");
                viewRestaurant(restaurants.get(key.nextInt() - 1));
                restaurantOptions();
            }
            System.out.println("Returning to main menu...");
        }
    }

    // EFFECTS: displays options for recipes
    private void recipeOptions() {
        System.out.println("View another recipe? (y/n)");
        String input = key.next();

        if (input.equals("y")) {
            System.out.println("Input the recipe number.");
            viewRecipe(recipeBook.getRecipeBook().get(key.nextInt() - 1));
            recipeOptions();
        } else if (input.equals("n")) {
            System.out.println("Returning to previous menu...");
            displayRecipesMenu();
        } else {
            System.out.println("That is not a valid input.");
            System.out.println("Returning to main menu...");
        }
    }


    // EFFECTS: displays options for restaurants menu
    private void restaurantOptions() {
        System.out.println("\n View another restaurant? (y/n)");
        String input = key.next();

        if (input.equals("y")) {
            System.out.println("Input the number of the restaurant.");
            viewRestaurant(restaurantList.getRestaurantList().get(key.nextInt() + 1));
        } else if (input.equals("n")) {
            System.out.println("Returning to previous menu...");
            displayRestaurantsMenu();
        } else {
            System.out.println("That is not a valid input.");

        }
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