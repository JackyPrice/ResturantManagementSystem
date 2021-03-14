package com.jp.resturantmanagementsystem.exception;

public class InvalidReservationIdException extends RuntimeException {
    public InvalidReservationIdException(String message) {
        super(message);
    }
}
