package me.aborozdykh.cinema.dao.impl;

import me.aborozdykh.cinema.lib.Dao;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.util.HibernateUtil;
import me.aborozdykh.cinema.dao.MovieSessionDao;
import me.aborozdykh.cinema.exceptions.DataProcessingException;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

@Dao
public class MovieSessionDaoImpl implements MovieSessionDao {
    @Override
    public List<MovieSession> findAvailableSessions(Long movieId, LocalDate date) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
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
    public MovieSession add(MovieSession movieSession) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(movieSession);
            transaction.commit();
            return movieSession;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add MovieSession entity", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
