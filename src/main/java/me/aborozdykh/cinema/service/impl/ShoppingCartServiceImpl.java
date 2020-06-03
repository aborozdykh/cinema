package me.aborozdykh.cinema.service.impl;

import me.aborozdykh.cinema.dao.ShoppingCartDao;
import me.aborozdykh.cinema.dao.TicketDao;
import me.aborozdykh.cinema.lib.Inject;
import me.aborozdykh.cinema.lib.Service;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.service.ShoppingCartService;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Inject
    private ShoppingCartDao shoppingCartDao;

    @Inject
    private TicketDao ticketDao;

    @Override
    public void addSession(MovieSession movieSession, User user) {
        var ticket = new Ticket();
        ticket.setMovieSession(movieSession);
        ticket.setUser(user);
        var usersShoppingCart = shoppingCartDao.getByUser(user);
        usersShoppingCart.getTickets().add(ticket);
        ticketDao.add(ticket);
        shoppingCartDao.update(usersShoppingCart);
    }

    @Override
    public ShoppingCart getByUser(User user) {
        return shoppingCartDao.getByUser(user);
    }

    @Override
    public void registerNewShoppingCart(User user) {
        var shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartDao.add(shoppingCart);
    }

    @Override
    public void clear(ShoppingCart shoppingCart) {
        shoppingCart.getTickets().removeAll(shoppingCart.getTickets());
        shoppingCartDao.update(shoppingCart);
    }
}
