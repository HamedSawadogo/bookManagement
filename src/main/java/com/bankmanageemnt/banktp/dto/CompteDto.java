package com.bankmanageemnt.banktp.dto;

import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.model.Operation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class CompteDto{
    private String numCompte;
    private Double solde;
    private Date creationDate;
}
