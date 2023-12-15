package com.bookmanagement.gestionlivres.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Table
@Entity
@NoArgsConstructor @AllArgsConstructor
public class Reservation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date dateRetourLivre;
    private Date reservationDate;


    @ManyToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Book book;

    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;

    private int nbLivre;

    private  int nbJours;

    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", dateRetourLivre=" + dateRetourLivre +
                ", reservationDate=" + reservationDate +
                ", book=" + book +
                ", nbLivre=" + nbLivre +
                ", nbJours=" + nbJours +
                '}';
    }
}
