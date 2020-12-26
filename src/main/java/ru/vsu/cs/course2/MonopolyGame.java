package ru.vsu.cs.course2;

import ru.vsu.cs.course2.json.JSon;
import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.services.InfoService;
import ru.vsu.cs.course2.services.MonopolyService;
import ru.vsu.cs.course2.util.CircleList;


import java.io.IOException;
import java.util.*;

public class MonopolyGame {
    private InfoService infoService = new InfoService();
    private CircleList<BaseField> fields;
    private Queue<Treasury> treasury;
    private Queue<Chance> chance;
    private Queue<Player> players;
    private ArrayList<Actions> playerActions;

    public MonopolyGame() {
        fields = new CircleList<>();
        treasury = new LinkedList<>();
        chance = new LinkedList<>();
        players = new LinkedList<>();
        playerActions = new ArrayList<>();
    }

    public void setUpMonopoly() {
        infoService.addFields(fields);
        infoService.addTreasury(treasury, players);
        infoService.addChance(chance);
        infoService.addPlayers(players);
    }

    public void playGame() {
        MonopolyService monopolyService = new MonopolyService();
        monopolyService.playMonopoly(fields, treasury, chance, players, playerActions);
    }

}
