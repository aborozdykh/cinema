package me.aborozdykh.cinema.models.dto;

import me.aborozdykh.cinema.models.Movie;

/**
 * @author Andrii Borozdykh
 */
public class MovieRequestDto {
    private String title;
    private String description;

    public MovieRequestDto() {
    }

    public MovieRequestDto(Movie movie) {
        this.title = movie.getTitle();
        this.description = movie.getDescription();
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
