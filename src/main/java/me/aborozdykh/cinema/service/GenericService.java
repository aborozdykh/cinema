package me.aborozdykh.cinema.service;

public interface GenericService<T> {
    T add(T t);

    T get(Long id);

    void update(T t);
}
