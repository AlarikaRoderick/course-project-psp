package com.company.entities;

import java.io.Serializable;

public class FilmEntity implements Serializable {

    private int id_film;
    private String filmName;
    private String filmGenre;
    private String filmTime;
    private String filmRating;

    public FilmEntity(){}

    public FilmEntity(String filmName, String filmGenre, String filmTime, String filmRating) {
        this.filmName = filmName;
        this.filmGenre = filmGenre;
        this.filmTime = filmTime;
        this.filmRating = filmRating;
    }

    public int getId_film() {
        return id_film;
    }

    public void setId_film(int id_film) {
        this.id_film = id_film;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    public String getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(String filmTime) {
        this.filmTime = filmTime;
    }

    public String getFilmRating() {
        return filmRating;
    }

    public void setFilmRating(String filmRating) {
        this.filmRating = filmRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FilmEntity that = (FilmEntity) o;

        if (id_film != that.id_film) return false;
        if (filmName != null ? !filmName.equals(that.filmName) : that.filmName != null) return false;
        if (filmGenre != null ? !filmGenre.equals(that.filmGenre) : that.filmGenre != null) return false;
        if (filmTime != null ? !filmTime.equals(that.filmTime) : that.filmTime != null) return false;
        if (filmRating != null ? !filmRating.equals(that.filmRating) : that.filmRating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_film;
        result = 31 * result + (filmName != null ? filmName.hashCode() : 0);
        result = 31 * result + (filmGenre != null ? filmGenre.hashCode() : 0);
        result = 31 * result + (filmTime != null ? filmTime.hashCode() : 0);
        result = 31 * result + (filmRating != null ? filmRating.hashCode() : 0);
        return result;
    }
}
