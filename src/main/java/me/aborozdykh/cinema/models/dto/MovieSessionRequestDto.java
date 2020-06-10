package me.aborozdykh.cinema.models.dto;

import java.time.LocalDateTime;
import me.aborozdykh.cinema.models.MovieSession;

/**
 * @author Andrii Borozdykh
 */
public class MovieSessionRequestDto {
    private Long movieId;
    private Long cinemaHallId;
    private LocalDateTime showTime;

    public MovieSessionRequestDto() {
    }

    public MovieSessionRequestDto(MovieSession movieSession) {
        this.movieId = movieSession.getMovie().getId();
        this.showTime = movieSession.getShowTime();
        this.cinemaHallId = movieSession.getCinemaHall().getId();
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }
}
