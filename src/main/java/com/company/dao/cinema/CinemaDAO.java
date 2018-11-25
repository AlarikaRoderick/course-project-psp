package com.company.dao.cinema;

import com.company.entities.CinemaEntity;
import com.company.entities.HallEntity;

import java.util.List;

public interface CinemaDAO {
    CinemaEntity findCinemaById(int id);
    void saveCinema(CinemaEntity cinema);
    void updateCinema(CinemaEntity cinema);
    void deleteCinema(CinemaEntity cinema);
    List<CinemaEntity> findAll();
}
