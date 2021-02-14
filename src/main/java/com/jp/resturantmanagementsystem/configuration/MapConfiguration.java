package com.jp.resturantmanagementsystem.configuration;

import com.jp.resturantmanagementsystem.repository.ReservationRepository;
import com.jp.resturantmanagementsystem.service.ReservationService;
import com.jp.resturantmanagementsystem.service.ReservationServiceImpl;
import com.jp.resturantmanagementsystem.util.IdGenerator;
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
