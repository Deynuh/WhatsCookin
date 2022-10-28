package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a restaurant having a name and description
public class Restaurant implements Writable {

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

    // EFFECTS: returns this restaurant as a JSONObject
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("Name", name);
        json.put("Description", description);
        return json;
    }
}
