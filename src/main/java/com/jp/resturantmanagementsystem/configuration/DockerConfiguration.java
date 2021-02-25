package com.jp.resturantmanagementsystem.configuration;

import com.jp.resturantmanagementsystem.repository.ReservationRespository;
import com.jp.resturantmanagementsystem.service.ReservationServiceImpl;
import com.jp.resturantmanagementsystem.service.ReservationService;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerConfiguration {

    private final ReservationRespository reservationRespository;
    private final IdGenerator idGenerator;

    public DockerConfiguration(ReservationRespository reservationRespository, IdGenerator idGenerator) {
        this.reservationRespository = reservationRespository;
        this.idGenerator = idGenerator;
    }

    @Bean
    public ReservationService reservationService() {
        return new ReservationServiceImpl(reservationRespository, idGenerator);
    }
}
