package me.aborozdykh.cinema.models.dto;

import java.util.List;

/**
 * @author Andrii Borozdykh
 */
public class ShoppingCartResponseDto {
    private Long shoppingCartId;
    private List<Long> ticketsId;
    private Long userId;

    public ShoppingCartResponseDto() {
    }

    public Long getShoppingCartId() {
        return shoppingCartId;
    }

    public void setShoppingCartId(Long shoppingCartId) {
        this.shoppingCartId = shoppingCartId;
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
