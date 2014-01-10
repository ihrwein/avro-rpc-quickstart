package example.cli;

import java.util.HashMap;

public class CLIOptionsStore {
    private HashMap<String, Object> options;

    public CLIOptionsStore() {
        options = new HashMap<String, Object>();
    }

    public void addOption(String name, Object value) {
        options.put(name, value);
    }

    public Object getValue(String name) {
        return options.get(name);
    }

    public int size() {
        return options.size();
    }

    public Object getValue(String name, Object defaultValue) {
        if (!options.containsKey(name))
            return defaultValue;
        else
            return options.get(name);
    }

    public int getIntValue(String name) {
        return (Integer) getValue(name);
    }

    public int getIntValue(String name, Integer defaultValue) {
        return (Integer) getValue(name, defaultValue);
    }

    public String getStringValue(String name) {
        return (String) getValue(name);
    }

    public String getStringValue(String name, String defaultValue) {
        return (String) getValue(name, defaultValue);
    }

    public Boolean getBooleanValue(String name, Boolean defaultValue) {
        return (Boolean) getValue(name, defaultValue);
    }

    public Boolean getBooleanValue(String name) {
        return (Boolean) getValue(name);
    }
}
