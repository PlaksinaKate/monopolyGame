package ru.vsu.cs.course2.model.cards;

import ru.vsu.cs.course2.model.fields.BaseField;

public class Chance extends BaseCard {
    private int numberOfField;

    public Chance(String text, int field) {
        super(text);
        this.numberOfField = numberOfField;
    }

    public int getNumberOfField() {
        return numberOfField;
    }
}
