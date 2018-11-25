package com.company.dao.session;

import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;

import java.util.List;

public class SessionService {
    private SessionDAOImpl sessionDAO = new SessionDAOImpl();

    public SessionService(){}

    public SessionEntity findSession(int id){
        return new SessionDAOImpl().findSessionById(id);
    }

    public void saveSession(SessionEntity session){
        new SessionDAOImpl().saveSession(session);
    }

    public void updateSession(SessionEntity session){
        new SessionDAOImpl().updateSession(session);
    }

    public void deleteSession(SessionEntity session){
        new SessionDAOImpl().deleteSession(session);
    }

    public List<SessionEntity> findAllSessions(){
        return new SessionDAOImpl().findAll();
    }

    public List<SessionEntity> findSessionsByFilmName(String filmName){
        return new SessionDAOImpl().findSessionsByFilmName(filmName);
    }
}
