package com.company.dao.film;

import com.company.entities.FilmEntity;
import com.company.entities.SessionEntity;

import java.util.List;

public class FilmService {
    private FilmDAOImpl filmDAO = new FilmDAOImpl();

    public FilmService(){}

    public FilmEntity findFilm(int id){
        return filmDAO.findFilmById(id);
    }

    public void saveFilm(FilmEntity film){
        filmDAO.saveFilm(film);
    }

    public void updateFilm(FilmEntity film){
        filmDAO.updateFilm(film);
    }

    public void deleteFilm(FilmEntity film){
        filmDAO.deleteFilm(film);
    }

    public SessionEntity findSessionById(int id){
        return filmDAO.findSessionById(id);
    }

    public List<FilmEntity> findAllFilms(){
        return filmDAO.findAll();
    }
}
