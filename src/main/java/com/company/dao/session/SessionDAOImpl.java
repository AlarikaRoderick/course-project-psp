package com.company.dao.session;

import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;
import com.company.transaction.TransactionUtil;
import com.company.utils.HibernateSessionFactory;

import java.util.List;

public class SessionDAOImpl implements SessionDAO {
    private TransactionUtil<SessionEntity> sessionEntityTransactionUtil = new TransactionUtil<>();

    @Override
    public SessionEntity findSessionById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(SessionEntity.class, id);
    }

    @Override
    public void saveSession(SessionEntity session) {
        sessionEntityTransactionUtil.save(session);
    }

    @Override
    public void updateSession(SessionEntity session) {
        sessionEntityTransactionUtil.update(session);
    }

    @Override
    public void deleteSession(SessionEntity session) {
        sessionEntityTransactionUtil.delete(session);
    }

    @Override
    public TicketEntity findTicketById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(TicketEntity.class, id);
    }

    @Override
    public List<SessionEntity> findAll() {
        List<SessionEntity> sessionEntities = (List<SessionEntity>) HibernateSessionFactory.getSessionFactory()
                .openSession().createQuery("From SessionEntity").list();
        return sessionEntities;
    }
}
