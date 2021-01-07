package com.JP.ResturantManagementSystem.service;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import static com.JP.ResturantManagementSystem.util.IdGenerator.createId;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImpl(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Reservation createReservation(Reservation reservation) {
        ReservationEntity entity = convertToEntity(reservation);
        return convertToModel(reservationRepository.save(entity));
    }

    @Override
    public Reservation getReservation(String id) {
        return convertToModel(reservationRepository.get(id));
    }

    private ReservationEntity convertToEntity(Reservation reservation) {
        return ReservationEntity.builder()
                .id(createId(reservation.getFirstName(), reservation.getLastName()))
                .firstName(reservation.getFirstName())
                .lastName(reservation.getLastName())
                .reservationTime(reservation.getReservationTime())
                .numberOfGuests(reservation.getNumberOfGuests())
                .build();
    }

    private Reservation convertToModel(ReservationEntity reservationEntity) {
        return Reservation.builder()
                .id(reservationEntity.getId())
                .firstName(reservationEntity.getFirstName())
                .lastName(reservationEntity.getLastName())
                .reservationTime(reservationEntity.getReservationTime())
                .numberOfGuests(reservationEntity.getNumberOfGuests())
                .build();
    }

//TODO: look up MapStruct for model for conversions
//TODO: from and to entity conversion
}
