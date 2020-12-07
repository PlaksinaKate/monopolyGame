package ru.vsu.cs.course2.model.cards;

public class Treasury extends BaseCard {
    private int money;

    public Treasury(String text, int money) {
        super(text);
        this.money = money;
    }
}

