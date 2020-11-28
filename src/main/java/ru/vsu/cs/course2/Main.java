package ru.vsu.cs.course2;

import ru.vsu.cs.course2.services.MonopolyService;

public class Main {

    public static void main(String[] args) {
        MonopolyService m = new MonopolyService();
        m.setUpMonopoly();
        m.playMonopoly();
    }
}
