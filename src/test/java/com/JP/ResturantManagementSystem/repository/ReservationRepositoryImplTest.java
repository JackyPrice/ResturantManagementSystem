package com.JP.ResturantManagementSystem.repository;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ReservationRepositoryImplTest {

    @BeforeEach
    void setUp() {
    }

    @Autowired
    private ReservationRepositoryImpl reservationRepository;


    @Test
    @DisplayName("Given a complete reservation, when post endpoint is called, then reservation is saved to the hashmap")
    void save() {
        //    given
        ReservationEntity input = ReservationEntity.builder()
                .id("tesoof00001")
                .firstName("test")
                .lastName("proof")
                .numberOfGuests(1)
                .reservationTime(LocalDateTime.parse("2021-01-06T17:00:00"))
                .build();

        ReservationEntity expected = ReservationEntity.builder()
                .id("tesoof00001")
                .firstName("test")
                .lastName("proof")
                .numberOfGuests(1)
                .reservationTime(LocalDateTime.parse("2021-01-06T17:00:00"))
                .build();

        //    when
        ReservationEntity actual = reservationRepository.save(input);

        //    then
        assertEquals(expected, actual);
    }

    @Test
    void get() {
    }
}