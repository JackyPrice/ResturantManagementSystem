package com.JP.ResturantManagementSystem.service;

import com.JP.ResturantManagementSystem.entity.H2ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.repository.H2ReservationRespository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.JP.ResturantManagementSystem.util.IdGenerator.createId;

@Profile("h2")
@Service
public class H2ReservationServiceImpl implements ReservationService{

    private H2ReservationRespository h2ReservationRespository;

    public H2ReservationServiceImpl(H2ReservationRespository h2ReservationRespository) {
        this.h2ReservationRespository = h2ReservationRespository;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        System.out.println("\n\n\nH2 Service");
        if(reservation.getId() == null ) {
            reservation = reservation.toBuilder()
                    .id(createId(reservation.getFirstName(), reservation.getLastName()))
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
