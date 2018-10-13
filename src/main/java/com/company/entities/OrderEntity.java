package com.company.entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "order", schema = "cinema", catalog = "")
public class OrderEntity {
    private int idOrder;
    private Timestamp dateOrder;
    private Integer numbTickets;
    private Integer orderSum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user")
    private UserEntity user;

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TicketEntity> ticketEntities;

    public void addTicket(TicketEntity ticket){
        ticket.setOrder(this);
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

    @Id
    @Column(name = "id_order", nullable = false)
    public int getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(int idOrder) {
        this.idOrder = idOrder;
    }

    @Basic
    @Column(name = "date_order", nullable = true)
    public Timestamp getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Timestamp dateOrder) {
        this.dateOrder = dateOrder;
    }

    @Basic
    @Column(name = "numb_tickets", nullable = true)
    public Integer getNumbTickets() {
        return numbTickets;
    }

    public void setNumbTickets(Integer numbTickets) {
        this.numbTickets = numbTickets;
    }

    @Basic
    @Column(name = "order_sum", nullable = true)
    public Integer getOrderSum() {
        return orderSum;
    }

    public void setOrderSum(Integer orderSum) {
        this.orderSum = orderSum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderEntity that = (OrderEntity) o;

        if (idOrder != that.idOrder) return false;
        if (dateOrder != null ? !dateOrder.equals(that.dateOrder) : that.dateOrder != null) return false;
        if (numbTickets != null ? !numbTickets.equals(that.numbTickets) : that.numbTickets != null) return false;
        if (orderSum != null ? !orderSum.equals(that.orderSum) : that.orderSum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idOrder;
        result = 31 * result + (dateOrder != null ? dateOrder.hashCode() : 0);
        result = 31 * result + (numbTickets != null ? numbTickets.hashCode() : 0);
        result = 31 * result + (orderSum != null ? orderSum.hashCode() : 0);
        return result;
    }
}
