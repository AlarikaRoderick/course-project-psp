package com.company.dao.order;

import com.company.entities.Order;

import java.util.List;

public class OrderService {

    private OrderDAOImpl orderDAO = new OrderDAOImpl();
    public List<Order> findAllOrders(int idUser){
        return orderDAO.createOrder(idUser);
    }
}
