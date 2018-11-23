package com.company.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film")
public class FilmEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_film;

    @Column(name = "film_name")
    private String filmName;

    @Column(name = "film_genre")
    private String filmGenre;

    @Column(name = "film_time")
    private String filmTime;

    @Column(name = "film_rating")
    private String filmRating;

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionEntity> sessionEntities;

    public FilmEntity(){}

    public FilmEntity(String filmName, String filmGenre, String filmTime, String filmRating) {
        this.filmName = filmName;
        this.filmGenre = filmGenre;
        this.filmTime = filmTime;
        this.filmRating = filmRating;
        sessionEntities = new ArrayList<>();
    }

    public void addSession(SessionEntity session){
        session.setFilm(this);
        sessionEntities.add(session);
    }

    public void removeSession(SessionEntity session){
        sessionEntities.remove(session);
    }

    public List<SessionEntity> getSessionEntities() {
        return sessionEntities;
    }

    public void setSessionEntities(List<SessionEntity> sessionEntities) {
        this.sessionEntities = sessionEntities;
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
