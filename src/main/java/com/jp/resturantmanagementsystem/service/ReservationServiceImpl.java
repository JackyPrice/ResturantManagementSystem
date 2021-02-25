package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.repository.ReservationRespository;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRespository reservationRespository;
    private final IdGenerator idGenerator;

    public ReservationServiceImpl(ReservationRespository reservationRespository, IdGenerator idGenerator) {
        this.reservationRespository = reservationRespository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        log.info("Docker Service is in effect");
        if(reservation.getId() == null ) {
            reservation = reservation.toBuilder()
                    .id(idGenerator.createId(reservation.getFirstName(), reservation.getLastName()))
                    .build();
        }

        ReservationEntity newEntity = ReservationEntity.from(reservation);
        ReservationEntity savedEntity = reservationRespository.save(newEntity);
        return Reservation.from(savedEntity);
    }

    @Override
    public Reservation getReservation(String id) {
        ReservationEntity savedEntity = reservationRespository.getById(id);
        return Reservation.from(savedEntity);
    }

    @Override
    public List<Reservation> getReservations() {
        return reservationRespository.findAll()
                .stream().map(Reservation::from)
                .collect(Collectors.toList());
    }

    @Override
    public Reservation updateReservation(String id, Reservation reservation) {
        Reservation updateReservation = reservation.toBuilder().id(id).build();
        return createReservation(updateReservation);
    }

    @Override
    public void deleteReservation(String id) {
        reservationRespository.deleteById(id);
    }
}
