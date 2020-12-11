package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import java.util.ArrayList;
import java.util.Queue;

public class ChanceService {
    private StreetService streetService = new StreetService();
    private PlayerService playerService = new PlayerService();
    private ActionService actionService = new ActionService();

    public void chance(Player player, ArrayList<Actions> playerAction, Queue<Chance> chance, CircleList<BaseField> fields, String answer, int numberOfField, Queue<Player> players) {
        FieldService fieldService = new FieldService();
        System.out.println(chance.peek().getText());
        System.out.println("Игрок:" + player.getPlayerName() + " отправляется на поле под номером: " + chance.peek().getNumberOfField());
        actionService.addAction(player, playerAction, fieldService.searchField(chance.peek().getNumberOfField(), fields));
        if (fieldService.searchField(chance.peek().getNumberOfField(), fields).getClass().getSimpleName().equals("StreetField")) {
            StreetField streetField = (StreetField) fieldService.searchField(chance.peek().getNumberOfField(), fields);
            if (streetField.getPlayer() != player) {
                System.out.println("Игроку нужно заплатить за аренду");
                streetService.payRent(streetField, player);
            } else if (streetField.getPlayer() == null) {
                System.out.println("Вы можете купить эту улицу");
                if (answer == "Да") {
                    System.out.println("Я хочу ее купить!");
                    streetService.buyStreet(player, streetField);
                } else if (answer == "Нет") {
                    System.out.println("Я не хочу ее покупать!");
                }
            }
        } else if (fieldService.searchField(chance.peek().getNumberOfField(), fields).getClass().getSimpleName().equals("BaseField")) {
            System.out.println("Вы на старте!");
            playerService.checkStart(players.peek(),numberOfField);

        }
        chance.add(chance.poll());
    }
}
