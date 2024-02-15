package com.bankmanageemnt.banktp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",length = 10)
public abstract class Compte {

    @Id
    private String numCompte;
    private Double solde;
    private Date creationDate;


    @ManyToOne(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Client client;

    @OneToMany(fetch = FetchType.LAZY,
    cascade = CascadeType.ALL)
    private List<Operation>operations;

    @Override
    public String toString() {
        return "Compte{" +
                "numCompte='" + numCompte + '\'' +
                ", solde=" + solde +
                ", creationDate=" + creationDate +
                ", client=" + client +
                ", operations=" + operations +
                '}';
    }
}
