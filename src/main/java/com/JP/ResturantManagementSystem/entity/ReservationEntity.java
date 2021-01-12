package com.JP.ResturantManagementSystem.entity;

import com.JP.ResturantManagementSystem.model.Reservation;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

import static com.JP.ResturantManagementSystem.util.IdGenerator.createId;

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
                .id(createId(reservation.getFirstName(), reservation.getLastName()))
                .firstName(reservation.getFirstName())
                .lastName(reservation.getLastName())
                .reservationTime(reservation.getReservationTime())
                .numberOfGuests(reservation.getNumberOfGuests())
                .build();
    }

}
