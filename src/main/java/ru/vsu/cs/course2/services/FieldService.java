package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.util.CircleList;

public class FieldService {
    StreetService streetService;

    public FieldService() {
        streetService = new StreetService();
    }

    public void checkField(BaseField field, String answer, Player player) {
        if (field.getClass().getSimpleName().equals("StreetField")) {
            StreetField street = (StreetField) field;
            if (street.getPlayer() == null && answer == "Да") {
                streetService.buyStreet(player, street);
            } else if (street.getPlayer() != null) {
                streetService.payRent(street, player);
            }
        }else if(field.getClass().getSimpleName().equals("ActionField")){
            if(field.getName()=="Шанс"){

            }else if(field.getName()=="Казна"){

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
