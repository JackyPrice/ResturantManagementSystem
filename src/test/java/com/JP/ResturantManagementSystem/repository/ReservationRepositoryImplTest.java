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
    @DisplayName("Given valid id for saved customer, when get endpoint is called, then expected reservation is returned")
    void get() {
        //    given
        ReservationEntity validInput = ReservationEntity.builder()
                .id("tesoof00001")
                .firstName("test")
                .lastName("proof")
                .numberOfGuests(1)
                .reservationTime(LocalDateTime.parse("2021-01-06T17:00:00"))
                .build();

        ReservationEntity expected = reservationRepository.save(validInput);

    //    when
        ReservationEntity actual = reservationRepository.get("tesoof00001");

    //    then
        assertEquals(expected, actual);
    }
}