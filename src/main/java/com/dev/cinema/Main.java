package com.dev.cinema;

import com.dev.cinema.lib.Injector;
import com.dev.cinema.models.Movie;
import com.dev.cinema.service.MovieService;

public class Main {
    private static Injector injector = Injector.getInstance("com.dev.cinema");

    public static void main(String[] args) {
        MovieService movieService = (MovieService) injector.getInstance(MovieService.class);
        movieService.getAll().forEach(System.out::println);

        // Add new Movie
        Movie movie = new Movie();
        movie.setTitle("Phantomas returns");
        movie.setDescription("The best movie about Phantomas");
        movieService.add(movie);

        Ci


        movieService.getAll().forEach(System.out::println);
    }
}
