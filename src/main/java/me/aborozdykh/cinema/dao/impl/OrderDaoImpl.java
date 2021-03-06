package me.aborozdykh.cinema.dao.impl;

import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import me.aborozdykh.cinema.dao.OrderDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl extends AbstractDaoImpl<Order> implements OrderDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        try (Session session = sessionFactory.openSession()) {
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

    @Override
    public List<Order> getAll() {
        return super.getAll(Order.class);
    }

    @Override
    public Order get(Long id) {
        return super.get(Order.class, id);
    }
}
