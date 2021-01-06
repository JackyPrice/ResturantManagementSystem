package com.JP.ResturantManagementSystem.controller;

import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/reservation")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        log.info(reservation.toString());
        return reservationService.createReservation(reservation);
    }
}
