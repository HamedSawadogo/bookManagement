package com.bookmanagement.gestionlivres.Dtos;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class ReservationDto {
    private Integer id;

    private Date dateRetourLivre;
    private Date reservationDate;

    private  BookDto book;

    private int nbLivre;
    private  int nbJours;

    //private UserDto userDto;
}
