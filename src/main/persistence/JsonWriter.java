package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

// JSONSerializationDemo application used as reference

// Represents a writer that writes JSON representation of meal, shopping list, pantry, and day to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of meal to file
    public void write(Meal meal) {
        JSONObject json = meal.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event(meal.getName() + "( " + meal.getType() + ") "
                + " written to file"));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of pantry to file
    public void write(Pantry pantry) {
        JSONObject json = pantry.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Pantry written to file"));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of shopping list to file
    public void write(ShoppingList shoppingList) {
        JSONObject json = shoppingList.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event("Shopping list written to file"));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of day to file
    public void write(Day day) {
        JSONObject json = day.toJson();
        saveToFile(json.toString(TAB));
        EventLog.getInstance().logEvent(new Event(day.getName() + " written to file"));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
    }

     /*public void write(ArrayList<String> los) {
        saveToFile(stringsToJson(los).toString(TAB));
    }

    protected JSONArray stringsToJsonArray(ArrayList<String> los) {
        JSONArray jsonArray = new JSONArray();

        for (String s: los) {
            JSONObject json = new JSONObject();
            json.put("value", s);
            jsonArray.put(json);
        }

        return jsonArray;
    }

    // EFFECTS: inherited from Writable
    //@Override
    public JSONObject stringsToJson(ArrayList<String> los) {
        JSONObject json = new JSONObject();
        json.put("strings", stringsToJsonArray(los));
        return json;
    }*/

}
