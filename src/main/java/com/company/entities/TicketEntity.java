package com.company.entities;

import java.io.Serializable;

public class TicketEntity implements Serializable {

    private int id_ticket;
    private int ticketPrice;
    private int idSessionTicket;
    private int idUserTicket;

    public TicketEntity() {
    }

    public TicketEntity(int ticketPrice, int idSessionTicket, int idUserTicket) {
        this.ticketPrice = ticketPrice;
        this.idSessionTicket = idSessionTicket;
        this.idUserTicket = idUserTicket;
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

    public int getIdSessionTicket() {
        return idSessionTicket;
    }

    public void setIdSessionTicket(int idSessionTicket) {
        this.idSessionTicket = idSessionTicket;
    }

    public int getIdUserTicket() {
        return idUserTicket;
    }

    public void setIdUserTicket(int idUserTicket) {
        this.idUserTicket = idUserTicket;
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
