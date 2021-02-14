package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.repository.ReservationRepositoryImpl;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRepositoryImpl reservationRepository;

    @Mock
    private IdGenerator idGenerator;

    @InjectMocks
    private ReservationServiceImpl reservationServiceImpl;

    private LocalDateTime now;
    private Reservation expectedReservation1;
    private Reservation expectedReservation2;
    private Reservation expectedReservation3;
    private ReservationEntity savedReservationEntity1;
    private ReservationEntity savedReservationEntity2;
    private ReservationEntity savedReservationEntity3;

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

        savedReservationEntity1 = ReservationEntity.builder()
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

        savedReservationEntity2 = ReservationEntity.builder()
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

        savedReservationEntity3 = ReservationEntity.builder()
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
    @DisplayName("given a reservation without without an Id, when reservation is saved then an id is created")
    void createReservation() {
        //given
        Reservation inputReservation1 = Reservation.builder()
                .firstName("test1")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        when(idGenerator.createId(inputReservation1.getFirstName(), inputReservation1.getLastName())).thenReturn("tesion10000");
        when(reservationRepository.save(savedReservationEntity1)).thenReturn(savedReservationEntity1);

        Reservation actualReservation = reservationServiceImpl.createReservation(inputReservation1);

        //then
        assertEquals(expectedReservation1, actualReservation);
    }

    @Test
    @DisplayName("given valid id, when get is called with id, then the correct reservation is returned")
    void getReservation() {

        //    given
        String id1 = "tesion10000";
        String id2 = "tesion10001";
        //TODO: change to parameterisd query

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
    @DisplayName("when getReservations is called, then a list of all reservations are returned")
    void getAllReservations() {
        //    given
        List<Reservation> expectedReservationList = List.of(expectedReservation1, expectedReservation2, expectedReservation3);

        //    when
        when(reservationRepository.getAll()).thenReturn(expectedReservationList);

        List<Reservation> actualReservationList = reservationServiceImpl.getReservations();

        //    then
        assertEquals(actualReservationList, expectedReservationList);
    }

    @Test
    @DisplayName("given a valid update reservation, when update is called, then reservation of the same id is replaced with update reservation")
    void updateRerservation() {
        //    given
        String id = "tesion10000";
        Reservation updateReservation = Reservation.builder()
                .firstName("update")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        ReservationEntity updateEntity = ReservationEntity.builder()
                .id("tesion10000")
                .firstName("update")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        Reservation expectedUpdateEntity = Reservation.builder()
                .id("tesion10000")
                .firstName("update")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        //    when
        when(reservationRepository.save(updateEntity)).thenReturn(updateEntity);

        //    then

        Reservation actualReservation = reservationServiceImpl.updateReservation(id, updateReservation);

        assertEquals(expectedUpdateEntity, actualReservation);
    }

    @Test
    @DisplayName("given a valid id, when delete is called, the repository is called to delete the entity")
    void deleteReservation() {

        //given
        String id = "tesion10000";

        //when
        reservationServiceImpl.deleteReservation(id);

        //then
        verify(reservationRepository).deleteById(id);
    }
}