package me.aborozdykh.cinema.dao;

import me.aborozdykh.cinema.models.CinemaHall;
import java.util.List;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}
