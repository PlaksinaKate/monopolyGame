package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.Field;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import java.util.Map;

public class StreetService {
    public Field searchField(String nameOfField, CircleList<Field> fields) {
        for (Field field : fields) {
            if (field.getName().equals(nameOfField)) {
                return field;
            }
        }
        return null;
    }
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

    public Map<Player, Field> getAllStreets(Player player, CircleList<Field> fields) {
        Map<Player, Field> ownProperty = null;
        for (Field field : fields) {
            if (field.getClass().getSimpleName().equals("StreetField")) {
                StreetField sf = (StreetField) field;
                if (sf.getPlayer().equals(player) && sf.getClass().getSimpleName().equals("StreetField")) {
                    ownProperty.put(player, sf);
                }
            }

        }
        return ownProperty;
    }

}
