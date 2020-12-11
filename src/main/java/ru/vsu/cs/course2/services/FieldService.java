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
     private StreetService streetService=new StreetService();
     private TreasuryService treasuryService=new TreasuryService();

    public void checkField(BaseField field, String answer, Player player, ArrayList<Actions> playerAction, Queue<Chance> chance, CircleList<BaseField> fields, Queue<Treasury> treasury, int numberOfField, Queue<Player> players) {
       ChanceService chanceService = new ChanceService();
        if (field.getClass().getSimpleName().equals("StreetField")) {
            StreetField street = (StreetField) field;
            if (street.getPlayer() == null && answer == "Да") {
                System.out.println("Да, я ее покупаю");
                streetService.buyStreet(player, street);
            } else if (street.getPlayer() != null) {
                streetService.payRent(street, player);
            }
        } else if (field.getClass().getSimpleName().equals("ActionField")) {
            if (field.getName() == "Шанс") {
                chanceService.chance(player, playerAction, chance, fields, answer, numberOfField, players);
            } else if (field.getName() == "Казна") {
                treasuryService.treasury(treasury, player);
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

}
