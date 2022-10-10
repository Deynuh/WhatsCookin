package model;

import java.util.HashMap;

// Represents a "book" (list) of recipes
public class RecipeBook {

    private HashMap recipeBook; //(String name, String description)

    public RecipeBook() {
        this.recipeBook = recipeBook;
    }

    public HashMap getRecipeBook() {
        return this.recipeBook;
    }

    // MODIFIES: this
    // EFFECTS:  adds a recipe to the recipe book
    public void addRecipe(Recipe recipe) {
       recipeBook.put(recipe.getName(), recipe.getDescription());
    }

    // MODIFIES: this
    // REQUIRES: a non-empty recipe list
    // EFFECTS:  deletes a recipe from the recipe book
    public void deleteRecipe(Recipe recipe) {
        recipeBook.remove(recipe.getName());
    }

}
