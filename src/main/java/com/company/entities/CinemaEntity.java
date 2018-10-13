package com.company.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinema", schema = "cinema")
public class CinemaEntity {
    private int idCinema;
    private String cinemaName;
    private String cinemaAddress;
    private String cinemaUnderground;
    private String cinemaPhone;

    @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HallEntity> hallEntities;

    public CinemaEntity(){}

    public CinemaEntity(String cinemaName, String cinemaAddress, String cinemaUnderground, String cinemaPhone) {
        this.cinemaName = cinemaName;
        this.cinemaAddress = cinemaAddress;
        this.cinemaUnderground = cinemaUnderground;
        this.cinemaPhone = cinemaPhone;
        hallEntities = new ArrayList<>();
    }

    public void addHall(HallEntity hall){
        hall.setCinema(this);
        hallEntities.add(hall);
    }

    public void removeHall(HallEntity hall){
        hallEntities.remove(hall);
    }

    public List<HallEntity> getHallEntities() {
        return hallEntities;
    }

    public void setHallEntities(List<HallEntity> hallEntities) {
        this.hallEntities = hallEntities;
    }

    @Id
    @Column(name = "id_cinema", nullable = false)
    public int getIdCinema() {
        return idCinema;
    }

    public void setIdCinema(int idCinema) {
        this.idCinema = idCinema;
    }

    @Basic
    @Column(name = "cinema_name", nullable = false, length = 45)
    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    @Basic
    @Column(name = "cinema_address", nullable = false, length = 45)
    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    @Basic
    @Column(name = "cinema_underground", nullable = false, length = 45)
    public String getCinemaUnderground() {
        return cinemaUnderground;
    }

    public void setCinemaUnderground(String cinemaUnderground) {
        this.cinemaUnderground = cinemaUnderground;
    }

    @Basic
    @Column(name = "cinema_phone", nullable = false, length = 45)
    public String getCinemaPhone() {
        return cinemaPhone;
    }

    public void setCinemaPhone(String cinemaPhone) {
        this.cinemaPhone = cinemaPhone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CinemaEntity that = (CinemaEntity) o;

        if (idCinema != that.idCinema) return false;
        if (cinemaName != null ? !cinemaName.equals(that.cinemaName) : that.cinemaName != null) return false;
        if (cinemaAddress != null ? !cinemaAddress.equals(that.cinemaAddress) : that.cinemaAddress != null)
            return false;
        if (cinemaUnderground != null ? !cinemaUnderground.equals(that.cinemaUnderground) : that.cinemaUnderground != null)
            return false;
        if (cinemaPhone != null ? !cinemaPhone.equals(that.cinemaPhone) : that.cinemaPhone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCinema;
        result = 31 * result + (cinemaName != null ? cinemaName.hashCode() : 0);
        result = 31 * result + (cinemaAddress != null ? cinemaAddress.hashCode() : 0);
        result = 31 * result + (cinemaUnderground != null ? cinemaUnderground.hashCode() : 0);
        result = 31 * result + (cinemaPhone != null ? cinemaPhone.hashCode() : 0);
        return result;
    }
}
