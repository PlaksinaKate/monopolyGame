package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.Field;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import java.util.*;

public class PrintService {
    public boolean isGameOver(Queue<Player> players, CircleList<Field> fields, StreetService streetService, PlayerService playerService) {
        int countOfBankrupt = 0;
        Player winner = null;
        for (Player player : players) {
            if (player.getMoney() == 0) {
                countOfBankrupt++;
            } else {
                winner = player;
            }
        }
        if (countOfBankrupt >= (players.size() - 1)) {
            endGame(players, fields, streetService, playerService);
            System.out.println("Победитель:" + winner.getPlayerName());
            return true;
        }
        return false;
    }

    public void endGame(Queue<Player> players, CircleList<Field> fields, StreetService streetService, PlayerService playerService) {
        int highAmountOfMoney = 0;
        int count = 0;
        Map<Integer, Player> rating = new HashMap<>();
        for (Player player : players) {
            for (Map.Entry<Player, Field> field : streetService.getAllStreets(player, fields).entrySet()) {
                StreetField street = (StreetField) field.getValue();
                if (street.isHotel()) {
                    playerService.addMoney(player, street.getPrice().getPrice() + street.getPrice().getPriceForNewBuildings() * 4);
                } else if (street.isHouse()) {
                    playerService.addMoney(player, street.getPrice().getPriceForNewBuildings() + street.getPrice().getPrice());
                } else {
                    playerService.addMoney(player, street.getPrice().getPrice());
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

    public void SellTheStreetField(Queue<Player> players, Player player, CircleList<Field> fields, PlayerService playerService) {
        String selection;
        boolean valueInput = false;
        StreetField chosenStreet = null;
        Player chosenPlayer = null;

        System.out.println("Текущий игрок: " + player.getPlayerName());

        do {
            System.out.println("Выберите одно из ваших полей, чтобы продать их другому игроку: ");
            ArrayList<StreetField> playerStreets = null;
            for (Field field : fields) {
                StreetField sf = (StreetField) field;
                if (sf.getPlayer() == player) {
                    playerStreets.add(sf);
                }
            }
            for (int i = 0; i < playerStreets.size(); i++) {
                System.out.println(playerStreets.get(i).getName());
            }

            System.out.println("Введите улицу, которую хотите продать: ");
            selection = getText();
            for (StreetField street : playerStreets) {
                if (selection == street.getName()) {
                    chosenStreet = street;
                    valueInput = true;
                }
            }

        } while (!valueInput);

        int priceSellStreet = 0;
        Player playerFS = null;
        do {
            System.out.println("Укажите, по какой цене хотите продать поле");
            priceSellStreet = Integer.parseInt(getText());
        } while (priceSellStreet <= 0);
        do {
            valueInput = false;
            System.out.println("Выберите угрока, которому хотите продать улицу");
            String playerInput = getText();
            for (Player playerForSell : players) {
                if (playerForSell.getPlayerName() == playerInput) {
                    playerFS = playerForSell;
                }

            }

            if (playerFS.getMoney() < priceSellStreet) {
                System.err.println("Выберите другого игрока для продажи вашей улицы. У него недостаточно денег!");
            } else {
                valueInput = true;
            }
        } while (!valueInput);

        System.out.println(player.getPlayerName() + "Продает" + chosenStreet + "игроку по имени" + playerFS.getPlayerName() + "за" + priceSellStreet);

        playerService.addMoney(player, priceSellStreet);
        playerService.minMoney(playerFS, priceSellStreet);
        chosenStreet.setPlayer(playerFS);

        System.out.println("Продано!");
    }

    public int getTextValue(String text, int min, int max) {
        Optional<String> result = null;
        String valueString = "";
        int valueInt = 0;
        boolean isNumber = true;

        do {
            Scanner console = new Scanner(System.in);
            //String textFromConsole = console.nextLine();
            result = Optional.ofNullable(console.nextLine());
            if (result.isPresent()) {
                valueString = result.get();
                try {
                    if (valueString.length() > 0) {
                        valueInt = Integer.parseInt(valueString);
                    } else isNumber = false;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка! Слово имеет неправильный формат.");
                    isNumber = false;
                }
                if (valueInt < min || valueInt >= max) {
                    System.out.println("Ошибка! Слово неверно.");
                    isNumber = false;
                }
            } else isNumber = false;
        } while (!isNumber);
        return valueInt;
    }

    public String getText() {
        Scanner console = new Scanner(System.in);
        String textFromConsole = console.nextLine();
        return textFromConsole;
    }

    public String[] getMenuBeforeRound(CircleList<Field> fields, StreetService streetService) {
        ArrayList<String> list = new ArrayList<>();

        for (Field field : fields) {
            if (field.getClass().getSimpleName().equals("StreetField")) {
                StreetField street = (StreetField) field;
                if (!(street.isHotel()) || !(street.isHouse())) {
                    list.add("Купить дом или отель на одном из полей");
                    break;
                }
            }
        }
        if (fields.size() > 0) {
            list.add("Продать улицу");
            list.add("Показать информацию о улице");
        }
        for (Field field : fields) {
            if (field.getClass().getSimpleName().equals("StreetField")) {
                StreetField street = (StreetField) field;
                if (street.isHotel() || streetService.amountOfHouses(street) > 0) {
                    list.add("Продать здания на поле");
                    break;
                }
            }
        }

        list.add("Бросить кости");

        return list.toArray(new String[list.size()]);
    }

    public int showMenuBeforeRound(Player player, String[] arrayOptions) {
        System.out.println("Текущий игрок:" + player.getPlayerName());
        System.out.println("Выберите, что вы хотите сделать: ");
        String textFromConsole = null;
        for (int i = 0; i < arrayOptions.length; i++) {
            textFromConsole += (i + ") " + arrayOptions[i] + "\n");
        }
        return getTextValue(textFromConsole, 0, arrayOptions.length);
    }
    public int showFieldsMenu(Player player, String[] arrayOptions, Field field) {
        System.out.println("Текущий игрок " + player.getPlayerName());
        String textToShow = "[Поле " + field.getNumberOfField() + " - " + field.getName() + "]\n\nВыберите, что вы хотите сделать\n";
        for (int i = 0; i < arrayOptions.length; i++) {
            textToShow += (i + ") " + arrayOptions[i] + "\n");
        }
        return getTextValue(textToShow, 0, arrayOptions.length + 1);
    }

    public void notEnoughMoney(Queue<Player> players, CircleList<Field> fields, StreetService streetService, PlayerService playerService) {
        System.out.println("У вас не хватает денег! Вы банкрот");
        endGame(players, fields, streetService, playerService);
        players.poll();
    }

    public String[] printAvailable(String[] actions) {

        String textToShow = "Выберите, что вы хотите сделать:";

        for (int i = 0; i < actions.length; i++) {
            textToShow += (i + ") " + actions[i] + "\n");
        }

        return actions;

    }


}
