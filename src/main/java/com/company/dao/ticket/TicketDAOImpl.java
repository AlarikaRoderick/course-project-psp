package com.company.dao.ticket;

import com.company.dbHandler.DbHandler;
import com.company.entities.TicketEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    //private Connection connection = DbHandler.getInstance().getConnection();

    @Override
    public TicketEntity findTicketById(int id) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String findTicket = "SELECT * FROM ticket WHERE id_ticket=" + id;
        ResultSet resultSet;
        TicketEntity ticket = null;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(findTicket);
            resultSet = preparedStatement.executeQuery(findTicket);
            while (resultSet.next()){
                ticket = getTicketFromDB(resultSet);
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return ticket;
    }

    private TicketEntity getTicketFromDB(ResultSet resultSet) throws SQLException{
        int id = resultSet.getInt(1);
        int ticketPrice = resultSet.getInt(2);
        int idSessionTicket = resultSet.getInt(3);
        int idUserTicket = resultSet.getInt(4);
        int placeNumber = resultSet.getInt(5);
        int placeRow = resultSet.getInt(6);
        TicketEntity ticket = new TicketEntity(ticketPrice, idSessionTicket, idUserTicket, placeNumber, placeRow);
        ticket.setId_ticket(id);
        return ticket;
    }

    @Override
    public void saveTicket(TicketEntity ticket) {
        String saveUser = "INSERT INTO ticket (ticket_price, id_session_ticket, id_user_ticket, place_number, place_row)"
                + "VALUES(?,?,?,?,?)";
        try{
            setTicketInfo(ticket, saveUser);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void setTicketInfo(TicketEntity ticket, String saveUser) throws SQLException {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(saveUser);
        preparedStatement.setInt(1, ticket.getTicketPrice());
        preparedStatement.setInt(2, ticket.getIdSessionTicket());
        preparedStatement.setNull(3, Types.INTEGER);
        preparedStatement.setInt(4, ticket.getPlaceNumber());
        preparedStatement.setInt(5, ticket.getPlaceRow());

        preparedStatement.executeUpdate();
    }

    @Override
    public void updateTicket(TicketEntity ticket) {
        int id = ticket.getId_ticket();
        String updateTicket = "UPDATE ticket SET ticket_price=?, id_session_ticket=?, id_user_ticket=?,"
                + " place_number=?, place_row=?"
                + " WHERE id_ticket=" + id;
        try{
            setTicketInfo(ticket, updateTicket);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTicket(TicketEntity ticket) {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        int id = ticket.getId_ticket();
        String delete = "DELETE FROM ticket WHERE id_ticket=" + id;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(delete);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<TicketEntity> findTicketBySessionId(int idSession){
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String find = "SELECT * FROM ticket WHERE id_session_ticket=" + idSession;
        List<TicketEntity> tickets = new ArrayList<>();
        tickets = setTicketList(connection, find, tickets);
        return tickets;
    }

    public List<TicketEntity> findFreeTickets(int idSession){
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String find = "SELECT * FROM ticket WHERE id_session_ticket=" + idSession + " AND id_user_ticket IS NULL";
        List<TicketEntity> tickets = new ArrayList<>();
        tickets = setTicketList(connection, find, tickets);
        return tickets;
    }

    public void setUserTicket(int idUser, int idTicket){
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        String setTicket = "UPDATE ticket SET id_user_ticket=" + idUser + " WHERE id_ticket=" + idTicket;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(setTicket);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    private List<TicketEntity> setTicketList(Connection connection, String find, List<TicketEntity> tickets) {
        ResultSet resultSet;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(find);
            resultSet = preparedStatement.executeQuery(find);
            while (resultSet.next()){
                TicketEntity ticket = getTicketFromDB(resultSet);
                tickets.add(ticket);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public List<TicketEntity> findAll() {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        List<TicketEntity> tickets = new ArrayList<>();
        String selectAllTickets = "SELECT * FROM ticket";
        tickets = setTicketList(connection, selectAllTickets, tickets);
        return tickets;
    }
}
