package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Random;

// Represents a "book" (list) of recipes
public class RecipeBook implements Writable {

    private ArrayList<Recipe> recipeBook;
    private String name;
    private final Random random = new Random();

    public RecipeBook(String name) {
        this.name = name;
        recipeBook = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Recipe> getRecipeBook() {
        return this.recipeBook;
    }

    public void setRecipeBook(ArrayList<Recipe> recipeBook) {
        this.recipeBook = recipeBook;
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
        if (index == 0) {
            recipeBook.remove(0);
        } else {
            recipeBook.remove(index);
        }
    }

    // REQUIRES: a non-empty recipe book
    // EFFECTS:  returns a random recipe from recipe book
    public Recipe randomRecipe() {
        try {
            return recipeBook.get(random.nextInt(recipeBook.size()));
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("recipeBook", recipeBookToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray recipeBookToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Recipe r : recipeBook) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }

}
