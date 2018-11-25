package com.company.dao.hall;

import com.company.entities.HallEntity;
import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;

import java.util.List;

public class HallService {
    private HallDAOImpl hallDAO = new HallDAOImpl();

    public HallService(){}

    public HallEntity findHall(int id){
        return new HallDAOImpl().findHallById(id);
    }

    public void saveHall(HallEntity hall){
        new HallDAOImpl().saveHall(hall);
    }

    public void updateHall(HallEntity hall){
        new HallDAOImpl().updateHall(hall);
    }

    public void deleteHall(HallEntity hall){
        new HallDAOImpl().deleteHall(hall);
    }

    public List<HallEntity> findAllHalls(){
        return new HallDAOImpl().findAll();
    }
}
