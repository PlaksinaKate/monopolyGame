package ru.vsu.cs.course2.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import ru.vsu.cs.course2.model.actions.Actions;


import java.io.*;
import java.util.ArrayList;

public class JSon {
    public JSon() {
    }

    public FileWriter serialize(ArrayList<Actions> playerActions, String file) throws IOException {
        Gson gson = initG();
        FileWriter fileWriter = new FileWriter(file);
        gson.toJson(playerActions, fileWriter);
        fileWriter.close();
        return fileWriter;
    }

    public ArrayList<Actions> deserialize(ArrayList<Actions> playerActions, String file) throws IOException {
        Gson gson = initG();
        FileReader fileReader = new FileReader(file);
        JsonReader jsonReader = new JsonReader(fileReader);
        return gson.fromJson(jsonReader, ArrayList.class);
    }


    private Gson initG() {
        return new GsonBuilder().
                setPrettyPrinting()
                .create();
    }


}
