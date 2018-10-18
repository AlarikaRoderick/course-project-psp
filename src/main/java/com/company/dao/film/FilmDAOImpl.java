package com.company.dao.film;

import com.company.entities.FilmEntity;
import com.company.entities.HallEntity;
import com.company.entities.SessionEntity;
import com.company.transaction.TransactionUtil;
import com.company.utils.HibernateSessionFactory;

import java.util.List;

public class FilmDAOImpl implements FilmDAO {
    private TransactionUtil<FilmEntity> filmEntityTransactionUtil = new TransactionUtil<>();

    @Override
    public FilmEntity findFilmById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(FilmEntity.class, id);
    }

    @Override
    public void saveFilm(FilmEntity film) {
        filmEntityTransactionUtil.save(film);
    }

    @Override
    public void updateFilm(FilmEntity film) {
        filmEntityTransactionUtil.update(film);
    }

    @Override
    public void deleteFilm(FilmEntity film) {
        filmEntityTransactionUtil.delete(film);
    }

    @Override
    public SessionEntity findSessionById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(SessionEntity.class, id);
    }

    @Override
    public List<FilmEntity> findAll() {
        List<FilmEntity> filmEntities = (List<FilmEntity>) HibernateSessionFactory
                .getSessionFactory().openSession().createQuery("From FilmEntity").list();
        return filmEntities;
    }
}
