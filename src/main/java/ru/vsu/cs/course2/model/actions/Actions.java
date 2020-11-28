package ru.vsu.cs.course2.model.actions;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.Field;

public class Actions {
    private Field location;
    private Player player;

    public Actions(Field location, Player player) {
        this.location = location;
        this.player = player;
    }

    public Field getLocation() {
        return location;
    }

    public void setLocation(Field location) {
        this.location = location;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}

