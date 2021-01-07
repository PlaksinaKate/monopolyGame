package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class PrintService {
    private FieldService fieldService = new FieldService();
    private ActionService actionService = new ActionService();

    public void printFields(CircleList<BaseField> fields) {
        String reset = "\u001b[0m";
        for (BaseField field : fields) {
            if (field.getClass().getSimpleName().equals("StreetField")) {
                StreetField streetField = (StreetField) field;
                System.out.println(streetField.getColor() + streetField.getNumberOfField() + ". " + field.getName() + reset);
            } else if (field.getClass().getSimpleName().equals("ActionField")) {
                System.out.println(field.getNumberOfField() + ". " + field.getName());
            } else if (field.getClass().getSimpleName().equals("BaseField")) {
                System.out.println(field.getNumberOfField() + ". " + field.getName());
            }
        }
    }

    public void printPlayers(Queue<Player> players) {
        int count = 0;
        for (Player player : players) {
            count++;
            System.out.println(count + ". " + player.getPlayerName());
        }

    }

    public void printField(int numberOfField, CircleList<BaseField> fields, Actions actions, Player player) {
        if (fieldService.searchField(numberOfField, fields).getClass().getSimpleName().equals("StreetField")) {
            StreetField street = (StreetField) fieldService.searchField(numberOfField, fields);
            if (street.getPlayer() == null) {
                System.out.println("Вам выпала улица: " + street.getName() + ", у которой нет владельца. Желаете ли вы ее купить?");
            } else {
                System.out.println("Вы передвинулись на улицу: " + street.getName() + ", у которой есть владелец. Вы должны заплатить арендную плату.");
            }
            actionService.addAction(player, actions, street);
        } else if (fieldService.searchField(numberOfField, fields).getClass().getSimpleName().equals("ActionField")) {
            System.out.println("Вам выпало поле: " + fieldService.searchField(numberOfField, fields).getName());
        } else if (fieldService.searchField(numberOfField, fields).getClass().getSimpleName().equals("BaseField")) {
            System.out.println("Вам выпало поле: " + fieldService.searchField(numberOfField, fields).getName());
        }
    }

    public void printFieldsWithPlayers(CircleList<BaseField> fields, Actions actions, Queue<Player> players) {
        String reset = "\u001b[0m";
        Map<Player, BaseField> lastField = new HashMap<>();
        for (Player player : players) {
            lastField.put(player, actions.getMoves().get(player).get(actions.getMoves().get(player).size() - 1));
        }

        for (BaseField field : fields) {
            Player playerOnField = null;
            for (Map.Entry<Player, BaseField> map : lastField.entrySet()) {
                if (field == map.getValue())
                    playerOnField = map.getKey();
            }
            if (playerOnField != null) {
                if (field.getClass().getSimpleName().equals("StreetField")) {
                    StreetField streetField = (StreetField) field;
                    System.out.println(streetField.getColor() + streetField.getNumberOfField() + ". " + field.getName() + reset + "                      " + playerOnField.getPlayerName());
                } else if (field.getClass().getSimpleName().equals("ActionField")) {
                    System.out.println(field.getNumberOfField() + ". " + field.getName() +"                      " + playerOnField.getPlayerName());
                } else if (field.getClass().getSimpleName().equals("BaseField")) {
                    System.out.println(field.getNumberOfField() + ". " + field.getName() + "                      " + playerOnField.getPlayerName());
                }
            } else {
                if (field.getClass().getSimpleName().equals("StreetField")) {
                    StreetField streetField = (StreetField) field;
                    System.out.println(streetField.getColor() + streetField.getNumberOfField() + ". " + field.getName() + reset);
                } else if (field.getClass().getSimpleName().equals("ActionField")) {
                    System.out.println(field.getNumberOfField() + ". " + field.getName());
                } else if (field.getClass().getSimpleName().equals("BaseField")) {
                    System.out.println(field.getNumberOfField() + ". " + field.getName());
                }
            }
        }

    }
}
