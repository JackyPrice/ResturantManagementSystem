package com.JP.ResturantManagementSystem.configuration;

import com.JP.ResturantManagementSystem.repository.H2ReservationRespository;
import com.JP.ResturantManagementSystem.repository.ReservationRepository;
import com.JP.ResturantManagementSystem.service.H2ReservationServiceImpl;
import com.JP.ResturantManagementSystem.service.ReservationService;
import com.JP.ResturantManagementSystem.service.ReservationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final H2ReservationRespository h2ReservationRespository;
    private final ReservationRepository reservationRepository;

    public ServiceConfiguration(H2ReservationRespository h2ReservationRespository, ReservationRepository reservationRepository) {
        this.h2ReservationRespository = h2ReservationRespository;
        this.reservationRepository = reservationRepository;
    }

    @Bean
    public ReservationService h2ReservationService() {
        return new H2ReservationServiceImpl(h2ReservationRespository);
    }
    @Bean
    public ReservationService reservationService() {
        return new ReservationServiceImpl(reservationRepository);
    }
}
