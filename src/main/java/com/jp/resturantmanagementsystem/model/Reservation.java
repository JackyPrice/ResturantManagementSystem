package com.jp.resturantmanagementsystem.model;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
// import com.jp.resturantmanagementsystem.entity.H2ReservationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime reservationTime;
    private int numberOfGuests;

        public static Reservation from(ReservationEntity dockerReservationEntity){
        return Reservation.builder()
                .id(dockerReservationEntity.getId())
                .firstName(dockerReservationEntity.getFirstName())
                .lastName(dockerReservationEntity.getLastName())
                .reservationTime(dockerReservationEntity.getReservationTime())
                .numberOfGuests(dockerReservationEntity.getNumberOfGuests())
                .build();
    }
}
