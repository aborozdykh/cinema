package me.aborozdykh.cinema.models.dto;

import me.aborozdykh.cinema.models.CinemaHall;

/**
 * @author Andrii Borozdykh
 */
public class CinemaHallResponseDto {
    private Long cinemaHallId;
    private int capacity;
    private String description;

    public CinemaHallResponseDto() {
    }

    public CinemaHallResponseDto(CinemaHall cinemaHall) {
        this.cinemaHallId = cinemaHall.getId();
        this.capacity = cinemaHall.getCapacity();
        this.description = cinemaHall.getDescription();
    }

    public Long getCinemaHallId() {
        return cinemaHallId;
    }

    public void setCinemaHallId(Long cinemaHallId) {
        this.cinemaHallId = cinemaHallId;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
