package com.JP.ResturantManagementSystem.configuration;

import com.JP.ResturantManagementSystem.repository.H2ReservationRespository;
import com.JP.ResturantManagementSystem.service.H2ReservationServiceImpl;
import com.JP.ResturantManagementSystem.service.ReservationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("h2")
@Configuration
public class H2Configuration {

    private final H2ReservationRespository h2ReservationRespository;

    public H2Configuration(H2ReservationRespository h2ReservationRespository) {
        this.h2ReservationRespository = h2ReservationRespository;
    }

    @Bean
    public ReservationService reservationService() {
        return new H2ReservationServiceImpl(h2ReservationRespository);
    }
}
