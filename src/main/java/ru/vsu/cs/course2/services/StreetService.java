package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.fields.ActionField;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StreetService {

    public int amountOfHouses(StreetField street) {
        int count = amountOfHouses(street);
        if (street.isHouse()) {
            count++;
            street.setHouse(false);
        } else {
            System.err.println("Вы купили все дома");
        }
        return count;
    }

    public void buyHouse(Player player, StreetField streetField) {
        player.setMoney(player.getMoney() - streetField.getPrice().getPriceForNewBuildings());
        streetField.setHouse(true);
    }

    public void buyHotel(Player player, StreetField streetField) {
        player.setMoney(player.getMoney() - streetField.getPrice().getPriceForNewBuildings());
        streetField.setHotel(true);
    }

    public void buyStreet(Player player, StreetField streetField) {
        if (streetField.getPlayer() == null) {
            player.setMoney(player.getMoney() - streetField.getPrice().getPriceForNewBuildings());
            streetField.setPlayer(player);
        } else {
            System.err.println("Эта улица уже куплена");
        }
    }

    public void payRent(StreetField streetField, Player player) {
        if (!streetField.isHouse()) {
            System.out.println("Заплатите аренду в размере:" + streetField.getPrice().getRentPrice().getRentPriceWithoutBuildings());
            player.setMoney(player.getMoney() - streetField.getPrice().getRentPrice().getRentPriceWithoutBuildings());
        } else if (streetField.isHouse() && !(streetField.isHotel())) {
            System.out.println("Заплатите аренду в размере:" + streetField.getPrice().getRentPrice().getRentPriceWithHouse());
            player.setMoney(player.getMoney() - streetField.getPrice().getRentPrice().getRentPriceWithHouse());
        } else if (streetField.isHotel()) {
            System.out.println("Заплатите аренду в размере:" + streetField.getPrice().getRentPrice().getRentPriceWithHotel());
            player.setMoney(player.getMoney() - streetField.getPrice().getRentPrice().getRentPriceWithHotel());
        }
    }

    public Map<Player, BaseField> getAllStreets(Player player, CircleList<BaseField> fields) {
        Map<Player, BaseField> ownProperty = new HashMap<>();
        for (BaseField baseField : fields) {
            if (baseField.getClass().getSimpleName().equals("StreetField")) {
                StreetField sf = (StreetField) baseField;
                if (sf.getPlayer().equals(player) && sf.getClass().getSimpleName().equals("StreetField")) {
                    ownProperty.put(player, sf);
                }
            }

        }
        return ownProperty;
    }

    public void street(StreetField street, int answer, Player player, int answer2) {
        if (street.getPlayer() == null) {
            if (answer == 1) {
                System.out.println("Да, я ее покупаю");
                buyStreet(player, street);
            } else {
                System.out.println("Нет, я не хочу покупать");
            }
        } else if (street.getPlayer() != null && street.getPlayer() != player) {
            payRent(street, player);
        } else if (street.getPlayer() == player) {
            if (!street.isHouse()) {
                System.out.println("Хотите купить дом?");
                if (answer == 0) {
                    System.out.println("Нет, я не хочу покупать");
                    System.out.println("Хотите продать улицу? ");
                    sellStreet(street, player, answer2);
                } else if (answer == 1) {
                    System.out.println("Да, я покупаю");
                    buyHouse(player, street);
                }
            } else if (!street.isHotel()) {
                System.out.println("Хотите купить отель?");
                if (answer == 0) {
                    System.out.println("Нет, я не хочу покупать");
                    System.out.println("Хотите продать улицу? ");
                    sellStreet(street, player, answer2);
                } else if (answer == 1) {
                    System.out.println("Да, я покупаю");
                    buyHotel(player, street);
                }
            }
        }
    }

    public void sellStreet(StreetField street, Player player, int answer2) {
        if (answer2 == 1) {
            System.out.println("Да, хочу");
            if (street.isHotel()) {
                player.setMoney(player.getMoney() - street.getPrice().getPriceForNewBuildings() * 2 - street.getPrice().getPrice());
            } else if (street.isHouse()) {
                player.setMoney(player.getMoney() - street.getPrice().getPriceForNewBuildings() - street.getPrice().getPrice());
            } else {
                player.setMoney(player.getMoney() - street.getPrice().getPrice());
            }
        } else if (answer2 == 0) {
            System.out.println("Нет, не хочу");
        }
    }
}
