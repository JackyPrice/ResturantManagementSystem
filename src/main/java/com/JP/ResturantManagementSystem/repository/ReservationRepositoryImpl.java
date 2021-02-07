package com.JP.ResturantManagementSystem.repository;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
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
    public void deleteById(String id) {
        reservations.remove(id);
    }
}
