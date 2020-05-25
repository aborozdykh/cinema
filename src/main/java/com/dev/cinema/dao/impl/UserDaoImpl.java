package com.dev.cinema.dao.impl;

import com.dev.cinema.dao.UserDao;
import com.dev.cinema.exceptions.DataProcessingException;
import com.dev.cinema.lib.Dao;
import com.dev.cinema.models.User;
import com.dev.cinema.util.HibernateUtil;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public Optional<User> add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            var userId = (Long) session.save(user);
            transaction.commit();
            user.setId(userId);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add user entity with login "
                    + user.getEmail(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (var session = HibernateUtil.getSessionFactory().openSession()) {
            var cb = session.getCriteriaBuilder();
            CriteriaQuery<User> query = cb.createQuery(User.class);
            Root<User> userRoot = query.from(User.class);
            query.select(userRoot)
                    .where(cb.like(userRoot.get("email"), email));
            return Optional.of(session.createQuery(query).getSingleResult());
        } catch (Exception e) {
            throw new DataProcessingException("Can't find user with login " + email, e);
        }
    }
}
