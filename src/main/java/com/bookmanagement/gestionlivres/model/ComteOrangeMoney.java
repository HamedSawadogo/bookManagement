package com.bookmanagement.gestionlivres.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor @AllArgsConstructor
public class ComteOrangeMoney extends  Compte{

    @Column(nullable = false,length = 11)
    private String comptePhone;


}
