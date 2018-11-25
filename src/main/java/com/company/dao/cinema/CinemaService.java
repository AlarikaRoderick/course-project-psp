package com.company.dao.cinema;

import com.company.entities.CinemaEntity;
import com.company.entities.HallEntity;

import java.util.List;

public class CinemaService {
    private CinemaDAOImpl cinemaDAO = new CinemaDAOImpl();

    public CinemaService(){}

    public CinemaEntity findCinema(int id){
        return new CinemaDAOImpl().findCinemaById(id);
    }

    public void saveCinema(CinemaEntity cinemaEntity){
        new CinemaDAOImpl().saveCinema(cinemaEntity);
    }

    public void updateCinema(CinemaEntity cinemaEntity){
        new CinemaDAOImpl().updateCinema(cinemaEntity);
    }

    public void deleteCinema(CinemaEntity cinemaEntity){
        new CinemaDAOImpl().deleteCinema(cinemaEntity);
    }

    public List<CinemaEntity> findAllCinema(){
        return new CinemaDAOImpl().findAll();
    }
}
