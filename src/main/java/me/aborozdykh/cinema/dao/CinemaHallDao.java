package me.aborozdykh.cinema.dao;

import java.util.List;
import me.aborozdykh.cinema.models.CinemaHall;

public interface CinemaHallDao extends AbstractDao<CinemaHall> {
    List<CinemaHall> getAll();

    CinemaHall get(Long id);
}
