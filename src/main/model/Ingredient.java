package model;

import org.json.JSONObject;
import persistence.Writable;

// JSONSerializationDemo application used as reference

public class Ingredient implements Writable {

    private String name;
    private double quantity;

    // REQUIRES: quantity is a positive number
    // EFFECTS: creates an ingredient with the given name and quantity
    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    // EFFECTS: returns the name of the ingredient
    public String getName() {
        return this.name;
    }

    // EFFECTS: returns the quantity of the ingredient
    public double getQuantity() {
        return this.quantity;
    }

    // REQUIRES: amount is not a negative number
    // MODIFIES: this
    // EFFECTS: increases the quantity of the ingredient by the given amount
    public void increaseQuantity(double amount) {
        this.quantity += amount;
        EventLog.getInstance().logEvent(new Event(this.name + " quantity increased by " + amount));
    }

    // REQUIRES: the final quantity of the ingredient must be a positive number
    // MODIFIES: this
    // EFFECTS: decreases the quantity of the ingredient by the given amount
    public void decreaseQuantity(double amount) {
        this.quantity -= amount;
        EventLog.getInstance().logEvent(new Event(this.name + "Ingredient quantity decreased by " + amount));
    }

    // EFFECTS: inherited from Writable
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("quantity", quantity);
        return json;
    }
}
