package com.bankmanageemnt.banktp.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;


@Data @ToString
@NoArgsConstructor @AllArgsConstructor
@Table
@Entity
@Builder
public class Client {
    @Id
    private String code;
    @NotBlank(message = "Le nom du Client est invalide!! ")
    private String name;


    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL,
    mappedBy = "client")
    private List<Compte>comptes;
}
