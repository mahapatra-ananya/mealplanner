package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DayList {
    protected ArrayList<Day> days;

    // EFFECTS: creates a new empty day list
    public DayList() {
        this.days = new ArrayList<>();
    }

    // EFFECTS: returns all the days in the meal list
    public ArrayList<Day> getDays() {
        return this.days;
    }

    // MODIFIES: this
    // EFFECTS: adds the given day to the day list
    public void addDay(Day day) {
        days.add(day);
    }

    // EFFECTS: returns days in this day list as a JSON array
    protected JSONArray daysToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Day day: days) {
            jsonArray.put(day.toJson());
        }
        return jsonArray;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("days", daysToJson());
        return json;
    }

}
