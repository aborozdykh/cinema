package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.CinemaHall;
import me.aborozdykh.cinema.models.dto.CinemaHallRequestDto;
import me.aborozdykh.cinema.models.dto.CinemaHallResponseDto;
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
@RequestMapping("/cinemahalls")
public class CinemaHallController {
    private final CinemaHallService cinemaHallService;

    @Autowired
    public CinemaHallController(CinemaHallService cinemaHallService) {
        this.cinemaHallService = cinemaHallService;
    }

    @GetMapping("/inject")
    public String inject() {
        cinemaHallService.add(new CinemaHall(100, "Blue Ice"));
        cinemaHallService.add(new CinemaHall(200, "Yellow Warm"));
        cinemaHallService.add(new CinemaHall(300, "Red Hot"));
        return "Cinema halls was injected";
    }

    @GetMapping
    public List<CinemaHallResponseDto> getAllCinemaHalls() {
        return cinemaHallService.getAll().stream()
                .map(CinemaHallResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/add")
    public void addCinemaHall(@RequestBody CinemaHallRequestDto cinemaHallRequestDto) {
        cinemaHallService.add(getCinemaHallFromDto(cinemaHallRequestDto));
    }

    private CinemaHall getCinemaHallFromDto(CinemaHallRequestDto cinemaHallRequestDto) {
        var cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(cinemaHallRequestDto.getCapacity());
        cinemaHall.setDescription(cinemaHallRequestDto.getDescription());
        return cinemaHall;
    }
}
