package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.exception.InvalidReservationException;
import com.jp.resturantmanagementsystem.exception.InvalidReservationIdException;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.repository.ReservationRespository;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRespository reservationRespository;
    private final IdGenerator idGenerator;

    public ReservationServiceImpl(ReservationRespository reservationRespository, IdGenerator idGenerator) {
        this.reservationRespository = reservationRespository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Reservation createReservation(Reservation reservation) {
        return saveReservation(validatedReservation(reservation));
    }

    @Override
    public Reservation getReservation(String id) {
        validateId(id);
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
        return saveReservation(updateReservation);
    }

    @Override
    public void deleteReservation(String id) {
        reservationRespository.deleteById(id);
    }

    private Reservation saveReservation(Reservation reservation) {
        ReservationEntity newEntity = ReservationEntity.from(reservation);
        ReservationEntity savedEntity = reservationRespository.save(newEntity);
        return Reservation.from(savedEntity);
    }

    private Reservation validatedReservation(Reservation reservation) {
        if (reservation.getId() == null) {
            reservation = reservation.toBuilder()
                    .id(idGenerator.createId(reservation.getFirstName(), reservation.getLastName()))
                    .build();
            return reservation;
        } else {
            throw new InvalidReservationException("Reservation id must be null at time of creation");
        }
    }

    private void validateId(String id) {
        checkIdLength(id);
        checkForSpecialCharacters(id);
    }

    private void checkIdLength(String id) {
        if (id.length() != 11) {
            throw new InvalidReservationIdException("Reservation Id is of invalid length");
        }
    }

    private void checkForSpecialCharacters(String id) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(id);
        if (m.find()) {
            throw new InvalidReservationIdException("Reservation Id can not contain special characters");
        }
    }
}