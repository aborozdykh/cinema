package me.aborozdykh.cinema.dao;

import me.aborozdykh.cinema.models.ShoppingCart;
import me.aborozdykh.cinema.models.User;

public interface ShoppingCartDao extends AbstractDao<ShoppingCart> {
    ShoppingCart getByUser(User user);
}
