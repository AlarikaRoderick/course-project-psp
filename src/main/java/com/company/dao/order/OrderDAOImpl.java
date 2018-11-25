package com.company.dao.order;

import com.company.dbHandler.DbHandler;
import com.company.entities.Order;
import com.company.entities.SessionEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public List<Order> createOrder(int idUser) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        String findOrder = "SELECT cinema.ticket.id_ticket, cinema.session.session_date, cinema.session.session_time_hour, cinema.session.session_time_minute, cinema.film.film_name" +
                " from ticket, session, film" +
                " where ticket.id_session_ticket = session.id_session and session.id_film_session = film.id_film" +
                " and ticket.id_user_ticket=" + idUser;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(findOrder);
            resultSet = preparedStatement.executeQuery(findOrder);
            while (resultSet.next()){
                Order order = getOrderFromDB(resultSet);
                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    private Order getOrderFromDB(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt("id_ticket");
        Date sessionDate = resultSet.getDate("session_date");
        int sessionHour = resultSet.getInt("session_time_hour");
        int sessionMinute = resultSet.getInt("session_time_minute");
        String filmName = resultSet.getString("film_name");
        Order order = new Order(id, sessionDate, sessionHour, sessionMinute, filmName);
        return order;
    }
}
