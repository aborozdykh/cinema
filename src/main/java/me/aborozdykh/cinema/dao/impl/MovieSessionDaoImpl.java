package me.aborozdykh.cinema.dao.impl;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import me.aborozdykh.cinema.dao.MovieSessionDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import me.aborozdykh.cinema.models.MovieSession;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieSessionDaoImpl extends GenericDaoImpl<MovieSession> implements MovieSessionDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieSessionDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = sessionFactory.openSession()) {
            var cb = session.getCriteriaBuilder();
            CriteriaQuery<MovieSession> q = cb.createQuery(MovieSession.class);
            Root<MovieSession> root = q.from(MovieSession.class);
            var predicateForMovieId
                    = cb.equal(root.get("movie"), movieId);
            var dateTime = date.atStartOfDay();
            var predicateForDate
                    = cb.between(root.get("showTime"), dateTime, dateTime.plusDays(1));
            var finalPredicate = cb.or(predicateForMovieId, predicateForDate);
            q.where(finalPredicate);
            return session.createQuery(q).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find available sessions", e);
        }
    }

    @Override
    public List<MovieSession> getAll() {
        return super.getAll(MovieSession.class);
    }

    @Override
    public MovieSession get(Long id) {
        return super.get(MovieSession.class, id);
    }
}
