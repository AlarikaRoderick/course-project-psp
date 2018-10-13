package com.company.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hall", schema = "cinema")
public class HallEntity {
    private int idHall;
    private String hallName;
    private int hallRows;
    private int hallPlaces;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionEntity> sessionEntityList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cinema")
    private CinemaEntity cinema;

    public HallEntity(){}

    public HallEntity(String hallName, int hallRows, int hallPlaces) {
        this.hallName = hallName;
        this.hallRows = hallRows;
        this.hallPlaces = hallPlaces;
    }

    public CinemaEntity getCinema(){ return cinema; }

    public void setCinema(CinemaEntity cinema){ this.cinema = cinema; }

    public void addSession(SessionEntity session){
        session.setHall(this);
        sessionEntityList.add(session);
    }

    public void removeSession(SessionEntity session){
        sessionEntityList.remove(session);
    }

    public List<SessionEntity> getSessionEntityList() {
        return sessionEntityList;
    }

    public void setSessionEntityList(List<SessionEntity> sessionEntityList) {
        this.sessionEntityList = sessionEntityList;
    }

    @Id
    @Column(name = "id_hall", nullable = false)
    public int getIdHall() {
        return idHall;
    }

    public void setIdHall(int idHall) {
        this.idHall = idHall;
    }

    @Basic
    @Column(name = "hall_name", nullable = true, length = 45)
    public String getHallName() {
        return hallName;
    }

    public void setHallName(String hallName) {
        this.hallName = hallName;
    }

    @Basic
    @Column(name = "hall_rows", nullable = false)
    public int getHallRows() {
        return hallRows;
    }

    public void setHallRows(int hallRows) {
        this.hallRows = hallRows;
    }

    @Basic
    @Column(name = "hall_places", nullable = false)
    public int getHallPlaces() {
        return hallPlaces;
    }

    public void setHallPlaces(int hallPlaces) {
        this.hallPlaces = hallPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        HallEntity that = (HallEntity) o;

        if (idHall != that.idHall) return false;
        if (hallRows != that.hallRows) return false;
        if (hallPlaces != that.hallPlaces) return false;
        if (hallName != null ? !hallName.equals(that.hallName) : that.hallName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idHall;
        result = 31 * result + (hallName != null ? hallName.hashCode() : 0);
        result = 31 * result + hallRows;
        result = 31 * result + hallPlaces;
        return result;
    }
}
