package com.company.dao.ticket;

import com.company.entities.TicketEntity;
import com.company.transaction.TransactionUtil;
import com.company.utils.HibernateSessionFactory;

import java.util.List;

public class TicketDAOImpl implements TicketDAO {
    private TransactionUtil<TicketEntity> ticketEntityTransactionUtil = new TransactionUtil<>();

    @Override
    public TicketEntity findTicketById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(TicketEntity.class, id);
    }

    @Override
    public void saveTicket(TicketEntity ticket) {
        ticketEntityTransactionUtil.save(ticket);
    }

    @Override
    public void updateTicket(TicketEntity ticket) {
        ticketEntityTransactionUtil.update(ticket);
    }

    @Override
    public void deleteTicket(TicketEntity ticket) {
        ticketEntityTransactionUtil.delete(ticket);
    }

    @Override
    public List<TicketEntity> findAll() {
        return HibernateSessionFactory.getSessionFactory().openSession()
                .createQuery("From TicketEntity").list();
    }
}
