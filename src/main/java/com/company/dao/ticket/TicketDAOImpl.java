package com.company.dao.ticket;

import com.company.dbHandler.DbHandler;
import com.company.entities.TicketEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        ResultSet resultSet = null;
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
        TicketEntity ticket = new TicketEntity(ticketPrice, idSessionTicket, idUserTicket);
        ticket.setId_ticket(id);
        return ticket;
    }

    @Override
    public void saveTicket(TicketEntity ticket) {
        String saveUser = "INSERT INTO ticket (ticket_price, id_session_ticket, id_user_ticket)"
                + "VALUES(?,?,?)";
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
        preparedStatement.setInt(3, ticket.getIdUserTicket());
        preparedStatement.executeUpdate();
    }

    @Override
    public void updateTicket(TicketEntity ticket) {
        int id = ticket.getId_ticket();
        String updateTicket = "UPDATE user SET ticket_price=?, id_session_ticket=?, id_user_ticket=?"
                + "WHERE id_ticket=" + id;
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

    @Override
    public List<TicketEntity> findAll() {
        DbHandler dbHandler = DbHandler.getInstance();
        dbHandler.createConnection();
        Connection connection = DbHandler.getInstance().getConnection();
        ResultSet resultSet = null;
        List<TicketEntity> tickets = new ArrayList<>();
        String selectAllTickets = "SELECT * FROM ticket";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(selectAllTickets);
            resultSet = preparedStatement.executeQuery(selectAllTickets);

            while (resultSet.next()){
                TicketEntity ticket = getTicketFromDB(resultSet);
                tickets.add(ticket);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return tickets;
    }
}
