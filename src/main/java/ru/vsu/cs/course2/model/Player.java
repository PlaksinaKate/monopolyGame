package ru.vsu.cs.course2.model;

public class Player {
    private String playerName;
    private int money;

    public Player(String playerName, int money) {
        this.playerName = playerName;
        this.money = money;
    }

    public Player() {
        this(null, 0);
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getMoney() {
        return money;
    }

}

