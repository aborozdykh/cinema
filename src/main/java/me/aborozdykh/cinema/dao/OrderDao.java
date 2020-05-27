package me.aborozdykh.cinema.dao;

import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.User;
import java.util.List;

public interface OrderDao {
    Order add(Order order);

    void update(Order order);

    List<Order> getOrderHistory(User user);
}
