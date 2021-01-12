package com.JP.ResturantManagementSystem.model;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Reservation {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime reservationTime;
    private int numberOfGuests;

    public static Reservation from(ReservationEntity reservationEntity){
        return Reservation.builder()
                .id(reservationEntity.getId())
                .firstName(reservationEntity.getFirstName())
                .lastName(reservationEntity.getLastName())
                .reservationTime(reservationEntity.getReservationTime())
                .numberOfGuests(reservationEntity.getNumberOfGuests())
                .build();
    }
}
