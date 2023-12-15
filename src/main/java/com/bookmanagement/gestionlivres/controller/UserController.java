package com.bookmanagement.gestionlivres.controller;

import com.bookmanagement.gestionlivres.Dtos.ReservationDto;
import com.bookmanagement.gestionlivres.Dtos.UserDto;
import com.bookmanagement.gestionlivres.exceptions.InvalidBookException;
import com.bookmanagement.gestionlivres.exceptions.ReservationNotFoundException;
import com.bookmanagement.gestionlivres.exceptions.UserNotFoundException;
import com.bookmanagement.gestionlivres.model.Reservation;
import com.bookmanagement.gestionlivres.repositories.ReservationDao;
import com.bookmanagement.gestionlivres.service.ReservationService;
import com.bookmanagement.gestionlivres.service.ReservationServiceImpl;
import com.bookmanagement.gestionlivres.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserServiceImpl userService;
    private final ReservationServiceImpl reservationService;

    public  UserController(UserServiceImpl userService,ReservationServiceImpl reservationService){
        this.userService=userService;
        this.reservationService=reservationService;
    }

    @PostMapping("/reservation/{userId}/{compteId}/{bookId}")
    public ResponseEntity<?> addUserReservation(@PathVariable("userId") String userId,@PathVariable("compteId")String compteId,@PathVariable("bookId") Long bookId,@RequestBody ReservationDto reservation){
        try {
            UserDto reservationSaved=this.userService.addReservation(reservation,compteId,bookId,userId);
            return  ResponseEntity.status(HttpStatus.OK).body(reservationSaved);
        }catch (Exception | ReservationNotFoundException | InvalidBookException | UserNotFoundException e){
            //Une erreur est survenu ,impossible d'enregistrer cette reservation
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @RequestMapping(value = "user/{userId}/reservations",method = RequestMethod.GET)
    public ResponseEntity<List<ReservationDto>> findUsersReservations(@PathVariable("userId")String userId){
           return  ResponseEntity.status(HttpStatus.OK).body(this.reservationService.listReservationsByUser(userId));
    }
    @GetMapping("/user/{id}")
    public ResponseEntity<?> findUserById(@PathVariable("id")String userId){
        try {
            UserDto  optionalUser = this.userService.findUserById(userId);
            return  ResponseEntity.status(HttpStatus.OK).body(optionalUser);
        } catch (UserNotFoundException e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PostMapping("/user")
    public  ResponseEntity<?> addUser(@RequestBody UserDto user){
        try {
            return ResponseEntity.ok().body(this.userService.addUser(user));
        } catch (UserNotFoundException | Exception e) {
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @GetMapping("/users")
    public ResponseEntity<?> findAllUsers(){
      try {
          return ResponseEntity.ok().body(this.userService.getAllUsers());
      }catch (Exception e){
          return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
      }
    }




}
