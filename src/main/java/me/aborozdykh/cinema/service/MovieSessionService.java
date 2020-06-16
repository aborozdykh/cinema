package me.aborozdykh.cinema.service;

import java.time.LocalDate;
import java.util.List;
import me.aborozdykh.cinema.models.MovieSession;

public interface MovieSessionService extends AbstractService<MovieSession> {
    List<MovieSession> findAvailableSessions(Long movieId, LocalDate date);
}
