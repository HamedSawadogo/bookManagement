package com.bankmanageemnt.banktp.model;
import com.bankmanageemnt.banktp.enums.OperationType;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "La date de l'opération est invalide !! ")
    private Date operationDate;

    @Min(value = 0,message = "le montant doit etre positif !!")
    private Double montant;

    //Represente le Type de l'Opération (soit un Rétrait ou un Dépot)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "le type de l'opération est recquis")
    private OperationType operationType;


    //le compte sur lequel l'opération a été effectué
    @ManyToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @NotEmpty(message = "Une opération doit doit toujours etre associé a un Compte")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Compte compte;
}
