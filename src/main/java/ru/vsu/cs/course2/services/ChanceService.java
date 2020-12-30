package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;


import java.util.Queue;

public class ChanceService {
    private StreetService streetService = new StreetService();
    private ActionService actionService = new ActionService();

    public void chance(Player player, Actions action, Queue<Chance> chance, CircleList<BaseField> fields, int answer, int numberOfField, Queue<Player> players) {
        FieldService fieldService = new FieldService();
        System.out.println(chance.peek().getText());
        if (chance.peek().getNumberOfField() != 10) {
            System.out.println("Игрок:" + player.getPlayerName() + " отправляется на поле под номером: " + chance.peek().getNumberOfField());
        }
        actionService.addAction(player, action, fieldService.searchField(chance.peek().getNumberOfField(), fields));
        BaseField field = fieldService.searchField(chance.peek().getNumberOfField(), fields);
        if (field.getClass().getSimpleName().equals("StreetField")) {
            StreetField streetField = (StreetField) fieldService.searchField(chance.peek().getNumberOfField(), fields);
            streetService.street(streetField, answer, player);
        } else if (field.getClass().getSimpleName().equals("BaseField")) {
            if (field.getNumberOfField() == 1) {
                System.out.println("Вы на старте!");
                actionService.checkStart(players.peek(), numberOfField);
            } else if (field.getNumberOfField() == 10)
                player.setPrisonFree(true);
        } else
            chance.add(chance.poll());
    }

}
