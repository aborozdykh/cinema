package me.aborozdykh.cinema.dao.impl;

import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import me.aborozdykh.cinema.dao.UserDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import me.aborozdykh.cinema.models.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (var session = sessionFactory.openSession()) {
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

    @Override
    public List<User> getAll() {
        return super.getAll(User.class);
    }

    @Override
    public User get(Long id) {
        return super.get(User.class, id);
    }
}
