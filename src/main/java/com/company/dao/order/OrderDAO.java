package com.company.dao.order;

import com.company.entities.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> createOrder(int idUser);
}
