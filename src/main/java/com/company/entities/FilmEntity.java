package com.company.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "film")
public class FilmEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFilm;

    private String filmName;
    private String filmGenre;
    private String filmTime;
    private String filmRating;

    public FilmEntity(){}

    @OneToMany(mappedBy = "film", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionEntity> sessionEntities;

    @Id
    @Column(name = "id_film", nullable = false)
    public int getIdFilm() {
        return idFilm;
    }

    public void setIdFilm(int idFilm) {
        this.idFilm = idFilm;
    }

    @Basic
    @Column(name = "film_name", nullable = false, length = 45)
    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    @Basic
    @Column(name = "film_genre", nullable = false, length = 45)
    public String getFilmGenre() {
        return filmGenre;
    }

    public void setFilmGenre(String filmGenre) {
        this.filmGenre = filmGenre;
    }

    @Basic
    @Column(name = "film_time", nullable = false, length = 45)
    public String getFilmTime() {
        return filmTime;
    }

    public void setFilmTime(String filmTime) {
        this.filmTime = filmTime;
    }

    @Basic
    @Column(name = "film_rating", nullable = true, length = 45)
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

        if (idFilm != that.idFilm) return false;
        if (filmName != null ? !filmName.equals(that.filmName) : that.filmName != null) return false;
        if (filmGenre != null ? !filmGenre.equals(that.filmGenre) : that.filmGenre != null) return false;
        if (filmTime != null ? !filmTime.equals(that.filmTime) : that.filmTime != null) return false;
        if (filmRating != null ? !filmRating.equals(that.filmRating) : that.filmRating != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idFilm;
        result = 31 * result + (filmName != null ? filmName.hashCode() : 0);
        result = 31 * result + (filmGenre != null ? filmGenre.hashCode() : 0);
        result = 31 * result + (filmTime != null ? filmTime.hashCode() : 0);
        result = 31 * result + (filmRating != null ? filmRating.hashCode() : 0);
        return result;
    }
}
