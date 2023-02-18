package com.sakhbord.bord.enam;

public enum CategoryTypeEnum {


    SALE("Продам"), BUY("Куплю"),  RENT("Сдам"), TAKE("Сниму"),
    CHANGE("Меняю"), INTERCITY("Межгород"), SEARCH("Ищу"), OFFER("Предлагаю"),
    LOST("Утеряно"), FOUND("Найдено"), WOMAN("Женщины"), MAN("Мужчины");

    private final String name;

    CategoryTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
