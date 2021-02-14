package com.jp.resturantmanagementsystem.repository;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import java.util.List;

public interface ReservationRepository {
    ReservationEntity save(ReservationEntity reservationEntity);
    ReservationEntity get(String id);
    List<Reservation> getAll();
    void deleteById(String id);
}
