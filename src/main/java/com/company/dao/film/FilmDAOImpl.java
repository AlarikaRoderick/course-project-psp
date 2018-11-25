package com.company.dao.film;

import com.company.dbHandler.DbHandler;
import com.company.entities.FilmEntity;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FilmDAOImpl implements FilmDAO {
    //private Connection connection = DbHandler.getInstance().getConnection();

    @Override
    public FilmEntity findFilmById(int id) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String findFilm = "SELECT * FROM film WHERE id_film=" + id;
        ResultSet resultSet = null;
        FilmEntity film = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(findFilm);
            resultSet = preparedStatement.executeQuery(findFilm);
            while (resultSet.next()){
                film = getFilmFromDB(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return film;
    }

    private FilmEntity getFilmFromDB(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt(1);
        String filmName = resultSet.getString(2);
        String filmGenre = resultSet.getString(3);
        String filmTime = resultSet.getString(4);
        String filmRating = resultSet.getString(5);
        FilmEntity film = new FilmEntity(filmName, filmGenre, filmTime, filmRating);
        film.setId_film(id);
        return film;
    }

    @Override
    public void saveFilm(FilmEntity film) {
        String saveFilm = "INSERT INTO film (film_name, film_genre, film_time, film_rating)"
                + "VALUES(?,?,?,?)";
        try{
            setFilmInfo(film, saveFilm);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setFilmInfo(FilmEntity film, String saveFilm) throws SQLException{
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(saveFilm);
        preparedStatement.setString(1, film.getFilmName());
        preparedStatement.setString(2, film.getFilmGenre());
        preparedStatement.setString(3, film.getFilmTime());
        preparedStatement.setString(4, film.getFilmRating());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateFilm(FilmEntity film) {
        int id = film.getId_film();
        String updateFilm = "UPDATE film SET film_name=?, film_genre=?, film_time=?, film_rating=?,"
                + "WHERE id_film=" + id;
        try{
            setFilmInfo(film, updateFilm);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFilm(FilmEntity film) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        int id = film.getId_film();
        String delete = "DELETE FROM film WHERE id_film=" + id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<FilmEntity> findAll() {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        ResultSet resultSet = null;
        List<FilmEntity> films = new ArrayList<>();
        String selectAllFilms = "SELECT * FROM film";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllFilms);
            resultSet = preparedStatement.executeQuery(selectAllFilms);

            while (resultSet.next()){
                FilmEntity film = getFilmFromDB(resultSet);
                films.add(film);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return films;
    }
}
