package com.company.entities;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;

    @Column(name = "date_order")
    private Date dateOrder;

    @Column(name = "numb_tickets")
    private Integer numbTickets;

    @Column(name = "order_sum")
    private Integer orderSum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_user_order")
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

    public OrderEntity(){}

    public OrderEntity(Date dateOrder, Integer numbTickets, Integer orderSum, UserEntity user) {
        this.dateOrder = dateOrder;
        this.numbTickets = numbTickets;
        this.orderSum = orderSum;
        this.user = user;
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

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    public Integer getNumbTickets() {
        return numbTickets;
    }

    public void setNumbTickets(Integer numbTickets) {
        this.numbTickets = numbTickets;
    }

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

        if (id_order != that.id_order) return false;
        if (dateOrder != null ? !dateOrder.equals(that.dateOrder) : that.dateOrder != null) return false;
        if (numbTickets != null ? !numbTickets.equals(that.numbTickets) : that.numbTickets != null) return false;
        if (orderSum != null ? !orderSum.equals(that.orderSum) : that.orderSum != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id_order;
        result = 31 * result + (dateOrder != null ? dateOrder.hashCode() : 0);
        result = 31 * result + (numbTickets != null ? numbTickets.hashCode() : 0);
        result = 31 * result + (orderSum != null ? orderSum.hashCode() : 0);
        return result;
    }
}
