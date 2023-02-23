package com.sakhbord.bord.filter;

public class CategoryTypeFilter {

    private CategoryTypeFilter() {
    }


    public static String selectCity(int cityCode) {

        String selectCity;

        switch (cityCode) {
            case 1 -> selectCity = "Александровск-Сахалинский";
            case 2 -> selectCity = "Анива";
            case 3 -> selectCity = "Быков";
            case 4 -> selectCity = "Вахрушев";
            case 5 -> selectCity = "Горнозаводск";
            case 6 -> selectCity = "Долинск";
            case 7 -> selectCity = "Ильинский";
            case 8 -> selectCity = "Корсаков";
            case 9 -> selectCity = "Красногорск";
            case 10 -> selectCity = "Курильск";
            case 11 -> selectCity = "Макаров";
            case 12 -> selectCity = "Невельск";
            case 13 -> selectCity = "Ноглики";
            case 14 -> selectCity = "Онор";
            case 15 -> selectCity = "Оха";
            case 16 -> selectCity = "Поронайск";
            case 17 -> selectCity = "Северо-Курильск";
            case 18 -> selectCity = "Смирных";
            case 19 -> selectCity = "Томари";
            case 20 -> selectCity = "Тымовское";
            case 21 -> selectCity = "Углегорск";
            case 22 -> selectCity = "Холмск";
            case 23 -> selectCity = "Чехов";
            case 24 -> selectCity = "Шахтерск";
            case 25 -> selectCity = "Южно-Курильск";
            case 26 -> selectCity = "Южно-Сахалинск";
            default -> selectCity = "";
        }
        return selectCity;

    }

    public static String selectDepartament(int cityDepCode) {

        String selectDepartament;

        switch (cityDepCode) {
            case 101, 102, 103, 104, 105, 106 -> selectDepartament = "Недвижимость"; // +
            case 110 -> selectDepartament = "Авто-Мото"; // -
            case 111, 112 -> selectDepartament = "Компьютеры"; // +
            case 121 -> selectDepartament = "Мобильная связь"; // -
            case 132 -> selectDepartament = "Животные"; // -
            case 141, 142 -> selectDepartament = "Прочее"; // +
            case 151, 152 -> selectDepartament = "Услуги"; // +
            case 161, 162 -> selectDepartament = "Работа"; // +
            case 171, 172 -> selectDepartament = "Розыск"; // +
            case 181, 182 -> selectDepartament = "Знакомства"; // +
            case 190 -> selectDepartament = "Разное"; // -
            default -> selectDepartament = "";
        }


        return selectDepartament;

    }



    public static String selectType(int depTypeCode) {

        String selectType;

        switch (depTypeCode) {
            case 101, 111, 141 -> selectType = "Продам";
            case 102, 112, 142 -> selectType = "Куплю";
            case 105 -> selectType = "Сдам";
            case 106 -> selectType = "Сниму";
            case 103 -> selectType = "Меняю";
            case 104 -> selectType = "Межгород";
            case 151, 161 -> selectType = "Ищу";
            case 152, 162 -> selectType = "Предлагаю";
            case 171 -> selectType = "Утеряно";
            case 172 -> selectType = "Найдено";
            case 181 -> selectType = "Женщины";
            case 182 -> selectType = "Мужчины";
            default -> selectType = "";
        }
        return selectType;

    }


    public static String selectNoType(int noType) {

        String selectNoType;

        selectNoType = switch (noType) {

            case 110, 121, 132, 190 -> "noType";

            default -> "type";
        };


        return selectNoType;

    }


}
