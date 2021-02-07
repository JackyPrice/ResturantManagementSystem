package com.JP.ResturantManagementSystem.entity;

import com.JP.ResturantManagementSystem.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class H2ReservationEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private LocalDateTime reservationTime;
    private int numberOfGuests;

    public static H2ReservationEntity from(Reservation reservation) {
        return H2ReservationEntity.builder()
                .id(reservation.getId())
                .firstName(reservation.getFirstName())
                .lastName(reservation.getLastName())
                .reservationTime(reservation.getReservationTime())
                .numberOfGuests(reservation.getNumberOfGuests())
                .build();
    }
}
