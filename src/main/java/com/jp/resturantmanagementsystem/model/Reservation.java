package com.jp.resturantmanagementsystem.model;

import com.jp.resturantmanagementsystem.entity.H2ReservationEntity;
import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
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
        public static Reservation from(H2ReservationEntity h2ReservationEntity){
        return Reservation.builder()
                .id(h2ReservationEntity.getId())
                .firstName(h2ReservationEntity.getFirstName())
                .lastName(h2ReservationEntity.getLastName())
                .reservationTime(h2ReservationEntity.getReservationTime())
                .numberOfGuests(h2ReservationEntity.getNumberOfGuests())
                .build();
    }
}
