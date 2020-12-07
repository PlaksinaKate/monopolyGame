package ru.vsu.cs.course2.model.cards;

public class BaseCard {

    private String text;

    public BaseCard(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
