package com.jp.resturantmanagementsystem.repository;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
@NoArgsConstructor
public class ReservationRepositoryImpl implements ReservationRepository{

    private final Map<String, ReservationEntity> reservations = new HashMap<String, ReservationEntity>();

    @Override
    public ReservationEntity save(ReservationEntity reservationEntity) {
        reservations.put(reservationEntity.getId(), reservationEntity);
        return reservationEntity;
    }

    @Override
    public ReservationEntity get(String id) {
        return reservations.get(id);
    }

    @Override
    public List<Reservation> getAll() {
       return reservations.values().stream()
                .map(reservationEntity -> Reservation.from(reservationEntity))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(String id) {
        reservations.remove(id);
    }
}
