package com.jp.resturantmanagementsystem.configuration;

import com.jp.resturantmanagementsystem.repository.DockerReservationRespository;
import com.jp.resturantmanagementsystem.service.DockerReservationServiceImpl;
import com.jp.resturantmanagementsystem.service.ReservationService;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("docker")
@Configuration
public class DockerConfiguration {

    private final DockerReservationRespository dockerReservationRespository;
    private final IdGenerator idGenerator;

    public DockerConfiguration(DockerReservationRespository dockerReservationRespository, IdGenerator idGenerator) {
        this.dockerReservationRespository = dockerReservationRespository;
        this.idGenerator = idGenerator;
    }

    @Bean
    public ReservationService reservationService() {
        return new DockerReservationServiceImpl(dockerReservationRespository, idGenerator);
    }
}
