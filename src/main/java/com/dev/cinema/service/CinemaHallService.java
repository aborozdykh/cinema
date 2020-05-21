package com.dev.cinema.service;

import java.util.List;
import com.dev.cinema.models.CinemaHall;

public interface CinemaHallService {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
