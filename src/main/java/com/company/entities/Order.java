package com.company.entities;

import java.sql.Date;

public class Order {
    private int idTicket;
    private Date sessionDate;
    private int sessionHour;
    private int sessionMinute;
    private String filmName;

    public Order(int idTicket, Date sessionDate, int sessionHour, int sessionMinute, String filmName) {
        this.idTicket = idTicket;
        this.sessionDate = sessionDate;
        this.sessionHour = sessionHour;
        this.sessionMinute = sessionMinute;
        this.filmName = filmName;
    }

    public int getIdTicket() {
        return idTicket;
    }

    public void setIdTicket(int idTicket) {
        this.idTicket = idTicket;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    public int getSessionHour() {
        return sessionHour;
    }

    public void setSessionHour(int sessionHour) {
        this.sessionHour = sessionHour;
    }

    public int getSessionMinute() {
        return sessionMinute;
    }

    public void setSessionMinute(int sessionMinute) {
        this.sessionMinute = sessionMinute;
    }

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }
}
