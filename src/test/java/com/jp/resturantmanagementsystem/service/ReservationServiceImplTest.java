package com.jp.resturantmanagementsystem.service;

import com.jp.resturantmanagementsystem.entity.ReservationEntity;
import com.jp.resturantmanagementsystem.exception.InvalidReservationException;
import com.jp.resturantmanagementsystem.exception.InvalidReservationIdException;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.repository.ReservationRespository;
import com.jp.resturantmanagementsystem.util.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {

    @Mock
    private ReservationRespository reservationRepository;
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

    @Test
    void checkComponents(){
        assertThat(reservationRepository).isNotNull();
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

        //when
        try (MockedStatic<IdGenerator> mockRandom = mockStatic(IdGenerator.class)) {
            mockRandom.when(() -> idGenerator.createId(expectedReservation1.getFirstName(), expectedReservation1.getLastName())).thenReturn("tesion10000");
        }

        when(reservationRepository.save(savedReservationEntity1)).thenReturn(savedReservationEntity1);

        Reservation actualReservation = reservationServiceImpl.createReservation(inputReservation1);

        //then
        assertEquals(expectedReservation1, actualReservation);
    }

    @Test
    @DisplayName("given a reservation which contains an Id, when reservation is attempted to be created, an exception is thrown")
    void createReservationWhichContainsId() {
        //given
        Reservation inputReservation1 = Reservation.builder()
                .id("12345678901")
                .firstName("test1")
                .lastName("reservation")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();

        //when
        reservationServiceImpl.createReservation(inputReservation1);

        //then
        assertThrows(InvalidReservationException.class, () ->reservationServiceImpl.createReservation(inputReservation1));
    }

    @Test
    @DisplayName("given valid id, when get is called with id, then the correct reservation is returned")
    void getReservation() {

        //    given
        String id1 = "tesion10000";
        String id2 = "tesion10001";

        //    when
        when(reservationRepository.getById(id1)).thenReturn(savedReservationEntity1);
        when(reservationRepository.getById(id2)).thenReturn(savedReservationEntity2);

        //    then
        Reservation actualReservation1 = reservationServiceImpl.getReservation(id1);
        Reservation actualReservation2 = reservationServiceImpl.getReservation(id2);

        assertEquals(expectedReservation1, actualReservation1);
        assertEquals(expectedReservation2, actualReservation2);

    }

    @Test
    @DisplayName("given id of length not equal to 11, when get by id called, then an InvalidReservationIdException is called with the expected message")
    void getReservationWithInvalidLength(){
    //    given
        String invalidLengthId = "invalidLengthId";
    //    whenThen
        assertThrows(InvalidReservationIdException.class, () -> reservationServiceImpl.getReservation(invalidLengthId));
    }

    @Test
    @DisplayName("given id containing a special character, when get by id called, then an InvalidReservationIdException is called with the expected message")
    void getReservationWithSpecialCharacters(){
    //    given
        String idWithSpecialCharacters = "1234567890@";
    //    whenThen
        assertThrows(InvalidReservationIdException.class, () -> reservationServiceImpl.getReservation(idWithSpecialCharacters));
    }

    @Test
    @DisplayName("when getReservations is called, then a list of all reservations are returned")
    void getAllReservations() {
        //    given
        List<ReservationEntity> savedReservationEntityList = List.of(savedReservationEntity1,savedReservationEntity2,savedReservationEntity3);
        List<Reservation> expectedReservationList = List.of(expectedReservation1, expectedReservation2,expectedReservation3);

        //    when
        when(reservationRepository.findAll()).thenReturn(savedReservationEntityList);

        List<Reservation> actualReservationList = reservationServiceImpl.getReservations();
        System.out.println(actualReservationList);

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

        ReservationEntity savedEntity = ReservationEntity.builder()
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

        // reservationRepository.save(expectedReservation1)


        //    when
        when(reservationRepository.save(updateEntity)).thenReturn(savedEntity);

        //    then

        Reservation actualReservation = reservationServiceImpl.updateReservation(id, updateReservation);

        assertEquals(actualReservation, expectedReservation);
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



