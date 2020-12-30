package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import java.util.HashMap;
import java.util.Map;

public class StreetService {

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

    public void street(StreetField street, int answer, Player player) {
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
                    answer = (int) (Math.random() * 2);
                    sellStreet(street, player, answer);
                } else if (answer == 1) {
                    System.out.println("Да, я покупаю");
                    buyHouse(player, street);
                }
            } else if (!street.isHotel()) {
                System.out.println("Хотите купить отель?");
                if (answer == 0) {
                    System.out.println("Нет, я не хочу покупать");
                    System.out.println("Хотите продать улицу? ");
                    answer = (int) (Math.random() * 2);
                    sellStreet(street, player, answer);
                } else if (answer == 1) {
                    System.out.println("Да, я покупаю");
                    buyHotel(player, street);
                }
            }
        }
    }

    public void sellStreet(StreetField street, Player player, int answer) {
        if (answer == 1) {
            System.out.println("Да, хочу");
            if (street.isHotel()) {
                player.setMoney(player.getMoney() - street.getPrice().getPriceForNewBuildings() * 2 - street.getPrice().getPrice());
            } else if (street.isHouse() && !street.isHotel()) {
                player.setMoney(player.getMoney() - street.getPrice().getPriceForNewBuildings() - street.getPrice().getPrice());
            } else {
                player.setMoney(player.getMoney() - street.getPrice().getPrice());
            }
            street.setPlayer(null);
        } else if (answer == 0) {
            System.out.println("Нет, не хочу");
        }
    }

    public void changeColor(CircleList<BaseField> fields) {
        for (BaseField field : fields) {
            if (field.getClass().getSimpleName().equals("StreetField")) {
                StreetField streetField = (StreetField) field;
                if (streetField.getColor().equals("\u001b[37m")) {
                    streetField.setColor("белый");
                } else if (streetField.getColor().equals("\u001B[36m")) {
                    streetField.setColor("циан");
                } else if (streetField.getColor().equals("\u001B[35m")) {
                    streetField.setColor("пурпуный");
                } else if (streetField.getColor().equals("\u001B[34m")) {
                    streetField.setColor("голубой");
                } else if (streetField.getColor().equals("\u001B[33m")) {
                    streetField.setColor("желтый");
                } else if (streetField.getColor().equals("\u001B[32m")) {
                    streetField.setColor("зеленый");
                } else if (streetField.getColor().equals("\u001B[31m")) {
                    streetField.setColor("красный");
                }
            }
        }
    }
}
