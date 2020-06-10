package me.aborozdykh.cinema;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import me.aborozdykh.cinema.config.AppConfig;
import me.aborozdykh.cinema.exceptions.AuthenticationException;
import me.aborozdykh.cinema.models.CinemaHall;
import me.aborozdykh.cinema.models.Movie;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.security.AuthenticationService;
import me.aborozdykh.cinema.service.CinemaHallService;
import me.aborozdykh.cinema.service.MovieService;
import me.aborozdykh.cinema.service.MovieSessionService;
import me.aborozdykh.cinema.service.OrderService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) throws AuthenticationException {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var movieService = context.getBean(MovieService.class);
        movieService.getAll().forEach(System.out::println);

        // Add new Movie
        var movie = new Movie();
        movie.setTitle("Phantomas returns");
        movie.setDescription("The best movie about Phantomas");
        movieService.add(movie);
        movieService.getAll().forEach(System.out::println);

        var cinemaHallService = context.getBean(CinemaHallService.class);

        //Add a new CinemaHall - this is required because users will watch the movie in the cinema
        var cinemaHall = new CinemaHall();
        cinemaHall.setCapacity(100); // Only 100 users can sit in this hall
        cinemaHall = cinemaHallService.add(cinemaHall);

        //Add a new MovieSession. Users selects the movie and than selects the session.
        var movieSession = new MovieSession();
        movieSession.setMovie(movie);
        movieSession.setCinemaHall(cinemaHall);
        var dateTime = LocalDateTime.of(LocalDate.now(), LocalTime.of(19, 30));
        movieSession.setShowTime(dateTime);

        var movieSessionService = context.getBean(MovieSessionService.class);
        movieSessionService.add(movieSession);
        movieSessionService.findAvailableSessions(movie.getId(), LocalDate.now())
                .forEach(System.out::println);

        //Add a new User
        var authenticationService = context.getBean(AuthenticationService.class);
        var userService = context.getBean(UserService.class);
        var shoppingCartService = context.getBean(ShoppingCartService.class);

        var user = authenticationService.register("order@cinema.com.ua", "CoolPassword1_!");
        System.out.println("User findByEmail: " + userService.findByEmail("order@cinema.com.ua"));
        System.out.println("User findByEmail with wrong email: "
                + userService.findByEmail("test@gmail.com"));
        //Add ticket
        shoppingCartService.addSession(movieSession, user);
        var shoppingCart = shoppingCartService.getByUser(user);
        System.out.println(shoppingCart);

        //Try to login
        var userWithCorrectLoginAndPassword
                = authenticationService.login("order@cinema.com.ua", "CoolPassword1_!");
        System.out.println("User with correct login and password: "
                + userWithCorrectLoginAndPassword);

        //Add Order
        System.out.println("_____________________________________________________________");
        var orderService = context.getBean(OrderService.class);
        orderService.completeOrder(shoppingCart.getTickets(), shoppingCart.getUser());
        orderService.getOrderHistory(user).forEach(System.out::println);

//        context.close();
    }
}
