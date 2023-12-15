package com.bookmanagement.gestionlivres.exceptions;

public class ReservationNotFoundException extends Throwable {
    public ReservationNotFoundException(String cetteReservationEstInvalide) {
        super(cetteReservationEstInvalide);
    }
}
