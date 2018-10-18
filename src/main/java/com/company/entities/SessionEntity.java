package com.company.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "session")
public class SessionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_session;

    @Column(name = "session_date")
    private Date sessionDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_film")
    private FilmEntity film;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hall")
    private HallEntity hall;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketEntity> ticketEntities;

    public void addTicket(TicketEntity ticket){
        ticket.setSession(this);
        ticketEntities.add(ticket);
    }

    public void removeTicket(TicketEntity ticket){
        ticketEntities.remove(ticket);
    }

    public List<TicketEntity> getTicketEntities() {
        return ticketEntities;
    }

    public void setTicketEntities(List<TicketEntity> ticketEntities) {
        this.ticketEntities = ticketEntities;
    }

    public SessionEntity(){}

    public HallEntity getHall() {
        return hall;
    }

    public void setHall(HallEntity hall) {
        this.hall = hall;
    }

    public FilmEntity getFilm() {
        return film;
    }

    public void setFilm(FilmEntity film) {
        this.film = film;
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
