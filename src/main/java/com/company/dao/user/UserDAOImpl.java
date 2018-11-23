package com.company.dao.user;

import com.company.entities.UserEntity;
import com.company.transaction.TransactionUtil;
import com.company.utils.HibernateSessionFactory;

import java.util.List;

public class UserDAOImpl implements UserDAO {
    private TransactionUtil<UserEntity> userEntityTransactionUtil = new TransactionUtil<>();

    @Override
    public UserEntity findUserById(int id) {
        return HibernateSessionFactory.getSessionFactory().openSession().get(UserEntity.class, id);
    }

    @Override
    public void saveUser(UserEntity user) {
        userEntityTransactionUtil.save(user);
    }

    @Override
    public void updateUser(UserEntity user) {
        userEntityTransactionUtil.update(user);
    }

    @Override
    public void deleteUser(UserEntity user) {
        userEntityTransactionUtil.delete(user);
    }

    @Override
    public List<UserEntity> findAll() {
        return HibernateSessionFactory.getSessionFactory().openSession().createQuery("From UserEntity").list();
    }
}
