package com.company.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hall")
public class HallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_hall;

    @Column(name = "hall_name")
    private String hallName;

    @Column(name = "hall_rows")
    private int hallRows;

    @Column(name = "hall_places")
    private int hallPlaces;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SessionEntity> sessionEntityList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cinema_hall")
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
