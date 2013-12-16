package example.message;

import junit.framework.Assert;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.junit.Test;

public class JsonMessageTest {

    @Test
    public void testIsJsonParseable() {
        JsonMessage m = new JsonMessage("t", "f", "b");
        String s = m.toString();
        JSONObject o = (JSONObject) JSONValue.parse(s);
        Assert.assertEquals(o.get("to"), "t");
        Assert.assertEquals(o.get("from"), "f");
        Assert.assertEquals(o.get("body"), "b");
    }

}
