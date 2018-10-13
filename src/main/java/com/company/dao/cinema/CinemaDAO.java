package com.company.dao.cinema;

import com.company.entities.CinemaEntity;
import com.company.entities.HallEntity;

import java.util.List;

public interface CinemaDAO {
    public CinemaEntity findCinemaById(int id);
    public void saveCinema(CinemaEntity cinema);
    public void updateCinema(CinemaEntity cinema);
    public void deleteCinema(CinemaEntity cinema);
    public HallEntity findHallById(int id);
    public List<CinemaEntity> findAll();
}
