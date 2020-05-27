package me.aborozdykh.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import me.aborozdykh.cinema.dao.OrderDao;
import me.aborozdykh.cinema.lib.Inject;
import me.aborozdykh.cinema.lib.Service;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    private OrderDao orderDao;

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        var order = new Order();
        order.setUser(user);
        order.setTickets(tickets);
        order.setOrderDate(LocalDateTime.now());
        var orderFromDb = orderDao.add(order);
        return orderFromDb;
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }
}
