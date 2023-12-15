package com.bookmanagement.gestionlivres.model;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
public class CompteVisa extends Compte{
    private Date dateExpiration;
    private int code;
}
