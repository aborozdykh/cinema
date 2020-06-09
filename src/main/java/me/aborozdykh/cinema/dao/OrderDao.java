package me.aborozdykh.cinema.dao;

import java.util.List;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.User;

public interface OrderDao extends GenericDao<Order> {
    List<Order> getOrderHistory(User user);
}
