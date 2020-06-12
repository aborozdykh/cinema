package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.dto.CinemaHallRequestDto;
import me.aborozdykh.cinema.models.dto.CinemaHallResponseDto;
import me.aborozdykh.cinema.models.mappers.CinemaHallMapper;
import me.aborozdykh.cinema.service.CinemaHallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/cinema-halls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;
    private final CinemaHallMapper cinemaHallMapper;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService,
                                CinemaHallMapper cinemaHallMapper) {
        this.cinemaHallService = cinemaHallService;
        this.cinemaHallMapper = cinemaHallMapper;
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(CinemaHallResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public void addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(cinemaHallMapper.getCinemaHallFromDto(cinemaHallRequestDto));
    }
}
