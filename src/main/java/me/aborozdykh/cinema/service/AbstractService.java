package me.aborozdykh.cinema.service;

public interface AbstractService<T> {
    T add(T t);

    T get(Long id);

    void update(T t);
}
