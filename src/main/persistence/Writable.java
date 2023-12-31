package persistence;

// JsonSerializationDemo application used as reference

import org.json.JSONObject;

public interface Writable {
    // EFFECTS: returns this object as JSON object
    JSONObject toJson();
}
