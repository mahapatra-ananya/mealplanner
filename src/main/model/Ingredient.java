package model;

public class Ingredient {

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
    }

    // REQUIRES: the final quantity of the ingredient must be a positive number
    // MODIFIES: this
    // EFFECTS: decreases the quantity of the ingredient by the given amount
    public void decreaseQuantity(double amount) {
        this.quantity -= amount;
    }
}
