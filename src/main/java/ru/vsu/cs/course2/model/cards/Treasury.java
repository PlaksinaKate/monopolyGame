package ru.vsu.cs.course2.model.cards;

public class Treasury extends Cards{
    private int moneyPlusOrMinus;

    public Treasury(String text, int moneyPlusOrMinus) {
        super(text);
        this.moneyPlusOrMinus = moneyPlusOrMinus;
    }
}

