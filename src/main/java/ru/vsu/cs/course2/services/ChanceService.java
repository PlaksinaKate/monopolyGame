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
    ActionService actionService;
    FieldService fieldService;
    StreetService streetService;

    public ChanceService() {
        actionService = new ActionService();
        fieldService = new FieldService();
        streetService = new StreetService();
    }

    public void chance(Player player, ArrayList<Actions> playerAction, Queue<Chance> chance, CircleList<BaseField> fields) {
        System.out.println(chance.peek().getText());
        System.out.println("Игрок:" + player + " отправляется на поле под номером: " + chance.peek().getNumberOfField());
        actionService.addAction(player, playerAction, fieldService.searchField(chance.peek().getNumberOfField(), fields));
        if (fieldService.searchField(chance.peek().getNumberOfField(), fields).getClass().getSimpleName().equals("StreetField")) {
            StreetField streetField = (StreetField) fieldService.searchField(chance.peek().getNumberOfField(), fields);
            if (streetField.getPlayer() != player) {
                System.out.println("Игроку нужно заплатить за аренду");
                streetService.payRent(streetField, player);
            } else if (streetField.getPlayer() == null) {
                System.out.println("Вы можете купить эту улицу");
            }
        }
        chance.add(chance.poll());
    }
}
