package ru.vsu.cs.course2.json;

import com.google.gson.*;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.fields.BaseField;

import java.awt.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

public class ActionConverter implements JsonSerializer<Actions>, JsonDeserializer<Actions> {


    @Override
    public JsonElement serialize(Actions actions, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("player", actions.getMoves().toString());
//        object.addProperty("player", actions.getPlayer().toString());

        return object;
    }

    @Override
    public Actions deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
            jsonDeserializationContext) throws JsonParseException {
        JsonObject object = new JsonObject();
        String n = jsonDeserializationContext.deserialize(object.get("name"), String.class);
        return new Actions();
    }
}