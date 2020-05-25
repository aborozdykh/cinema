package me.aborozdykh.cinema.service;

import java.time.LocalDate;
import java.util.List;
import me.aborozdykh.cinema.models.MovieSession;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);
}
