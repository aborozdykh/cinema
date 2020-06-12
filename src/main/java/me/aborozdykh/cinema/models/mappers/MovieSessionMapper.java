package me.aborozdykh.cinema.models.mappers;

import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.models.dto.MovieSessionRequestDto;
import me.aborozdykh.cinema.models.dto.MovieSessionResponseDto;
import me.aborozdykh.cinema.service.CinemaHallService;
import me.aborozdykh.cinema.service.MovieService;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class MovieSessionMapper {
    private final CinemaHallService cinemaHallService;
    private final MovieService movieService;

    public MovieSessionMapper(CinemaHallService cinemaHallService, MovieService movieService) {
        this.cinemaHallService = cinemaHallService;
        this.movieService = movieService;
    }

    public MovieSession getMovieSessionFromDto(MovieSessionRequestDto movieSessionRequestDto) {
        var movieSession = new MovieSession();
        movieSession.setCinemaHall(cinemaHallService.get(movieSessionRequestDto.getCinemaHallId()));
        movieSession.setMovie(movieService.get(movieSessionRequestDto.getMovieId()));
        movieSession.setShowTime(movieSessionRequestDto.getShowTime());
        return movieSession;
    }

    public MovieSessionResponseDto getMovieSessionResponseDtoFromMovieSession(
            MovieSession movieSession) {
        if (movieSession == null) {
            return null;
        }
        var movieSessionResponseDto = new MovieSessionResponseDto();
        movieSessionResponseDto.setMovieSessionId(movieSession.getId());
        movieSessionResponseDto.setCinemaHallId(movieSession.getCinemaHall().getId());
        movieSessionResponseDto.setMovieId(movieSession.getMovie().getId());
        movieSessionResponseDto.setMovieTitle(movieSession.getMovie().getTitle());
        movieSessionResponseDto.setShowTime(movieSession.getShowTime());
        return movieSessionResponseDto;
    }
}
