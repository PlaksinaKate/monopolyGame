package ru.vsu.cs.course2.json.converter;

import com.google.gson.*;
import ru.vsu.cs.course2.model.Player;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class PlayerConverter implements JsonSerializer<Player>, JsonDeserializer<Map<String, Integer>> {

    @Override
    public Map<String, Integer> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = jsonElement.getAsJsonObject();
        String name = jsonDeserializationContext.deserialize(object.get("Имя"), String.class);
        Integer money = jsonDeserializationContext.deserialize(object.get("Деньги"), Integer.class);
        Map<String, Integer> map = new HashMap<>();
        map.put(name, money);
        return map;
    }

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("Имя", player.getPlayerName());
        object.addProperty("Деньги", player.getMoney());
        return object;
    }
}
