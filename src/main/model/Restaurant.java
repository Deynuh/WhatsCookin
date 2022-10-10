package model;

// Represents a restaurant having a name and type of cuisine
public class Restaurant {

    private String name;
    private String cuisine;

    public Restaurant(String name, String cuisine) {
        this.name = name;
        this.cuisine = cuisine;
    }

    public String getName() {
        return this.name;
    }

    public String getCuisine() {
        return this.cuisine;
    }

    // EFFECTS: sets the type of cuisine a restaurant has
    private void setCuisine() {

    }

}
