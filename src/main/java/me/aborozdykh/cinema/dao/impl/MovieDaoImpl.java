package me.aborozdykh.cinema.dao.impl;

import java.util.List;
import me.aborozdykh.cinema.dao.MovieDao;
import me.aborozdykh.cinema.models.Movie;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class MovieDaoImpl extends GenericDaoImpl<Movie> implements MovieDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public MovieDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Movie> getAll() {
        return super.getAll(Movie.class);
    }

    @Override
    public Movie get(Long id) {
        return super.get(Movie.class, id);
    }
}
