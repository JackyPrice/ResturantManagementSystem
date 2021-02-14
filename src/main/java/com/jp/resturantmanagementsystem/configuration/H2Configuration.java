package com.jp.resturantmanagementsystem.configuration;

import com.jp.resturantmanagementsystem.repository.H2ReservationRespository;
import com.jp.resturantmanagementsystem.service.H2ReservationServiceImpl;
import com.jp.resturantmanagementsystem.service.ReservationService;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("h2")
@Configuration
public class H2Configuration {

    private final H2ReservationRespository h2ReservationRespository;
    private final IdGenerator idGenerator;

    public H2Configuration(H2ReservationRespository h2ReservationRespository, IdGenerator idGenerator) {
        this.h2ReservationRespository = h2ReservationRespository;
        this.idGenerator = idGenerator;
    }

    @Bean
    public ReservationService reservationService() {
        return new H2ReservationServiceImpl(h2ReservationRespository, idGenerator);
    }
}
