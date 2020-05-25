package me.aborozdykh.cinema.service;

import me.aborozdykh.cinema.models.CinemaHall;
import java.util.List;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
