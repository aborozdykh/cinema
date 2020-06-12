package me.aborozdykh.cinema.models.dto;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Andrii Borozdykh
 */
public class OrderResponseDto {
    private Long orderId;
    private LocalDateTime orderDate;
    private List<Long> ticketsId;
    private Long userId;

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
