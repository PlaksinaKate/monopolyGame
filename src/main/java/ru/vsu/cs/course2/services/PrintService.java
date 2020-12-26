package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class PrintService {
    private FieldService fieldService = new FieldService();

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

    public void printField(int numberOfField, CircleList<BaseField> fields, ArrayList<Actions> playerActions, Player player) {
        if (fieldService.searchField(numberOfField, fields).getClass().getSimpleName().equals("StreetField")) {
            StreetField street = (StreetField) fieldService.searchField(numberOfField, fields);
            if (street.getPlayer() == null) {
                System.out.println("Вам выпала улица: " + street.getName() + ", у которой нет владельца. Желаете ли вы ее купить?");
            } else {
                System.out.println("Вы передвинулись на улицу: " + street.getName() + ", у которой есть владелец. Вы должны заплатить арендную плату.");
            }
            for (int i = 0; i < playerActions.size(); i++) {
                if (playerActions.get(i).getPlayer() == player) {
                    playerActions.get(i).getLocation().add(street);
                    break;
                }
            }
        } else if (fieldService.searchField(numberOfField, fields).getClass().getSimpleName().equals("ActionField")) {
            System.out.println("Вам выпало поле: " + fieldService.searchField(numberOfField, fields).getName());
        } else if (fieldService.searchField(numberOfField, fields).getClass().getSimpleName().equals("BaseField")) {
            System.out.println("Вам выпало поле: " + fieldService.searchField(numberOfField, fields).getName());
        }
    }


}
