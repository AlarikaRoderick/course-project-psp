package com.company.dao.hall;

import com.company.entities.HallEntity;
import com.company.entities.SessionEntity;

import java.util.List;

public interface HallDAO {
    HallEntity findHallById(int id);
    void saveHall(HallEntity hall);
    void updateHall(HallEntity hall);
    void deleteHall(HallEntity hall);
    List<HallEntity> findAll();
}
