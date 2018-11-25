package com.company.dao.film;

import com.company.entities.FilmEntity;
import com.company.entities.SessionEntity;
import net.bytebuddy.matcher.FilterableList;

import java.util.List;

public class FilmService {
    private FilmDAOImpl filmDAO = new FilmDAOImpl();

    public FilmService(){}

    public FilmEntity findFilm(int id){
        return new FilmDAOImpl().findFilmById(id);
    }

    public void saveFilm(FilmEntity film){
        new FilmDAOImpl().saveFilm(film);
    }

    public void updateFilm(FilmEntity film){
        new FilmDAOImpl().updateFilm(film);
    }

    public void deleteFilm(FilmEntity film){
        new FilmDAOImpl().deleteFilm(film);
    }

    public List<FilmEntity> findAllFilms(){
        return new FilmDAOImpl().findAll();
    }
}
