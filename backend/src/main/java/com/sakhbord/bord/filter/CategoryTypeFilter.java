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



    public static long getNumberCategory(int category) {

        long cat;

        // В html и в базе данных, коды категорий разные

        switch (category) {
            case 110 -> cat = 11L;
            case 121 -> cat = 3L;
            case 132 -> cat = 4L;
            case 190 -> cat = 10L;
            default -> cat = 0L;
        }

        return cat;

    }


    public static boolean isCityNumber(int city) {

        boolean isCityNumber;

        // В html и в базе данных, коды категорий разные

        isCityNumber =
                   city == 1
                || city == 2
                || city == 3
                || city == 4
                || city == 5
                || city == 6
                || city == 7
                || city == 8
                || city == 9
                || city == 10
                || city == 11
                || city == 12
                || city == 13
                || city == 14
                || city == 15
                || city == 16
                || city == 17
                || city == 18
                || city == 19
                || city == 20
                || city == 21
                || city == 22
                || city == 23
                || city == 24
                || city == 25
                || city == 26;

        return isCityNumber;

    }



    public static boolean selectDepartamentCode(int value) {

        boolean selectDepartamentCode;

        selectDepartamentCode = switch (value) {
            case 101, 102, 103, 104, 105, 106, 111, 112, 141, 142, 151, 152, 161, 162, 171, 172, 181, 182 -> true;
            default -> false;
        };


        return selectDepartamentCode;

    }



    public static long selectCategoryCode(int value) {


        // Это мы получаем категорию
        // В html и в базе данных, коды(id) категорий разные

        long cat;

        switch (value) {
            case 101, 102, 103, 104, 105, 106 -> cat = 1L; // +
            case 111, 112 -> cat = 2L; // +
            case 141, 142 -> cat = 5L; // +
            case 151, 152 -> cat = 6L; // +
            case 161, 162 -> cat = 7L; // +
            case 171, 172 -> cat = 8L; // +
            case 181, 182 -> cat = 9L; // +
            default -> cat = 0;
        }
        return cat;

    }


    public static long selectTypeCategoryCode(int value) {


        // Это мы получаем тип категории
        // В html и в базе данных, коды(id) типы категорий разные

        long cat;

        switch (value) {
            case 101, 111, 141 -> cat = 1L; // +
            case 102, 112, 142 -> cat = 2L; // +
            case 103 -> cat = 5L; // +
            case 104 -> cat = 6L; // +
            case 105 -> cat = 3L; // +
            case 106 -> cat = 4L; // +
            case 151, 161 -> cat = 7L; // +
            case 152, 162 -> cat = 8L; // +
            case 171 -> cat = 9L; // +
            case 172 -> cat = 10L; // +
            case 181 -> cat = 11L; // +
            case 182 -> cat = 12L; // +
            default -> cat = 0;
        }
        return cat;

    }

}
