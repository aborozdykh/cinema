package me.aborozdykh.cinema.dto;

import me.aborozdykh.cinema.models.Movie;

/**
 * @author Andrii Borozdykh
 */
public class MovieDto {
    private String title;
    private String description;

    public MovieDto(Movie movie) {
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
