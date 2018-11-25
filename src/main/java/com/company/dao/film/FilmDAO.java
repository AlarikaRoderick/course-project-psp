package com.company.dao.film;

import com.company.entities.FilmEntity;
import com.company.entities.SessionEntity;

import java.util.List;

public interface FilmDAO {
    FilmEntity findFilmById(int id);
    void saveFilm(FilmEntity film);
    void updateFilm(FilmEntity film);
    void deleteFilm(FilmEntity film);
    List<FilmEntity> findAll();
}
