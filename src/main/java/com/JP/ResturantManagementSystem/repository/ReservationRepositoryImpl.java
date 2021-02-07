package com.JP.ResturantManagementSystem.repository;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Reservation> reservationList = new ArrayList<>();
        for(ReservationEntity reservationEntity: reservations.values()) {
            reservationList.add(Reservation.from(reservationEntity));
        }
        return reservationList;
    }

    @Override
    public void deleteById(String id) {
        reservations.remove(id);
    }
}
