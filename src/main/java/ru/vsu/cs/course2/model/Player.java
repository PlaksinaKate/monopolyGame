package ru.vsu.cs.course2.model;

public class Player {
    private String playerName;
    private int money;
    private boolean prisonFree;

    public Player(String playerName, int money, boolean prisonFree) {
        this.playerName = playerName;
        this.money = money;
        this.prisonFree = prisonFree;
    }
//    public Player() {
//        this(null, 0, true);
//    }

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

    public boolean isPrisonFree() {
        return prisonFree;
    }

    public void setPrisonFree(boolean prisonFree) {
        this.prisonFree = prisonFree;
    }
}

