package com.bankmanageemnt.banktp.model;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor @AllArgsConstructor
@Entity
@DiscriminatorValue("COURANT")
public class CompteCourant extends Compte{
    private Double decouvert;

    @Override
    public String toString() {
        return super.toString()+"  decouvert=" + decouvert;
    }
}
