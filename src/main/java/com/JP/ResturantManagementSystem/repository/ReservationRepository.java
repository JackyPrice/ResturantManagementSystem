package com.JP.ResturantManagementSystem.repository;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;

public interface ReservationRepository {
    ReservationEntity save(ReservationEntity reservationEntity);
    ReservationEntity get(String id);
}
