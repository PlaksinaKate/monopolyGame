package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Treasury;

import java.util.Queue;

public class TreasuryService {
    public void treasury(Queue<Treasury> treasuries, Player player) {
        System.out.println(treasuries.peek().getText());
        if (treasuries.peek().getMoney() < 0) {
            System.out.println("Вы должны заплатить " + treasuries.peek().getMoney());
        } else {
            System.out.println("Получите " + treasuries.peek().getMoney());
        }
        player.setMoney(player.getMoney() + treasuries.peek().getMoney());
        treasuries.add(treasuries.poll());
    }
}
