package ru.geekbrains.miniMarket.base.enums;

import lombok.Getter;

public enum CategoryType {
    FOOD(1, "Food"),
    ELECTRONICS(2, "Electronics");

    @Getter
    private final Integer id;
    @Getter
    private final String title;


    CategoryType(Integer id, String title) {
        this.id = id;
        this.title= title;
    }
}
