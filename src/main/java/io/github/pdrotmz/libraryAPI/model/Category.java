package io.github.pdrotmz.libraryAPI.model;

import lombok.Getter;

@Getter
public enum Category {
    MANGA("mangá"),
    COMICS("comics"),
    HISTORIA("história"),
    ACAO("ação"),
    TERROR("terror"),
    AVENTURA("aventura"),
    ROMANCE("romance");

    private final String category;

    Category(String category) {
        this.category = category;
    }

}


