package me.aborozdykh.cinema.dao;

public interface GenericDao<T> {
    T add(T entity);

    void update(T entity);

    T get(Long id);
}
