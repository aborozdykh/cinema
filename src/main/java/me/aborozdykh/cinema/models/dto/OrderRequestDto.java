package me.aborozdykh.cinema.models.dto;

import java.util.List;

/**
 * @author Andrii Borozdykh
 */
public class OrderRequestDto {
    private List<Long> ticketsId;
    private Long userId;

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
