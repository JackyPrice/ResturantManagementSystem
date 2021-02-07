package com.JP.ResturantManagementSystem.service;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ThreadLocalRandom;


@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        if(reservation.getId() == null ) {
           reservation = reservation.toBuilder()
                   .id(createId(reservation.getFirstName(), reservation.getLastName()))
                   .build();
        }

        ReservationEntity newEntity = ReservationEntity.from(reservation);
        ReservationEntity savedEntity = reservationRepository.save(newEntity);
        return Reservation.from(savedEntity);
    }

    @Override
    public Reservation getReservation(String id) {
        ReservationEntity savedEntity = reservationRepository.get(id);
        return Reservation.from(savedEntity);
    }

    @Override
    public Reservation updateReservation(String id, Reservation reservation) {
        Reservation updateReservation = reservation.toBuilder().id(id).build();
        return createReservation(updateReservation);
    }

    public String createId(String firstName, String lastName) {
        int randomNum = ThreadLocalRandom.current().nextInt(10000, 99999 + 1);
        return firstName.substring(0, 3) + lastName.substring(lastName.length() - 3) + randomNum;
    }

//TODO: look up MapStruct for model for conversions
}
