package me.aborozdykh.cinema.controller;

import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.dto.OrderResponseDto;
import me.aborozdykh.cinema.models.dto.UserRequestDto;
import me.aborozdykh.cinema.models.mappers.OrderMapper;
import me.aborozdykh.cinema.service.OrderService;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    private final OrderMapper orderMapper;
    private final ShoppingCartService shoppingCartService;

    @Autowired
    public OrderController(OrderService orderService,
                           UserService userService,
                           OrderMapper orderMapper,
                           ShoppingCartService shoppingCartService) {
        this.orderService = orderService;
        this.userService = userService;
        this.orderMapper = orderMapper;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public List<OrderResponseDto> getOrderByUserId(@RequestParam Long userId) {
        return orderService.getOrderHistory(userService.get(userId))
                .stream()
                .map(orderMapper::getOrderResponseDtoFromOrder)
                .collect(Collectors.toList());
    }

    @PostMapping("/complete")
    public void completeOrder(@RequestBody UserRequestDto userRequestDto) {
        var user = userService.findByEmail(userRequestDto.getEmail());
        var tickets = shoppingCartService.getByUser(user).getTickets();
        if (!tickets.isEmpty()) {
            orderService.completeOrder(tickets, user);
        }
    }
}
