package model;

import java.util.ArrayList;

// Represents a recipe having a name, description, list of ingredients, and duration to make it (in minutes)
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

    public Recipe(String name) {
        this.name = name;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

}
