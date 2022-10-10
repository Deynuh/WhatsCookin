package model;

// Represents a restaurant having a name and type of cuisine
public class Restaurant {

    private String name;
    private String description;

    public Restaurant(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    // MODIFIES: this
    // EFFECTS: sets a restaurant's name
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: sets a restaurant's description
    public void setDescription(String description) {
        this.description = description;
    }

}
