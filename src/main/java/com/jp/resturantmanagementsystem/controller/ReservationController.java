package com.jp.resturantmanagementsystem.controller;

import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
