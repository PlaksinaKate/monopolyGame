package ru.vsu.cs.course2.json;

import com.google.gson.*;
import ru.vsu.cs.course2.model.Player;

import java.lang.reflect.Type;

public class PlayerConverter implements JsonSerializer<Player>, JsonDeserializer<Player> {

    @Override
    public JsonElement serialize(Player player, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("Имя игрока", player.getPlayerName());
        object.addProperty("Деньги", player.getMoney());
        object.addProperty("isPrisonFree", player.isPrisonFree());
        return object;
    }

    @Override
    public Player deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = new JsonObject();
        String name = jsonDeserializationContext.deserialize(object.get("Имя игрока"), String.class);
//        Integer money = jsonDeserializationContext.deserialize(object.get("Деньги"), Integer.class);
//        Boolean pf = jsonDeserializationContext.deserialize(object.get("isPrisonFree"), Boolean.class);
        return new Player(name, 1500, true);
    }
}
