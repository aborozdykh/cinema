package me.aborozdykh.cinema.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import me.aborozdykh.cinema.dao.GenericDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * @author Andrii Borozdykh
 */
public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    private final SessionFactory sessionFactory;

    protected GenericDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public T add(T entity) {
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

    public void update(T entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update entity "
                    + entity.getClass().getName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<T> getAll(Class clazz) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<T> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(clazz);
            criteriaQuery.from(clazz);
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get list of all elements "
                    + clazz.getName(), e);
        }
    }
}
