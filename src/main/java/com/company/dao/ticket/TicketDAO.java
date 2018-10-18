package com.company.dao.ticket;

import com.company.entities.TicketEntity;

import java.util.List;

public interface TicketDAO {
    TicketEntity findTicketById(int id);
    void saveTicket(TicketEntity ticket);
    void updateTicket(TicketEntity ticket);
    void deleteTicket(TicketEntity ticket);
    List<TicketEntity> findAll();
}
