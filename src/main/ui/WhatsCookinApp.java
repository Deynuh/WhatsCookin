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
    private Scanner key;
    private Random random;

    // EFFECTS: runs the application
    public WhatsCookinApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        int command = 0;

        init();

        while (keepGoing) {
            displayMenu();
            command = key.nextInt();

            if (command == 4) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("Goodbye!");
    }

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
    // EFFECTS: initializes recipe book and recipe list
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
        boolean keepGoing = true;

        System.out.println("Welcome to What's Cookin'!");
        System.out.println("Select from: \n 1: Random Meal Suggestion \n 2: Recipes \n 3: Restaurants \n 4: Exit");

    }

    // EFFECTS: displays menu for recipe options
    // !!! SHORTEN
    private void displayRecipesMenu() {
        boolean keepGoing = true;
        System.out.println("------- Recipes --------");
        System.out.println(" 1: Add a recipe \n 2: Delete a recipe \n 3: View all recipes \n 4: Previous menu");

        while (keepGoing) {
            switch (key.nextInt()) {
                case 1:
                    addRecipe();
                    keepGoing = false;
                    break;
                case 2:
                    System.out.println("Which recipe would you like to delete?");
                    showRecipes();
                    recipeBook.removeRecipe(key.nextInt());
                    keepGoing = false;
                    break;
                case 3:
                    showRecipes();
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

    // EFFECTS: displays menu for restaurant options
    // !!!
    private void displayRestaurantsMenu() {
        boolean keepGoing = true;

        System.out.println("------- Restaurants --------");
        System.out.println(" 1: Add a restaurant \n 2: Delete a restaurant ");
        System.out.println("\n 3: View all restaurants \n 4: Previous Menu");

        while (keepGoing) {
            switch (key.nextInt()) {
                case 1:
                    addRestaurant();
                    keepGoing = false;
                    break;
                case 2:
                    System.out.println("Which restaurant would you like to delete?");
                    showRestaurants();
                    restaurantList.removeRestaurant(key.nextInt());
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

    // REQUIRES: non-empty recipe book and restaurant list
    // EFFECTS:  gives the user either a random recipe or restaurant based on their choice
    // !!! shorten
    private void chooseRandom() {
        boolean keepGoing = true;
        System.out.println(" 1: Give me a recipe \n 2: Give me a restaurant \n 3: Choose for me");

        try {
            while (keepGoing) {
                switch (key.nextInt()) {
                    case 1:
                        getRandomRecipe();
                        keepGoing = false;
                        break;
                    case 2:
                        System.out.println("You should get " + restaurantList.randomRestaurant().getName());
                        keepGoing = false;
                        break;
                    case 3:
                        chooseForMe();
                        keepGoing = false;
                        break;
                    default:
                        System.out.println("That is not a valid input.");
                        chooseRandom(); //might be buggy?
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("There was a problem with the Random Meal Generator.");
        }
    }

    private void getRandomRecipe() {
        if (recipeBook.getRecipeBook() == null) {
            System.out.println("There is nothing in your recipe book!");
        } else {
            System.out.println("You should make " + recipeBook.randomRecipe().getName());
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
        recipe.setName(key.next());

        System.out.println("Recipe description: ");
        recipe.setDescription(key.next());

        System.out.println("Recipe duration: ");
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

    }

    // REQUIRES: non-empty recipe book
    // EFFECTS:  displays all recipes, numbered, and individual recipes
    private void showRecipes() {
        ArrayList<Recipe> recipes = recipeBook.getRecipeBook();
        boolean keepGoing = true;

        for (int i = 0; i < recipes.size(); i++) {
            System.out.println((i + 1) + recipes.get(i).getName());
        }

        while (keepGoing) {
            System.out.println("Input: \n 1: View a recipe \n 2: Previous menu");
            int input = key.nextInt();
            if (input == 1) {
                System.out.println("Input the number of the recipe you want to see.");
                viewRecipe(recipeBook.getRecipeBook().get(key.nextInt() + 1));
                keepGoing = false;
            } else if (input == 2) {
                displayRecipesMenu();
                keepGoing = false;
            } else {
                System.out.println("That is not a valid input.");
            }
        }
    }

    // REQUIRES: non-empty restaurant list
    // EFFECTS:  displays all restaurants, numbered, and individual restaurants
    private void showRestaurants() {
        ArrayList<Restaurant> restaurants = restaurantList.getRestaurantList();
        boolean keepGoing = true;

        for (int i = 0; i < restaurants.size(); i++) {
            System.out.println((i + 1) + restaurants.get(i).getName());
        }

        while (keepGoing) {
            System.out.println("Input: \n 1: View a restaurant \n 2: Previous menu");
            int input = key.nextInt();
            if (input == 1) {
                System.out.println("Input the number of the restaurant you want to see.");
                viewRestaurant(restaurantList.getRestaurantList().get(key.nextInt() + 1));
                keepGoing = false;
            } else if (input == 2) {
                displayRestaurantsMenu();
                keepGoing = false;
            } else {
                System.out.println("That is not a valid input.");
            }
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
