package com.bookmanagement.gestionlivres.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TypeCompte")
public abstract class Compte {
    @Id
    private String id;
    private Double montant;
    private Date dateCreation;


    @ManyToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL
    )
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private User user;
}
