package ru.vsu.cs.course2.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import ru.vsu.cs.course2.model.actions.Actions;

import java.io.*;

public class JSon {
    public JSon() {
    }

    public FileWriter serialize(Actions actions, String file) throws IOException {
        Gson gson = initG();
        FileWriter fileWriter = new FileWriter(file);
        gson.toJson(actions, fileWriter);
        fileWriter.close();
        return fileWriter;
    }

    public Actions deserialize(String file) throws IOException {
        Gson gson = initG();
        FileReader fileReader = new FileReader(file);
        JsonReader jsonReader = new JsonReader(fileReader);
        return gson.fromJson(jsonReader, Actions.class);
    }

    private Gson initG() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .enableComplexMapKeySerialization()
                .create();
    }
}
