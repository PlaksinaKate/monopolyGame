package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.Field;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.services.*;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class MonopolyService {
    private StreetService streetService;
    private PlayerService playerService;
    private ActionService actionService;
    private CircleList<Field> fields;
    private InfoService infoService;
    private Stack<Treasury> treasury;
    private Stack<Chance> chance;
    private Queue<Player> players;
    private Map<Player, Field> ownProperty;
    private ArrayList<Actions> playerActions;
    private PrintService botService;

    public MonopolyService() {
        streetService = new StreetService();
        playerService = new PlayerService();
        infoService = new InfoService();
        actionService = new ActionService();
        botService = new PrintService();
        fields = new CircleList<>();
        treasury = new Stack<>();
        chance = new Stack<>();
        players = new LinkedList<>();
        ownProperty = new HashMap<>();
        playerActions = new ArrayList<>();
    }


    public void setUpMonopoly() {
        infoService.addFields(fields);
        infoService.addTreasury(treasury, players);
        infoService.addChance(chance, fields, streetService);
        infoService.addPlayers(players);
    }

    public void playMonopoly() {
        boolean ifEndTurn = false;
        String[] options;
        int selection;
        ActionService.startLocation(playerActions, players, fields);
        while (!botService.isGameOver(players, fields, streetService, playerService)) {
            for (Player player : players) {
                System.out.println("Ход игрока номер: " + player.getPlayerName());
                System.out.println(player.toString()); // все свойства игрока
                if (ownProperty.get(player) != null) {
                    ifEndTurn = false;
                    int counter = 0;
                    while (!ifEndTurn) {
                        options = botService.getMenuBeforeRound(fields, streetService);
                        selection = botService.showMenuBeforeRound(player, options);
                        switch (options[selection]) {
//                            case "Купить дом или отель":
//                                if (MonopolyProcess.amountOfHouses(fields) < 3) {
//                                    if (MonopolyProcess.buyHouse(player, street))
//                                        counter++;
//                                } else {
//                                    MonopolyProcess.buyHotel(player, street);
//                                }
//                                break;
                            case "0 - Продать улицу":
                                botService.SellTheStreetField(players, player, fields, playerService);
                                break;
//                            case "Продать дом":
//                                showMenuSellTheHouses();
//                                break;
                            case "1 - Бросить кости":
                                ifEndTurn = true;
                                break;
                        }
                    }

                }
                System.out.println("Передвиньтесь, пожалуйста, на " + actionService.dice() + " клеток.");
                if (playerService.giveMoneyForStart(player, playerActions.get(playerActions.indexOf(player)), actionService.dice(), fields)) {
                    System.out.println("Вы получили деньги за прохождение одного круга!");
                    playerService.giveMoneyAfterStart(player);
                }
                ifEndTurn = false;
                while (!ifEndTurn) {
                    if (playerActions.get(playerActions.indexOf(player)).getLocation().getClass().getSimpleName().equals("StreetField")) {
                        StreetField street = (StreetField) playerActions.get(playerActions.indexOf(player)).getLocation();
                        if (street.getPlayer() == null) {
                            System.out.println("Выберите, пожалуйста, что вы хотите сделать с этим полем:");

                            options = botService.getMenuBeforeRound(fields, streetService);
                            selection = botService.showMenuBeforeRound(player, options);

                            switch (options[selection]) {
                                case "Купить улицу":
                                    streetService.buyStreet(player, street);
                                    break;
                                case "Конец хода":
                                    ifEndTurn = true;
                                    break;
                                case "Конец игры":
                                    botService.endGame(players, fields, streetService, playerService);
                                    break;
                            }
                        } else {
                            System.out.println("Вы должны заплатить ренту");

                            if (street.isHouse()) {
                                if (player.getMoney() < street.getPrice().getRentPrice().getRentPriceWithHouse() * streetService.amountOfHouses(street))
                                    botService.notEnoughMoney(players, fields, streetService, playerService);
                                else
                                    playerService.minMoney(player, street.getPrice().getRentPrice().getRentPriceWithHouse() * streetService.amountOfHouses(street));

                            } else if (street.isHotel()) {
                                if (player.getMoney() < street.getPrice().getRentPrice().getRentPriceWithHotel())
                                    botService.notEnoughMoney(players, fields, streetService, playerService);
                                else
                                    playerService.minMoney(player, street.getPrice().getRentPrice().getRentPriceWithHotel());

                            } else {
                                if (player.getMoney() < street.getPrice().getRentPrice().getRentPriceWithoutBuildings())
                                    botService.notEnoughMoney(players, fields, streetService, playerService);
                                else
                                    playerService.minMoney(player, street.getPrice().getRentPrice().getRentPriceWithoutBuildings());
                            }


                        }
                    } else if (playerActions.get(playerActions.indexOf(player)).getLocation().getClass().getSimpleName().equals("ActionField")) {

                    }
                }
            }
        }
    }

}

