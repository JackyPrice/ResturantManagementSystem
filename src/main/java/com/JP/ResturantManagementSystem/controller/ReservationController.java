package com.JP.ResturantManagementSystem.controller;

import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/reservation")
    public Reservation createReservation(String id) {
        return reservationService.getReservation(id);
    }

    @PutMapping("/reservation/{id}")
    public Reservation updateReservation(@PathVariable String id, @RequestBody Reservation reservation){
        return reservationService.updateReservation(id, reservation);
    }
}
