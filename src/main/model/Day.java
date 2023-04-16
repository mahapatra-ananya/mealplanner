package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// JSONSerializationDemo application used as reference

public class Day extends MealList implements Writable {

    private String name;

    // EFFECTS: creates a new day with the given name and an empty meal list
    public Day(String name) {
        this.name = name;
    }

    // EFFECTS: returns the name of the day
    public String getName() {
        return name;
    }


    // EFFECTS: returns the list of meals of that day formatted to be printed in UI
    public String printableMealList() {
        List returnStatement = new ArrayList<>();
        for (Meal m: this.meals) {
            returnStatement.add(m.getType() + " - " + m.getName());
        }
        EventLog.getInstance().logEvent(new Event("Meals for " + this.name + " printed to console"));
        return returnStatement.toString();
    }

    public void addMeal(Meal m) {
        super.addMeal(m);
        EventLog.getInstance().logEvent(new Event(m.getName() + " (" + m.getType() + ")" + " added to "
                + this.name));
    }

    // EFFECTS: inherited from Writable
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
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
