package me.aborozdykh.cinema.models.mappers;

import me.aborozdykh.cinema.models.CinemaHall;
import me.aborozdykh.cinema.models.dto.CinemaHallRequestDto;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class CinemaHallMapper {
    public CinemaHall getCinemaHallFromDto(CinemaHallRequestDto cinemaHallRequestDto) {
        var cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }
}
