package com.company.dao.order;

import com.company.entities.OrderEntity;
import com.company.entities.TicketEntity;

import java.util.List;

public interface OrderDAO {
    OrderEntity findOrderById(int id);
    void saveOrder(OrderEntity order);
    void updateOrder(OrderEntity order);
    void deleteOrder(OrderEntity order);
    TicketEntity findTicketById(int id);
    List<OrderEntity> findAll();
}
