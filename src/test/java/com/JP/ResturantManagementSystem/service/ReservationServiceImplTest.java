package com.JP.ResturantManagementSystem.service;

import com.JP.ResturantManagementSystem.entity.ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.repository.ReservationRepositoryImpl;
import com.JP.ResturantManagementSystem.util.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepositoryImpl reservationRepository;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    private LocalDateTime now;

    @BeforeEach
    void setup() {
        now = LocalDateTime.now();
        ReservationEntity testData1 = ReservationEntity.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        ReservationEntity testData2 = ReservationEntity.builder()
                .id("tesion10001")
                .firstName("test2")
                .lastName("reservation2")
                .reservationTime(now)
                .numberOfGuests(4)
                .build();

        reservationRepository.save(testData1);
        reservationRepository.save(testData2);
    }

    @Test
    void createReservation() {
        //given
        LocalDateTime now = LocalDateTime.now();

        Reservation inputReservation = Reservation.builder()
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        ReservationEntity inputReservationEntity = ReservationEntity.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        ReservationEntity expectedReservationEntity = ReservationEntity.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        Reservation expectedReservation = Reservation.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        //when

        try (MockedStatic<IdGenerator> mockRandom = mockStatic(IdGenerator.class)) {

            mockRandom.when(() -> IdGenerator.createId("this", "that")).thenReturn("tesion10000");

        }

        when(reservationRepository.save(inputReservationEntity)).thenReturn(expectedReservationEntity);

        Reservation actualReservation = reservationServiceImpl.createReservation(inputReservation);

        //then
        assertEquals(expectedReservation, actualReservation);
    }

    @Test
    void getReservation() {

        //    given
        String id1 = "tesion10000";
        String id2 = "tesion10001";

        Reservation expectedReservation1 = Reservation.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        ReservationEntity savedReservationEntity1 = ReservationEntity.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        Reservation expectedReservation2 = Reservation.builder()
                .id("tesion10001")
                .firstName("test2")
                .lastName("reservation2")
                .reservationTime(now)
                .numberOfGuests(4)
                .build();

        ReservationEntity savedReservationEntity2 = ReservationEntity.builder()
                .id("tesion10001")
                .firstName("test2")
                .lastName("reservation2")
                .reservationTime(now)
                .numberOfGuests(4)
                .build();

        //    when
        when(reservationRepository.get(id1)).thenReturn(savedReservationEntity1);
        when(reservationRepository.get(id2)).thenReturn(savedReservationEntity2);

        //    then
        Reservation actualReservation1 = reservationServiceImpl.getReservation(id1);
        Reservation actualReservation2 = reservationServiceImpl.getReservation(id2);

        assertEquals(expectedReservation1, actualReservation1);
        assertEquals(expectedReservation2, actualReservation2);

    }

    @Test
    void updateRerservation(){
    //    given
        String id = "tesion10000";
        Reservation updateReservation = Reservation.builder()
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        ReservationEntity updateEntity = ReservationEntity.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        ReservationEntity savedEntity = ReservationEntity.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        Reservation expectedReservation = Reservation.builder()
                .id("tesion10000")
                .firstName("test")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

    //    when
        when(reservationRepository.save(updateEntity)).thenReturn(savedEntity);

    //    then

        Reservation actualReservation = reservationServiceImpl.updateReservation(id, updateReservation);

        assertEquals(actualReservation, expectedReservation);
    }
}