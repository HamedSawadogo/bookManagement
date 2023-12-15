package com.bookmanagement.gestionlivres.repositories;

import com.bookmanagement.gestionlivres.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationDao extends JpaRepository<Reservation,Integer> {
    Iterable<Reservation>findAllById(Integer id);

    @Query("select r from Reservation  r where r.user.id=?1")
    List<Reservation>getAllReservationsByUser(String userId);
}
