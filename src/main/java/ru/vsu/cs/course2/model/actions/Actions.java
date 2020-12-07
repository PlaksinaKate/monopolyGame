package ru.vsu.cs.course2.model.actions;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.BaseField;

import java.util.ArrayList;

public class Actions {
    private Player player;
    private ArrayList<BaseField> location;

    public Actions(Player player, ArrayList<BaseField> location) {
        this.player = player;
        this.location = location;
    }

    public ArrayList<BaseField> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<BaseField> location) {
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

