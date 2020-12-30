package ru.vsu.cs.course2.model.fields;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.price.Price;


public class StreetField extends BaseField {
    private String color;
    private Player player;
    private Price price;
    private boolean hotel;
    private boolean house;

    public StreetField(String name, int numberOfField, String color, Player player, Price price, boolean hotel, boolean house) {
        super(name, numberOfField);
        this.color = color;
        this.player = player;
        this.price = price;
        this.hotel = hotel;
        this.house = house;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public boolean isHotel() {
        return hotel;
    }

    public void setHotel(boolean hotel) {
        this.hotel = hotel;
    }

    public boolean isHouse() {
        return house;
    }

    public void setHouse(boolean house) {
        this.house = house;
    }

    public Player getPlayer() {
        return player;
    }

    public Price getPrice() {
        return price;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

}

