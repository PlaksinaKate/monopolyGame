package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.Field;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

import java.util.ArrayList;
import java.util.Queue;

public class ActionService {

    public int dice() {
        int firstDice = (int) (Math.random() * 6 + 1);
        int secondDice = (int) (Math.random() * 6 + 1);


        int diceValue = firstDice + secondDice;
        if (firstDice != secondDice) {
            System.out.println("Бросаете кость. Выпало всего: " + diceValue);
        } else {
            System.out.println("У ваших игральных кубиков выпало одиноковое значение! Пожалуйста, бросьте еще раз.");
            firstDice = (int) (Math.random() * 6 + 1);
            secondDice = (int) (Math.random() * 6 + 1);

            diceValue += firstDice + secondDice;
            if (firstDice != secondDice) {
                System.out.println("Вам повезло. Продвиньтесь на такое количество полей вперед: " + diceValue);
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

    public static ArrayList<Actions> startLocation(ArrayList<Actions> playerAction, Queue<Player> players, CircleList<Field> fields) {
        Field startField = null;
        for (Field field : fields) {
            if (field.getName() == "Старт") {
                startField = field;
            }
        }
        for (Player player : players) {
            playerAction.add(new Actions(startField, player));
        }
        return playerAction;
    }


}
