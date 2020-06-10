package me.aborozdykh.cinema.models.dto;

import me.aborozdykh.cinema.models.Movie;

/**
 * @author Andrii Borozdykh
 */
public class MovieResponseDto {
    private Long movieId;
    private String title;
    private String description;

    public MovieResponseDto() {
    }

    public MovieResponseDto(Movie movie) {
        this.movieId = movie.getId();
        this.title = movie.getTitle();
        this.description = movie.getDescription();
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
