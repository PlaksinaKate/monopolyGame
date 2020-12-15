package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class MonopolyService {
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
        int count = 0;
        while (players.size() != 1) {
            if (players.peek().getMoney() >= 0) {
                System.out.println("Ход игрока: " + players.peek().getPlayerName());
                int answer = (int) (Math.random() * 2);
                int answer2 = (int) (Math.random() * 2);
                int numberOfField = actionService.getNumberOfField(playerActions, players.peek()) + actionService.dice(1, players.peek(), answer);
                numberOfField = playerService.checkStart(players.peek(), numberOfField);
                printService.printField(numberOfField, fields, playerActions, players.peek());

                if (count != 3 && !players.peek().isPrisonFree()) {
                    System.out.println("Вы пропускаете ход ");
                    count++;
                } else if (count == 3 && !players.peek().isPrisonFree()) {
                    System.err.println("Вы освобождены из тюрьмы!");
                    players.peek().setPrisonFree(true);
                    count = 0;
                } else {
                    fieldService.checkField(fieldService.searchField(numberOfField, fields), answer2, answer, players.peek(), playerActions, chance, fields, treasury, numberOfField, players);
                }
                System.out.println("Ваш счет: " + players.peek().getMoney());
                System.out.println();
                players.add(players.poll());
            } else {
                System.err.println("Игрок " + players.poll().getPlayerName() + " выбывает из игры");
            }
        }
        System.out.println();
        System.err.println("Выиграл(а) " + players.peek().getPlayerName() + " !");
    }

}

