package com.bookmanagement.gestionlivres.Dtos;

import com.bookmanagement.gestionlivres.enums.UserRole;
import com.bookmanagement.gestionlivres.model.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
public class UserDto {
    private String id;

    private String role;

    private String username;
    private String password;

    private String email;

    private String phone;

    //private List<ReservationDto> reservations;
}
