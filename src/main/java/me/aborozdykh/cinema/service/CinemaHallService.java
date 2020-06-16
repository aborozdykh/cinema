package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.CinemaHall;

public interface CinemaHallService extends AbstractService<CinemaHall> {
    List<CinemaHall> getAll();
}
