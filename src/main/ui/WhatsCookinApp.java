package ui;

import model.Recipe;
import model.Restaurant;
import model.RecipeBook;
import model.RestaurantList;
import persistence.JsonRecipeReader;
import persistence.JsonRecipeWriter;
import persistence.JsonRestaurantReader;
import persistence.JsonRestaurantWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;

// Recipe-managing application called "What's Cookin'"
public class WhatsCookinApp {
    private RecipeBook recipeBook; //changed for testing
    private RestaurantList restaurantList;
    private Scanner key;
    private Random random;
    private boolean isARecipeBook = true;
    private int size = 0;

    private static final String RESTAURANTS_JSON = "./data/restaurants.json";
    private static final String RECIPES_JSON = "./data/recipes.json";
    private JsonRecipeWriter jsonRecipeWriter;
    private JsonRecipeReader jsonRecipeReader;
    private JsonRestaurantWriter jsonRestaurantWriter;
    private JsonRestaurantReader jsonRestaurantReader;

    // EFFECTS: runs the application
    public WhatsCookinApp() throws FileNotFoundException {
        init();
    }

    // MODIFIES: this
    // EFFECTS:  initializes recipe book and recipe list
    public void init() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        recipeBook = new RecipeBook("Recipe Book");
        recipeBook.setRecipeBook(recipes);
        restaurantList = new RestaurantList("Restaurant List");
        restaurantList.setRestaurantList(restaurants);
        key = new Scanner(System.in);
        //key.useDelimiter("\n"); //not sure what this does, remove if key buggy
        random = new Random();

        jsonRecipeWriter = new JsonRecipeWriter(RECIPES_JSON);
        jsonRecipeReader = new JsonRecipeReader(RECIPES_JSON);

        jsonRestaurantWriter = new JsonRestaurantWriter(RESTAURANTS_JSON);
        jsonRestaurantReader = new JsonRestaurantReader(RESTAURANTS_JSON);
    }

    public RecipeBook recipeBook() {
        return this.recipeBook;
    }

    public RestaurantList restaurantList() {
        return this.restaurantList;
    }

    // EFFECTS: if Object o is a RecipeBook, set size to size of the RecipeBook
    //          if Object o is a RestaurantList, set size to size of the RestaurantList
    public void chooseObjectSize(Object o) {
        if (o instanceof RecipeBook) {
            size = (recipeBook.getRecipeBook().size());
            isARecipeBook = true;
        } else if (o instanceof RestaurantList) {
            size = (restaurantList.getRestaurantList().size());
            isARecipeBook = false;
        }
    }

    // MODIFIES: this
    // EFFECTS:  adds a recipe to the recipe book
    public void addRecipe(String name, String description, int duration) {
        Recipe recipe = new Recipe(name, description, duration, null);

        recipeBook.addRecipe(recipe);
        //isARecipeBook = true;
    }

    public void addRestaurant(String name, String description) {
        Restaurant restaurant = new Restaurant(name, description);
        restaurantList.addRestaurant(restaurant);
        //isARecipeBook
    }

    public void deleteRecipe(int index) {
        recipeBook.removeRecipe(index);
    }

    public void deleteRestaurant(int index) {
        restaurantList.removeRestaurant(index);
    }

    // EFFECTS: saves the recipe book and restaurant list to file
    public void save() {
        try {
            jsonRecipeWriter.open();
            jsonRecipeWriter.write(recipeBook);
            jsonRecipeWriter.close();
            System.out.println("Saved recipe book to " + RECIPES_JSON);
            jsonRestaurantWriter.open();
            jsonRestaurantWriter.write(restaurantList);
            jsonRestaurantWriter.close();
            System.out.println("Saved restaurant list to " + RESTAURANTS_JSON);

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + RECIPES_JSON);
            System.out.println("Unable to write to file: " + RESTAURANTS_JSON);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads RecipeBook and RestaurantList from file
    public void load() {
        try {
            recipeBook = jsonRecipeReader.read();
            System.out.println("Loaded recipe book from " + RECIPES_JSON);
            restaurantList = jsonRestaurantReader.read();
            System.out.println("Loaded restaurant list from " + RESTAURANTS_JSON);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + RECIPES_JSON);
            System.out.println("Unable to read from file: " + RESTAURANTS_JSON);
        }
    }
}