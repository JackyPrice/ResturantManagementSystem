package com.jp.resturantmanagementsystem.entity;

import com.jp.resturantmanagementsystem.model.Reservation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReservationEntity {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime reservationTime;
    private int numberOfGuests;

    public static ReservationEntity from(Reservation reservation){
       return ReservationEntity.builder()
                .id(reservation.getId())
                .firstName(reservation.getFirstName())
                .lastName(reservation.getLastName())
                .reservationTime(reservation.getReservationTime())
                .numberOfGuests(reservation.getNumberOfGuests())
                .build();
    }
}
