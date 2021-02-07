package com.JP.ResturantManagementSystem.service;

import com.JP.ResturantManagementSystem.model.Reservation;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation getReservation(String id);
    Reservation updateReservation(String id, Reservation reservation);
    void deleteReservation(String id);
}
