package me.aborozdykh.cinema.models.mappers;

import java.util.stream.Collectors;
import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.Ticket;
import me.aborozdykh.cinema.models.dto.ShoppingCartResponseDto;
import org.springframework.stereotype.Component;

/**
 * @author Andrii Borozdykh
 */
@Component
public class ShoppingCartMapper {
    public ShoppingCartResponseDto getShoppingCartResponseDtoFromShoppingCart(
            ShoppingCart shoppingCart) {
        var shoppingCartResponseDto = new ShoppingCartResponseDto();
        shoppingCartResponseDto.setUserId(shoppingCart.getUser().getId());
        shoppingCartResponseDto.setShoppingCartId(shoppingCart.getId());
        shoppingCartResponseDto.setTicketsId(shoppingCart.getTickets()
                .stream()
                .map(Ticket::getId)
                .collect(Collectors.toList()));
        return shoppingCartResponseDto;
    }
}
