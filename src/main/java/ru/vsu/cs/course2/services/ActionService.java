package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class ActionService {

    public int dice() {
        int firstDice = (int) (Math.random() * 6 + 1);
        int secondDice = (int) (Math.random() * 6 + 1);


        int diceValue = firstDice + secondDice;
        if (firstDice != secondDice) {
            System.out.println("Игрок бросает кости. Выпало всего: " + diceValue);
        } else {
            System.out.println("У ваших игральных кубиков выпало одиноковое значение! Пожалуйста, бросьте еще раз.");
            firstDice = (int) (Math.random() * 6 + 1);
            secondDice = (int) (Math.random() * 6 + 1);

            diceValue += firstDice + secondDice;
            if (firstDice != secondDice) {
                System.out.println("Вам повезло. Выпало: " + diceValue);
            } else {
                System.err.println("Вы должны отправиться в тюрьму ");
            }
        }
        return diceValue;

    }


//    public static boolean getFieldsAfter(Player player, Actions actions, int diceValue, CircleList<Field> fields) {
//        boolean value;
//        if ((actions.getLocation().getNumberOfField() + diceValue) > 35) {
//            value = true;
//        } else value = false;
//        for (Field field : fields) {
//            if (field.getNumberOfField() == actions.getLocation().getNumberOfField() + diceValue) {
//                actions.setLocation(field);
//                actions.setPlayer(player);
//            }
//
//        }
//        return value;
//    }

    public ArrayList<Actions> startLocation(ArrayList<Actions> playerAction, Queue<Player> players, CircleList<BaseField> fields) {
        BaseField startBaseField = null;

        for (BaseField baseField : fields) {
            if (baseField.getNumberOfField() == 1) {
                startBaseField = baseField;
                break;
            }
        }
        for (Player player : players) {
            ArrayList<BaseField> list = new ArrayList<>();
            list.add(startBaseField);
            playerAction.add(new Actions(player, list));
        }
        return playerAction;
    }

    public void whoGoesFirst(Queue<Player> players) {
        Map<Integer, Player> playerMap = new HashMap<>();
        int max = 0;
        for (Player player : players) {
            System.out.println("Игрок " + player.getPlayerName() + " бросает кубик");
            max = dice();
            if (playerMap.get(max) != playerMap.get(max)) {
                playerMap.put(max, player);
            } else {
                while (playerMap.get(max) != playerMap.get(max)) {
                    System.out.println("У игрока совпало выпавшее число, нужно перебросить.");
                    max = dice();
                }
                playerMap.put(max, player);
            }
            System.out.println();
        }
        int count = 1;
        int size = players.size();
        while (count != size + 1) {
            players.remove();
            count++;
        }
        ArrayList<Player> players2 = new ArrayList<>();

        for (Map.Entry<Integer, Player> player : playerMap.entrySet()) {
            players2.add(player.getValue());
        }
        for (int i = players2.size() - 1; i >= 0; i--) {
            players.add(players2.get(i));
        }
    }

//    public int lastLocation(Player player, ArrayList<Actions> playerAction) {
//
//    }

    public int getNumberOfField(ArrayList<Actions> playerActions, Player player) {
        int value = 0;
        for (int i = 0; i < playerActions.size(); i++) {
            if (playerActions.get(i).getPlayer() == player) {
                int lastField = playerActions.get(i).getLocation().size() - 1;
                value = playerActions.get(i).getLocation().get(lastField).getNumberOfField();
                break;
            }
        }
        return value;
    }

    public void addAction(Player player, ArrayList<Actions> playerAction,BaseField field) {
        for (int i = 0; i < playerAction.size(); i++) {
            if (playerAction.get(i).getPlayer() == player) {
                playerAction.get(i).getLocation().add(field);
                break;
            }
        }
    }

}
