package com.company.utils;

import com.company.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactory {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactory(){}

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
            try{
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(CinemaEntity.class);
                configuration.addAnnotatedClass(FilmEntity.class);
                configuration.addAnnotatedClass(HallEntity.class);
                //configuration.addAnnotatedClass(OrderEntity.class);
                configuration.addAnnotatedClass(SessionEntity.class);
                configuration.addAnnotatedClass(UserEntity.class);

                //StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
