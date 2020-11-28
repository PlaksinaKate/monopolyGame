package ru.vsu.cs.course2.model.price;

public class Price {
    private int price;
    private int priceForNewBuildings;
    private RentPrice rentPrice;

    public Price(int price, int priceForNewBuildings, RentPrice rentPrice) {
        this.price = price;
        this.priceForNewBuildings = priceForNewBuildings;
        this.rentPrice = rentPrice;
    }

    public int getPriceForNewBuildings() {
        return priceForNewBuildings;
    }

    public int getPrice() {
        return price;
    }

    public RentPrice getRentPrice() {
        return rentPrice;
    }
}
