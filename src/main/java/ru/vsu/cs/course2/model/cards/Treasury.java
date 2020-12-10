package ru.vsu.cs.course2.model.cards;

public class Treasury extends BaseCard {
    private int money;

    public Treasury(String text, int money) {
        super(text);
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }
}

