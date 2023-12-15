package com.bookmanagement.gestionlivres.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor @AllArgsConstructor
public class BookDto {
    private Long id;

    private  String bookImg;

    private String name;

    private boolean disponible;

    private int quantite;

    private String description;

    private Double price;
}
