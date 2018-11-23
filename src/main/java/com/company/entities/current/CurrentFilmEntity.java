package com.company.entities.current;

import com.company.entities.FilmEntity;

public class CurrentFilmEntity {
    private static FilmEntity film;

    public static FilmEntity getFilm() {
        return film;
    }

    public static void setFilm(FilmEntity film) {
        CurrentFilmEntity.film = film;
    }
}
