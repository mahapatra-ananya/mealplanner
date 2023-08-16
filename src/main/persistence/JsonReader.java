package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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

    // EFFECTS: reads meal from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Meal readMeal() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Meal meal = parseMeal(jsonObject);
       // EventLog.getInstance().logEvent(new Event(meal.getName() + " (" + meal.getType() + ") "
       //         + " read from file"));
        return meal;
    }

    // EFFECTS: reads pantry from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Pantry readPantry() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
       // EventLog.getInstance().logEvent(new Event("Pantry read from file"));
        return parsePantry(jsonObject);
    }

    // EFFECTS: reads shopping list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public ShoppingList readShoppingList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
       // EventLog.getInstance().logEvent(new Event("Shopping list read from file"));
        return parseShoppingList(jsonObject);
    }

    // EFFECTS: reads day from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Day readDay() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Day day = parseDay(jsonObject);
      //  EventLog.getInstance().logEvent(new Event(day.getName() + " read from file"));
        return day;
    }

    // EFFECTS: reads day list from file and returns it;
    // throws IOException if an error occurs reading data from file
    public DayList readDayList() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        DayList dayList = parseDayList(jsonObject);
        //  EventLog.getInstance().logEvent(new Event(day.getName() + " read from file"));
        return dayList;
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
        setMealType(meal, jsonObject);
        addIngredients(meal, jsonObject);
        return meal;
    }

    private void setMealType(Meal meal, JSONObject jsonObject) {
        if (jsonObject.getString("type") != null) {
            meal.setTypeForJson(jsonObject.getString("type"));
        }
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

    // EFFECTS: parses day list from JSON object and returns it
    private DayList parseDayList(JSONObject jsonObject) {
        DayList dayList = new DayList();
        addDays(dayList, jsonObject);
        return dayList;
    }

    // MODIFIES: DayList
    // EFFECTS: parses days from JSON object and adds them to day list
    private void addDays(DayList dayList, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("days");
        for (Object json : jsonArray) {
            JSONObject nextDay = (JSONObject) json;
            addDay(dayList, nextDay);
        }
    }

    // MODIFIES: DayList
    // EFFECTS: parses day from JSON object and adds it to day list
    private void addDay(DayList dayList, JSONObject jsonObject) {
        dayList.addDay(parseDay(jsonObject));
    }


}
