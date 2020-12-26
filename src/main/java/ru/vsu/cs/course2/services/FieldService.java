package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.actions.Actions;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

import java.util.ArrayList;
import java.util.Queue;

public class FieldService {
    private StreetService streetService = new StreetService();
    private TreasuryService treasuryService = new TreasuryService();
    private ActionService actionService = new ActionService();


    public void checkField(BaseField field, int answer, Player player, ArrayList<Actions> playerAction, Queue<Chance> chance, CircleList<BaseField> fields, Queue<Treasury> treasury, int numberOfField, Queue<Player> players) {
        ChanceService chanceService = new ChanceService();
        actionService.addAction(players.peek(), playerAction, searchField(numberOfField, fields));
        if (field.getClass().getSimpleName().equals("StreetField")) {
            StreetField street = (StreetField) field;
            streetService.street(street, answer, player);
        } else if (field.getClass().getSimpleName().equals("ActionField")) {
            if (field.getName() == "Шанс") {
                chanceService.chance(player, playerAction, chance, fields, answer, numberOfField, players);
            } else if (field.getName() == "Казна") {
                treasuryService.treasury(treasury, player);
            }
        } else if (field.getClass().getSimpleName().equals("BaseField")) {
            if (field.getNumberOfField() == 5) {
                System.out.println("Оплатите налог");
                tax(player);
            } else if (field.getNumberOfField() == 10) {
                player.setPrisonFree(false);
                prison(answer, player);
            }
        }
    }

    public BaseField searchField(int numberOfField, CircleList<BaseField> fields) {
        for (BaseField baseField : fields) {
            if (baseField.getNumberOfField() == numberOfField) {
                return baseField;
            }
        }
        return null;
    }

    public void prison(int answer, Player player) {
        if (player.isPrisonFree()) {
            System.out.println("Вы освобождены из тюрьмы, потому что у вас есть карта.");
        } else {
            System.out.println("Хотите заплатить 300, чтобы выйти из тюрьмы?");
            if (answer == 1) {
                System.out.println("Да");
                player.setMoney(player.getMoney() - 300);
                player.setPrisonFree(true);
            } else if (answer == 0) {
                System.out.println("Нет");
                System.out.println("Вам нужно пропустить 3 хода");
            }
        }
    }

    public void tax(Player player) {
        player.setMoney((int) (player.getMoney() * 0.2));
    }
}
