package com.JP.ResturantManagementSystem.repository;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import java.util.List;

public interface ReservationRepository {
    ReservationEntity save(ReservationEntity reservationEntity);
    ReservationEntity get(String id);
    List<Reservation> getAll();
    void deleteById(String id);
}
