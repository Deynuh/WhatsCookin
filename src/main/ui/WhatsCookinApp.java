package ui;

import model.Recipe;
import model.Restaurant;
import model.RecipeBook;
import model.RestaurantList;

import java.util.MissingFormatArgumentException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

// Recipe-managing application called "What's Cookin'"
public class WhatsCookinApp {
    private RecipeBook recipeBook;
    private RestaurantList restaurantList;
    private Scanner key;
    private Random random;
    private boolean isARecipeBook = true;
    private int size = 0;

    // EFFECTS: runs the application
    public WhatsCookinApp() {
        runApp();
    }

    // MODIFIES: this
    // EFFECTS:  processes user input
    public void runApp() {
        boolean keepGoing = true;
        int command;

        init();

        while (keepGoing) {
            try {
                displayMenuOfMainOptions();
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
                    displayMenu("recipe");
                    menu(true);
                    keepGoing = false;
                    break;
                case 3:
                    displayMenu("restaurant");
                    menu(false);
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

    // EFFECTS: displays menu of main options
    private void displayMenuOfMainOptions() {
        System.out.println("\nWelcome to What's Cookin'!");
        System.out.println("Select from: \n 1: Random Meal Suggestion \n 2: Recipes \n 3: Restaurants \n 4: Exit");

    }

    // EFFECTS: if type is recipe, displays specific menu for Recipes
    //          if type is restaurant, displays specific menu for Restaurants
    private void displayMenu(String type) {
        if (type.equals("recipe")) {
            isARecipeBook = true;
        } else {
            isARecipeBook = false;
        }

        System.out.println("------- " + type.toUpperCase() + "S -------");
        System.out.println(" 1: Add a " + type + " \n 2: Delete a " + type);
        System.out.println(" 3: View all " + type + "s \n 4: Previous menu");

        //menu(isARecipeBook);
    }

    // EFFECTS: if type is recipe, handles specific menu for Recipes
    //          if type is restaurant, handles specific menu for Restaurants
    private void menu(boolean isARecipeBook) {
        boolean keepGoing = true;
        int input = key.nextInt();

        while (keepGoing) {
            if (input == 1) {
                menuForAdding();
                keepGoing = false;
            } else if (input == 2) {
                menuForDeleting();
                keepGoing = false;
            } else if (input == 3) {
                menuForViewing();
                keepGoing = false;
            } else if (input == 4) {
                runApp();
            } else {
                System.out.println("That is not a valid input.");
            }
        }
    }

    private void menuForAdding() {
        if (isARecipeBook) {
            addRecipe();
        } else {
            addRestaurant();
        }
    }

    private void menuForDeleting() {
        if (isARecipeBook) {
            deleteMenu("recipe", recipeBook);
        } else {
            deleteMenu("recipe", recipeBook);
        }
    }

    private void menuForViewing() {
        if (isARecipeBook) {
            show("recipe", recipeBook);
            singleView("recipe");
        } else {
            show("restaurant", restaurantList);
            singleView("restaurant");
        }
    }

    // EFFECTS: if type is recipe, displays delete menu for RecipeBook
    //          if type is restaurant, displays delete menu for RestaurantList
    private void deleteMenu(String type, Object o) {
        chooseObjectSize(o);

        if (size == 0) {
            System.out.println("You have no " + type + "s to delete.");
            System.out.println("Returning to main menu...");
        } else {
            System.out.println("Which " + type + " would you like to delete?");
            show(type, o);
            int input = key.nextInt();
            if (input >= 0 && input <= size) {
                deleteMenuWorking(isARecipeBook, input);
                System.out.println("Deleted successfully!");
            } else {
                System.out.println("That is not a valid number.");
            }

            System.out.println("Would you like to delete another " + type + "? (y/n)");
            if (key.next().equals("y")) {
                deleteMenu(type, o);
            } else {
                System.out.println("Returning to main menu...");
            }
        }
    }

    // EFFECTS: if Object o is a RecipeBook, set size to size of the RecipeBook
    //          if Object o is a RestaurantList, set size to size of the RestaurantList
    private void chooseObjectSize(Object o) {
        if (o instanceof RecipeBook) {
            size = (recipeBook.getRecipeBook().size());
            isARecipeBook = true;
        } else if (o instanceof RestaurantList) {
            size = (restaurantList.getRestaurantList().size());
            isARecipeBook = false;
        }
    }

    // EFFECTS: if Object o is a RecipeBook, remove the recipe at the index of the input
    //          if Object o is a RestaurantList, remove the restaurant at the index of the input
    private void deleteMenuWorking(boolean isARecipeBook, int input) {
        if (isARecipeBook) {
            recipeBook.removeRecipe(input);
        } else {
            restaurantList.removeRestaurant(input);
        }
    }

    // REQUIRES: non-empty recipe book and restaurant list
    // EFFECTS:  displays options and handles input for choosing a random recipe or restaurant
    private void chooseRandom() {
        System.out.println(" 1: Give me a recipe \n 2: Give me a restaurant \n 3: Choose for me");

        boolean keepGoing = true;
        int input = key.nextInt();

        while (keepGoing) {
            if (input == 1) {
                getRandom("recipe", recipeBook);
                keepGoing = false;
            } else if (input == 2) {
                getRandom("restaurant", restaurantList);
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

    // REQUIRES: non-empty RecipeBook if getting a RecipeBook
    //           non-empty RestaurantList if getting a RestaurantList
    // EFFECTS:  gives the user a random recipe or restaurant based on user input
    private void getRandom(String type, Object o) {
        chooseObjectSize(o);
        isARecipeBook = o instanceof RecipeBook;
        if (size == 0) {
            System.out.println("You have no " + type + "s. Please add a " + type + " first.");
        } else {
            if (isARecipeBook) {
                System.out.println("You should make: " + recipeBook.randomRecipe().getName());
            } else {
                System.out.println("You should get: " + restaurantList.randomRestaurant().getName());
            }
        }
    }

    // REQUIRES: non-empty recipe book and restaurant list
    // EFFECTS:  gives the user a random recipe or restaurant based on random
    private void chooseForMe() {
        int rand = random.nextInt(2);
        boolean noRestaurant = restaurantList.getRestaurantList().size() == 0;
        boolean noRecipe = recipeBook.getRecipeBook().size() == 0;

        if (noRestaurant && noRecipe) {
            System.out.println("There is nothing in your restaurant list and recipe book! ");
            System.out.print("Please add a recipe and/or a restaurant first. \n");
        } else if (rand == 0 && !noRecipe) {
            getRandom("recipe", recipeBook);
        } else if (rand == 1 && !noRestaurant) {
            getRandom("restaurant", restaurantList);
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
        isARecipeBook = true;
        addAnother("recipe");
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
        isARecipeBook = false;
        addAnother("restaurant");
    }

    // EFFECTS: if dealing with a RecipeBook, asks if user would like to add another recipe
    //          if dealing with a RestaurantList, asks if user would like to add another restaurant
    private void addAnother(String type) {
        System.out.println("Added successfully!");
        System.out.println("Would you like to add another " + type + " (y/n)");
        if (key.next().equals("y")) {
            if (isARecipeBook) {
                addRecipe();
            } else {
                addRestaurant();
            }
        } else {
            System.out.println("Returning to main menu...");
        }
        //BUG: goes back to addRecipe/Restaurant instead of main menu
    }

    // REQUIRES: non-empty RecipeBook if dealing with RecipeBook
    //           non-empty RestaurantList if dealing with RestaurantList
    // EFFECTS:  if dealing with RecipeBook, displays all recipes, numbered, and option to view individual recipes
    //           if dealing with RestaurantList, displays all restaurants, numbered,
    //           and option to view individual restaurants
    private void show(String type, Object o) {
        chooseObjectSize(o);
        if (size == 0) {
            System.out.println("You do not have any " + type + "s yet.");
            System.out.println("Returning to main menu...");
        } else {
            for (int i = 0; i < size; i++) {
                if (isARecipeBook) {
                    System.out.println((i + 1) + " — " + recipeBook.getRecipeBook().get(i).getName());
                } else {
                    System.out.println((i + 1) + " — " + restaurantList.getRestaurantList().get(i).getName());
                }
            }
            singleView(type);
        }
    }

    private void singleView(String type) {
        System.out.println("Would you like to view a " + type + " in more detail? (y/n)");
        if (key.next().equalsIgnoreCase("y")) {
            System.out.println("Input the " + type + " number.");
            if (isARecipeBook) {
                viewRecipe(recipeBook.getRecipeBook().get(key.nextInt() - 1));
                options("recipe");
            } else {
                viewRestaurant(restaurantList.getRestaurantList().get(key.nextInt() - 1));
                options("restaurant");
            }
        }
        System.out.println("Returning to main menu...");
    }

    // EFFECTS: if dealing with RecipeBook, displays options for RecipeBook menu
    //          if dealing with RestaurantList, displays options for RestaurantList menu
    private void options(String type) {
        System.out.println("\nView another " + type + "? (y/n)");
        String input = key.next();

        if (input.equals("y")) {
            System.out.println("Input the number of the " + type);
            if (isARecipeBook) {
                viewRecipe(recipeBook.getRecipeBook().get(key.nextInt() + 1));
            } else {
                viewRestaurant(restaurantList.getRestaurantList().get(key.nextInt() + 1));
            }
        } else if (input.equals("n")) {
            System.out.println("Returning to previous menu...");
            if (isARecipeBook) {
                displayMenu("restaurant");
            } else {
                displayMenu("restaurant");
            }
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