package com.JP.ResturantManagementSystem.service;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.repository.ReservationRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.JP.ResturantManagementSystem.util.IdGenerator.createId;

@Profile("map")
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
    public List<Reservation> getReservations() {
        return reservationRepository.getAll();
    }

    @Override
    public Reservation updateReservation(String id, Reservation reservation) {
        Reservation updateReservation = reservation.toBuilder().id(id).build();
        return createReservation(updateReservation);
    }

    @Override
    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }

//TODO: look up MapStruct for model for conversions
}
