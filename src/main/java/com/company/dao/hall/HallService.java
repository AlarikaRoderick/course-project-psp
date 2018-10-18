package com.company.dao.hall;

import com.company.entities.HallEntity;
import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;

import java.util.List;

public class HallService {
    private HallDAOImpl hallDAO = new HallDAOImpl();

    public HallService(){}

    public HallEntity findHall(int id){
        return hallDAO.findHallById(id);
    }

    public void saveHall(HallEntity hall){
        hallDAO.saveHall(hall);
    }

    public void updateHall(HallEntity hall){
        hallDAO.updateHall(hall);
    }

    public void deleteHall(HallEntity hall){
        hallDAO.deleteHall(hall);
    }

    public SessionEntity findTicketById(int id){
        return hallDAO.findSessionById(id);
    }

    public List<HallEntity> findAllHalls(){
        return hallDAO.findAll();
    }
}
