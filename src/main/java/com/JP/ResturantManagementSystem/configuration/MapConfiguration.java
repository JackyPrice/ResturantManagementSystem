package com.JP.ResturantManagementSystem.configuration;

import com.JP.ResturantManagementSystem.repository.ReservationRepository;
import com.JP.ResturantManagementSystem.service.ReservationService;
import com.JP.ResturantManagementSystem.service.ReservationServiceImpl;
import com.JP.ResturantManagementSystem.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("map")
@Configuration
public class MapConfiguration {

    private final ReservationRepository reservationRepository;
    private final IdGenerator idGenerator;

    public MapConfiguration(ReservationRepository reservationRepository, IdGenerator idGenerator) {
        this.reservationRepository = reservationRepository;
        this.idGenerator = idGenerator;
    }

    @Bean
    public ReservationService reservationService() {
        return new ReservationServiceImpl(reservationRepository, idGenerator);
    }
}
