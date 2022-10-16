package model;

import java.util.ArrayList;

// Represents a recipe having a name, description, duration to make it (in minutes), and ingredients
public class Recipe {

    private String name;
    private String description;
    private int duration;
    private ArrayList<String> ingredients;

    public Recipe(String name, String description, int duration, ArrayList<String> ingredients) {
        this.name = name;
        this.description = description;
        this.duration = duration; //eventually use for a short meal option in randomizer?
        this.ingredients = ingredients;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public ArrayList<String> getIngredients() {
        return this.ingredients;
    }

    public int getDuration() {
        return this.duration;
    }

    // MODIFIES: this
    // EFFECTS:  sets this recipe's name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS:  sets this recipe's description
    public void setDescription(String description) {
        this.description = description;
    }

    // MODIFIES: this
    // EFFECTS:  sets this recipe's duration.
    public void setDuration(int duration) {
        if (duration < 0) {
            this.duration = 0;
        } else {
            this.duration = duration;
        }
    }

    // MODIFIES: this
    // EFFECTS:  sets this recipe's ingredients list
    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }


}
