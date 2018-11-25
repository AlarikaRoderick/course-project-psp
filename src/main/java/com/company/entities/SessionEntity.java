package com.company.entities;

import java.io.Serializable;
import java.sql.Date;

public class SessionEntity implements Serializable {

    private int id_session;
    private Date sessionDate;
    private int idFilmSession;
    private int idHallSession;
    private int sessionTimeHour;
    private int sessionTimeMinute;

    public SessionEntity() {
    }

    public SessionEntity(Date sessionDate, int idFilmSession, int idHallSession, int sessionTimeHour, int sessionTimeMinute) {
        this.sessionDate = sessionDate;
        this.idFilmSession = idFilmSession;
        this.idHallSession = idHallSession;
        this.sessionTimeHour = sessionTimeHour;
        this.sessionTimeMinute = sessionTimeMinute;
    }

    public int getId_session() {
        return id_session;
    }

    public void setId_session(int id_session) {
        this.id_session = id_session;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getIdFilmSession() {
        return idFilmSession;
    }

    public void setIdFilmSession(int idFilmSession) {
        this.idFilmSession = idFilmSession;
    }

    public int getIdHallSession() {
        return idHallSession;
    }

    public void setIdHallSession(int idHallSession) {
        this.idHallSession = idHallSession;
    }

    public int getSessionTimeHour() {
        return sessionTimeHour;
    }

    public void setSessionTimeHour(int sessionTimeHour) {
        this.sessionTimeHour = sessionTimeHour;
    }

    public int getSessionTimeMinute() {
        return sessionTimeMinute;
    }

    public void setSessionTimeMinute(int sessionTimeMinute) {
        this.sessionTimeMinute = sessionTimeMinute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionEntity that = (SessionEntity) o;

        if (id_session != that.id_session) return false;
        if (sessionDate != null ? !sessionDate.equals(that.sessionDate) : that.sessionDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_session;
        result = 31 * result + (sessionDate != null ? sessionDate.hashCode() : 0);
        return result;
    }
}
