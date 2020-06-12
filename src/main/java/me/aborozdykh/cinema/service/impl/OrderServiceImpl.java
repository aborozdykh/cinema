package me.aborozdykh.cinema.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import me.aborozdykh.cinema.dao.OrderDao;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.User;
import me.aborozdykh.cinema.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order completeOrder(List<Ticket> tickets, User user) {
        var order = new Order();
        order.setUser(user);
        order.setTickets(tickets);
        order.setOrderDate(LocalDateTime.now());
        return orderDao.add(order);
    }

    @Override
    public List<Order> getOrderHistory(User user) {
        return orderDao.getOrderHistory(user);
    }

    @Override
    public Order add(Order order) {
        return orderDao.add(order);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public void update(Order order) {
        orderDao.update(order);
    }
}
