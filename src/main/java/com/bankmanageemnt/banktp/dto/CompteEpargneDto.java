package com.bankmanageemnt.banktp.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@Data
@NoArgsConstructor @AllArgsConstructor
public class CompteEpargneDto extends CompteDto{
    private  Double taux;
}
