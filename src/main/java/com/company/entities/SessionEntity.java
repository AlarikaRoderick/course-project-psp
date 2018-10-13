package com.company.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "session")
public class SessionEntity {

    private int idSession;
    private Date sessionDate;
    private Integer idSesionHall;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_film")
    private FilmEntity film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hall")
    private HallEntity hall;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketEntity> ticketEntities;

    public SessionEntity(){}

    @Id
    @Column(name = "id_session", nullable = false)
    public int getIdSession() {
        return idSession;
    }

    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }

    @Basic
    @Column(name = "session_date", nullable = true)
    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }

    @Basic
    @Column(name = "id_sesion_hall", nullable = true)
    public Integer getIdSesionHall() {
        return idSesionHall;
    }

    public void setIdSesionHall(Integer idSesionHall) {
        this.idSesionHall = idSesionHall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SessionEntity that = (SessionEntity) o;

        if (idSession != that.idSession) return false;
        if (sessionDate != null ? !sessionDate.equals(that.sessionDate) : that.sessionDate != null) return false;
        if (idSesionHall != null ? !idSesionHall.equals(that.idSesionHall) : that.idSesionHall != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idSession;
        result = 31 * result + (sessionDate != null ? sessionDate.hashCode() : 0);
        result = 31 * result + (idSesionHall != null ? idSesionHall.hashCode() : 0);
        return result;
    }
}
