package ru.vsu.cs.course2.model.actions;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.BaseField;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Actions {
    private Map<Player, ArrayList<BaseField>> moves;

    public Actions() {
        this.moves = new HashMap<>();
    }

    public Actions(Map<Player, ArrayList<BaseField>> moves) {
        this.moves = moves;
    }

    public Map<Player, ArrayList<BaseField>> getMoves() {
        return moves;
    }

    public void setMoves(Map<Player, ArrayList<BaseField>> moves) {
        this.moves = moves;
    }
}

