package me.aborozdykh.cinema.dao;

import java.util.List;

public interface GenericDao<T> {
    T add(T t);

    void update(T t);

    List<T> getAll(Class clazz);
}
