package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.model.Reservation;
import java.util.List;

public interface ReservationService {
    Reservation createReservation(Reservation reservation);
    Reservation getReservation(String id);
    List<Reservation> getReservations();
    Reservation updateReservation(String id, Reservation reservation);
    void deleteReservation(String id);
}
