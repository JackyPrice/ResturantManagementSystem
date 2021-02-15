package com.jp.resturantmanagementsystem.repository;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.model.Reservation;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ReservationRepositoryImplTest {

    private ReservationRepositoryImpl reservationRepositoryImpl;

    private LocalDateTime now;
    private Reservation expectedReservation1;
    private Reservation expectedReservation2;
    private Reservation expectedReservation3;
    private ReservationEntity savedReservationEntity1;
    private ReservationEntity savedReservationEntity2;
    private ReservationEntity savedReservationEntity3;

    @BeforeEach
    void setup() {
        reservationRepositoryImpl = new ReservationRepositoryImpl();
        now = LocalDateTime.now();

        expectedReservation1 = Reservation.builder()
                .id("tesion10001")
                .firstName("test1")
                .lastName("reservation1")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        savedReservationEntity1 = ReservationEntity.builder()
                .id("tesion10001")
                .firstName("test1")
                .lastName("reservation1")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        expectedReservation2 = Reservation.builder()
                .id("tesion10002")
                .firstName("test2")
                .lastName("reservation2")
                .reservationTime(now)
                .numberOfGuests(4)
                .build();

        savedReservationEntity2 = ReservationEntity.builder()
                .id("tesion10002")
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

    @Test
    void checkComponents(){
        assertThat(reservationRepositoryImpl).isNotNull();
    }

    @Test
    @DisplayName("Given a complete reservation, when post endpoint is called, then reservation is saved to the hashmap")
    void save() {


        //    when
        ReservationEntity actual = reservationRepositoryImpl.save(savedReservationEntity1);

        //    then
        assertEquals(savedReservationEntity1, actual);
    }

    @Test
    @DisplayName("Given valid id, when get is called, then expected reservation entity is returned")
    void get() {
        //    given
        String id = savedReservationEntity1.getId();

        ReservationEntity expected = reservationRepositoryImpl.save(savedReservationEntity1);

    //    when
        ReservationEntity actual = reservationRepositoryImpl.get(id);

    //    then
        assertEquals(expected, actual);
    } //TODO parameterised test

    @Test
    @DisplayName("when getAll is called, then a list of all reservations is returned")
    void getAll() {
        //    given

        List<Reservation> expectedReservationList = List.of(expectedReservation1,expectedReservation2,expectedReservation3);
        //    when

        createSavedData();
        List<Reservation> actualReservationList = reservationRepositoryImpl.getAll();

        //    then
        assertEquals(expectedReservationList, actualReservationList);
    }

    @Test
    @DisplayName("given a valid id, when delete is called, the entity is deleted")
    void deleteById() {

        //given
        String id = savedReservationEntity1.getId();

        //when
        reservationRepositoryImpl.save(savedReservationEntity1);
        ReservationEntity savedEntity = reservationRepositoryImpl.get(id);

        //then
        assertNotNull(savedEntity);
        assertEquals(1, reservationRepositoryImpl.getAll().size());

        //when
        reservationRepositoryImpl.deleteById(id);
        savedEntity = reservationRepositoryImpl.get(id);
        //then
        assertNull(savedEntity);
        assertEquals(0, reservationRepositoryImpl.getAll().size());
    }


    private void createSavedData() {
        reservationRepositoryImpl.save(savedReservationEntity1);
        reservationRepositoryImpl.save(savedReservationEntity2);
        reservationRepositoryImpl.save(savedReservationEntity3);
    }
}