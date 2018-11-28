package com.company.dao.ticket;

import com.company.entities.TicketEntity;

import java.util.List;

public class TicketService {
    private TicketDAOImpl ticketDAO = new TicketDAOImpl();

    public TicketService(){}

    public TicketEntity findTicket(int id){
        return new TicketDAOImpl().findTicketById(id);
    }

    public void saveTicket(TicketEntity ticket){
        new TicketDAOImpl().saveTicket(ticket);
    }

    public void updateTicket(TicketEntity ticket){
        new TicketDAOImpl().updateTicket(ticket);
    }

    public void deleteTicket(TicketEntity ticket){
        new TicketDAOImpl().deleteTicket(ticket);
    }

    public List<TicketEntity> findAllTickets(){
        return new TicketDAOImpl().findAll();
    }

    public List<TicketEntity> findTicketsBySessionId(int idSession){
        return new TicketDAOImpl().findTicketBySessionId(idSession);
    }
}
