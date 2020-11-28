package ru.vsu.cs.course2.model.cards;

import ru.vsu.cs.course2.model.fields.Field;

public class Chance extends Cards{
    private Field numberOfFieldWherePlayerNeedToGo;

    public Chance(String text, Field numberOfFieldWherePlayerNeedToGo) {
        super(text);
        this.numberOfFieldWherePlayerNeedToGo = numberOfFieldWherePlayerNeedToGo;
    }
}
