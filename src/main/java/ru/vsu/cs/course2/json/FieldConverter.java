package ru.vsu.cs.course2.json;

import com.google.gson.*;
import ru.vsu.cs.course2.model.fields.BaseField;

import java.lang.reflect.Type;

public class FieldConverter implements JsonSerializer<BaseField>, JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject object = new JsonObject();
        String name = jsonDeserializationContext.deserialize(object.get("название поля"), String.class);
        return name;
    }

    @Override
    public JsonElement serialize(BaseField baseField, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonObject object = new JsonObject();
        object.addProperty("название поля", baseField.getName());
        return object;    }
}
