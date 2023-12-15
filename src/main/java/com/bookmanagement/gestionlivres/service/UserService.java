package com.bookmanagement.gestionlivres.service;

import com.bookmanagement.gestionlivres.Dtos.ReservationDto;
import com.bookmanagement.gestionlivres.Dtos.UserDto;
import com.bookmanagement.gestionlivres.exceptions.*;
import com.bookmanagement.gestionlivres.model.Book;
import com.bookmanagement.gestionlivres.model.Reservation;
import com.bookmanagement.gestionlivres.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService extends DtoInterface<User, UserDto>{
    /**
     * Ajouter un Utilisateur de la base de donnée
     * @param user
     * @return
     */
    UserDto addUser(UserDto user) throws UserNotFoundException;

    /**
     * Supprimer un utilisateur de la base de donnée
     * @param userId
     */
    void deleteUser(String userId);

    /**
     * Modifier Un utilisateur de la base de donnée
     * @param user
     * @return
     */
    UserDto updateUser(UserDto user,String userId);
    /**
     * Renvoyer la liste des utilisateurs de la base de donnée
     * @return
     */
    Iterable<UserDto>getAllUsers();

    /**
     * Rechercher un utilisateur par son Id
     * @param userid
     * @return
     */
    UserDto  findUserById(String userid) throws UserNotFoundException;


    /**
     * Ajouter une Reservation a un Utilisateur
     * @param reservation
     * @param userid
     * @return
     */
    UserDto addReservation(ReservationDto reservation,String compteId, Long bookId, String userid) throws ReservationNotFoundException, InvalidBookException, UserNotFoundException,MontantInsufisantException;

}
