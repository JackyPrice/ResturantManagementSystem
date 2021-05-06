package com.jp.resturantmanagementsystem.model;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
// import com.jp.resturantmanagementsystem.entity.H2ReservationEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//make sure that this is not the spring bean dependency
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

//TODO: answer the question: does this need to be tested?

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    private String id;

    @NotBlank(message="Required Field")
    private String firstName;

    @NotBlank(message="Required Field")
    @Size(min=3, message = "Last name must be at least 3 characters long")
    private String lastName;

    //TODO: validate this (can't make bookings in the past).
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
