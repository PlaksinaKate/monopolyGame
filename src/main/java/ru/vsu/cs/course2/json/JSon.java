package ru.vsu.cs.course2.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;
import ru.vsu.cs.course2.json.converter.PlayerConverter;
import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import sun.rmi.runtime.Log;


import javax.naming.Context;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
        return gson.fromJson(jsonReader, Actions.class);
    }

//    public FileWriter serialize(Player player, String file) throws IOException {
//        Gson gson = initG();
//        FileWriter fileWriter = new FileWriter(file);
//        gson.toJson(player, fileWriter);
//        fileWriter.close();
//        return fileWriter;
//    }
//
//    public Player deserialize(Player player, String file) throws IOException {
//        Gson gson = initG();
//        FileReader fileReader = new FileReader(file);
//        JsonReader jsonReader = new JsonReader(fileReader);
//        return gson.fromJson(jsonReader, Player.class);
//    }

    private Gson initG() {
        return new GsonBuilder().
                setPrettyPrinting()
                .registerTypeAdapter(Player.class, new PlayerConverter())
                .create();
    }


}
