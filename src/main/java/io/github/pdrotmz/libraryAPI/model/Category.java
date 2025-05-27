package io.github.pdrotmz.libraryAPI.model;

import lombok.Getter;

import java.util.Arrays;

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

    public static Category fromLabel(String label) {
        return Arrays.stream(Category.values())
                .filter(c -> c.getCategory().equalsIgnoreCase(label))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Categoria inválida: " + label));
    }

}


