package me.aborozdykh.cinema.dao;

import java.util.List;
import me.aborozdykh.cinema.models.CinemaHall;

public interface CinemaHallDao extends GenericDao<CinemaHall> {
    List<CinemaHall> getAll();
}
