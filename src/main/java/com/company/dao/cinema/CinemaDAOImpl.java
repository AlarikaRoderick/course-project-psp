package com.company.dao.cinema;

import com.company.entities.CinemaEntity;
import com.company.entities.HallEntity;
import com.company.transaction.TransactionUtil;
import com.company.utils.HibernateSessionFactory;

import java.util.List;

public class CinemaDAOImpl implements CinemaDAO {
    private TransactionUtil<CinemaEntity> cinemaEntityTransactionUtil = new TransactionUtil<>();

    public CinemaEntity findCinemaById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(CinemaEntity.class, id);
    }

    public void saveCinema(CinemaEntity cinema) {
        cinemaEntityTransactionUtil.save(cinema);
    }

    public void updateCinema(CinemaEntity cinema) {
        cinemaEntityTransactionUtil.update(cinema);
    }

    public void deleteCinema(CinemaEntity cinema) {
        cinemaEntityTransactionUtil.delete(cinema);
    }

    public HallEntity findHallById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(HallEntity.class, id);
    }

    public List<CinemaEntity> findAll() {
        List<CinemaEntity> cinemaEntities = (List<CinemaEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From CinemaEntity").list();
        return cinemaEntities;
    }
}
