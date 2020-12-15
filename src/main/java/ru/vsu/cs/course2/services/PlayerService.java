package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class PlayerService {
    private ActionService actionService = new ActionService();

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

    public void buyHotel(Player player, StreetField street, StreetService streetService) {
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

    public void buyHouse(Player player, StreetField street, StreetService streetService) {
        int count = 0;
        if (!street.isHouse() && streetService.amountOfHouses(street) <= 3) {
            player.setMoney(player.getMoney() - street.getPrice().getPriceForNewBuildings());
            street.setHouse(true);
        }
    }

    public int checkStart(Player player, int numberOfField) {
        if (numberOfField > 33) {
            player.setMoney(player.getMoney() + 200);
            numberOfField -= 33;
            return numberOfField;
        }
        return numberOfField;
    }

    public void endGame(Queue<Player> players, CircleList<BaseField> fields, StreetService streetService) {
        int highAmountOfMoney = 0;
        int count = 0;
        Map<Integer, Player> rating = new HashMap<>();
        for (Player player : players) {
            for (Map.Entry<Player, BaseField> field : streetService.getAllStreets(player, fields).entrySet()) {
                StreetField street = (StreetField) field.getValue();
                if (street.isHotel()) {
                    addMoney(player, street.getPrice().getPrice() + street.getPrice().getPriceForNewBuildings() * 4);
                } else if (street.isHouse()) {
                    addMoney(player, street.getPrice().getPriceForNewBuildings() + street.getPrice().getPrice());
                } else {
                    addMoney(player, street.getPrice().getPrice());
                }
            }
            if (highAmountOfMoney == player.getMoney()) {
                count++;
                rating.put(count, player);
            } else if (highAmountOfMoney < player.getMoney()) {
                count = 1;
                rating.put(count, player);
                highAmountOfMoney = player.getMoney();
            } else {
                rating.put(rating.size(), player);
            }
        }

        System.out.println("Рейтинг игроков: ");
        for (int i = 0; i < count; i++) {
            System.out.println(rating.get(i - 1).getPlayerName());
        }
        System.exit(0);
    }


}
