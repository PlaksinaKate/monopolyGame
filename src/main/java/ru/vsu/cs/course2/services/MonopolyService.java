package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class MonopolyService {
    private StreetService streetService;
    private PlayerService playerService;
    private ActionService actionService;
    private PrintService printService;
    private FieldService fieldService;
    private ChanceService chanceService;
    private TreasuryService treasuryService;

    public MonopolyService() {
        streetService = new StreetService();
        playerService = new PlayerService();
        actionService = new ActionService();
        printService = new PrintService();
        fieldService = new FieldService();
        chanceService = new ChanceService();
        treasuryService = new TreasuryService();
    }

    public void playMonopoly(CircleList<BaseField> fields, Queue<Treasury> treasury, Queue<Chance> chance, Queue<Player> players, Map<Player, BaseField> ownProperty, ArrayList<Actions> playerActions) {
        System.out.println("Игра началась");
        System.out.println("Поля:");

        printService.printFields(fields);
        System.out.println();

        System.out.println("Игроки: ");
        printService.printPlayers(players);
        System.out.println();
        actionService.whoGoesFirst(players);

        System.out.println("Теперь список игроков выглядит таким образом: ");
        printService.printPlayers(players);
        System.out.println();

        System.out.println("Игроки положили свои фишки на поле 'Старт'");
        actionService.startLocation(playerActions, players, fields);
        System.err.println("Игра началась!");
        System.out.println();

        System.out.println();
        actionService.startLocation(playerActions, players, fields);
        while (!printService.isGameOver(players, fields, streetService, playerService)) {
            System.out.println("Ход игрока: " + players.peek().getPlayerName());
            int dice = actionService.dice();
            playerService.checkStart(players.peek(), dice, playerActions);
            printService.printField(dice, fields, playerActions, players.peek(), actionService, streetService);
            fieldService.checkField(fieldService.searchField(actionService.getNumberOfField(playerActions, players.peek()) + dice, fields), "Да", players.peek(), playerActions, chance, fields, treasury, dice, players);
            System.out.println();
            players.add(players.poll());
        }
    }

    public StreetService getStreetService() {
        return streetService;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public ActionService getActionService() {
        return actionService;
    }

    public PrintService getPrintService() {
        return printService;
    }

    public FieldService getFieldService() {
        return fieldService;
    }

    public ChanceService getChanceService() {
        return chanceService;
    }

    public TreasuryService getTreasuryService() {
        return treasuryService;
    }
}

