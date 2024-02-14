package com.bankmanageemnt.banktp.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@ToString
@Data @NoArgsConstructor @AllArgsConstructor
@Entity
@DiscriminatorValue("EPARGNE")
public class CompteEpargne extends Compte {
    private Double taux;

    @Override
    public String toString() {
        return  super.toString()+"  taux=" + taux;
    }
}
