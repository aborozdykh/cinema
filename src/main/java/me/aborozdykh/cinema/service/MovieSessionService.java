package me.aborozdykh.cinema.service;

import me.aborozdykh.cinema.models.MovieSession;
import java.time.LocalDate;
import java.util.List;

public interface MovieSessionService {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);

    MovieSession add(MovieSession movieSession);
}
