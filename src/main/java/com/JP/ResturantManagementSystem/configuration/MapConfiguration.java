package com.JP.ResturantManagementSystem.configuration;

import com.JP.ResturantManagementSystem.repository.ReservationRepository;
import com.JP.ResturantManagementSystem.service.ReservationService;
import com.JP.ResturantManagementSystem.service.ReservationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("map")
@Configuration
public class MapConfiguration {

    private final ReservationRepository reservationRepository;

    public MapConfiguration(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Bean
    public ReservationService reservationService() {
        return new ReservationServiceImpl(reservationRepository);
    }
}
