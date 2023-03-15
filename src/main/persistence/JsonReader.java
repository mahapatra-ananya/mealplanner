package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

import model.*;
import org.json.*;


// JSONSerializationDemo application used as a reference

// Represents a reader that reads ingredient, meal, shopping list, pantry, and day from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Meal read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseMeal(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses meal from JSON object and returns it
    private Meal parseMeal(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Meal meal = new Meal(name);
        meal.setTypeForJson(jsonObject.getString("type"));
        addIngredients(meal, jsonObject);
        return meal;
    }

    // EFFECTS: parses pantry from JSON object and returns it
    private Pantry parsePantry(JSONObject jsonObject) {
        Pantry pantry = new Pantry();
        addIngredients(pantry, jsonObject);
        return pantry;
    }

    // EFFECTS: parses shopping list from JSON object and returns it
    private ShoppingList parseShoppingList(JSONObject jsonObject) {
        ShoppingList shoppingList = new ShoppingList();
        addIngredients(shoppingList, jsonObject);
        return shoppingList;
    }

    // MODIFIES: ingredientList
    // EFFECTS: parses ingredients from JSON object and adds them to meal
    private void addIngredients(IngredientList ingredientList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("ingredients");
        for (Object json : jsonArray) {
            JSONObject nextIngredient = (JSONObject) json;
            addIngredient(ingredientList, nextIngredient);
        }
    }

    // MODIFIES: ingredientList
    // EFFECTS: parses ingredient from JSON object and adds it to meal
    private void addIngredient(IngredientList ingredientList, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        double quantity = jsonObject.getDouble("quantity");
        Ingredient ingredient = new Ingredient(name, quantity);
        ingredientList.addIngredient(ingredient);
    }


    // EFFECTS: parses day from JSON object and returns it
    private Day parseDay(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Day day = new Day(name);
        addMeals(day, jsonObject);
        return day;
    }

    // EFFECTS: parses meal list from JSON object and returns it
    private MealList parseMealList(JSONObject jsonObject) {
        MealList mealList = new MealList();
        addMeals(mealList, jsonObject);
        return mealList;
    }

    // MODIFIES: MealList
    // EFFECTS: parses meals from JSON object and adds them to meal list
    private void addMeals(MealList mealList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("meals");
        for (Object json : jsonArray) {
            JSONObject nextMeal = (JSONObject) json;
            addMeal(mealList, nextMeal);
        }
    }

    // MODIFIES: MealList
    // EFFECTS: parses meal from JSON object and adds it to meal list
    private void addMeal(MealList mealList, JSONObject jsonObject) {
        mealList.addMeal(parseMeal(jsonObject));
    }
}