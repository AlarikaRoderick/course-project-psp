package com.company.dao.hall;

import com.company.dbHandler.DbHandler;
import com.company.entities.HallEntity;
import com.company.entities.UserEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HallDAOImpl implements HallDAO {
    //private Connection connection = DbHandler.getInstance().getConnection();

    @Override
    public HallEntity findHallById(int id) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String findHall = "SELECT * FROM hall WHERE id_hall=" + id;
        ResultSet resultSet = null;
        HallEntity hall = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(findHall);
            resultSet = preparedStatement.executeQuery(findHall);
            while (resultSet.next()){
                hall = getHallFromDB(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return hall;
    }

    private HallEntity getHallFromDB(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt(1);
        String hallName = resultSet.getString(2);
        int hallRows = resultSet.getInt(3);
        int hallPlaces = resultSet.getInt(4);
        int idCinemaHall = resultSet.getInt(5);
        HallEntity hall = new HallEntity(hallName, hallRows, hallPlaces, idCinemaHall);
        hall.setId_hall(id);
        return hall;
    }

    @Override
    public void saveHall(HallEntity hall) {
        String saveHall = "INSERT INTO hall (hall_name, hall_rows, hall_places, id_cinema_hall)"
                + "VALUES(?,?,?,?)";
        try{
            setHallInfo(hall, saveHall);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setHallInfo(HallEntity hall, String saveHall) throws SQLException{
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(saveHall);
        preparedStatement.setString(1, hall.getHallName());
        preparedStatement.setInt(2, hall.getHallRows());
        preparedStatement.setInt(3, hall.getHallPlaces());
        preparedStatement.setInt(4, hall.getIdCinemaHall());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateHall(HallEntity hall) {
        int id = hall.getId_hall();
        String updateHall = "UPDATE hall SET hall_name=?, hall_rows=?, hall_places=?, id_cinema_hall=? WHERE id_hall=" + id;
        try{
            setHallInfo(hall, updateHall);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteHall(HallEntity hall) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        int id = hall.getId_hall();
        String delete = "DELETE FROM hall WHERE id_hall=" + id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<HallEntity> findAll() {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        ResultSet resultSet = null;
        List<HallEntity> halls = new ArrayList<>();
        String selectAllHalls = "SELECT * FROM hall";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllHalls);
            resultSet = preparedStatement.executeQuery(selectAllHalls);

            while (resultSet.next()){
                HallEntity hall = getHallFromDB(resultSet);
                halls.add(hall);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return halls;
    }
}
