package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.json.JSon;
import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

import java.io.IOException;
import java.util.*;

public class MonopolyService {
    private ActionService actionService = new ActionService();
    private PrintService printService = new PrintService();
    private FieldService fieldService = new FieldService();
    private StreetService streetService = new StreetService();

    public void playMonopoly(CircleList<BaseField> fields, Queue<Treasury> treasury, Queue<Chance> chance, Queue<Player> players) {
        Actions actions = new Actions(new HashMap<>());
        System.out.println("Игра началась");
        System.out.println("Поля:");

        printService.printFields(fields);
        System.out.println();

        System.out.println("Игроки: ");
        printService.printPlayers(players);
        System.out.println();
        actionService.whoGoesFirst(players, actions, fields);

        System.out.println("Теперь список игроков выглядит таким образом: ");
        printService.printPlayers(players);
        System.out.println();

        System.out.println("Игроки положили свои фишки на поле 'Старт'");
        actionService.startLocation(actions, players, fields);
        System.err.println("Игра началась!");
        System.out.println();

        int count = 0;
        int temp = 0;
        while (players.size() != 1) {
            if (temp == players.size()) {
                System.out.println();
                printService.printFieldsWithPlayers(fields, actions, players);
                System.out.println();
                temp = 0;
            }
            if (players.peek().getMoney() >= 0) {
                System.out.println("Ход игрока: " + players.peek().getPlayerName());
                int answer = (int) (Math.random() * 2);
                int numberOfField = actionService.getNumberOfField(actions, players.peek()) + actionService.dice(1, players.peek(), answer, actions, fields);
                numberOfField = actionService.checkStart(players.peek(), numberOfField);

                if (count < 3 && actionService.getNumberOfField(actions, players.peek()) == 10 && !players.peek().isPrisonFree()) {
                    System.out.println("Вы пропускаете ход ");
                    count++;
                } else if (count == 3 && actionService.getNumberOfField(actions, players.peek()) == 10 && !players.peek().isPrisonFree()) {
                    System.err.println("Вы освобождены из тюрьмы!");
                    players.peek().setPrisonFree(true);
                    count = 0;
                } else {
                    printService.printField(numberOfField, fields, actions, players.peek());
                    fieldService.checkField(fieldService.searchField(numberOfField, fields), answer, players.peek(), actions, chance, fields, treasury, numberOfField, players);
                }
                System.out.println("Счет " + players.peek().getPlayerName() + " : " + players.peek().getMoney());
                System.out.println();
                players.add(players.poll());
            } else {
                System.err.println("Игрок " + players.poll().getPlayerName() + " выбывает из игры");
            }
            temp++;
        }
        System.out.println();
        System.err.println("Выиграл(а) " + players.peek().getPlayerName() + " !");
        serialization(actions, fields);
    }

    public void serialization(Actions actions, CircleList<BaseField> fields) {
        JSon jSon = new JSon();
        try {
            streetService.changeColor(fields);
            jSon.serialize(actions, ".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Actions deserialization() {
        JSon jSon = new JSon();
        try {
            return jSon.deserialize(".json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

