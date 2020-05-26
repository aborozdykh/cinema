package me.aborozdykh.cinema.dao;

import java.util.List;
import me.aborozdykh.cinema.models.CinemaHall;

public interface CinemaHallDao {
    CinemaHall add(CinemaHall cinemaHall);

    List<CinemaHall> getAll();
}