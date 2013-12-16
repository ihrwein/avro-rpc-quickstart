package example.message;

import com.google.gson.JsonObject;
import example.util.Utils;
import org.json.simple.JSONObject;

public class JsonMessage {

    private String to;
    private String from;
    private String body;

    public JsonMessage(String to, String from, String body) {
        this.to = to;
        this.from = from;
        this.body = body;
    }

    @Override
    public String toString() {
        JSONObject obj = new JSONObject();
        obj.put("to", to);
        obj.put("from", from);
        obj.put("body", body);

        return obj.toString();
    }
}
