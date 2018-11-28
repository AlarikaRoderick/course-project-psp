package com.company.entities;

import java.sql.Date;

public class Order {
    private int idTicket;
    private Date sessionDate;
    private int sessionHour;
    private int sessionMinute;
    private String filmName;
    private int placeRow;
    private int placeNumber;

    public Order(int idTicket, Date sessionDate, int sessionHour, int sessionMinute, String filmName, int placeRow, int placeNumber) {
        this.idTicket = idTicket;
        this.sessionDate = sessionDate;
        this.sessionHour = sessionHour;
        this.sessionMinute = sessionMinute;
        this.filmName = filmName;
        this.placeRow = placeRow;
        this.placeNumber = placeNumber;
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

    public int getPlaceRow() {
        return placeRow;
    }

    public void setPlaceRow(int placeRow) {
        this.placeRow = placeRow;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }
}
