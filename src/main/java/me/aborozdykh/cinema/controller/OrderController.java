package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.dto.OrderRequestDto;
import me.aborozdykh.cinema.models.dto.OrderResponseDto;
import me.aborozdykh.cinema.service.OrderService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Andrii Borozdykh
 */
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderController(OrderService orderService,
                           UserService userService,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<OrderResponseDto> getOrderByUserId(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.get(userId))
                .stream()
                .map(OrderResponseDto::new)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(OrderRequestDto orderRequestDto) {
        orderService.add(getOrderFromDto(orderRequestDto));
    }

    private Order getOrderFromDto(OrderRequestDto orderRequestDto) {
        var order = new Order();
        var user = userService.get(orderRequestDto.getUserId());
        order.setOrderDate(orderRequestDto.getOrderDate());
        order.setUser(user);
        order.setTickets(shoppingCartService.getByUser(user).getTickets());
        return order;
    }
}
