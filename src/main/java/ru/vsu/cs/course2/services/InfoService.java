package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.ActionField;
import ru.vsu.cs.course2.model.fields.Field;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.model.price.Price;
import ru.vsu.cs.course2.model.price.RentPrice;
import ru.vsu.cs.course2.util.CircleList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;

public class InfoService {
    public void addFields(CircleList<Field> field) {
        field.add(new Field("Старт", 1));
        field.add(new StreetField("Старая дорога", 2, null, null, new Price(60, 50, new RentPrice(2, 10, 250)), false, false));
        field.add(new ActionField("Казна", 3));
        field.add(new StreetField("Главное шоссе", 4, null, null, new Price(60, 50, new RentPrice(4, 20, 450)), false, false));
        field.add(new ActionField("Налог с дохода", 5));
        field.add(new StreetField("Аквапарк", 6, Color.YELLOW, null, new Price(100, 50, new RentPrice(6, 30, 550)), false, false));
        field.add(new ActionField("Шанс", 7));
        field.add(new StreetField("Городской парк", 8, Color.YELLOW, null, new Price(100, 50, new RentPrice(6, 30, 550)), false, false));
        field.add(new StreetField("Горнолыжный курорт", 9, Color.YELLOW, null, new Price(120, 50, new RentPrice(8, 40, 600)), false, false));
        field.add(new ActionField("Тюрьма", 10));
        field.add(new StreetField("Спальный район", 11, Color.GREEN, null, new Price(140, 100, new RentPrice(10, 50, 750)), false, false));
        field.add(new StreetField("Деловой квартал ", 12, Color.GREEN, null, new Price(140, 100, new RentPrice(10, 50, 750)), false, false));
        field.add(new StreetField("Торговая площадь", 13, Color.GREEN, null, new Price(160, 100, new RentPrice(12, 60, 900)), false, false));
        field.add(new StreetField("Компьютеры", 15, Color.PINK, null, new Price(260, 150, new RentPrice(22, 120, 1150)), false, false));
        field.add(new StreetField("Интернет", 16, Color.PINK, null, new Price(260, 150, new RentPrice(22, 110, 1150)), false, false));
        field.add(new StreetField("Сотовая связь", 17, Color.PINK, null, new Price(280, 150, new RentPrice(24, 120, 1200)), false, false));
        field.add(new ActionField("Шанс", 18));
        field.add(new StreetField("Морские перевозки", 19, Color.BLUE, null, new Price(300, 200, new RentPrice(26, 130, 1275)), false, false));
        field.add(new StreetField("Железная дорога", 20, Color.BLUE, null, new Price(300, 200, new RentPrice(26, 130, 1275)), false, false));
        field.add(new ActionField("Казна", 21));
        field.add(new StreetField("Авиакомпания", 22, Color.BLUE, null, new Price(320, 200, new RentPrice(28, 150, 1400)), false, false));
        field.add(new StreetField("Улица Пушкина", 24, Color.CYAN, null, new Price(180, 100, new RentPrice(14, 70, 950)), false, false));
        field.add(new ActionField("Казна", 25));
        field.add(new StreetField("Проспект мира", 26, Color.CYAN, null, new Price(180, 100, new RentPrice(14, 70, 950)), false, false));
        field.add(new StreetField("Проспект победы", 27, Color.CYAN, null, new Price(200, 100, new RentPrice(16, 80, 1000)), false, false));
        field.add(new ActionField("Бесплатная парковка", 28));
        field.add(new StreetField("Бар", 29, Color.RED, null, new Price(220, 150, new RentPrice(18, 90, 1050)), false, false));
        field.add(new ActionField("Шанс", 30));
        field.add(new StreetField("Ночной клуб", 31, Color.RED, null, new Price(220, 150, new RentPrice(20, 110, 1070)), false, false));
        field.add(new StreetField("Ресторан", 32, Color.RED, null, new Price(240, 150, new RentPrice(22, 120, 1200)), false, false));
        field.add(new ActionField("Шанс", 33));
        field.add(new StreetField("Курортная зона", 34, Color.LIGHT_GRAY, null, new Price(350, 200, new RentPrice(35, 175, 1500)), false, false));
        field.add(new StreetField("Гостиничный комплекс", 35, Color.LIGHT_GRAY, null, new Price(400, 200, new RentPrice(50, 200, 2000)), false, false));
    }

    public void addTreasury(Stack<Treasury> treasury, Queue<Player> players) {
        treasury.add(new Treasury("Банковская ошибка в вашу пользу", 200));
        treasury.add(new Treasury("Выгодная продажа акций", 25));
        treasury.add(new Treasury("Освобождение из тюрьмы", 0));
        treasury.add(new Treasury("Вы получили наследство", 100));
        treasury.add(new Treasury("Возмещение налога", 25));
        treasury.add(new Treasury("Вас арестовали", 0));
        treasury.add(new Treasury("Оплата страховки", -50));
        treasury.add(new Treasury("Вы заняли второе место на конкурсе красоты", 10));
        treasury.add(new Treasury("Выгодня продажа облигаций", 50));
        treasury.add(new Treasury("Оплата лечения", 100));
        treasury.add(new Treasury("Сбор ренты", 100));
        treasury.add(new Treasury("С днем рождения!", 10 * (players.size() - 1)));
        treasury.add(new Treasury("Заплатите штраф или возьмите 'Шанс' ", -10));
        treasury.add(new Treasury("Оплата услуг доктора", -50));
    }

    public void addChance(Stack<Chance> chance, CircleList<Field> field, StreetService streetService) {
        chance.add(new Chance("Отправляйтесь в гостиничный комплекс", streetService.searchField("Гостиничный комплекс", field)));
        chance.add(new Chance("Пройдите на старт", streetService.searchField("Старт", field)));
        chance.add(new Chance("Освобождение из тюрьмы", null));
        chance.add(new Chance("Отправляйтесь в ресторан", streetService.searchField("Ресторан", field)));
        chance.add(new Chance("Отправляйтесь в бар", streetService.searchField("Бар", field)));
        chance.add(new Chance("Отправляйтесь в аквапарк", streetService.searchField("Аквапарк", field)));
        chance.add(new Chance("Отправляйтесь в тюрьму", streetService.searchField("Тюрьма", field)));
    }

    public void addPlayers(Queue<Player> players) {

        ArrayList<String> names = new ArrayList<>();
        names.add("Екатерина");
        names.add("Никита");
        names.add("Даниил");
        names.add("Олеся");
        for (String name : names) {
            Player player = new Player();
            player.setPlayerName(name);
            player.setMoney(1500);
            players.add(player);
        }

    }

}
