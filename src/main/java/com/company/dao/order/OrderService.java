package com.company.dao.order;

import com.company.entities.OrderEntity;
import com.company.entities.TicketEntity;

import java.util.List;

public class OrderService {
    private OrderDAOImpl orderDAO = new OrderDAOImpl();

    public OrderService(){}

    public OrderEntity findOrder(int id){
        return orderDAO.findOrderById(id);
    }

    public void saveOrder(OrderEntity order){
        orderDAO.saveOrder(order);
    }

    public void updateOrder(OrderEntity order){
        orderDAO.updateOrder(order);
    }

    public void deleteOrder(OrderEntity order){
        orderDAO.deleteOrder(order);
    }

    public TicketEntity findTicketById(int id){
        return orderDAO.findTicketById(id);
    }

    public List<OrderEntity> findAllOrders(){
        return orderDAO.findAll();
    }
}
