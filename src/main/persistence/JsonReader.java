package persistence;

import model.RecipeBook;
import model.Recipe;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads recipe book from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RecipeBook read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRecipeBook(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses RecipeBook from JSON object and returns it
    private RecipeBook parseRecipeBook(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        RecipeBook rb = new RecipeBook(name);
        addRecipes(rb, jsonObject);
        return rb;
    }

    // MODIFIES: rb
    // EFFECTS: parses Recipes from JSON object and adds them to RecipeBook
    private void addRecipes(RecipeBook rb, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("recipeBook");
        for (Object json : jsonArray) {
            JSONObject nextRecipe = (JSONObject) json;
            addRecipe(rb, nextRecipe);
        }
    }

    // MODIFIES: rb
    // EFFECTS: parses Recipe from JSON object and adds it to RecipeBook
    private void addRecipe(RecipeBook rb, JSONObject jsonObject) {
        String name = jsonObject.getString("Name");
        String description = jsonObject.getString("Description");
        int duration = jsonObject.getInt("Duration");
        //ArrayList<String> ingredients = jsonObject.get("ingredients");
        Recipe recipe = new Recipe(name, description, duration, null);
        rb.addRecipe(recipe);
    }
}
