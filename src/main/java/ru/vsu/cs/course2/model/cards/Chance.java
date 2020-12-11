package ru.vsu.cs.course2.model.cards;

public class Chance extends BaseCard {
    private int numberOfField;

    public Chance(String text, int numberOfField) {
        super(text);
        this.numberOfField = numberOfField;
    }

    public int getNumberOfField() {
        return numberOfField;
    }
}
