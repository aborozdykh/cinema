package me.aborozdykh.cinema.service.impl;

import me.aborozdykh.cinema.dao.ShoppingCartDao;
import me.aborozdykh.cinema.dao.TicketDao;
import me.aborozdykh.cinema.models.MovieSession;
import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartDao shoppingCartDao;
    private final TicketDao ticketDao;

    @Autowired
    public ShoppingCartServiceImpl(ShoppingCartDao shoppingCartDao, TicketDao ticketDao) {
        this.shoppingCartDao = shoppingCartDao;
        this.ticketDao = ticketDao;
    }

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

    @Override
    public ShoppingCart add(ShoppingCart shoppingCart) {
        return shoppingCartDao.add(shoppingCart);
    }

    @Override
    public ShoppingCart get(Long id) {
        return shoppingCartDao.get(id);
    }

    @Override
    public void update(ShoppingCart shoppingCart) {
        shoppingCartDao.update(shoppingCart);
    }
}
