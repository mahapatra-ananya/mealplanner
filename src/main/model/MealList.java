package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

public class MealList implements Writable {

    protected List<Meal> meals;

    // EFFECTS: creates a new meal list with an empty list of meals
    public MealList() {
        this.meals = new ArrayList<>();
    }

    // EFFECTS: returns the total quantity of the given ingredient in the meal list
    public double quantityOfIngredientRequiredForWeek(Ingredient inputIngredient) {
        double quantity = 0;
        for (Meal m : this.meals) {
            for (Ingredient mealIngredient : m.getIngredients()) {
                if (mealIngredient.getName().equals(inputIngredient.getName())) {
                    quantity += mealIngredient.getQuantity();
                }
            }
        }
        return quantity;
    }

    // EFFECTS: returns all the meals in the meal list
    public List<Meal> getMeals() {
        return meals;
    }

    // MODIFIES: this
    // EFFECTS: adds the given meal to the meal list
    public void addMeal(Meal m) {
        this.meals.add(m);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("meals", mealsToJson());
        return json;
    }

    // EFFECTS: returns meals in this meal list as a JSON array
    protected JSONArray mealsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Meal m : meals) {
            jsonArray.put(m.toJson());
        }

        return jsonArray;
    }
}