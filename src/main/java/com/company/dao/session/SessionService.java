package com.company.dao.session;

import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;

import java.util.List;

public class SessionService {
    private SessionDAOImpl sessionDAO = new SessionDAOImpl();

    public SessionService(){}

    public SessionEntity findSession(int id){
        return sessionDAO.findSessionById(id);
    }

    public void saveSession(SessionEntity session){
        sessionDAO.saveSession(session);
    }

    public void updateSession(SessionEntity session){
        sessionDAO.updateSession(session);
    }

    public void deleteSession(SessionEntity session){
        sessionDAO.deleteSession(session);
    }

    public TicketEntity findTicketById(int id){
        return sessionDAO.findTicketById(id);
    }

    public List<SessionEntity> findAllSessions(){
        return sessionDAO.findAll();
    }
}
