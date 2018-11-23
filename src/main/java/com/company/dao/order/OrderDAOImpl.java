package com.company.dao.order;

import com.company.entities.OrderEntity;
import com.company.entities.TicketEntity;
import com.company.transaction.TransactionUtil;
import com.company.utils.HibernateSessionFactory;

import java.util.List;

public class OrderDAOImpl implements OrderDAO {
    private TransactionUtil<OrderEntity> orderEntityTransactionUtil = new TransactionUtil<>();

    @Override
    public OrderEntity findOrderById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(OrderEntity.class, id);
    }

    @Override
    public void saveOrder(OrderEntity order) {
        orderEntityTransactionUtil.save(order);
    }

    @Override
    public void updateOrder(OrderEntity order) {
        orderEntityTransactionUtil.update(order);
    }

    @Override
    public void deleteOrder(OrderEntity order) {
        orderEntityTransactionUtil.delete(order);
    }

    @Override
    public TicketEntity findTicketById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(TicketEntity.class, id);
    }

    @Override
    public List<OrderEntity> findAll() {
        List<OrderEntity> orderEntities = (List<OrderEntity>) HibernateSessionFactory.getSessionFactory()
                .openSession().createQuery("From OrderEntity").list();
        return orderEntities;
    }
}
