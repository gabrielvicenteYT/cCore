package cc.causalmc.ccore.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonChain {

    private final JsonObject jsonObject;

    public JsonChain() {
        this.jsonObject = new JsonObject();
    }

    public JsonChain append(String key, String value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public JsonChain append(String key, int value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public JsonChain append(String key, double value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public JsonChain append(String key, float value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public JsonChain append(String key, boolean value) {
        jsonObject.addProperty(key, value);
        return this;
    }

    public JsonChain add(String key, JsonElement value) {
        jsonObject.add(key, value);
        return this;
    }

    public JsonObject getAsJsonObject() {
        return jsonObject;
    }

}