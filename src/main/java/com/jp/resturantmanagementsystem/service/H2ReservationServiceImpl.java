package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.entity.H2ReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.repository.H2ReservationRespository;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Profile("h2")
@Service
@Slf4j
public class H2ReservationServiceImpl implements ReservationService{

    private H2ReservationRespository h2ReservationRespository;
    private IdGenerator idGenerator;

    public H2ReservationServiceImpl(H2ReservationRespository h2ReservationRespository, IdGenerator idGenerator) {
        this.h2ReservationRespository = h2ReservationRespository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        log.info("H2 Service is in effect");
        if(reservation.getId() == null ) {
            reservation = reservation.toBuilder()
                    .id(idGenerator.createId(reservation.getFirstName(), reservation.getLastName()))
                    .build();
        }

        H2ReservationEntity newEntity = H2ReservationEntity.from(reservation);
        H2ReservationEntity savedEntity = h2ReservationRespository.save(newEntity);
        return Reservation.from(savedEntity);
    }

    @Override
    public Reservation getReservation(String id) {
        H2ReservationEntity savedEntity = h2ReservationRespository.getById(id);
        return Reservation.from(savedEntity);
    }

    @Override
    public List<Reservation> getReservations() {
        return h2ReservationRespository.findAll()
                .stream().map(h2ReservationEntity -> Reservation.from(h2ReservationEntity))
                .collect(Collectors.toList());
    }

    @Override
    public Reservation updateReservation(String id, Reservation reservation) {
        Reservation updateReservation = reservation.toBuilder().id(id).build();
        return createReservation(updateReservation);
    }

    @Override
    public void deleteReservation(String id) {
        h2ReservationRespository.deleteById(id);
    }
}
