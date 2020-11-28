package ru.vsu.cs.course2.model.fields;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.price.Price;

import java.awt.*;

public class StreetField extends Field {
    private Color color;
    private Player player;
    private Price price;
    private boolean hotel, house;

    public StreetField(String name, int numberOfField, Color color, Player player, Price price, boolean hotel, boolean house) {
        super(name, numberOfField);
        this.color = color;
        this.player = player;
        this.price = price;
        this.hotel = hotel;
        this.house = house;
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

