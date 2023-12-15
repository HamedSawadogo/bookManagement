package com.bookmanagement.gestionlivres.service;

import com.bookmanagement.gestionlivres.Dtos.ReservationDto;
import com.bookmanagement.gestionlivres.exceptions.ReservationNotFoundException;
import com.bookmanagement.gestionlivres.model.Reservation;

import java.util.Optional;

public interface ReservationService {

    /**
     * Ajouter une Reservation dans la base de donnée
     * @param reservation
     * @return
     */

    ReservationDto addReservation(ReservationDto reservation, Long bookId) throws ReservationNotFoundException;

    /**
     * Supprimer une reservation de la base de donnée
     * @param reservationId
     */
    void deleteReservation(Integer reservationId);

    /**
     * Renvoie la liste des reservations de la base de donnée
     * @return
     */
    Iterable<ReservationDto>getAllReservations();


    /**
     * Rechercher Une Reservation  par   id
     * @param id
     * @return
     */
    ReservationDto findReservationById(Integer id) throws ReservationNotFoundException;


}
