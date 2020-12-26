package ru.vsu.cs.course2.json.converter;

import com.google.gson.*;
import ru.vsu.cs.course2.model.Player;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PlayerConverter implements JsonSerializer<Player>, JsonDeserializer<String> {

    @Override
    public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String name = jsonDeserializationContext.deserialize(object.get("Имя"), String.class);
        return name;
    }

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("Имя", player.getPlayerName());
        return object;
    }
}
