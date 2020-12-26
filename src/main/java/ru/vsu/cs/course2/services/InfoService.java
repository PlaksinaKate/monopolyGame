package ru.vsu.cs.course2.services;

import ru.vsu.cs.course2.model.Player;
import ru.vsu.cs.course2.model.cards.Chance;
import ru.vsu.cs.course2.model.cards.Treasury;
import ru.vsu.cs.course2.model.fields.ActionField;
import ru.vsu.cs.course2.model.fields.BaseField;
import ru.vsu.cs.course2.model.fields.StreetField;
import ru.vsu.cs.course2.model.price.Price;
import ru.vsu.cs.course2.model.price.RentPrice;
import ru.vsu.cs.course2.util.CircleList;

import java.util.ArrayList;
import java.util.Queue;


public class InfoService {

    public void addFields(CircleList<BaseField> field) {
        field.add(new BaseField("Старт", 1));
        field.add(new StreetField("Старая дорога", 2, "\u001b[37m", null, new Price(60, 50, new RentPrice(2, 10, 250)), false, false));
        field.add(new ActionField("Казна", 3));
        field.add(new StreetField("Главное шоссе", 4, "\u001b[37m", null, new Price(60, 50, new RentPrice(4, 20, 450)), false, false));
        field.add(new BaseField("Налог", 5));
        field.add(new StreetField("Аквапарк", 6, "\u001b[33m", null, new Price(100, 50, new RentPrice(6, 30, 550)), false, false));
        field.add(new ActionField("Шанс", 7));
        field.add(new StreetField("Городской парк", 8, "\u001b[33m", null, new Price(100, 50, new RentPrice(6, 30, 550)), false, false));
        field.add(new StreetField("Горнолыжный курорт", 9, "\u001b[33m", null, new Price(120, 50, new RentPrice(8, 40, 600)), false, false));
        field.add(new BaseField("Тюрьма", 10));
        field.add(new StreetField("Спальный район", 11, "\u001b[32m", null, new Price(140, 100, new RentPrice(10, 50, 750)), false, false));
        field.add(new StreetField("Деловой квартал ", 12, "\u001b[32m", null, new Price(140, 100, new RentPrice(10, 50, 750)), false, false));
        field.add(new StreetField("Торговая площадь", 13, "\u001b[32m", null, new Price(160, 100, new RentPrice(12, 60, 900)), false, false));
        field.add(new StreetField("Компьютеры", 14, "\u001b[35m", null, new Price(260, 150, new RentPrice(22, 120, 1150)), false, false));
        field.add(new StreetField("Интернет", 15, "\u001b[35m", null, new Price(260, 150, new RentPrice(22, 110, 1150)), false, false));
        field.add(new StreetField("Сотовая связь", 16, "\u001b[35m", null, new Price(280, 150, new RentPrice(24, 120, 1200)), false, false));
        field.add(new ActionField("Шанс", 17));
        field.add(new StreetField("Морские перевозки", 18, "\u001b[34m", null, new Price(300, 200, new RentPrice(26, 130, 1275)), false, false));
        field.add(new StreetField("Железная дорога", 19, "\u001b[34m", null, new Price(300, 200, new RentPrice(26, 130, 1275)), false, false));
        field.add(new ActionField("Казна", 20));
        field.add(new StreetField("Авиакомпания", 21, "\u001b[34m", null, new Price(320, 200, new RentPrice(28, 150, 1400)), false, false));
        field.add(new StreetField("Улица Пушкина", 22, "\u001b[36m", null, new Price(180, 100, new RentPrice(14, 70, 950)), false, false));
        field.add(new ActionField("Казна", 23));
        field.add(new StreetField("Проспект мира", 24, "\u001b[36m", null, new Price(180, 100, new RentPrice(14, 70, 950)), false, false));
        field.add(new StreetField("Проспект победы", 25, "\u001b[36m", null, new Price(200, 100, new RentPrice(16, 80, 1000)), false, false));
        field.add(new ActionField("Бесплатная парковка", 26));
        field.add(new StreetField("Бар", 27, "\u001b[31m", null, new Price(220, 150, new RentPrice(18, 90, 1050)), false, false));
        field.add(new ActionField("Шанс", 28));
        field.add(new StreetField("Ночной клуб", 29, "\u001b[31m", null, new Price(220, 150, new RentPrice(20, 110, 1070)), false, false));
        field.add(new StreetField("Ресторан", 30, "\u001b[31m", null, new Price(240, 150, new RentPrice(22, 120, 1200)), false, false));
        field.add(new ActionField("Шанс", 31));
        field.add(new StreetField("Курортная зона", 32, "\u001b[37m", null, new Price(350, 200, new RentPrice(35, 175, 1500)), false, false));
        field.add(new StreetField("Гостиничный комплекс", 33, "\u001b[37m", null, new Price(400, 200, new RentPrice(50, 200, 2000)), false, false));
    }

    public void addTreasury(Queue<Treasury> treasury, Queue<Player> players) {
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

    public void addChance(Queue<Chance> chance) {
        chance.add(new Chance("Отправляйтесь в гостиничный комплекс", 33));
        chance.add(new Chance("Пройдите на старт", 1));
        chance.add(new Chance("Освобождение из тюрьмы", 10));
        chance.add(new Chance("Отправляйтесь в ресторан", 30));
        chance.add(new Chance("Отправляйтесь в бар", 27));
        chance.add(new Chance("Отправляйтесь в аквапарк", 6));
        chance.add(new Chance("Отправляйтесь в тюрьму", 10));
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
            player.setPrisonFree(false);
            players.add(player);
        }

    }

}
