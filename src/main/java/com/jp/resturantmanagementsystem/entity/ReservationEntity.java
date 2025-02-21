package com.jp.resturantmanagementsystem.entity;

import com.jp.resturantmanagementsystem.model.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
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
public class ReservationEntity {

    @Id
    private String id;
    @Column(name= "first_name")
    private String firstName;
    @Column(name= "last_name")
    private String lastName;
    @Column(name= "reservation_time")
    private LocalDateTime reservationTime;
    @Column(name= "number_of_guests")
    private int numberOfGuests;

    public static ReservationEntity from(Reservation reservation) {
        return ReservationEntity.builder()
                .id(reservation.getId())
                .firstName(reservation.getFirstName())
                .lastName(reservation.getLastName())
                .reservationTime(reservation.getReservationTime())
                .numberOfGuests(reservation.getNumberOfGuests())
                .build();
    }
}
