package com.JP.ResturantManagementSystem.controller;

import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService h2ReservationService) {
        this.reservationService = h2ReservationService;
    }

    @PostMapping("/reservation")
    public Reservation createReservation(@RequestBody Reservation reservation) {
        log.info(reservation.toString());
        return reservationService.createReservation(reservation);
    }

    @GetMapping("/reservation")
    public Reservation getReservation(String id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/reservations")
    public List<Reservation> getReservations() {
        return reservationService.getReservations();
    }

    @PutMapping("/reservation/{id}")
    public Reservation updateReservation(@PathVariable String id, @RequestBody Reservation reservation){
        return reservationService.updateReservation(id, reservation);
    }

    @DeleteMapping("/reservation/{id}")
    public void deleteReservation(@PathVariable String id){
        reservationService.deleteReservation(id);
    }
}
