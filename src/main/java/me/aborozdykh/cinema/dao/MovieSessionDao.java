package me.aborozdykh.cinema.dao;

import java.time.LocalDate;
import java.util.List;
import me.aborozdykh.cinema.models.MovieSession;

public interface MovieSessionDao {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);
}
