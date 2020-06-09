package me.aborozdykh.cinema.dao.impl;

import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import me.aborozdykh.cinema.dao.ShoppingCartDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingCartDaoImpl extends GenericDaoImpl<ShoppingCart> implements ShoppingCartDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public ShoppingCartDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ShoppingCart getByUser(User user) {
        try (Session session = sessionFactory.openSession()) {
            var cb = session.getCriteriaBuilder();
            CriteriaQuery<ShoppingCart> shoppingCartCriteriaQuery
                    = cb.createQuery(ShoppingCart.class);
            Root<ShoppingCart> shoppingCartRoot
                    = shoppingCartCriteriaQuery.from(ShoppingCart.class);
            shoppingCartRoot.fetch("tickets", JoinType.LEFT);
            var predicateByUser = cb.equal(shoppingCartRoot.get("user"), user.getId());
            shoppingCartCriteriaQuery.where(predicateByUser);
            return session.createQuery(shoppingCartCriteriaQuery).uniqueResult();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get shoppingCart by user " + user, e);
        }
    }
}
