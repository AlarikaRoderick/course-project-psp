package com.company.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cinema")
public class CinemaEntity implements Serializable {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_cinema;

    @Column(name="cinema_name")
    private String cinemaName;

    @Column(name = "cinema_address")
    private String cinemaAddress;

    @Column(name = "cinema_underground")
    private String cinemaUnderground;

    @Column(name = "cinema_phone")
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

    public int getId_cinema() {
        return id_cinema;
    }

    public void setId_cinema(int id_cinema) {
        this.id_cinema = id_cinema;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getCinemaAddress() {
        return cinemaAddress;
    }

    public void setCinemaAddress(String cinemaAddress) {
        this.cinemaAddress = cinemaAddress;
    }

    public String getCinemaUnderground() {
        return cinemaUnderground;
    }

    public void setCinemaUnderground(String cinemaUnderground) {
        this.cinemaUnderground = cinemaUnderground;
    }

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

        if (id_cinema != that.id_cinema) return false;
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
        int result = id_cinema;
        result = 31 * result + (cinemaName != null ? cinemaName.hashCode() : 0);
        result = 31 * result + (cinemaAddress != null ? cinemaAddress.hashCode() : 0);
        result = 31 * result + (cinemaUnderground != null ? cinemaUnderground.hashCode() : 0);
        result = 31 * result + (cinemaPhone != null ? cinemaPhone.hashCode() : 0);
        return result;
    }
}
