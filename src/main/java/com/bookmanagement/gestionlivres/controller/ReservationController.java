package com.bookmanagement.gestionlivres.controller;

import com.bookmanagement.gestionlivres.Dtos.ReservationDto;
import com.bookmanagement.gestionlivres.exceptions.ReservationNotFoundException;
import com.bookmanagement.gestionlivres.model.Reservation;
import com.bookmanagement.gestionlivres.service.ReservationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
public class ReservationController {

    private final ReservationServiceImpl reservationService;

    public  ReservationController(ReservationServiceImpl reservationService){
        this.reservationService=reservationService;
    }

    @PostMapping("/reservation/{bookId}")
    public  ResponseEntity<ReservationDto> addReservation(@RequestBody ReservationDto reservation, @PathVariable("bookId")Long id){
        try {
            return  ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.reservationService.addReservation(reservation,id));
        } catch (ReservationNotFoundException e) {
            return  ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/reservation/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable("id")Integer id){
        try {
            ReservationDto  reservation = this.reservationService.findReservationById(id);
            return  ResponseEntity.ok().body(reservation);
        } catch (ReservationNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/reservations")
    public ResponseEntity<Iterable<ReservationDto>> getReservations(){
        try{
            return  ResponseEntity.ok().body(this.reservationService.getAllReservations());
        }catch (Exception e){
            return  ResponseEntity.notFound().build();
        }
    }

}
