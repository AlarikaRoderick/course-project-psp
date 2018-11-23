package com.company.dao.hall;

import com.company.entities.HallEntity;
import com.company.entities.SessionEntity;
import com.company.transaction.TransactionUtil;
import com.company.utils.HibernateSessionFactory;

import java.util.List;

public class HallDAOImpl implements HallDAO {
    private TransactionUtil<HallEntity> hallEntityTransactionUtil = new TransactionUtil<>();

    @Override
    public HallEntity findHallById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(HallEntity.class, id);
    }

    @Override
    public void saveHall(HallEntity hall) {
        hallEntityTransactionUtil.save(hall);
    }

    @Override
    public void updateHall(HallEntity hall) {
        hallEntityTransactionUtil.update(hall);
    }

    @Override
    public void deleteHall(HallEntity hall) {
        hallEntityTransactionUtil.delete(hall);
    }

    @Override
    public SessionEntity findSessionById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(SessionEntity.class, id);
    }

    @Override
    public List<HallEntity> findAll() {
        List<HallEntity> hallEntities = (List<HallEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From HallEntity").list();
        return hallEntities;
    }
}
