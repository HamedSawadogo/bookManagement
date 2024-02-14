package com.bankmanageemnt.banktp.model;
import com.bankmanageemnt.banktp.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Date;

@ToString
@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Builder
public class Operation {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numOperation;
    private Date operationDate;
    private Double montant;

    //Represente le Type de l'Opération (soit un Rétrait ou un Dépot)
    @Enumerated(EnumType.STRING)
    private OperationType operationType;


    //le compte sur lequel l'opération a été effectué
    @ManyToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Compte compte;
}
