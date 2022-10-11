package model;

import java.util.ArrayList;
import java.util.Random;

// Represents a "book" (list) of recipes
public class RecipeBook {

    private ArrayList<Recipe> recipeBook;
    private Random random = new Random();

    public RecipeBook() {
        this.recipeBook = recipeBook;
    }

    public ArrayList<Recipe> getRecipeBook() {
        return this.recipeBook;
    }

    // MODIFIES: this
    // EFFECTS:  adds a recipe to the recipe book
    public void addRecipe(Recipe recipe) {
        recipeBook.add(recipe);
    }

    // MODIFIES: this
    // REQUIRES: a non-empty recipe list
    // EFFECTS:  deletes a recipe from the recipe book
    public void removeRecipe(int index) {
        recipeBook.remove(index);
    }

    // REQUIRES: a non-empty recipe book
    // EFFECTS:  returns a random recipe from recipe book
    public Recipe randomRecipe() {
        try {
            return recipeBook.get(random.nextInt(recipeBook.size()));
        } catch (Exception e) {
            System.out.println("ye no");
            return null;
        }
    }
}
