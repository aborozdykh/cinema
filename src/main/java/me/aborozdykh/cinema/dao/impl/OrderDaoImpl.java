package me.aborozdykh.cinema.dao.impl;

import me.aborozdykh.cinema.dao.OrderDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import me.aborozdykh.cinema.lib.Dao;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order add(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(order);
            transaction.commit();
            return order;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add order entity " + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void update(Order order) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(order);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't update order entity "
                    + order, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            var cb = session.getCriteriaBuilder();
            CriteriaQuery<Order> orderCriteriaQuery
                    = cb.createQuery(Order.class);
            Root<Order> orderRoot
                    = orderCriteriaQuery.from(Order.class);
            orderRoot.fetch("tickets", JoinType.LEFT);
            var predicateByUser = cb.equal(orderRoot.get("user"), user.getId());
            orderCriteriaQuery.where(predicateByUser);
            return session.createQuery(orderCriteriaQuery).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get order by user " + user, e);
        }
    }
}
