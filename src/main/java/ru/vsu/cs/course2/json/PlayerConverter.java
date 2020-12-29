package ru.vsu.cs.course2.json;

import com.google.gson.*;
import ru.vsu.cs.course2.model.Player;

import java.lang.reflect.Type;

public class PlayerConverter implements JsonSerializer<Player>, JsonDeserializer<String> {

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("имя", player.getPlayerName());
        return object;
    }

    @Override
    public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = new JsonObject();
        String name = jsonDeserializationContext.deserialize(object.get("имя"), String.class);
        return name;
    }
}
