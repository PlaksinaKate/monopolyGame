package ru.vsu.cs.course2.json;

import com.google.gson.*;

import java.awt.*;
import java.lang.reflect.Type;

public class ColorConverter implements JsonSerializer<String>, JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = new JsonObject();
        String c = jsonDeserializationContext.deserialize(object.get("цвет"), String.class);
        return c;
    }

    @Override
    public JsonElement serialize(String color, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        if (color.equals("\u001b[37m")) {
            object.addProperty("цвет", "белый");
        } else if (color.equals("\u001B[36m")) {
            object.addProperty("цвет", "циан");
        } else if (color.equals("\u001B[35m")) {
            object.addProperty("цвет", "пурпуный");
        } else if (color.equals("\u001B[34m")) {
            object.addProperty("цвет", "голубой");
        } else if (color.equals("\u001B[33m")) {
            object.addProperty("цвет", "желтый");
        } else if (color.equals("\u001B[32m")) {
            object.addProperty("цвет", "зеленый");
        } else if (color.equals("\u001B[31m")) {
            object.addProperty("цвет", "красный");
        }
        return object;
    }
}
