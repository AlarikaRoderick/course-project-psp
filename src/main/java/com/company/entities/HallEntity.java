package com.company.entities;

import java.io.Serializable;

public class HallEntity implements Serializable {

    private int id_hall;
    private String hallName;
    private int hallRows;
    private int hallPlaces;
    private int idCinemaHall;

    public HallEntity(){}

    public HallEntity(String hallName, int hallRows, int hallPlaces, int idCinemaHall) {
        this.hallName = hallName;
        this.hallRows = hallRows;
        this.hallPlaces = hallPlaces;
        this.idCinemaHall = idCinemaHall;
    }

    public int getId_hall() {
        return id_hall;
    }

    public void setId_hall(int id_hall) {
        this.id_hall = id_hall;
    }

    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    public int getHallRows() {
        return hallRows;
    }

    public void setHallRows(int hallRows) {
        this.hallRows = hallRows;
    }

    public int getHallPlaces() {
        return hallPlaces;
    }

    public void setHallPlaces(int hallPlaces) {
        this.hallPlaces = hallPlaces;
    }

    public int getIdCinemaHall() {
        return idCinemaHall;
    }

    public void setIdCinemaHall(int idCinemaHall) {
        this.idCinemaHall = idCinemaHall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HallEntity that = (HallEntity) o;

        if (id_hall != that.id_hall) return false;
        if (hallRows != that.hallRows) return false;
        if (hallPlaces != that.hallPlaces) return false;
        if (hallName != null ? !hallName.equals(that.hallName) : that.hallName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_hall;
        result = 31 * result + (hallName != null ? hallName.hashCode() : 0);
        result = 31 * result + hallRows;
        result = 31 * result + hallPlaces;
        return result;
    }
}
