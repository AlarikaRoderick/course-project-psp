package com.company.dao.ticket;

import com.company.entities.TicketEntity;

import java.util.List;

public class TicketService {
    private TicketDAOImpl ticketDAO = new TicketDAOImpl();

    public TicketService(){}

    public TicketEntity findTicket(int id){
        return ticketDAO.findTicketById(id);
    }

    public void saveTicket(TicketEntity ticket){
        ticketDAO.saveTicket(ticket);
    }

    public void updateTicket(TicketEntity ticket){
        ticketDAO.updateTicket(ticket);
    }

    public void deleteTicket(TicketEntity ticket){
        ticketDAO.deleteTicket(ticket);
    }

    public List<TicketEntity> findAllTickets(){
        return ticketDAO.findAll();
    }
}
