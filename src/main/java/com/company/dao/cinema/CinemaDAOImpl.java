package com.company.dao.cinema;

import com.company.entities.CinemaEntity;
import com.company.entities.HallEntity;
import com.company.utils.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CinemaDAOImpl implements CinemaDAO {
    public CinemaEntity findCinemaById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(CinemaEntity.class, id);
    }

    public void saveCinema(CinemaEntity cinema) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cinema);
        transaction.commit();
        session.close();
    }

    public void updateCinema(CinemaEntity cinema) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cinema);
        transaction.commit();
        session.close();
    }

    public void deleteCinema(CinemaEntity cinema) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cinema);
        transaction.commit();
        session.close();
    }

    public HallEntity findHallById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(HallEntity.class, id);
    }

    public List<CinemaEntity> findAll() {
        List<CinemaEntity> cinemaEntities = (List<CinemaEntity>) HibernateSessionFactory.getSessionFactory().openSession().createQuery("From CinemaEntity").list();
        return cinemaEntities;
    }
}
