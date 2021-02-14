package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.repository.ReservationRepository;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;


@Profile("map")
@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final IdGenerator idGenerator;

    public ReservationServiceImpl(ReservationRepository reservationRepository, IdGenerator idGenerator) {
        this.reservationRepository = reservationRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        log.info("Map Service is in effect");
        if(reservation.getId() == null ) {
            reservation = reservation.toBuilder()
                    .id(idGenerator.createId(reservation.getFirstName(), reservation.getLastName()))
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
