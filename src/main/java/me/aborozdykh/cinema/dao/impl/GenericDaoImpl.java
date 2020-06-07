package me.aborozdykh.cinema.dao.impl;

import me.aborozdykh.cinema.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Andrii Borozdykh created on 07.06.2020
 */
public abstract class GenericDaoImpl<T> {
    private final SessionFactory sessionFactory;

    protected GenericDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected T add(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add entity " + entity.getClass(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
