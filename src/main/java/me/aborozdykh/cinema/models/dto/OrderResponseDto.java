package me.aborozdykh.cinema.models.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.Order;
import me.aborozdykh.cinema.models.Ticket;

/**
 * @author Andrii Borozdykh
 */
public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private List<Long> ticketsId;
    private Long userId;

    public OrderResponseDto() {
    }

    public OrderResponseDto(Order order) {
        this.orderId = order.getId();
        this.orderDate = order.getOrderDate();
        this.ticketsId = order.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList());
        this.userId = order.getUser().getId();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public List<Long> getTicketsId() {
        return ticketsId;
    }

    public void setTicketsId(List<Long> ticketsId) {
        this.ticketsId = ticketsId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
