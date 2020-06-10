package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.CinemaHall;

public interface CinemaHallService extends GenericService<CinemaHall> {
    List<CinemaHall> getAll();
}
