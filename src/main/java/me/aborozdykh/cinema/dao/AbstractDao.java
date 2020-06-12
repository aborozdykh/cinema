package me.aborozdykh.cinema.dao;

public interface AbstractDao<T> {
    T add(T entity);

    void update(T entity);

    T get(Long id);
}
