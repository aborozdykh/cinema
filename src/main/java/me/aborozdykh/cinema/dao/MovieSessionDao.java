package me.aborozdykh.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import me.aborozdykh.cinema.models.MovieSession;

public interface MovieSessionDao extends GenericDao<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    List<MovieSession> getAll();
}
