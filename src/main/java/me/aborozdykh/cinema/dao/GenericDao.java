package me.aborozdykh.cinema.dao;

public interface GenericDao<T> {
    T add(T t);

    void update(T t);

    T get(Long id);
}
