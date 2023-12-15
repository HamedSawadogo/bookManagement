package com.bookmanagement.gestionlivres.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table
@NoArgsConstructor @AllArgsConstructor
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private  String bookImg;

    @Column(unique = true)
    private String name;

    private boolean disponible=true;

    private int quantite;

    private String description;
    private Date date_creation;
    private Double price;

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", bookImg='" + bookImg + '\'' +
                ", name='" + name + '\'' +
                "quantite: "+quantite+'\n'+
                ", description='" + description + '\'' +
                ", date_creation=" + date_creation +
                ", price=" + price +
                '}';
    }
}
