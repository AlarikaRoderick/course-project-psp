package com.company.dao.session;

import com.company.entities.SessionEntity;
import com.company.entities.TicketEntity;

import java.util.List;

public interface SessionDAO {
    SessionEntity findSessionById(int id);
    void saveSession(SessionEntity session);
    void updateSession(SessionEntity session);
    void deleteSession(SessionEntity session);
    TicketEntity findTicketById(int id);
    List<SessionEntity> findAll();
}
