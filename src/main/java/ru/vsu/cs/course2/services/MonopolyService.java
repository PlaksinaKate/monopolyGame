package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class MonopolyService {
    private StreetService streetService;
    private PlayerService playerService;
    private ActionService actionService;
    private PrintService printService;

    public MonopolyService() {
        streetService = new StreetService();
        playerService = new PlayerService();
        actionService = new ActionService();
        printService = new PrintService();
    }

    public void playMonopoly(CircleList<BaseField> fields, Queue<Treasury> treasury, Queue<Chance> chance, Queue<Player> players, Map<Player, BaseField> ownProperty, ArrayList<Actions> playerActions) {
        System.out.println("Игра началась");
        System.out.println("Поля:");

        printService.printFields(fields);
        System.out.println();

        System.out.println("Игроки: ");
        printService.printPlayers(players);
        System.out.println();
        actionService.whoGoesFirst(players);

        System.out.println("Теперь список игроков выглядит таким образом: ");
        printService.printPlayers(players);
        System.out.println();

        System.out.println("Игроки положили свои фишки на поле 'Старт'");
        actionService.startLocation(playerActions, players, fields);
        System.err.println("Игра началась!");
        System.out.println();

        System.out.println();
//        boolean ifEndTurn = false;
//        String[] options;
//        int selection;
        actionService.startLocation(playerActions, players, fields);
        while (!printService.isGameOver(players, fields, streetService, playerService)) {
            System.out.println("Ход игрока: " + players.peek().getPlayerName());
            int dice = actionService.dice();
            playerService.checkStart(players.peek(), dice, playerActions, actionService);
            printService.printField(dice, fields, playerActions, players.peek(), actionService, streetService);
            System.out.println();
            players.add(players.poll());
//            for (Player player : players) {
//
//                if (ownProperty.get(player) != null) {
//                    ifEndTurn = false;
//                    int counter = 0;
//                    while (!ifEndTurn) {
//                        options = printService.getMenuBeforeRound(fields, streetService);
//                        selection = printService.showMenuBeforeRound(player, options);
//                        switch (options[selection]) {
////                            case "Купить дом или отель":
////                                if (MonopolyProcess.amountOfHouses(fields) < 3) {
////                                    if (MonopolyProcess.buyHouse(player, street))
////                                        counter++;
////                                } else {
////                                    MonopolyProcess.buyHotel(player, street);
////                                }
////                                break;
//                            case "0 - Продать улицу":
//                                printService.SellTheStreetField(players, player, fields, playerService);
//                                break;
////                            case "Продать дом":
////                                showMenuSellTheHouses();
////                                break;
//                            case "1 - Бросить кости":
//                                ifEndTurn = true;
//                                break;
//                        }
//                    }
//
//                }
//                System.out.println("Передвиньтесь, пожалуйста, на " + actionService.dice() + " клеток.");
//                if (playerService.giveMoneyForStart(player, playerActions.get(playerActions.indexOf(player)), actionService.dice(), fields)) {
//                    System.out.println("Вы получили деньги за прохождение одного круга!");
//                    playerService.giveMoneyAfterStart(player);
//                }
//                ifEndTurn = false;
//                while (!ifEndTurn) {
//                    if (playerActions.get(playerActions.indexOf(player)).getLocation().getClass().getSimpleName().equals("StreetField")) {
//                        StreetField street = (StreetField) playerActions.get(playerActions.indexOf(player)).getLocation();
//                        if (street.getPlayer() == null) {
//                            System.out.println("Выберите, пожалуйста, что вы хотите сделать с этим полем:");
//
//                            options = printService.getMenuBeforeRound(fields, streetService);
//                            selection = printService.showMenuBeforeRound(player, options);
//
//                            switch (options[selection]) {
//                                case "Купить улицу":
//                                    streetService.buyStreet(player, street);
//                                    break;
//                                case "Конец хода":
//                                    ifEndTurn = true;
//                                    break;
//                                case "Конец игры":
//                                    playerService.endGame(players, fields, streetService);
//                                    break;
//                            }
//                        } else {
//                            System.out.println("Вы должны заплатить ренту");
//
//                            if (street.isHouse()) {
//                                if (player.getMoney() < street.getPrice().getRentPrice().getRentPriceWithHouse() * streetService.amountOfHouses(street))
//                                    printService.notEnoughMoney(players, fields, streetService, playerService);
//                                else
//                                    playerService.minMoney(player, street.getPrice().getRentPrice().getRentPriceWithHouse() * streetService.amountOfHouses(street));
//
//                            } else if (street.isHotel()) {
//                                if (player.getMoney() < street.getPrice().getRentPrice().getRentPriceWithHotel())
//                                    printService.notEnoughMoney(players, fields, streetService, playerService);
//                                else
//                                    playerService.minMoney(player, street.getPrice().getRentPrice().getRentPriceWithHotel());
//
//                            } else {
//                                if (player.getMoney() < street.getPrice().getRentPrice().getRentPriceWithoutBuildings())
//                                    printService.notEnoughMoney(players, fields, streetService, playerService);
//                                else
//                                    playerService.minMoney(player, street.getPrice().getRentPrice().getRentPriceWithoutBuildings());
//                            }
//
//
//                        }
//                    } else if (playerActions.get(playerActions.indexOf(player)).getLocation().getClass().getSimpleName().equals("ActionField")) {
//
//                    }
//                }
            //}
        }
    }


}

