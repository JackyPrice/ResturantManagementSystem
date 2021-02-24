package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.entity.DockerReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.repository.DockerReservationRespository;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Profile("docker")
@Service
@Slf4j
public class DockerReservationServiceImpl implements ReservationService{

    private final DockerReservationRespository dockerReservationRespository;
    private final IdGenerator idGenerator;

    public DockerReservationServiceImpl(DockerReservationRespository dockerReservationRespository, IdGenerator idGenerator) {
        this.dockerReservationRespository = dockerReservationRespository;
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

        DockerReservationEntity newEntity = DockerReservationEntity.from(reservation);
        DockerReservationEntity savedEntity = dockerReservationRespository.save(newEntity);
        return Reservation.from(savedEntity);
    }

    @Override
    public Reservation getReservation(String id) {
        DockerReservationEntity savedEntity = dockerReservationRespository.getById(id);
        return Reservation.from(savedEntity);
    }

    @Override
    public List<Reservation> getReservations() {
        return dockerReservationRespository.findAll()
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
        dockerReservationRespository.deleteById(id);
    }
}
