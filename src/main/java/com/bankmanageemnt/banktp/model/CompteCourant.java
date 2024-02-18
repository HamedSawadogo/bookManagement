package com.bankmanageemnt.banktp.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

@Data
@AllArgsConstructor @NoArgsConstructor
@Entity
@Builder
@DiscriminatorValue("COURANT")
public class CompteCourant extends Compte{
    private Double decouvert;

    @Override
    public String toString() {
        return super.toString()+"  decouvert=" + decouvert;
    }
}
