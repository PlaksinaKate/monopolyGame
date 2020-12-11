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
    private StreetService streetService = new StreetService();
    private PlayerService playerService = new PlayerService();
    private ActionService actionService = new ActionService();
    private PrintService printService = new PrintService();
    private FieldService fieldService = new FieldService();

    public void playMonopoly(CircleList<BaseField> fields, Queue<Treasury> treasury, Queue<Chance> chance, Queue<Player> players, ArrayList<Actions> playerActions) {
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
            int numberOfField = actionService.getNumberOfField(playerActions, players.peek()) + actionService.dice();
            numberOfField = playerService.checkStart(players.peek(), numberOfField);
            printService.printField(numberOfField, fields, playerActions, players.peek());
            fieldService.checkField(fieldService.searchField(numberOfField, fields), "Да", players.peek(), playerActions, chance, fields, treasury, numberOfField, players);
            System.out.println();
            players.add(players.poll());
        }
    }

}

