package com.jp.resturantmanagementsystem.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.jp.resturantmanagementsystem.model.Reservation;
import com.jp.resturantmanagementsystem.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class ReservationControllerTest {

    @Mock
    private ReservationService reservationService;

    @InjectMocks
    private ReservationController reservationController;

    private MockMvc mockMvc;
    private LocalDateTime now;
    private Reservation expectedReservation1;
    private Reservation expectedReservation2;
    private Reservation expectedReservation3;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(reservationController).build();
        now = LocalDateTime.now();

        expectedReservation1 = Reservation.builder()
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

        expectedReservation3 = Reservation.builder()
                .id("tesion10003")
                .firstName("test3")
                .lastName("reservation3")
                .reservationTime(now)
                .numberOfGuests(2)
                .build();
    }

    @Test
    void checkComponents() {
        assertNotNull(reservationService);
        assertNotNull(reservationController);
    }


    @Test
    void createReservation() throws Exception {
        //given

        //when
        when(reservationService.createReservation(expectedReservation1)).thenReturn(expectedReservation1);

        //then
        mockMvc.perform(MockMvcRequestBuilders.post("/reservation")
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedReservation1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedReservation1)));
    }

    @Test
    void getReservation() throws Exception {
        //given
        String id = "tesion10001";

        //when
        when(reservationService.getReservation(id)).thenReturn(expectedReservation1);

        //then
        mockMvc.perform(get("/reservation").param("id", "tesion10001")
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(asJsonString(expectedReservation1)));
    }


    @Test
    void getAllReservations() throws Exception {
        //    given
        List<Reservation> expectedReservationList = List.of(expectedReservation1, expectedReservation2, expectedReservation3);

        //    when
        when(reservationService.getReservations()).thenReturn(expectedReservationList);

        //    then
        mockMvc.perform(get("/reservations"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().json(asJsonString(expectedReservationList)));

    }

    @Test
    void updateReservation() throws Exception {
        String id = expectedReservation1.getId();

        //    when
        when(reservationService.updateReservation(id,expectedReservation1)).thenReturn(expectedReservation1);

        //    then
        mockMvc.perform(put("/reservation/" + id)
                .accept(MediaType.APPLICATION_JSON)
                .content(asJsonString(expectedReservation1))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(asJsonString(expectedReservation1)));

    }

    @Test
    void deleteReservation() throws Exception {

        //given
        String id = expectedReservation1.getId();

        //when
        mockMvc.perform(delete("/reservation/" + id))
                .andExpect(status().isOk());

        //then
        verify(reservationService).deleteReservation(id);
    }

    private String asJsonString(Reservation reservation) {
        return "{" +
                "\"id\":\"" + reservation.getId() + "\"," +
                "\"firstName\":\"" + reservation.getFirstName() + "\"," +
                "\"lastName\":\"" + reservation.getLastName() + "\"," +
                "\"reservationTime\":[" + getReservationTimeAsString(reservation.getReservationTime()) + "]," +
                "\"numberOfGuests\":" + reservation.getNumberOfGuests()
                + "}";
    }

    private String asJsonString(List<Reservation> reservationList) {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < reservationList.size(); i++) {
            if (i == reservationList.size() - 1) {
                output.append(asJsonString(reservationList.get(i)));
            } else {
                output.append(asJsonString(reservationList.get(i))).append(",");
            }
        }
        output.append("]");
        return output.toString();
    }

    private String getReservationTimeAsString(LocalDateTime reservationTime) {
        return reservationTime.getYear() + ","
                + reservationTime.getMonthValue() + ","
                + reservationTime.getDayOfMonth() + ","
                + reservationTime.getHour() + ","
                + reservationTime.getMinute() + ","
                + reservationTime.getSecond() + ","
                + reservationTime.getNano();
    }
}