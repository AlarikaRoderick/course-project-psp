package com.company.dao.cinema;

import com.company.dbHandler.DbHandler;
import com.company.entities.CinemaEntity;
import com.company.entities.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CinemaDAOImpl implements CinemaDAO {
    //private Connection connection = DbHandler.getInstance().getConnection();

    public CinemaEntity findCinemaById(int id) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String findCinema = "SELECT * FROM cinema.cinema WHERE id_cinema=" + id;
        ResultSet resultSet = null;
        CinemaEntity cinema = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(findCinema);
            resultSet = preparedStatement.executeQuery(findCinema);
            while (resultSet.next()){
                cinema = getCinemaFromDB(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return cinema;
    }

    private CinemaEntity getCinemaFromDB(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt(1);
        String cinemaName = resultSet.getString(2);
        String cinemaAddress = resultSet.getString(3);
        String cinemaUnderground = resultSet.getString(4);
        String cinemaPhone = resultSet.getString(5);
        CinemaEntity cinema = new CinemaEntity(cinemaName, cinemaAddress, cinemaUnderground, cinemaPhone);
        cinema.setId_cinema(id);
        return cinema;
    }

    public void saveCinema(CinemaEntity cinema) {
        String saveCinema = "INSERT INTO cinema (cinema_name, cinema_address, cinema_underground, cinema_phone)"
                + "VALUES(?,?,?,?)";
        try{
            setCinemaInfo(cinema, saveCinema);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setCinemaInfo(CinemaEntity cinema, String saveCinema) throws SQLException {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(saveCinema);
        preparedStatement.setString(1, cinema.getCinemaName());
        preparedStatement.setString(2, cinema.getCinemaAddress());
        preparedStatement.setString(3, cinema.getCinemaUnderground());
        preparedStatement.setString(4, cinema.getCinemaPhone());
        preparedStatement.executeUpdate();
    }

    public void updateCinema(CinemaEntity cinema) {
        int id = cinema.getId_cinema();
        String saveCinema = "UPDATE cinema SET cinema_name=?, cinema_address=?, cinema_underground=?,"
                + "cinema_phone=? WHERE id_cinema=" + id;
        try{
            setCinemaInfo(cinema, saveCinema);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteCinema(CinemaEntity cinema) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        int id = cinema.getId_cinema();
        String delete = "DELETE FROM cinema WHERE id_cinema=" + id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<CinemaEntity> findAll() {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        ResultSet resultSet = null;
        List<CinemaEntity> cinemas = new ArrayList<>();
        String selectAllClients = "SELECT * FROM cinema";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllClients);
            resultSet = preparedStatement.executeQuery(selectAllClients);

            while (resultSet.next()){
                CinemaEntity cinema = getCinemaFromDB(resultSet);
                cinemas.add(cinema);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return cinemas;
    }
}
