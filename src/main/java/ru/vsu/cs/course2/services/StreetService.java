package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.fields.ActionField;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import javax.swing.*;
import java.util.ArrayList;
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
        Map<Player, BaseField> ownProperty = null;
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
}
