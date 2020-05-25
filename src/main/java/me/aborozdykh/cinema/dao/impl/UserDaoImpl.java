package me.aborozdykh.cinema.dao.impl;

import me.aborozdykh.cinema.dao.UserDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import me.aborozdykh.cinema.lib.Dao;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.util.HibernateUtil;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class UserDaoImpl implements UserDao {
    @Override
    public User add(User user) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return user;
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
            return session.createQuery(query).uniqueResultOptional();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find user with login " + email, e);
        }
    }
}
