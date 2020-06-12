package me.aborozdykh.cinema.models.mappers;

import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.models.dto.MovieRequestDto;
import me.aborozdykh.cinema.models.dto.MovieResponseDto;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class MovieMapper {
    public Movie getMovieFromMovieRequestDto(MovieRequestDto movieRequestDto) {
        var movie = new Movie();
        movie.setTitle(movieRequestDto.getTitle());
        movie.setDescription(movieRequestDto.getDescription());
        return movie;
    }

    public MovieResponseDto getMovieResponseDtoFromMovie(Movie movie) {
        if (movie == null) {
            return null;
        }
        var movieResponseDto = new MovieResponseDto();
        movieResponseDto.setMovieId(movie.getId());
        movieResponseDto.setTitle(movie.getTitle());
        movieResponseDto.setDescription(movie.getDescription());
        return movieResponseDto;
    }
}
