package com.company.dao.cinema;

import com.company.entities.CinemaEntity;
import com.company.entities.HallEntity;

import java.util.List;

public class CinemaService {
    private CinemaDAOImpl cinemaDAO = new CinemaDAOImpl();

    public CinemaService(){}

    public CinemaEntity findCinema(int id){
        return cinemaDAO.findCinemaById(id);
    }

    public void saveCinema(CinemaEntity cinemaEntity){
        cinemaDAO.saveCinema(cinemaEntity);
    }

    public void updateCinema(CinemaEntity cinemaEntity){
        cinemaDAO.updateCinema(cinemaEntity);
    }

    public void deleteCinema(CinemaEntity cinemaEntity){
        cinemaDAO.deleteCinema(cinemaEntity);
    }

    public HallEntity findHallById(int id){
        return cinemaDAO.findHallById(id);
    }

    public List<CinemaEntity> findAllCinema(){
        return cinemaDAO.findAll();
    }
}
