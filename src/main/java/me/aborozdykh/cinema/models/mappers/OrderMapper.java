package me.aborozdykh.cinema.models.mappers;

import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.dto.OrderRequestDto;
import me.aborozdykh.cinema.models.dto.OrderResponseDto;
import me.aborozdykh.cinema.service.ShoppingCartService;
import me.aborozdykh.cinema.service.UserService;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class OrderMapper {
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;

    public OrderMapper(UserService userService, ShoppingCartService shoppingCartService) {
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
    }

    public Order getOrderFromOrderRequestDto(OrderRequestDto orderRequestDto) {
        var order = new Order();
        var user = userService.get(orderRequestDto.getUserId());
        var tickets = shoppingCartService.getByUser(user).getTickets();
        order.setUser(user);
        order.setTickets(tickets);
        return order;
    }

    public OrderResponseDto getOrderResponseDtoFromOrder(Order order) {
        if (order == null) {
            return null;
        }
        var orderResponseDto = new OrderResponseDto();
        orderResponseDto.setOrderId(order.getId());
        orderResponseDto.setOrderDate(order.getOrderDate());
        orderResponseDto.setUserId(order.getUser().getId());
        orderResponseDto.setTicketsId(order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return orderResponseDto;
    }
}
