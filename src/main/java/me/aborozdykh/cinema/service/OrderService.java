package me.aborozdykh.cinema.service;

import java.util.List;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.User;

public interface OrderService extends GenericService<Order> {
    Order completeOrder(List<Ticket> tickets, User user);

    List<Order> getOrderHistory(User user);
}
