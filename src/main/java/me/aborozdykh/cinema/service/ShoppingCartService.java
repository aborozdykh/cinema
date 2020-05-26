package me.aborozdykh.cinema.service;

import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.User;

public interface ShoppingCartService {
    /**
     * This method is responsible to add a Ticket to the ShoppingCart
     * @param movieSession Contains the information required for a ticket
     * @param user - user who wan't to buy a ticket for a specific MovieSession
     */
    void addSession(MovieSession movieSession, User user);

    ShoppingCart getByUser(User user);

    void registerNewShoppingCart(User user);
}
