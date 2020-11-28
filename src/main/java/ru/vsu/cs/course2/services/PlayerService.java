package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.Field;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

public class PlayerService {
    public void addMoney(Player player, int money) {
        int playersMoney = player.getMoney() + money;
        player.setMoney(playersMoney);
    }

    public void minMoney(Player player, int money) {
        int playersMoney = player.getMoney() - money;
        player.setMoney(playersMoney);
    }

    public void giveMoneyAfterStart(Player player) {
        addMoney(player, 400);
    }

    public void buyHotel(Player player, StreetField street,StreetService streetService) {
        if (!street.isHotel()) {
            if (streetService.amountOfHouses(street) == 3) {
                player.setMoney(player.getMoney() - street.getPrice().getPriceForNewBuildings());
                street.setHotel(true);
            } else {
                System.err.println("Вам осталось купить еще" + (3 - streetService.amountOfHouses(street)) + " дома");
            }
        } else {
            System.err.println("Эта улица уже имеет отель");
        }
    }

    public void buyHouse(Player player, StreetField street,StreetService streetService) {
        int count = 0;
        if (!street.isHouse() && streetService.amountOfHouses(street) <= 3) {
            player.setMoney(player.getMoney() - street.getPrice().getPriceForNewBuildings());
            street.setHouse(true);
        }
    }
    public boolean giveMoneyForStart(Player player, Actions actions, int diceValue, CircleList<Field> fields) {
        boolean value;
        if ((actions.getLocation().getNumberOfField() + diceValue) > 35) {
            value = true;
        } else value = false;
        for (Field field : fields) {
            if (field.getNumberOfField() == actions.getLocation().getNumberOfField() + diceValue) {
                actions.setLocation(field);
                actions.setPlayer(player);
            }

        }
        return value;
    }

}
