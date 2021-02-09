package com.JP.ResturantManagementSystem.service;

import com.JP.ResturantManagementSystem.entity.H2ReservationEntity;
import com.JP.ResturantManagementSystem.model.Reservation;
import com.JP.ResturantManagementSystem.repository.H2ReservationRespository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class H2ReservationServiceImplTest {

    @Mock
    private H2ReservationRespository h2ReservationRepository;

    @InjectMocks
    private H2ReservationServiceImpl h2ReservationServiceImpl;

    private LocalDateTime now;

    private Reservation expectedReservation1;
    private Reservation expectedReservation2;
    private Reservation expectedReservation3;
    private H2ReservationEntity savedH2ReservationEntity1;
    private H2ReservationEntity savedH2ReservationEntity2;
    private H2ReservationEntity savedH2ReservationEntity3;

    @BeforeEach
    void setup() {
        now = LocalDateTime.now();
        expectedReservation1 = Reservation.builder()
                .id("tesion10000")
                .firstName("test1")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        savedH2ReservationEntity1 = H2ReservationEntity.builder()
                .id("tesion10000")
                .firstName("test1")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        expectedReservation2 = Reservation.builder()
                .id("tesion10001")
                .firstName("test2")
                .lastName("reservation2")
                .reservationTime(now)
                .numberOfGuests(4)
                .build();

        savedH2ReservationEntity2 = H2ReservationEntity.builder()
                .id("tesion10001")
                .firstName("test2")
                .lastName("reservation2")
                .reservationTime(now)
                .numberOfGuests(4)
                .build();

        expectedReservation3 = Reservation.builder()
                .id("tesion10003")
                .firstName("test3")
                .lastName("reservation3")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        savedH2ReservationEntity2 = H2ReservationEntity.builder()
                .id("tesion10003")
                .firstName("test3")
                .lastName("reservation3")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();
    }

    @AfterEach
    void teardown() {

    }

    @Test
    void getReservation() {

        //    given
        String id1 = "tesion10000";
        String id2 = "tesion10001";

        //    when
        when(h2ReservationRepository.getById(id1))
                .thenReturn(savedH2ReservationEntity1);
        when(h2ReservationRepository.getById(id2))
                .thenReturn(savedH2ReservationEntity2);

        //    then
        Reservation actualReservation1 = h2ReservationServiceImpl.getReservation(id1);
        Reservation actualReservation2 = h2ReservationServiceImpl.getReservation(id2);

        assertEquals(expectedReservation1, actualReservation1);
        assertEquals(expectedReservation2, actualReservation2);

    }

    @Test
    void getAllReservations() {
        //    given

        List<H2ReservationEntity> savedReservationEntityList = List.of(savedH2ReservationEntity1,savedH2ReservationEntity2,savedH2ReservationEntity3);

        List<Reservation> expectedReservationList = List.of(expectedReservation1, expectedReservation2,expectedReservation3);

        //    when
        when(h2ReservationRepository.findAll()).thenReturn(savedReservationEntityList);

        List<Reservation> actualReservationList = h2ReservationServiceImpl.getReservations();
        System.out.println(actualReservationList);

        //    then
        assertEquals(actualReservationList, expectedReservationList);
    }

    @Test
    void updateRerservation() {
        //    given
        String id = "tesion10000";

        Reservation updateReservation = Reservation.builder()
                .firstName("update")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        H2ReservationEntity updateEntity = H2ReservationEntity.builder()
                .id("tesion10000")
                .firstName("update")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        H2ReservationEntity savedEntity = H2ReservationEntity.builder()
                .id("tesion10000")
                .firstName("update")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        Reservation expectedReservation = Reservation.builder()
                .id("tesion10000")
                .firstName("update")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        // h2ReservationRepository.save(expectedReservation1)


        //    when
        when(h2ReservationRepository.save(updateEntity)).thenReturn(savedEntity);

        //    then

        Reservation actualReservation = h2ReservationServiceImpl.updateReservation(id, updateReservation);

        assertEquals(actualReservation, expectedReservation);
    }

    @Test
    void deleteReservation() {

        //given
        String id = "tesion10000";

        //when
        h2ReservationServiceImpl.deleteReservation(id);

        //then
        verify(h2ReservationRepository).deleteById(id);
    }
}



