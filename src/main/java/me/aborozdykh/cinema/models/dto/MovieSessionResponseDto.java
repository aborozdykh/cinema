package me.aborozdykh.cinema.models.dto;

import java.time.LocalDateTime;
import me.aborozdykh.cinema.models.MovieSession;

/**
 * @author Andrii Borozdykh
 */
public class MovieSessionResponseDto {
    private Long movieSessionId;
    private Long movieId;
    private String movieTitle;
    private Long cinemaHallId;
    private LocalDateTime showTime;

    public MovieSessionResponseDto() {
    }

    public MovieSessionResponseDto(MovieSession movieSession){
        this.movieSessionId = movieSession.getId();
        this.movieId = movieSession.getMovie().getId();
        this.movieTitle = movieSession.getMovie().getTitle();
        this.cinemaHallId = movieSession.getCinemaHall().getId();
        this.showTime = movieSession.getShowTime();
    }

    public Long getMovieSessionId() {
        return movieSessionId;
    }

    public void setMovieSessionId(Long movieSessionId) {
        this.movieSessionId = movieSessionId;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
