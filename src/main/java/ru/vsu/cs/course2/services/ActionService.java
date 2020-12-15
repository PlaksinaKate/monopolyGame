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
    private FieldService fieldService = new FieldService();

    public int dice(int count, Player player, int answer) {
        int firstDice = (int) (Math.random() * 6 + 1);
        int secondDice = (int) (Math.random() * 6 + 1);


        int diceValue = firstDice + secondDice;
        if (count != 0) {
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
                    player.setPrisonFree(false);
                    fieldService.prison(answer, player);
                }
            }
        } else {
            System.out.println("Игрок бросает кости. Выпало всего: " + diceValue);
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
        Map<Player, Integer> playerMap = new HashMap<>();
        ArrayList<Player> players1 = new ArrayList<>();
        int answer = (int) (Math.random() * 2);
        for (Player player : players) {
            System.out.println("Игрок " + player.getPlayerName() + " бросает кубик");
            players1.add(player);
            playerMap.put(player, dice(0, player, answer));
            System.out.println();
        }
        //проверка на одинаковые значения
        for (int i = 0; i < players1.size(); i++) {
            int temp = 0;
            while (temp != players.size()) {
                if (playerMap.get(players1.get(i)) == playerMap.get(players1.get(temp)) && temp != i) {
                    System.out.println("У игроков " + players1.get(i).getPlayerName() + " " + players1.get(temp).getPlayerName() + " выпало одинаковое значение кубика." + players1.get(temp).getPlayerName() + " должен перебросить кубик.");
                    playerMap.get(players1.get(temp)).equals(dice(0, players1.get(temp), answer));
                    System.out.println();
                }
                temp++;
            }
        }

        int count = 1;
        int size = players.size();
        while (count != size + 1) {
            players.remove();
            count++;
        }
        ArrayList<Player> players2 = new ArrayList<>();

        for (Map.Entry<Player, Integer> player : playerMap.entrySet()) {
            players2.add(player.getKey());
        }
        for (int i = players2.size() - 1; i >= 0; i--) {
            players.add(players2.get(i));
        }
    }

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

    public void addAction(Player player, ArrayList<Actions> playerAction, BaseField field) {
        for (int i = 0; i < playerAction.size(); i++) {
            if (playerAction.get(i).getPlayer() == player) {
                playerAction.get(i).getLocation().add(field);
                break;
            }
        }
    }

}
