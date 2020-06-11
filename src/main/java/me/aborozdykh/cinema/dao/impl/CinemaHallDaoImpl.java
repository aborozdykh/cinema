package me.aborozdykh.cinema.dao.impl;

import java.util.List;
import me.aborozdykh.cinema.dao.CinemaHallDao;
import me.aborozdykh.cinema.models.CinemaHall;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CinemaHallDaoImpl extends AbstractDaoImpl<CinemaHall> implements CinemaHallDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public CinemaHallDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<CinemaHall> getAll() {
        return super.getAll(CinemaHall.class);
    }

    @Override
    public CinemaHall get(Long id) {
        return super.get(CinemaHall.class, id);
    }
}
