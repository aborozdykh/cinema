package me.aborozdykh.cinema.dao;

import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.User;

public interface ShoppingCartDao {
    ShoppingCart add(ShoppingCart shoppingCart);

    ShoppingCart getByUser(User user);

    void update(ShoppingCart shoppingCart);
}
