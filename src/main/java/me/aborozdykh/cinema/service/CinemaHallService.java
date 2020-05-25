package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
