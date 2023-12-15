package com.bookmanagement.gestionlivres.repositories;

import com.bookmanagement.gestionlivres.model.Reservation;
import com.bookmanagement.gestionlivres.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends JpaRepository<User,String> {
   // List<Reservation>findUserByReservations(String id);
}
