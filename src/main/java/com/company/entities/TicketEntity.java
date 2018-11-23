package com.company.entities;

import javax.persistence.*;

@Entity
@Table(name = "ticket")
public class TicketEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_ticket;

    @Column(name = "ticket_price")
    private int ticketPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_session_ticket")
    private SessionEntity session;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_ticket")
    private UserEntity user;

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public int getId_ticket() {
        return id_ticket;
    }

    public void setId_ticket(int id_ticket) {
        this.id_ticket = id_ticket;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TicketEntity that = (TicketEntity) o;

        if (id_ticket != that.id_ticket) return false;
        if (ticketPrice != that.ticketPrice) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_ticket;
        result = 31 * result + ticketPrice;
        return result;
    }
}
