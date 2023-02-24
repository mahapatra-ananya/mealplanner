package model;

public class Ingredient {

    private String name;
    private double quantity;

    // EFFECTS: creates an ingredient with the given name
    public Ingredient(String name, double quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void increaseQuantity(double quantity) {
        this.quantity += quantity;
    }

    public void decreaseQuantity(double quantity) {
        this.quantity -= quantity;
    }
}
